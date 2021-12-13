# Fast Radius Homework

## Overview & Tutorial
I built this project as a REST API using Java and Dropwizard.

To run this project, run the Gradle command ```gradle clean build run```. This will clean out old builds, run the tests,
and bootstrap the project. 

I used IntelliJ to write the project, but if you don't have it available, you should be able
to run the project through a terminal (with Gradle installed) by drilling down into the root folder and running:
```./gradlew clean build run```

When the API is up and running, you can hit the following endpoint using Postman, cURL, or most browsers:
>localhost:8080/stereolithography/get-shape-representation?filename=YOUR_STL_FILENAME

The two supported STL files are 'Moon' and 'simplePart'.

## Added Libraries
- **Dropwizard** - An open source library for RESTful API development. Provides support for dependency injection, 
writing endpoints, HTTP methods, and JSON serialization/deserialization.
- **Lombok** - An open source library for abstracting away Java boilerplate code. The annotations you see at the top of
 most classes here are from Lombok. I used it for things like getters/setters and constructors
- **Javax Vecmath** - A Java library for vector math. I added this to have classes for representing vertices and applying
math operations on them, like cross products.

- **Mockito** - A testing library for mocking.
- **JUnit Jupiter** - A unit testing library for Java.

## Design Choices

I wrote the API in a Domain Driven Design(ish) pattern. The code is split into 3 layers, an application logic layer
(controller) for managing HTTP communication, a service layer for handling most of the business logic and calculations,
and a data layer (repositories) for handling importing/parsing data to feed into the service layer.

Across the board I used dependency injection with interfaces to provide loose coupling between implementations and easy
mocking for testing. This helps the code be extendable as new implementations just have to implement an interface to be
compatible.

For example, if we extend the API to supporting binary STL files, this new `BinaryStlFileRepository` would implement the
`StlFileRepository` interface. This ensures it would be compatible with all classes who use that interface and would not require
other changes for the callers.

For the STL file parser, I chose to use `BufferedReader` for the performance enhancements (it reads files line by line as text)
, and because STL files are strictly formatted, allowing me to write a more deterministic parser.

STL data is stored into the `Solid` domain object, which contains a list of `Facets`. Having the data captured in the object
makes it easier to add new calculations to the API, because any new calculations can operate on that object or we can extend
 the object to hold other bits of data. 
 
 The trade off, however, is performance/memory will suffer in cases where a solid has millions of triangles(or more), since
 creating millions of objects is expensive and re-looping through that many triangles in `Solid` to run a calculation can be slow.
 
 A few ideas for improving this are:
 - Running some of the calculations during file parsing, rather than parsing all of the data, then looping through it again
 to do the math.
 - Parallelizing file parsing and calculations. Because the file is uniform and some calculations can be parallelized
  (like surface area), there's an opportunity here to split the processing into multiple threads, then sum the results.
  For example, you could spool 4 threads, each one parsing 1/4 of the facets, then sum the results to get the number of triangles.
 - Using bit shifting over arithmetic operations. The caveat here would be the trade-off between code readability and what
  the performance boost would be. The performance boost would only be realized in large sets of data. This would not be my first choice.

## Next Steps
- Add sanitization and more validation around REST request parameters, e.g. uppercase all STL files so I could 
sanitize/uppercase file names from requests
- More granular error handling around file parsing, i.e. giving different responses to the caller depending on if a file
is incorrectly formatted versus not found, and etc.. Additionally, figuring out if a 204 or 404 is more appropriate when
a file is not found.