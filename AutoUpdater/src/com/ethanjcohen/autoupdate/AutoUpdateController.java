package com.ethanjcohen.autoupdate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AutoUpdateController implements Runnable
{	
	private static String defaultLocation = "";
	private Class<?> mainClass = null;
	private String mainClassName = null;
	private static Class<?> serverClass = null;
	private static Object serverInstance = null;
	
	private Thread theThread = new Thread(this);
	
	private static ClassLoader parentClassLoader = BasicURLClassLoader.class.getClassLoader();
	private static BasicURLClassLoader classLoader = null;
	
	public void setClassLocation(String location)
	{
		defaultLocation = location;
	}
	public void setMainClass(String mainClassName)
	{
		this.mainClassName = mainClassName;
	}
	public void setMainClass(Class<?> mainClass)
	{
		this.mainClass = mainClass;
	}
	
	public boolean start()
	{
		System.out.println("Starting AutoUpdateController...");
		theThread.start();
		
		return true;
	}
	public boolean stop()
	{
		System.out.println("Stopping AutoUpdateController...");
		theThread.interrupt();
		return true;
	}
	
	public void run()
	{
		System.out.println("AutoUpdateController running.");
		
		while(!Thread.currentThread().isInterrupted())
		{
			try 
			{
				Thread.sleep(3000);
				refreshMainClass();
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
		System.out.println("AutoUpdateController stopped.");
	}
	
	private void refreshMainClass()
	{
		System.out.println("Refreshing class...");
		
		String name = mainClass == null ? mainClassName : mainClass.getCanonicalName();
		
		if(serverInstance != null)
		{
			
			try {
				Method stopMethod = serverClass.getMethod("stop", null);
				stopMethod.invoke(serverInstance, null);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		serverInstance = null;
		serverClass = null;
		classLoader = null;
		
		serverClass = getNewClassReference(name, defaultLocation);
		
		try 
		{
			serverInstance = serverClass.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		
		if(serverInstance != null)
		{
			try
			{
				Method startMethod = serverClass.getMethod("start", null);
				startMethod.invoke(serverInstance, null);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	    
	    System.out.println("Refreshed class!");
	}
	@Override
	public void finalize()
	{
		System.out.println("cleaned - AutoUpdateController");
	}
	
	public static Class<?> getNewClassReference(String name, String location)
	{
		try
		{
		    classLoader = new BasicURLClassLoader(location, parentClassLoader);
		    return classLoader.loadClass(name);
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
