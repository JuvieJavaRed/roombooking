# Spring Boot Booking app test

This is a very simple shell of a spring boot application used for making bookings for a bingo hall.
The application can be run from BookingSystemApplication.java and once run 
t he booking endpoints are accessible http://localhost:8080/swagger-ui/index.html

### The Test
- Complete the rest controller controller/Order.java implementation adding necessary services/beans
for the required functionality. 

- You can either use the h2 database provided on startup or any 
type of java based collection. 

- Regardless of the persistence type used concurrency and transaction management must be handled

- As seen necessary add unit and integration 

### The Solution

- The solution was built to spec. I did however leave out the following to get the solution to get it to a deployable state

1. Spring security using either jwt or user login (certain places do not need to secure endpoints as they live behind a firewall)
2. Unhappy path tests
3. A docker file to create an image of the system
4. Document the models of the system.

I was not sure if there was a need to implement the delete function. However this is my solution. :)
