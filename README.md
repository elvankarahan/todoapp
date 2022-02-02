#### ToDo
------------

This is a ToDo application that users can register/login and, create/update and delete their own notes.

###### Build the Project
	mvn package
This command line will print out following:

	...
	[INFO] ------------------------------------------------------------------------
	[INFO] BUILD SUCCESS
	[INFO] ------------------------------------------------------------------------
	[INFO] Total time:  3.507 s
	[INFO] Finished at: 2022-02-02T10:53:14+03:00
	[INFO] ------------------------------------------------------------------------

###### Run the Project
	java -jar target/todoapp-0.0.1-SNAPSHOT.jar

Logs should end like this after run command: 

	...
	2022-02-02 12:15:45.132  INFO 15892 --- [main] o.s.b.w.embedded.tomcat.TomcatWebServer: Tomcat started on port(s): 8080...
	2022-02-02 12:15:45.147  INFO 15892 --- [main] com.todo.TodoappApplication: Started TodoappApplication in 10.496 seconds (JVM running for...

###### Documentation

Detailed api documentation will be avalible at http://localhost:8080/v3/api-docs . This link returns a json response so it should be formatted with [Swagger Editor](https://editor.swagger.io/ "Swagger Edito") and this gives a dynamic page.

###### Docker Hub
Pull the image

	docker pull elvankarahan/todospring:latest

Run

	docker run -p 8080:8080 -t todospring:latest

###### Requests
Some practices

- Sing up
`http://localhost:8080/api/auth/signup`

	```json
	{
		"username":"testuser",
		"email":"testuser@gmail.com",
		"password":"123456",
		"role":["ROLE_USER"]
	}
	```
- Login
`http://localhost:8080/api/auth/signin`

	```json
	{     
		"username":"testuser",
		"password":"123456"
	}
	```
  
Login will return:

  ```json
    {
      "id": 1,
      "username": "testuser",
      "email": "testuser@gmail.com",
      "roles": [
        "ROLE_USER"
      ],
      "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImlhdCI6MTY0Mzc5NTUwMCwiZXhwIjoxNjQzNzk2MTAwfQ.o3DGZBWgL2kXqupk2x7Dnp-EvueU2Jbj6qt_B6jeokHPqXkNn1kMxv9PTRM2e-EDpNFq2hYqc0hZKCm3hGPjxw",
      "tokenType": "Bearer"
    }
  ```
  
- Add a Note

	```json
	{
			"title": "My title",
			"content": "Content "
	}
	```

##### Technologies
- Java
- Spring Boot
- Spring Security
- H2 Database
- Swagger
- Docker
- React for Front-End

