
# File Hider App

A Java application to hide files into a MySQL database.


## Description

A console-based Java application to hide local file on the computer into a MySQL database, with email & OTP-based authentication access for multiple users. Files can be hidden and unhidden after logging in. Also implements a HikariCP based connection pooling to ensure better connection management.

## Key Features
File Hiding and Retrieval: Users can select files from their local system and hide them securely within a MySQL database and the orignal file gets deleted.

User Management: The project supports multiple users, each with their accounts. Robust user authentication ensures that only authorized users can access their hidden files.

Email & OTP Verification: During signup and login, users receive OTPs via email for verification.

Efficient data management using MySQL and efficient connection management using HikariCP

## Getting Started

### Prerequisites

* JDK 8 or later
* MySQL database
* IDE (IntelliJ Idea or Eclipse preferred)
* Google SMTP access (email & password)

My development OS: Windows 10

### Dependencies

Dependencies required for this project handled by Apache Maven. Check pom.xml file for the list of dependencies required.


### Database intitiation

Below lists the SQL commands 

1. Creating the database:

      `CREATE DATABASE fileHider;`

2. Switching to the database:

   `USE fileHider;`


3. Creating the tables users & data in the DB:

   `create table users (id int primary key auto_increment, name varchar(100), email varchar(100) unique);`

   `create table data(id int primary key auto_increment, name varchar(100), path varchar(255), email varchar(100), bin_data blob);`


### Executing program

1. Clone this repo
2. Open on any IDE
3. Setup emailFrom and password fields in SendOTPService class
4. Setup DBConnection class with MySQL credentials
5. Run Main.java class

## Version History

* v1.1
    * Minor bug fixes and optimizations
    * Implementation of connection pooling using HikariCP
    * For more see commit change or see release history
* v1.0
    * Initial Release
 
### Future Versions Planned

* v1.2 (Proper error handling in DBConnection, external config file, error logging in whole application, profile management)
* v1.3 (File-type validation, transaction management, file-size limitation, dynamic file-size handling -- DAO, Service and Views)
* v1.4 (3rd party SMTP, JavaMail API wrapper, Dockerize)
* v1.5 (Huffman-algo based compression-decompression while hiding-unhiding)
