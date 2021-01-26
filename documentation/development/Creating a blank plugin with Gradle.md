# Creating a blank plugin with Gradle

### Setting up
1. Follow the setup instructions in [Building](../Building.md).
2. Inside the root directory, run ```mvn clean install```.

### Creating the project
1. Create a basic Gradle project, using ```gradle init```.
2. Add the following to build.gradle:
```gradle

ext {
    loomVersion = "Loom Version"
}

repositories {
    mavenLocal()
}

dependencies {
    implementation "org.loomdev:loom-api:$loomVersion"
}
```
Make sure to replace "Loom Version" with the version of Loom you want to use.

To create the main class, see [Creating the main class](Creating%20the%20main%20class.md).
