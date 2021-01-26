# Creating a blank plugin with Eclipse

### Setting up
1. Follow the setup instructions in [Building](../Building.md).
2. Inside the root directory, run ```mvn clean install```.

### Creating the project
1. Create a new Java project.
2. Right click on the project and click on "Build Path -> Add External Archives".
3. Navigate to the folder Loom was cloned into.
4. Select api/target/loom-api-version.jar (where version is the version of Loom).

To create the main class, see [Creating the main class](Creating%20the%20main%20class.md).
