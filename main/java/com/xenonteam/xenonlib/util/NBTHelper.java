/**
 * 
 */
package com.xenonteam.xenonlib.util;

import java.io.IOException;

import com.xenonteam.xenonlib.util.java.StorageHelper;

import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;

/**
 * @author tim4242
 * @author philipas
 *
 * This is a basic NBT helper providing useful methods to manipulate NBT and form more complex NBT structures
 *
 */
public class NBTHelper
{

	public static NBTTagByteArray ObjectToNBT(Object obj) throws IOException
	{
		return new NBTTagByteArray(StorageHelper.serialize(obj));
	}
	
	public static Object NBTToObject(NBTTagByteArray b) throws ClassNotFoundException, IOException
	{
		return StorageHelper.deserialize(b.getByteArray());
	}
	
}
