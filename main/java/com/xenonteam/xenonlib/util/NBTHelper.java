/**
 * 
 */
package com.xenonteam.xenonlib.util;

import java.io.IOException;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.util.Constants.NBT;

import com.xenonteam.xenonlib.util.java.StorageHelper;

/**
 * @author tim4242
 * @author philipas
 *
 *         This is a basic NBT helper providing useful methods to manipulate NBT
 *         and form more complex NBT structures
 *
 */
public class NBTHelper
{
	/**
	 * Returned by {@link net.minecraft.nbt.NBTBase#getId() NBTBase.getID()} in
	 * {@link net.minecraft.nbt.NBTTagByte NBTTagByte}
	 */
	public static final int BYTE_ID = new NBTTagByte((byte) 0).getId();
	/**
	 * Returned by {@link net.minecraft.nbt.NBTBase#getId() NBTBase.getID()} in
	 * {@link net.minecraft.nbt.NBTTagByteArray NBTTagByteArray}
	 */
	public static final int BYTE_ID_A = new NBTTagByteArray(new byte[0]).getId();

	/**
	 * Returned by {@link net.minecraft.nbt.NBTBase#getId() NBTBase.getID()} in
	 * {@link net.minecraft.nbt.NBTTagInt NBTTagInt}
	 */
	public static final int INT_ID = new NBTTagInt(0).getId();
	/**
	 * Returned by {@link net.minecraft.nbt.NBTBase#getId() NBTBase.getID()} in
	 * {@link net.minecraft.nbt.NBTTagIntArray NBTTagIntArray}
	 */
	public static final int INT_ID_A = new NBTTagIntArray(new int[0]).getId();

	/**
	 * Returned by {@link net.minecraft.nbt.NBTBase#getId() NBTBase.getID()} in
	 * {@link net.minecraft.nbt.NBTTagShort NBTTagShort}
	 */
	public static final int SHORT_ID = new NBTTagShort((short) 0).getId();

	/**
	 * Returned by {@link net.minecraft.nbt.NBTBase#getId() NBTBase.getID()} in
	 * {@link net.minecraft.nbt.NBTTagFloat NBTTagFloat}
	 */
	public static final int FLOAT_ID = new NBTTagFloat(0).getId();

	/**
	 * Returned by {@link net.minecraft.nbt.NBTBase#getId() NBTBase.getID()} in
	 * {@link net.minecraft.nbt.NBTTagDouble NBTTagDouble}
	 */
	public static final int DOUBLE_ID = new NBTTagDouble(0).getId();

	/**
	 * Returned by {@link net.minecraft.nbt.NBTBase#getId() NBTBase.getID()} in
	 * {@link net.minecraft.nbt.NBTTagLong NBTTagLong}
	 */
	public static final int LONG_ID = new NBTTagLong(0).getId();

	/**
	 * Returned by {@link net.minecraft.nbt.NBTBase#getId() NBTBase.getID()} in
	 * {@link net.minecraft.nbt.NBTTagString NBTTagString}
	 */
	public static final int STRING_ID = new NBTTagString("").getId();

	/**
	 * Returned by {@link net.minecraft.nbt.NBTBase#getId() NBTBase.getID()} in
	 * {@link net.minecraft.nbt.NBTTagList NBTTagList}
	 */
	public static final int LIST_ID = new NBTTagList().getId();

	/**
	 * Returned by {@link net.minecraft.nbt.NBTBase#getId() NBTBase.getID()} in
	 * {@link net.minecraft.nbt.NBTTagCompound NBTTagCompound}
	 */
	public static final int COMP_ID = new NBTTagCompound().getId();

	/** The end of NBT */
	public static final int END_ID = 0;

	public static String getIdAsString(byte id)
	{
		switch (id)
		{
		case NBT.TAG_BYTE:
			return "Byte";
		case NBT.TAG_BYTE_ARRAY:
			return "Byte Array";
		case NBT.TAG_INT:
			return "Int";
		case NBT.TAG_INT_ARRAY:
			return "Int Array";
		case NBT.TAG_SHORT:
			return "Short";
		case NBT.TAG_FLOAT:
			return "Float";
		case NBT.TAG_DOUBLE:
			return "Double";
		case NBT.TAG_LONG:
			return "Long";
		case NBT.TAG_STRING:
			return "String";
		case NBT.TAG_LIST:
			return "List";
		case NBT.TAG_COMPOUND:
			return "Compound";
		case NBT.TAG_END:
			return "End";
		default:
			return "NULL/" + id;
		}
	}

	/**
	 * Encodes an {@link java.lang.Object Object} into a
	 * {@link net.minecraft.nbt.NBTTagByteArray NBTTagByteArray} using
	 * {@link com.xenonteam.xenonlib.util.java.StorageHelper#serialize(java.lang.Object)
	 * StorageHelper.serialize(Object)}
	 * 
	 * @param obj
	 *            The {@link java.lang.Object Object}
	 * @return A {@link net.minecraft.nbt.NBTTagByteArray NBTTagByteArray}
	 * @throws IOException
	 */
	public static NBTTagByteArray ObjectToNBT(Object obj) throws IOException
	{
		return new NBTTagByteArray(StorageHelper.serialize(obj));
	}

	/**
	 * 
	 * Decodes an {@link net.minecraft.nbt.NBTTagByteArray NBTTagByteArray} to a
	 * {@link java.lang.Object Object} using
	 * {@link com.xenonteam.xenonlib.util.java.StorageHelper#deserialize(byte[])
	 * StorageHelper.deserialize(byte[])}
	 * 
	 * @param b
	 *            A {@link net.minecraft.nbt.NBTTagByteArray NBTTagByteArray}
	 * @return A {@link java.lang.Object Object}
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Object NBTToObject(NBTTagByteArray b) throws ClassNotFoundException, IOException
	{
		return StorageHelper.deserialize(b.getByteArray());
	}

	/**
	 * Encodes a {@link net.minecraft.util.BlockPos BlockPos} into a
	 * {@link net.minecraft.nbt.NBTTagCompound NBTTagCompound}
	 * 
	 * @param pos
	 *            A {@link net.minecraft.util.BlockPos BlockPos}
	 * @return A {@link net.minecraft.nbt.NBTTagCompound NBTTagCompound}
	 */
	public static NBTTagCompound BlockPosToNBT(BlockPos pos)
	{
		NBTTagCompound comp = new NBTTagCompound();

		comp.setInteger("blockpos_x", pos.getX());
		comp.setInteger("blockpos_y", pos.getY());
		comp.setInteger("blockpos_z", pos.getZ());

		return comp;
	}

	/**
	 * Decodes a {@link net.minecraft.nbt.NBTTagCompound NBTTagCompound} to a
	 * {@link net.minecraft.util.BlockPos BlockPos}
	 * 
	 * @param comp
	 *            A {@link net.minecraft.nbt.NBTTagCompound NBTTagCompound}
	 * @return A {@link net.minecraft.util.BlockPos BlockPos}
	 */
	public static BlockPos NBTToBlockPos(NBTTagCompound comp)
	{
		if (comp.hasKey("blockpos_x") && comp.hasKey("blockpos_y") && comp.hasKey("blockpos_z"))
		{
			return new BlockPos(comp.getInteger("blockpos_x"), comp.getInteger("blockpos_y"), comp.getInteger("blockpos_z"));
		}

		return null;
	}

	/**
	 * Encodes an Inventory into a {@link net.minecraft.nbt.NBTTagCompound
	 * NBTTagCompound}
	 * 
	 * @param inv
	 *            A Inventory
	 * @return An {@link net.minecraft.nbt.NBTTagCompound NBTTagCompound}
	 */
	public static NBTTagCompound InventoryToNBT(ItemStack[] inv)
	{

		NBTTagCompound comp = new NBTTagCompound();

		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < inv.length; i++)
		{
			if (inv[i] != null)
			{
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				inv[i].writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		comp.setTag("Items", nbttaglist);

		return comp;
	}

	/**
	 * Decodes a {@link net.minecraft.nbt.NBTTagCompound NBTTagCompound} into an
	 * Inventory
	 * 
	 * @param comp
	 *            A {@link net.minecraft.nbt.NBTTagCompound NBTTagCompound}
	 * @return An Inventory
	 */
	public static ItemStack[] NBTToInventory(NBTTagCompound comp)
	{
		NBTTagList nbttaglist = comp.getTagList("Items", COMP_ID);
		ItemStack[] inv = new ItemStack[nbttaglist.tagCount()];

		for (int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			byte byte0 = nbttagcompound.getByte("Slot");

			if (byte0 >= 0 && byte0 < inv.length)
			{
				inv[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound);
			}
		}

		return inv;
	}

	/**
	 * Returns the value of a {@link net.minecraft.nbt.NBTBase NBTBase} as the
	 * correct type
	 * 
	 * @param nbt
	 *            A {@link net.minecraft.nbt.NBTBase NBTBase}
	 * @return An {@link java.lang.Object Object}
	 */
	public static Object getValue(NBTBase nbt)
	{
		if (nbt instanceof NBTTagInt)
		{
			return ((NBTTagInt) nbt).getInt();
		} else if (nbt instanceof NBTTagByte)
		{
			return ((NBTTagByte) nbt).getByte();
		} else if (nbt instanceof NBTTagShort)
		{
			return ((NBTTagShort) nbt).getShort();
		} else if (nbt instanceof NBTTagLong)
		{
			return ((NBTTagLong) nbt).getLong();
		} else if (nbt instanceof NBTTagFloat)
		{
			return ((NBTTagFloat) nbt).getFloat();
		} else if (nbt instanceof NBTTagDouble)
		{
			return ((NBTTagDouble) nbt).getDouble();
		} else if (nbt instanceof NBTTagIntArray)
		{
			return ((NBTTagIntArray) nbt).getIntArray();
		} else if (nbt instanceof NBTTagString)
		{
			return ((NBTTagString) nbt).getString();
		} else if (nbt instanceof NBTTagByteArray)
		{
			return ((NBTTagByteArray) nbt).getByteArray();
		} else if (nbt instanceof NBTTagCompound)
		{
			return nbt;
		} else if (nbt instanceof NBTBase)
		{
			return nbt;
		} else
		{
			return null;
		}
	}

}
