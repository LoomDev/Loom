# Logging

In most cases, it is better to use a logger for diplaying messages in the console, rather than using ```System.out.println```.

Sometimes ```System.out.println``` can be used for debug messages, but loggers allow you to use different logging levels.

Logging levels:
| Name  | Description                                                                                                          |
| :---- | :------------------------------------------------------------------------------------------------------------------- |
| DEBUG | Used to show debug messages, that most likely aren't useful to the server admin.                                     |
| INFO  | Used for simple information messages.                                                                                |
| WARN  | Used to show warning messages (e.g. "This plugin is outdated. Please download a new version at example.com/plugin"). |
| ERROR | Used to show error messages, that most likely will allow the plugin to continue working.                             |
| FATAL | Used to show serious errors, that most likely will cause the the plugin not to work.                                 |

### Using a logger in your plugin
1. Inside the main class create a field, with a sensible name like "logger" and an "@Inject" annotation:
```java
@Inject
private Logger logger;
```
2. To log message, call the method with the name corrosponding to the log level:
```java
logger.info("The plugin is working!");
if (!checkLatestVersion()) {
    logger.warn("Outdated version detected. Please update to the latest version.");
}
if (!aBoringMethod()) {
    logger.error("Failed run a boring method.").
}
```
Example output:
```
[00:00:00] [INFO] [Plugin] The plugin is working!
[00:00:00] [WARN] [Plugin] Outdated version detected. Please update to the latest version.
[00:00:00] [ERROR] [Plugin] Failed to run a boring method.
```
3. To log exceptions, pass the exception into the arguments:
```java
try {
    dangerousMethod();
} catch (DangerousException error) {
    logger.error("The extremely dangerous method failed", error);
}
try {
    crucialForThisPluginToFunction();
} catch (Throwable error) {
    logger.fatal("Disabling plugin due to the following error", error);
    Loom.getPluginManager().disablePlugin("plugin-id");
}
```
Example output:
```
[00:00:00] [ERROR] [Plugin] The extremely dangerous method failed
com.example.plugin.DangerousException: null
    at com.example.plugin.Plugin.dangerousMethod(Plugin.java:132)
[00:00:00] [FATAL] [Plugin] Disabling plugin due to the following error
java.lang.Throwable: null
    at com.example.plugin.Plugin.crucialForThisPluginToFunction(Plugin.java:157)
[00:00:00] [INFO] [Plugin] Disabling Plugin...
```

> Tip: Make sure to import org.apache.logging.log4j.Logger.
