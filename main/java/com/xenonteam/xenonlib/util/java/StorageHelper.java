/**
 * 
 */
package com.xenonteam.xenonlib.util.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class StorageHelper
{

	public static byte[] serialize(Object obj) throws IOException 
	{
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
	
	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException 
	{
	    ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is = new ObjectInputStream(in);
	    return is.readObject();
	}
	
	public static void writeSerialized(Object obj, File f) throws IOException
	{
		FileOutputStream out = new FileOutputStream(f);
		byte[] ser = serialize(obj);
		byte[] ba = new byte[ser.length + 1];
		ba[0] = new Integer(ser.length).byteValue(); 
		
		for(int i = 0; i < ser.length; i++)
		{
			ba[i + 1] = ser[i];
		}
		
		out.write(ba);
	}
	
	public static Object readSearialized(File f) throws ClassNotFoundException, IOException
	{
		byte[] lenB = new byte[1];
		
		FileInputStream in = new FileInputStream(f);
		
		in.read(lenB);
		
		int len = new Byte(lenB[0]).intValue();
		
		byte[] obj = new byte[len];
		
		in.read(obj);
		
		return deserialize(obj);
	}
	
}
