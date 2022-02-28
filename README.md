#src/main/resources/application.properties
spring.jpa.hibernate.ddl-auto=update    
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_exemple    
spring.datasource.username=user    
spring.datasource.password=psw    
spring.datasource.driver-class-name =com.mysql.jdbc.Driver    
#spring.jpa.show-sql: true    


curl  http://localhost:8080/demo/all -s | json_pp

curl  http://localhost:8080/demo/16 -s | json_pp

curl -H "Content-Type: application/json" -X POST -d '{"name":"myName","email":"my@email"}' http://localhost:8080/demo/save

curl -X PUT http://localhost:8080/demo/16 -H 'Content-type:application/json' -d '{"name":"Someone", "email":"someone@email"}'

curl -X DELETE http://localhost:8080/demo/3
