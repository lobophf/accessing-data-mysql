I made this simple REST API using Spring Boot for training purposes. It sends and receives data stored in the MySQL database.
To configure  the application file, you need to create a database and add a file `src/main/resources/application.properties` which the following content. Don't forget to replace the fields with the database name, user, and password.

```
#src/main/resources/application.properties
spring.jpa.hibernate.ddl-auto=update    
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_exemple    
spring.datasource.username=user    
spring.datasource.password=psw    
spring.datasource.driver-class-name =com.mysql.jdbc.Driver    
#spring.jpa.show-sql: true    
```

To execute the CRUD operations, use the commands below.

#### Create:
```
curl -H "Content-Type: application/json" -X POST -d '{"name":"myName","email":"my@email"}' http://localhost:8080/demo/save
```

#### Read
```
curl  http://localhost:8080/demo/all -s | json_pp
curl  http://localhost:8080/demo/16 -s | json_pp
```

#### Update
```
curl -X PUT http://localhost:8080/demo/16 -H 'Content-type:application/json' -d '{"name":"Someone", "email":"someone@email"}'
```

#### Delete
```
curl -X DELETE http://localhost:8080/demo/3
```
