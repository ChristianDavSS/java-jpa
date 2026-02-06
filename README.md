## **Java Jakarta Persistence project**

### **What's Jakarta persistence API?**
Jakarta Persistence is a Java ORM used for relational database mapping and management with Java native objects.
This is the base of multiple web frameworks built in Java, such as Spring JPA, so it's important to know how it works.


### **Why developing this?**
As i'm always looking for improvement and continuous learning, I decided to understand the Spring frameworks from inside out,
without the magics things the framework does (annotations or methods you don't even know why they work the way they do). In
order to solve the problem, I decided to start working with the Jakarta, so I can deeply understand why the framework works
the way it does.


### **Features**
The base project is a clear example of hexagonal software architecture, keeping the core and application layers out of frameworks
or dependencies (in this case, database managers). This way, the code is kept maintainable, scalable and clean.

With this approach, if you ever want to change database it would be easier because you'll just have to modify the infrastructure code.

*Written and developed by Christian David Sánchez Sánchez.*