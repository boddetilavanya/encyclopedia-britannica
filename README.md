# encyclopedia-britannica

Import the code as gradle sts poject 

#Build steps:
Run the gradle build from Gralde tasks.
Jar will be created in build/libs folder
We have test cases in test class - will also run along with the build.

Table Creation
create table topic (topic_id INT PRIMARY KEY NOT NULL, title VARCHAR(50),class_name VARCHAR(50) NOT NULL);
Insert statements
insert into topic values(4144,'acrobatic-skiing-Year-In-Review-1997','sports');
insert into topic values(1376,'ablation-cluster-preparation','science');
insert into topic values(4143,'acrobatic-skiing','sports');


To Deploy into PCF : 
Login to PCF :
cf.exe login --skip-ssl-validation -a https://api.run.pivotal.io -o <<Org-Name>> -u <<Username>> -p <<Password> or 
  cf login --skip-ssl-validation -a https://api.run.pivotal.io -o <<Org-Name>> -u <<Username>> -p <<Password> or
 Push the application to PCF 
 cf.exe push <PCF ApplicationName> -p <<Jar path>> or 
  cf push <PCF ApplicationName> -p <<Jar path>> 
 
  Since we are using cloud connectors we would need to create a user provided service in PCF 
  Here is the command :
  cf create-user-provided-service TOPIC_DB_SERVICE -p \'{\"jdbcUrl\":\"<<URLValue>>\"}\'
  cf bind-service <<PCFApplicationName>> TOPIC_DB_SERVICE
  Example of Url Value
  jdbcUrl (for Oracle)
  jdbcUrl = jdbc:oracle:thin:<UserName>/<password>@ldap://<hostname:port>/<schemaName>,cn=OracleContext



Swagger ui can be seen once the app is in running state
https://encyclopediabritanicaapi.cfapps.io/swagger-ui.html
https://<PCFAppName>>/swagger-ui.html
  

