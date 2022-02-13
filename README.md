#src/main/resources/application.properties
spring.jpa.hibernate.ddl-auto=update    
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_exemple    
spring.datasource.username=user    
spring.datasource.password=psw    
spring.datasource.driver-class-name =com.mysql.jdbc.Driver    
#spring.jpa.show-sql: true    
