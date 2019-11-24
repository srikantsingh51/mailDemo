# mailDemo
Step 1 . Clone the repo https://github.com/srikantsingh51/mailDemo.git to your local space and go inside the src/main/resource
          and upat the application.properties for your mongo DB
Step 2 . Go inside the project folder and run below command 
         gradle build  
Step 3 Go inside the buid/libs folder and run the below command 
          java -jar demo-0.0.1_SNAPSHOT.jar
Step 4 Open the PostMan or any testing tool and start testing
Step 5 post below request to this URL http://localhost:8080/send with below headers and body with mehod type POST
       for sending mail 
       
       headers Content-Type:application/json, Accept: application/json
       
       Body
       {   
        "id":"1",
        "senderId":"srikantsingh@gmail.com",
        "receiverId":"renumandal@gmail.com",
        "subject":"Hellow youre Intervie is schedule ",
        "content":"Hi Srikant I will be there on time regards Srikant Singh"
	
	
        }
  Step 6  To fetch all mails by http://localhost:8080/findAll into the server. It is a simple Get method call .
  
  Step 7  To fetch all  mail by given reciever email is  http://localhost:8080/findbyreciever/srikantsingh51@gmail.com
  Step 8 To fetch all mail by sender email is use this endpoint http://localhost:8080/findbyreciever/renumandal@gmail.com  
  
  Step 9 To fetch the content of email by email Id http://localhost:8080/readcontent/1
  
  Step 10 To find the  estimate number of mail will be sent by today http://localhost:8080/findmailsoftheday . This is calculated on mail sent per hr on based on passed hour of the day againt hr left in today ;
  
  Step 10 To find the  estimate number of mail will be sent by today http://localhost:8080/findmailsoftheweek . his is calculated on mail sent per hr on based on passed hour of the day against the hours left in the week 
