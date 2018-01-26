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
* Specify values of "username" and "password" keys in _\src\main\webapp\META-INF\context.xml_
* Start MySQL server and execute script initdb.sql from _\src\main\resources\dbscripts_ to init database
* cd to root project folder and execute command _mvn clean install_
* copy artifact expocalendar.war from _target_ folder to _%TOMCAT%\webapps_

### Running
* Start Tomcat server by running the script _%TOMCAT%\bin\startup_ .bat for Windows or .sh for Unix based OS.
* After server start, application will be available by URL _http://localhost:8080/expocalendar_
* Use login _**"root"**_ and password _**"root"**_ to log in with administrator rights.  
* To stop server run script _%TOMCAT%\bin\shutdown_ .bat for Windows or .sh for Unix based OS.
