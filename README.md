Have you had a breakup and are feeling sad? Or perhaps you had a stressful day and are feeling frustrated? Yes, I am talking about bibliotherapy using books. We all know that books are the best companions; when we read, it feels good and helps our minds focus on new things.

AI BookWise – an innovative library management system with a touch of AI. This system goes beyond traditional library management by offering bibliotherapy to help users through tough times. Here are some of the key features:

Sentiment Analysis and Book Recommendations: Using AI-driven sentiment analysis, the system can understand user emotions based on their text inputs (e.g., "I had a breakup today and I am feeling very broken. Can you suggest some books that can give me some relaxation?"). Based on this input, the system will recommend books that can help improve the user's mood and provide comfort.

Automated Notifications: Utilizes a cron scheduling system to send email reminders to users one day before their due date, helping them avoid fines.
Enhanced Security: Integrated Spring Security to ensure robust protection for user data.

Fast Data Retrieval: Connected with Redis, achieving up to a 60% improvement in data retrieval speed.

AI Book Recommendations: Leveraged OpenAI to recommend three books via email based on the user’s recent returns.

CI/CD Pipeline: Implemented a continuous integration and deployment pipeline, allowing for seamless future feature additions.

Running the Application:
Clone the application into your local system
run "mvn clean package"(make sure that you have maven installed in your local system)
jar file will be targeted and it will be in your target folder
run "java -jar name of the jar file}
make sure that you have sql server and redis installed you can run the application using mysql also just change the details in application properties 
Note : one thing to use the ai functionality kindly refer the fastapi readme file the other application should run parellely to this application because for functionality it 
get the data 




