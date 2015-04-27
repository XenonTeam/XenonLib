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
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import com.xenonteam.xenonlib.blocks.IXenonBlock;
import com.xenonteam.xenonlib.common.networking.packet.NetworkHandler;

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
		
		
		
		String modid;
		String unlocName;
		
		for(Field f : toRegister)
		{
			f.setAccessible(true);
			
			Annotation an = f.getAnnotation(Register.class);
			
			
			if(an == null)
				continue;
			
			
			
		
				Register reg = (Register) an;
				
				modid = reg.modid();
				unlocName = reg.unlocName();
				
				
				
				if(f.getType().isAssignableFrom(Block.class))
				{
					Block b = null;
					try
					{
						b = (Block) f.get(b);
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
					if(b != null)
					{
						
					}
					else
					{
						try
						{
							b = (Block) f.getType().newInstance();
						} 
						catch (InstantiationException e)
						{
							e.printStackTrace();
						} 
						catch (IllegalAccessException e)
						{
							e.printStackTrace();
						}
					}
					
					
					if(b instanceof IXenonBlock)
					{
						if(reg.tileenity() != Register.DefaultTE.class)
						{
							((IXenonBlock) b).setTileEntity(reg.tileenity());
						}
						else
						{
							((IXenonBlock) b).setTileEntity(null);
						}
						
						if(reg.itemBlock() != Register.DefaultSTRING)
						{
							((IXenonBlock) b).setItemBlock((ItemBlock) GameRegistry.findItem(reg.itemBlock().split(":")[0], reg.itemBlock().split(":")[1]));
						}
						else
						{
							((IXenonBlock) b).setItemBlock(null);
						}
					}
					
					b.setUnlocalizedName(modid + "." + unlocName);
					
					GameRegistry.registerBlock(b, modid + "_" + unlocName);
					
					continue;
					
				}
				
				if(f.getType().isAssignableFrom(Item.class))
				{
					Item b = null;
					try
					{
						b = (Item) f.get(b);
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
					if(b != null)
					{
						
					}
					else
					{
						try
						{
							b = (Item) f.getType().newInstance();
						} 
						catch (InstantiationException e)
						{
							e.printStackTrace();
						} 
						catch (IllegalAccessException e)
						{
							e.printStackTrace();
						}
					}
					
					b.setUnlocalizedName(modid + "." + unlocName);
					
					GameRegistry.registerItem(b, modid + "_" + unlocName);
					
					continue;
				}
				
				if(f.getType().isAssignableFrom(TileEntity.class))
				{
					GameRegistry.registerTileEntity((Class<? extends TileEntity>) f.getDeclaringClass(), modid + "_" + unlocName);
					
					continue;
				}
				
				if(f.getType().isAssignableFrom(IMessageHandler.class))
				{
					Side s = reg.side();
					
					NetworkHandler.registerMessage0((Class<? extends IMessageHandler<? extends IMessage ,? extends IMessage>>) f.getType(), s);
					
					continue;
				}
			}
		
	}
	
}
