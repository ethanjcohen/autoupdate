package com.ethanjcohen.infoserver;

public class Server
{
	public boolean start()
	{
		System.out.println("Starting server...");
		System.out.println("-------------------\n| Server running? |\n-------------------");
		
		Test test = new Test();
		System.out.println("Test: " + test.getInfo());
		test = null;
		return true; 
	}
	
	public boolean stop() 
	{
		System.out.println("Stopping server...");
		System.out.println("-------------------\n| Server stopped! |\n-------------------");
		return true;
	}
}
