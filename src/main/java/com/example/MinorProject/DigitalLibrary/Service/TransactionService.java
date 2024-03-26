package com.example.MinorProject.DigitalLibrary.Service;

import com.example.MinorProject.DigitalLibrary.DTO.SearchBookRequest;
import com.example.MinorProject.DigitalLibrary.Model.*;
import com.example.MinorProject.DigitalLibrary.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    BookService bookService;

    @Autowired
    StudentService studentService;

    @Value("${student.issue.number_of_days}")
    private int maxBookAllowedForIssuance;

    @Value("${student.issue.max_books}")
    private int noOfDaysAllowedAfterIssuance;

    @Autowired
    TransactionRepository transactionRepository;

    public String issueTxn(String bookName, int studentId) throws Exception {
        List<Book> bookList;

        try {
            // builder always invoke the parameterised constructor
            // while request body like from controller always invoke default constructor
            //that why I am getting an error book not found
            bookList = bookService.bookSearch(
                    SearchBookRequest.builder()
                            .searchKey("name")
                            .searchValue(bookName)
                            .operator("=")
                            .available(true)
                            .build()

            );
        }
        catch (Exception e){
            throw new Exception("Book Not Found");
        }
        Student student = studentService.getAStudent(studentId);

        //validation
        if(student.getBookList()!= null && student.getBookList().size() >= maxBookAllowedForIssuance){
            throw  new Exception("Book Limit Reached");
        }

        if(bookList.isEmpty()){
            throw new Exception("Not able to find any book in the library");
        }
        Book book = bookList.get(0);
        Transaction transaction = Transaction.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .student(student)
                .book(book)
                .transactionStatus(TransactionStatus.PENDING)
                .build();

        transaction = transactionRepository.save(transaction);
        String externalTxnId;
        try {
            book.setStudent(student);
            bookService.assignBookToStudent(book, student);

            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            transaction.setTransactionStatus(TransactionStatus.FAILED);
        }finally {
            externalTxnId = transactionRepository.save(transaction).getExternalTxnId();
        }
        return externalTxnId;
    }

    public String returnTxn(int Id,int studentId) throws Exception {
        Book book;
        try{
            book = bookService.bookSearch(
                    SearchBookRequest.builder()
                            .searchKey("id")
                            .searchValue(String.valueOf(Id))
                            .build()
            ).get(0);
        } catch (Exception e) {
            throw new Exception("not able to fetch book details");
        }
        Student student = studentService.getAStudent(studentId);

        if(book.getStudent() == null && book.getStudent().getId() != studentId){
            throw new Exception("Book is not assigned to this student");
        }
        Transaction transaction = Transaction.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.RETURN)
                .student(student)
                .book(book)
                .transactionStatus(TransactionStatus.PENDING)
                .build();

        transaction = transactionRepository.save(transaction);

        Transaction issueTxnDb = transactionRepository.findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByTransactionTimeDesc(student,book,TransactionType.ISSUE,TransactionStatus.SUCCESS);

        long issueTxnInMillis = issueTxnDb.getTransactionTime().getTime();
        long currentTimeMillis = System.currentTimeMillis();
        long timeDifferenceInMillis = currentTimeMillis - issueTxnInMillis;
        long timeDifferenceInDays = TimeUnit.DAYS.convert(timeDifferenceInMillis,TimeUnit.MILLISECONDS);
        double fine = 0;
        if(timeDifferenceInDays > noOfDaysAllowedAfterIssuance){
            fine = (timeDifferenceInDays - noOfDaysAllowedAfterIssuance) * 1.0;
        }
        String externalTxnId;

        try{
            book.setStudent(null);
            bookService.unassignBookToStudent(book);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            transaction.setTransactionStatus(TransactionStatus.FAILED);
        }finally {
            transaction.setFine(fine);
            externalTxnId = transactionRepository.save(transaction).getExternalTxnId();
        }
        return  externalTxnId;


    }




}
