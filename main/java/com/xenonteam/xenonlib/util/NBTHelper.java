/**
 * 
 */
package com.xenonteam.xenonlib.util;

import java.io.IOException;

import net.minecraft.nbt.NBTTagByteArray;

import com.xenonteam.xenonlib.util.java.StorageHelper;

/**
 * @author tim4242
 * @author philipas
 *
 * This is a basic NBT helper providing useful methods to manipulate NBT and form more complex NBT structures
 *
 */
public class NBTHelper
{
	/**
	 * Encodes an {@link java.lang.Object Object} into a {@link net.minecraft.nbt.NBTTagByteArray NBTTagByteArray} using {@link com.xenonteam.xenonlib.util.java.StorageHelper#serialize(java.lang.Object) StorageHelper.serialize(Object)}
	 * 
	 * @param obj The {@link java.lang.Object Object}
	 * @return A {@link net.minecraft.nbt.NBTTagByteArray NBTTagByteArray}
	 * @throws IOException
	 */
	public static NBTTagByteArray ObjectToNBT(Object obj) throws IOException
	{
		return new NBTTagByteArray(StorageHelper.serialize(obj));
	}
	
	/**
	 * 
	 * Decodes an {@link net.minecraft.nbt.NBTTagByteArray NBTTagByteArray} to a {@link java.lang.Object Object} using {@link com.xenonteam.xenonlib.util.java.StorageHelper#deserialize(byte[]) StorageHelper.deserialize(byte[])}
	 * 
	 * @param b A {@link net.minecraft.nbt.NBTTagByteArray NBTTagByteArray}
	 * @return A {@link java.lang.Object Object}
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Object NBTToObject(NBTTagByteArray b) throws ClassNotFoundException, IOException
	{
		return StorageHelper.deserialize(b.getByteArray());
	}
	
}
