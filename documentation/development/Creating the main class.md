# Creating the main class

1. Create a new package. You can call it anything you want, but it's best to begin it with a reversed domain name (e.g. "Simple Plugin" at "example.com" becomes "com.example.simpleplugin").
If you don't own a domain name, you can use a user you own on an existing domain (for example, com.github.username.pluginname). For more information, see [this tutorial](https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html).
2. Inside that package, create a class, again using the standard naming conventions (ClassName).
The class should look something like this:
```java
package com.example.simpleplugin;

public class SimplePlugin {

}
```
3. Add a "@LoomPlugin" annotation to the class:
```java
@LoomPlugin(
    id = "simple-plugin",
    name = "Simple Plugin",
    description = "My first Loom plugin.",
    version = "1.0",
    dependencies = {
       @Dependency(id = "required-plugin", optional = false),
       @Dependency(id = "recommended-plugin", optional = true)
    },
    minimumApiVersion = ApiVersion.UNKNOWN
)
```
The only required field is "id", but the other fields are recommended (apart from "dependencies" which should only be used if the plugin depends on another).

"minimumApiVersion" is the minimum Minecraft version for the plugin (e.g. "v1.16", if the plugin uses blocks added in the Nether Update).

"dependencies" is a list of plugins that the plugin requires, or gives the plugin extra functionality when installed. Each dependency is another annotation with two fields - "id" (the ID of the dependency) and "optional" (whether the plugin can be loaded without the dependency).

4. Add hooks. To detect when the plugin is enabled, use a PluginEnableHook:
```java
@Hook
public void onEnable(PluginEnableHook hook) {
    System.out.println("This plugin is working!");
}
```
To detect when the plugin is disabled, use PluginDisableHook:
```java
@Hook
public void onDisable(PluginDisableHook hook) {
    System.out.println("Goodbye!");
}
```
For a more complicated example of a main class, see [this class](https://github.com/LoomDev/Loom-Example-Plugin/blob/master/src/main/java/com/github/loomdev/example/plugin/DemoPlugin.java).
