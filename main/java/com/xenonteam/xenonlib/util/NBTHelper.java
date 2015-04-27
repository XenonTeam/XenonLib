/**
 * 
 */
package com.xenonteam.xenonlib.util;

import java.io.IOException;

import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

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
	
	public static NBTTagCompound BlockPosToNBT(BlockPos pos)
	{
		NBTTagCompound comp = new NBTTagCompound();
		
		comp.setInteger("blockpos_x", pos.getX());
		comp.setInteger("blockpos_y", pos.getY());
		comp.setInteger("blockpos_z", pos.getZ());
		
		return comp;
	}
	
	public static BlockPos NBTToBlockPos(NBTTagCompound comp)
	{
		if(comp.hasKey("blockpos_x") && comp.hasKey("blockpos_y") && comp.hasKey("blockpos_z"))
		{
			return new BlockPos(comp.getInteger("blockpos_x"), comp.getInteger("blockpos_y"), comp.getInteger("blockpos_z"));
		}
		
		return null;
	}
	
}
