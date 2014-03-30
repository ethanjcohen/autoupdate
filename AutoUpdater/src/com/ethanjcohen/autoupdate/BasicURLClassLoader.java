package com.ethanjcohen.autoupdate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class BasicURLClassLoader extends ClassLoader 
{
	private String sourceFolder = null;
	private UUID id = null;
	
	public BasicURLClassLoader(String sourceFolder, ClassLoader parent) 
	{
		super(parent);
		this.sourceFolder = sourceFolder;
		id = UUID.randomUUID();
    }  
	
	public Class<?> findClass(String name)
	{
		System.out.println("findClass - Checking for class: " + name);
		
		Class<?> c =  null;
        
		byte[] classData = getClassBytes(name);
		
		if(classData == null)
			return null;
		
        c = defineClass(name, classData, 0, classData.length);

        return c;

	}
	public byte[] getClassBytes(String name)
	{
		try 
		{
            String url = sourceFolder + name.replace('.', '\\') + ".class";
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while(data != -1)
            {
                buffer.write(data);
                data = input.read();
            }

            input.close();

            byte[] classData = buffer.toByteArray();

            System.out.println("findClass - Defining class: " + name + " - " + id);
            
            return classData;
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
		
		return null;
	}
}
