## Expositions Calendar
7. Система Календарь выставок. Существует список Выставочных залов в каждом из которых есть перечень Экспозиций. Посетитель покупает Билеты оформив Платёж  и выбрав Тему выставки.

### Setup 
* JDK 1.8 or higher
* Apache Maven 3.5.2 or higher
* MySQL 5.7 or higher
* Apache Tomcat 8.5.13 or higher

### Installation
* Download project from GitHub
* Unpack .zip
* Specify values of "username" and "password" keys in \src\main\webapp\META-INF\context.xml
* Start MySQL server and execute script initdb.sql from \src\main\resources\dbscripts to init database
* cd to root project folder and execute command mvn clean install

### Running
* Start Tomcat server by running the script %TOMCAT%\bin\startup .bat for Windows or .sh for Unix based OS.
* After server start, application will be available by URL http://localhost:8080/expocalendar
* Use login "root" and password "root" to log in with administrator rights  
* To stop server run script %TOMCAT%\bin\shutdown .bat for Windows or .sh for Unix based OS.
