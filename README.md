# MenuApp
Application designed for showing the potential employer my Android capabilities.

This application was an assigment in one of the companies for potential employment. This README file will have a description about the assigment itself, how the application was designed (which design pattern was used), which libraries were used and application and package structure.

Libraries used in this project: 

1. Constraint layout - for layouting the views 
2. Navigation Component (androidx.navigation) - for navigating between the fragments (from one screen to another)
3. Hilt - dependency injection framework
4. Picasso - for downloading and caching images
5. Retrofit - library for making HTTP calls
6. ViewBinding - to bind views via view id, and using those views in activity or fragment
7. DataBinding - to bind properties in the XML layout (email and password text fields have the example of the 2-way data binding)

Design of the project:
- Application has a single Activity with 3 fragments. I used Navigation Component to change the fragments and going from one fragment to another.
- MVVM (Model - View - ViewModel) design pattern was used, to show the communication between these components in this application and to show how can you decouple the components in an Android application

Package structure of the application:

1. data package - in this package you have the components necessary to get the data from the server or from local database. In this example, data was only pulled from the server. What classes are defined in this package? 
 - DTOs (Data Transfer Objects) - objects that are used the get the data from the server
 - Network configuration (Retrofit interface to define API endpoints)
 - Repository implementation (object that will be used to communicate to the ViewModel that the data is pulled from the server)
 - additional objects to inform the user about the status of the HTTP call: loading status (waiting to get data from the server), success status (HTTP call was successful) and error status (error in HTTP call)

2. di package - dependency injection package, where modules are defined for injecting dependencies into activities, view m.odels and fragments via Hilt library. Creation of the neccesary objects in the application are defined here (Retrofit instance, with interceptors, and repository instance)

3.  domain package - this package contains business model objects (objects that are used for core business logic in the application). In this example core objects are the Venue object, with name, description, isOpen, welcomeMessage and mediumThumbnailImage properties, and AccessToken object, to save the token after the user has logged in into the application. Also in this package you have mapper objects, that are used to convert data transfer objects into the domain object, and repository interface definition.

4. ui package - this is where the user interface is defined, with all the usecases (login, venue list and venue details) which contains viewmodels (where core business logic is contained) and fragments to show the ui to the user.

5. utils package - utility classes to add additional functionality to the application, like showing and hiding the progress dialog, network connection check, shared preferences for storing authentication data, error constants to inform the user about the validation error messages for login and showing the error message from strings.xml file.
