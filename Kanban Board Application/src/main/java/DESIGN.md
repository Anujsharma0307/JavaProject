### Design Decisions
Ques 1. How did you apply MVC design pattern to build this application?<br>
The overall design of this project is based on the MVC design pattern.
This means all source code is within the `main` directory, again split up
by `java` and `resources` package. The `sample` directory is split up into a `controller`
package that contains all of the UI controller classes. The `model` package is for all of the back-end code, and in the
`resources` package the `sample` directory contains all of the fxml files in the program. The `model` package was 
further split up into another package with the name `object` that represent our main objects 'project', 'task' , 'user'

Ques 2. How does your code adhere to SOLID design principles?<br>
The following five concepts make up our SOLID principles:

####Single Responsibility- 
Every class/package in my model package i.e `LoginModel`,`ProjectModel`,`RegisterModel`,`object.project`
,`object.taskManagement` and `object.user` serves only a single purpose/responsibility.
every class in controller package has been named after the object's ui that it is controlling like `projectcontroller`,
`projectcontroller`,`usercontroller` and the author insured to keep these classes low coupled.<br>
####Open/Closed - 
Most of the variables and characteristic of the object used are kept private and only utilized through 
various constructors, getters and setters methods just to ensure that classes are open for extension but closed for 
modification.<br>
####Liskov Substitution-
I haven't used any interfaces other than initializable but instead i have used abstract class for my task model because 
it is given in the assignment specification that a user can create 2 types of tasks 'basic' and 'advance'. Therefore i 
have made a abstract class for task and then 2 classes basicTask and advanceTask represent their own functionality.
####Interface Segregation-
I have only used 'initializable' interface in my code for initializing my controller classes. It provides with the
initialize method { initialize(URL location, ResourceBundle resources) } that is called to initialize a controller after 
its root element has been completely processed.
####Dependency Inversion-
In order to reduce the dependency and reduce the coupling between the two test classes I have introduced an abstract 
class for the tasks and let the basic and advance task classes communicate through the tasks' abstraction. 

Ques 3. What other design patterns does your code follow? Why did you choose these design patterns?<br>
I choose only MVC design pattern in the code because I didn't feel the need for another design pattern as most of my design
needs were already fulfilled by MVC design pattern <br>
REFRENCES
1. Millington, S. (2019, February 5). A Solid Guide to SOLID Principles | Baeldung. Baeldung. https://www.baeldung.com/solid-principles