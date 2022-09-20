![](https://img.shields.io/badge/Spring%20Boot-2.7.3-brightgreen) ![](https://img.shields.io/badge/Java-11%20%3E-yellow)
# Spring Boot Simple REST API with Exception handling.

------------

### About the demo:
Nothing Fancy here, Simple app with tow entities:
- Person Entity that can have one or more Cat Entities.
- ManyToOne and OneToMany relationships.
- H2 in-memory data base to make things easy to demonstrate.
- Simple Rest API with some basic functionalities.
> please note that I did not make request-methods for all the service-methods.
- One simple ExceptionHandler using *@ControllerAdvice* annotation.
- To override Spring default bad API request-path exception you will need to add these tow lines in `application.properties`:

```yaml
spring.mvc.throw-exception-if-no-handler-found=true
spring.web.resources.add-mappings=false
```
and then expect to handle the `NoHandlerFoundException`.
- included a simple custom exception: `ResourceNotFoundException` as a demo.

------------

&copy; Mohamad Ojail 2022