package com.ethanjcohen.autoupdate;

public class Main 
{	
	public static String classLocation = "file:C:\\Users\\ZenTsuna\\Code\\Eclipse Workspace\\InfoServer\\bin\\";
	
	public static void main(String[] args) 
	{
		final AutoUpdateController autoUpdate = new AutoUpdateController();
		autoUpdate.setClassLocation(classLocation);
		autoUpdate.setMainClass("com.ethanjcohen.infoserver.Server");
		
		autoUpdate.start();
		
		Runtime.getRuntime().addShutdownHook(new Thread() 
		{
			public void run() 
			{
				autoUpdate.stop();
			}
		});
	}
}
