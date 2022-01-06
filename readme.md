# Inventory Tracker
## How to use
### System requirements:  
Maven 3.2 or higher: you can check which version of maven you have by doing `mvn -v`  
Java JDK 11 or higher: you can check which version of java you have by doing `java -version`
### Steps: 
1. Either clone the project or download and extract the zip
1. In cmd, navigate to the folder with the files. (directory should end with ShopifySu22)
1. Type `mvn spring-boot:run`  
1. visit `localhost:8080` on your web browser (if different, it should say in the console which port to go to).  
1. press `ctrl+c` to terminate the server. (enter `y` when prompted)

#### Misc: 
I used an in memory database for convenience, so terminating the server will get rid of anything in the database. You can query the database by going to `localhost:8080/h2`. Log in by putting `jdbc:h2:mem:testdb` in "JDBC URL", the username is sa and the password is empty. There you can execute SQL queries.

