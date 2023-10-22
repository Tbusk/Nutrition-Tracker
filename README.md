# Nutrition-Tracker
Spring Boot Nutrition Tracker App for SENG 315

This is an application for tracking nutrition in Spring Boot utilizing the following technologies:
* Spring Boot
* Spring Security
* ThymeLeaf
* Bootstrap
* HTML
* CSS
* Java
* JPA (Hybernate)

This application has a foods page which utilizes a bootstrap data table with search and records from the USDA foods API. 
On the foods page, the user gets to see basic data points of a food item like name, protein, carbs, fats, brand name, and calories. The user can choose to add it to a meal and gets a choice of one of the meal options available (meal 1, meal 2, or meal 3)
The foods journal page shows a table with a total for protein, fat, carbs, and calories for the day. Additionally, each meal has its own table with those data points, plus quantity (editable) and a button to update quantity as well as a button to remove the item.
This project also uses Spring security and models rather than showing any raw json data or javascript dealing with the APIs.  Additionally, there are two roles that are added to the project, user and admin, which the status determines the access granted and what the user sees.
The application has the capabilities of listing all of the attributes of the items in the USDA food API for any given food, including totaling all micronutrients, protein breakdown, ingredients, etc. 

This application makes use of an application.properties file.  When using this project, create an application.properties file and place it in src/main/resources.
In that file, be sure to include the following:
* spring.jpa.show-sql=true
* spring.datasource.url=jdbc:mariadb://localhost:3306/diet-tracker
* spring.datasource.username=
* spring.datasource.password=
* spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
* spring.jpa.generate-ddl=true
* spring.jpa.hibernate.ddl-auto=none
* server.servlet.context-path=/
* spring.mvc.hiddenmethod.filter.enabled=true
* usda.api.key=

Templates used in this project:
* https://startbootstrap.com/snippets/full-image-header
* https://mdbootstrap.com/docs/standard/extended/login/
