# Creating a blank plugin with Maven

### Setting up
1. Follow the setup instructions in [Building](../Building.md).
2. Inside the root directory, run ```mvn clean install```.

### Creating the project
1. Create a basic Maven project.
2. In "pom.xml" add the following:
```xml
<dependencies>
    <dependency>
        <groupId>org.loomdev</groupId>
        <artifactId>loom-api</artifactId>
        <version>Loom Version</version>
    </dependency>
</dependencies>
```
Make sure to replace "Loom Version" with the Loom version you want to use.

To create the main class, see [Creating the main class](Creating%20the%20main%20class.md).
