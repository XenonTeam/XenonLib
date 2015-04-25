/**
 * 
 */
package com.xenonteam.xenonlib.util.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.xenonteam.xenonlib.util.java.FilenameFilters.AllFilter;

/**
 * @author tim4242
 * 
 * 
 *
 */
public class ZipUtil
{

	private static URLClassLoader m_sysloader = null;
	private static Method m_addURL = null;
	
	public static boolean unzip(File zip, File out, FilenameFilter filter)
	{
		byte[] buffer = new byte[1024];
		 
	     try{
	    	 
	    	
	    	if(!out.exists())
	    	{
	    		out.mkdir();
	    	}
	 
	    	ZipInputStream zis = new ZipInputStream(new FileInputStream(zip));
	    	ZipEntry ze;
	 
	    	while((ze = zis.getNextEntry()) != null){
	 
	    		if(!filter.accept(zip, ze.getName()))
	    			continue;
	    		
	    	   String fileName = ze.getName();
	           File newFile = new File(out + File.separator + fileName);
	 
	            new File(newFile.getParent()).mkdirs();
	            
	            
	            if(fileName.endsWith("/"))
	    			continue;
	    		
	            
	            FileOutputStream fos = new FileOutputStream(newFile);             
	 
	            int len;
	            
	            while ((len = zis.read(buffer)) > 0) 
	            {
	            	fos.write(buffer, 0, len);
	            }
	 
	            fos.close();   
	    	}
	 
	        zis.closeEntry();
	    	zis.close();
	 
	    	return true;
	 
	    }
	    catch(IOException ex)
	    {
	      ex.printStackTrace(); 
	      return false;
	    }
	      
	}
	
	public static boolean unzipAll(File in, File out)
	{
		return unzip(in, out, new AllFilter());
	}
	
	public static boolean unzipSingleFile(File zip, File out, String resFileName) throws Exception
	{
		byte[] buffer = new byte[1024];
		 
	     try{
	    	 
	    	
	    	if(!out.exists())
	    	{
	    		out.mkdir();
	    	}
	 
	    	ZipInputStream zis = new ZipInputStream(new FileInputStream(zip));
	    	ZipEntry ze;
	 
	    	while((ze = zis.getNextEntry()) != null)
	    	{
	 
	    		
	    		String fileName = ze.getName();
	    		
	    		if(fileName.contains("/"))
	    		{
	    			if(!(fileName.endsWith("/")))
	    			{
	    			fileName = ze.getName().split("/")[ze.getName().split("/").length - 1];
	    			}
	    		}
	    		
	    		
	    		if(!(resFileName.equals(fileName)))
	    			continue;
	    		
	    		
	    	   
	           File newFile = new File(new File(out.getName() + File.separator + fileName).toString());
	           
	 
	           out.mkdirs();
	            
	            
	            
	    		
	            
	            FileOutputStream fos = new FileOutputStream(newFile);             
	 
	            int len;
	            
	            while ((len = zis.read(buffer)) > 0) 
	            {
	            	fos.write(buffer, 0, len);
	            }
	 
	            fos.close();   
	    	}
	 
	        zis.closeEntry();
	    	zis.close();
	 
	    	return true;
	 
	    }
	    catch(IOException ex)
	    {
	      ex.printStackTrace(); 
	      return false;
	    }
	}
	
	public static void checkSysloader()
	{
		if(m_addURL == null || m_sysloader == null)
		{
			m_sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
			
			try
			{
				m_addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			m_addURL.setAccessible(true);
		}
	}
	
	public static boolean addToBuildpath(File loc) throws Exception
	{
		checkSysloader();
		
		if(loc != null)
		{
			
			m_addURL.invoke(m_sysloader, loc.toURI().toURL());
			
			return true;
		}
		
		return false;
	}
	
	public static Class<?> loadClass(String name) throws ClassNotFoundException
	{
		checkSysloader();
		
		return m_sysloader.loadClass(name);
	}
	
}
