/**
 * 
 */
package com.xenonteam.xenonlib.registry;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
					
					GameRegistry.registerBlock(b, modid + ":" + unlocName);
					
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
					
					System.err.println(b.getUnlocalizedName());
					System.err.println(modid + ":" + unlocName);
					
					GameRegistry.registerItem(b, modid + ":" + unlocName);
					
					continue;
				}
				
				if(f.getType().isAssignableFrom(TileEntity.class))
				{
					GameRegistry.registerTileEntity((Class<? extends TileEntity>) f.getDeclaringClass(), modid + ":" + unlocName);
					
					continue;
				}
			}
		
	}
	
}
