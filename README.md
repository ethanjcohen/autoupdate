Java Auto Update
==========

A simple Java project designed to wrap existing projects and enable auto-updates.

Main.java starts the auto-update controller. From here, you can configure the location of your .class files, as well as the project's main class to load and start up.

For each auto-update, the controller does the following:
<ol>
<li>Execute .stop() on the loaded class</li>
<li>Reload the main class</li>
<li>Execute .start() on the loaded class</li>
</ol>
```java
final AutoUpdateController autoUpdate = new AutoUpdateController();
autoUpdate.setClassLocation(classLocation);
autoUpdate.setMainClass("com.testautoupdate.myserver");
		
autoUpdate.start();
```
