Java Auto Update
==========

A simple Java project designed to wrap existing projects and enable auto-updates.

Main.java starts the auto-update controller. From here, you can configure the location of your .class files, as well as the project's main class to load and start up.

For each auto-update, the controller does the following:
1. Execute .stop() on the loaded class
2. Reload the main class
3. Execute .start() on the loaded class

```java
final AutoUpdateController autoUpdate = new AutoUpdateController();
autoUpdate.setClassLocation(classLocation);
autoUpdate.setMainClass("com.testautoupdate.myserver");
		
autoUpdate.start();
```
