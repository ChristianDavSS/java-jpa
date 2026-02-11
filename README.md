## Backend server with Spring Context 🍂
Java backend server from scratch using Tomcat, JPA and Spring Context made to reinforce my Java understanding as well as my knowledge about web servers and relational databases.
This project could be taken as an example for bigger projects that doesn't want to use magic frameworks, just pure Java implementations. With this, you have fully control on whatever you're doing so you don't rely on magic annotations or methods.

#### Languages 💻
Java (100%) ☕

#### Dependencies (Maven) 📂
- Spring Context (v7.0.3)
- Hibernate (v6.5.2)
- Apache Tomcat (v11.0.14)
- Gson (v2.13.2)
- Jakarta Persistance Api (JPA) (v3.2.0)
- PostgreSQL JDBC Driver (v42.7.7)

### Project summary ✏️
The entry point (Main class) is where the whole process starts, running the web server on a determined port and injecting the beans into the servlets in order to execute the services successfully. Tomcat was very useful to understand how the HTTP requests are received and managed by a real server.

With Spring Context, I was able to register the dependant classes with others, allowing me to practice the **dependency injection** and make the code cleaner and mantainable.

### Project arquitecture 📐
The whole project lives in the **main folder** using a kind of hexagonal architecture
```text
├───java
│   └───com
│       └───backend
│           ├───application          # Use cases. In this case, it contains the services for each entity
│           ├───configuration        # Spring context bean registry
│           ├───domain
│           │   ├───entity           # JPA entity definition (ORM)
│           │   └───ports            # Repository definition
│           └───infrastructure
│               ├───implementations  # Repository interface implementations (bean)
│               └───servlet          # Entity controllers for HTTP request management
└───resources                        # XML configuration files
    └───META-INF
```
This structure allows me to have a better control and understanding on the files I'm working with.

If you want to add more queries to any entity, you must do:
- Create a single repository interface for each entity (in case you need each entity to have different queries)
- Add the abstract methodinto the interface
- In the file **EntityImpl.java** you must create it's implementation
- Create it's service method
- Add the request logics into **EntityServlet.java** (depending on what's the HTTP method).

Just as simple as that :D.
