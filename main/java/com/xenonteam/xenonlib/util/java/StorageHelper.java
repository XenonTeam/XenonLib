/**
 * 
 */
package com.xenonteam.xenonlib.util.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
	
	/**
	 * Serializes an {@link java.lang.Object Object} to a byte[]
	 * 
	 * @param obj The {@link java.lang.Object Object}
	 * @return A byte[] representation of the {@link java.lang.Object Object}
	 * @throws IOException
	 */
	public static byte[] serialize(Object obj) throws IOException 
	{
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    
	    byte[] b = out.toByteArray();
	    
	    out.close();
	    os.close();
	    
	    return b;
	}
	
	/**
	 * Deserializes {@link java.lang.Object Objects} serialized by {@link com.xenonteam.xenonlib.util.java.StorageHelper#serialize(java.lang.Object) serialize(Object)}  
	 * 
	 * @param data The Object as a byte[]
	 * @return an
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException 
	{
	    ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is = new ObjectInputStream(in);
	    
	    Object o = is.readObject();
	    
	    in.close();
	    is.close();
	    
	    return o;
	}
	
	/**
	 * Writes an {@link java.lang.Object Object} to the specified {@link java.io.File File}
	 * 
	 * @param obj The Object to write
	 * @param f The {@link java.io.File File}
	 * @throws IOException
	 */
	public static void writeSerialized(Object obj, File f) throws IOException
	{
		File temp = f;
		
		if(!f.toString().contains("."))
		{
			temp = new File(f + ".jobj");
		}
		
		FileOutputStream out = new FileOutputStream(temp);
		byte[] ser = serialize(obj);
		byte[] ba = new byte[ser.length + 1];
		ba[0] = new Integer(ser.length).byteValue(); 
		
		for(int i = 0; i < ser.length; i++)
		{
			ba[i + 1] = ser[i];
		}
		
		out.write(ba);
		
		out.close();
	}
	
	/**
	 * Reads the {@link java.io.File File} created by {@link com.xenonteam.xenonlib.util.java.StorageHelper#writeSerialized(java.lang.Object, java.io.File) writeSerialized()}
	 * 
	 * @param f The {@link java.io.File File}
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Object readSearialized(File f) throws ClassNotFoundException, IOException
	{
		File temp = f;
		
		if(!f.toString().contains("."))
		{
			temp = new File(f + ".jobj");
		}	
		
		byte[] lenB = new byte[1];
		
		FileInputStream in = new FileInputStream(temp);
		
		in.read(lenB);
		
		int len = new Byte(lenB[0]).intValue();
		
		byte[] obj = new byte[len];
		
		in.read(obj);
		
		in.close();
		
		return deserialize(obj);
	}
	
}
