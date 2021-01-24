# GPS track processor
Program for searching the best representative track from a list with avoiding noises.

### Dependencies
Java 11+

Maven 3.6.0+

Lombok plugin for IDE

### Using

`mvn spring-boot:run -Dspring-boot.run.arguments="<Source port> <Destination port> <Input file> <Output file>"`

For example:

`mvn spring-boot:run -Dspring-boot.run.arguments="'DEBRV' 'DEHAM' 'routes.csv' 'result.csv'"`

**The original data with "noise"**
![Screenshot](with noise.png)

**The most representative track after processing**
![Screenshot](result.png)