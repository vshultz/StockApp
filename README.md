# StockApp
Website application that displays historical S&amp;P 500 stock data.

Database was created with Amazon's AWS RDS MySQL and MySQL Workbench.

Screenshot of the AWS RDS MySQL instance:
<img width="1450" alt="Screen Shot 2021-10-21 at 9 23 38 AM" src="https://user-images.githubusercontent.com/77592389/138297703-ded4e478-d561-4f74-bb17-64c4daeb9c4a.png">

Screenshot of some database entry examples using MySQL Workbench:
<img width="1792" alt="Screen Shot 2021-10-21 at 9 25 57 AM" src="https://user-images.githubusercontent.com/77592389/138298176-7e93bf5d-5bd3-4fea-9750-e212788151d5.png">
<img width="1792" alt="Screen Shot 2021-10-21 at 9 28 10 AM" src="https://user-images.githubusercontent.com/77592389/138298648-93549498-1e82-4d61-99d5-fd310d08c53a.png">

To create the backend, I use Spring Boot Framework with Apache Maven dependencies. Spring Boot has an embedded Tomcat Server, so the Tomcat Server did not need to be manually configured. Within the Spring Boot project, I used the Spring Data JPA with Hibernate that allowed me to use annotations to configure the different Java objects. The Java objects included the controllers that issued the front end requests and told the backend requests what to fetch. The controllers then sent a response with the set model attributes from the backend into the forwarded http page.

The service level of the project took care of the business level of the project. The controller called the service interface, which was extended by the concrete instance class. The service level included forwarding the requests to the repository interface (the next lower level) and creating a JSON file with the received data. This JSON file was then used in the response http pages to create the graphs.

The repository or data access object (dao) had an interface that was extended by two concrete classes. Each class corresponded with the two tables used within the database. The classes used the Entity Manager to create a query and receive a list of domain objects. These lists were then passed to the service level.

The domain levels included the price entity and attribute entity, which are the two tables used in the database. A third class was created as an Embedded Id due to the price table having two primary keys. The two primary keys were needed because the symbol and the date together created unique keys. Had the symbol been the primary key, the key would not have been unique due to many entries (for each date) having the same (non-unique) key. The attribue entity only had the primary key of name, which represented the company's full name.

The front end was created with CSS Bootstrap and Javascript Charting Libraries from Highcharts and CanvasJS. Animations were added (credit of the animation templates given in the acknowledgements) to the hamburger menu the mouse icon. The Javascript Charting Libraries read the JSON file that was created earlier. The web page was made reactive to fit neatly on any screen.
<img width="1737" alt="Screen Shot 2021-10-21 at 9 22 21 AM" src="https://user-images.githubusercontent.com/77592389/138297429-0ab1a561-7349-420d-8e24-3d43168f9de5.png">
<img width="571" alt="Screen Shot 2021-10-21 at 9 48 42 AM" src="https://user-images.githubusercontent.com/77592389/138302532-60040ae5-38f8-4786-aa56-c5026c01d1de.png">
<img width="1649" alt="Screen Shot 2021-10-21 at 9 49 08 AM" src="https://user-images.githubusercontent.com/77592389/138302614-059fc341-9c30-4a44-a593-75bc1ce04f9c.png">

