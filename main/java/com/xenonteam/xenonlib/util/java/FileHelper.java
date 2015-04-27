/**
 * 
 */
package com.xenonteam.xenonlib.util.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tim4242
 * 
 * 
 *
 */
public class FileHelper
{
	
	public static BufferedReader getReader(File f) throws IOException
	{
		return new BufferedReader(new FileReader(f));
	}
	
	public static BufferedWriter getWriter(File f) throws IOException
	{
		return new BufferedWriter(new FileWriter(f));
	}
	
	public static FileOutputStream getOutStream(File f) throws FileNotFoundException
	{
		return new FileOutputStream(f);
	}
	
	public static FileInputStream getInStream(File f) throws FileNotFoundException
	{
		return new FileInputStream(f);
	}
	
	public static List<String> readFile(File f) throws IOException
	{
		BufferedReader reader = getReader(f);
		
		List<String> lines = new ArrayList<String>();
		
		String line;
		
		while((line = reader.readLine()) != null)
		{
			lines.add(line);
		}
		
		reader.close();
		
		return lines;
	}
	
	public static void writeToFile(File f, List<String> lines) throws IOException
	{
		BufferedWriter writer = getWriter(f);
		
		for(String line : lines)
		{
			writer.write(line);
			writer.newLine();
		}
		
		writer.close();
	}
	
}
