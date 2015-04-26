package com.xenonteam.xenonlib.util;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.xenonteam.xenonlib.config.Refs;

/**
 * @author tim4242
 * @author philipas
 *
 */

public class GenericUtils {

	public static boolean isServerSide(World world) {
		if (world.isRemote == false) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isClientSide(World world) {
		if (world.isRemote == true) {
			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * DO NOT USE
	 */
	@Deprecated
	public static int PlayerLookdir(float yaw) {
		int dir = (MathHelper
				.floor_double((double) (yaw * 4.0F / 360.0F) + 0.5D) & 3) + 3;
		if (dir > 4)
			dir -= 4;
		switch (dir) {
		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 3;
		case 4:
			return 4;
		default:
			return 0;
		}
	}

	public static int getMetaForFacing(float yaw) {
		int meta = 2;

		switch (GenericUtils.PlayerLookdir(yaw)) {

		default:
			break;

		case 4:
			meta = 5;
			break;

		case 1:
			meta = 3;
			break;

		case 2:
			meta = 4;
			break;

		case 3:
			meta = 2;
			break;

		}

		return meta;
	}

	/**
	 * 
	 * This Method sends a single line to a specified player.
	 * 
	 * @param player The player to send to
	 * @param msg The message to send
	 */
	public static void sendChat(EntityPlayer player, String msg) {
		if (player.worldObj.isRemote) {
			player.addChatComponentMessage(new ChatComponentText(msg));
		}
	}
	
	/**
	 * 
	 * This Method sends multiple lines to a specified player.
	 * 
	 * @param player The player to send to
	 * @param msg The messages to send
	 */
	public static void sendChat(EntityPlayer player, String... mes)
	{
		for(String s : mes)
			sendChat(player, s);
	}
	
	/**
	 * no longer used only left for 1.7.10
	 */
	@Deprecated
	public static void registerItem(Item item) {
		GameRegistry.registerItem(item,
				item.getUnlocalizedName().replaceAll("item."+Refs.MOD_ID+":", ""));
	}

	/**
	 * no longer used only left for 1.7.10
	 */
	@Deprecated
	public static void registerBlock(Block block) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName()
				.replaceAll("tile."+Refs.MOD_ID+":", ""));
	}
	
	/**
	 * no longer used only left for 1.7.10
	 * might be re added
	 */
	@Deprecated
	public static void registerTileEntity(
			Class<? extends TileEntity> TileEntity, String id) {
		GameRegistry.registerTileEntity(TileEntity, Refs.MOD_ID + ":"
				+ id);
	}

	public static boolean isInt(String str) {
		try {
			int f = Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public static boolean isFloat(String str) {
		try {
			float f = Float.parseFloat(str);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public static boolean isDouble(String str) {
		try {
			double f = Double.parseDouble(str);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	public static EntityPlayer getClientPlayer() {
		return FMLClientHandler.instance().getClientPlayerEntity();
	}

	public static Item getFromItemReg(String name) {
		if (Item.itemRegistry.containsKey(name)) {
			return (Item) Item.itemRegistry.getObject(name);
		}

		return null;
	}

	public static Block getFromBlockReg(String name) {
		if (Item.itemRegistry.containsKey(name)) {
			return (Block) Block.blockRegistry.getObject(name);
		}

		return null;
	}

	public static boolean hasItemReg(String name) {
		return Item.itemRegistry.containsKey(name);
	}

	public static boolean hasBlockReg(String name) {
		return Block.blockRegistry.containsKey(name);
	}

	public static boolean hasReg(String name) {
		return (hasBlockReg(name) || hasItemReg(name));
	}

	public static Item getFromReg(String name) {
		if (hasItemReg(name)) {
			return getFromItemReg(name);
		}

		if (hasBlockReg(name)) {
			return Item.getItemFromBlock(getFromBlockReg(name));
		}

		return null;
	}

}
