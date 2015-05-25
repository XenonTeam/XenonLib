/**
 * 
 */
package com.xenonteam.xenonlib.registry;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.xenonteam.xenonlib.blocks.IXenonIBP;
import com.xenonteam.xenonlib.blocks.IXenonTEP;
import com.xenonteam.xenonlib.common.networking.DescriptionHandler.XSide;
import com.xenonteam.xenonlib.common.networking.packet.NetworkHandler;
import com.xenonteam.xenonlib.util.Log;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class RegistryHelper
{

	public static void registerObjects(Class<?> c)
	{

		List<Field> toRegister = Arrays.asList(c.getDeclaredFields());

		Register cAn = c.getAnnotation(Register.class);

		if (cAn != null)
		{

			if (IMessageHandler.class.isAssignableFrom(c))
			{
				XSide side = cAn.side();

				NetworkHandler.registerMessage0((Class<? extends IMessageHandler<? extends IMessage, ? extends IMessage>>) c, side);
				Log.debug("Regsitered message");
			}
			
			if (TileEntity.class.isAssignableFrom(c))
			{
				GameRegistry.registerTileEntity((Class<? extends TileEntity>) c, cAn.modid() + "_" + cAn.unlocName());
				Log.debug("Regsitered tileentity as: " + cAn.modid() + "_" + cAn.unlocName());
			}

		}

		String modid;
		String unlocName;

		for (Field f : toRegister)
		{
			f.setAccessible(true);

			Annotation an = f.getAnnotation(Register.class);

			if (an == null)
				continue;

			Register reg = (Register) an;

			modid = reg.modid();
			unlocName = reg.unlocName();

			if (Block.class.isAssignableFrom(f.getType()))
			{

				Block b = null;
				try
				{
					b = (Block) f.get(b);
				} catch (Exception e)
				{
					e.printStackTrace();
				}

				if (b != null)
				{

				} else
				{
					try
					{
						b = (Block) f.getType().newInstance();
						f.set(null, b);
					} catch (InstantiationException e)
					{
						e.printStackTrace();
					} catch (IllegalAccessException e)
					{
						e.printStackTrace();
					}

				}

				if (b instanceof IXenonIBP)
				{
					if (!reg.itemBlock().equalsIgnoreCase(Register.DefaultSTRING))
					{
						((IXenonIBP) b).setItemBlock((ItemBlock) GameRegistry.findItem(reg.itemBlock().split(":")[0], reg.itemBlock().split(":")[1]));
					} else
					{
						((IXenonIBP) b).setItemBlock(null);
					}
				}
				
				if(b instanceof IXenonTEP)
				{
					if (reg.tileenity() != Register.DefaultTE.class)
					{
						((IXenonTEP) b).setTileEntity(reg.tileenity());
					} else
					{
						((IXenonTEP) b).setTileEntity(null);
					}
				}

				b.setUnlocalizedName(modid + "." + unlocName);

				GameRegistry.registerBlock(b, modid + "_" + unlocName);
				Log.debug("Regsitered block as: " + modid + "_" + unlocName);
				continue;

			}

			if (Item.class.isAssignableFrom(f.getType()))
			{
				Item b = null;
				try
				{
					b = (Item) f.get(b);
				} catch (Exception e)
				{
					e.printStackTrace();
				}

				if (b != null)
				{

				} else
				{
					try
					{
						b = (Item) f.getType().newInstance();
						f.set(null, b);
					} catch (InstantiationException e)
					{
						e.printStackTrace();
					} catch (IllegalAccessException e)
					{
						e.printStackTrace();
					}
				}

				b.setUnlocalizedName(modid + "." + unlocName);

				GameRegistry.registerItem(b, modid + "_" + unlocName);
				Log.debug("Regsitered item as: " + modid + "_" + unlocName);
				continue;
			}

			if (TileEntity.class.isAssignableFrom(f.getType()))
			{
				GameRegistry.registerTileEntity((Class<? extends TileEntity>) f.getDeclaringClass(), modid + "_" + unlocName);
				Log.debug("Regsitered tileentity as: " + cAn.modid() + "_" + cAn.unlocName());
				continue;
			}
		}

	}

}
