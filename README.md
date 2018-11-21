# Data Services Code Challenge
This dynamic web project is built using Java9 and Servlet v4.0 optimized for Tomcat9 deployment.

The application utilizes an MVC design pattern to read user requests in via HTTP RESTful methods through the RequestHandler class. 
Business logic for validating user input and synchronizing database access can be found in the 'service' and 'data' packages. 
Application wide context variables are initialized in the StartupListener found in the listeners package. 

In order to run this application you must either export the project to a .WAR file and mount onto Tomcat directly, or you can import
the project in Eclipse and configure your Tomcat there.

Also included is a Python locust file. This module may be run to simulate concurrent user behavior and was used to test and verify
application performance integrity. Find details regarding running locust tests here: https://docs.locust.io/en/stable/quickstart.html