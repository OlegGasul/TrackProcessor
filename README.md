# GPS track processor

### Dependencies
Java 11

Maven

Lombok plugin for IDE

### Using

mvn spring-boot:run -Dspring-boot.run.arguments="<Source port> <Destination port> <Input file> <Output file>"

For example:

mvn spring-boot:run -Dspring-boot.run.arguments="'DEBRV' 'DEHAM' 'DEBRV_DEHAM_historical_routes.csv' 'result.csv'"