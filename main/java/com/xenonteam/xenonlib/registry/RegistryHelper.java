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
			
			System.out.println(Arrays.asList(f.getAnnotations()));
			
			if(an == null)
				continue;
			
			
			
		
				Register reg = (Register) an;
				
				modid = reg.modid();
				unlocName = reg.unlocName();
				
				
				
				if(f.getDeclaringClass().isAssignableFrom(Block.class))
				{
					Block b = null;
					try
					{
						f.get(b);
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
					GameRegistry.registerBlock(b, modid + ":" + unlocName);
					
					continue;
				}
				
				if(f.getDeclaringClass().isAssignableFrom(Item.class))
				{
					Item b = null;
					try
					{
						f.get(b);
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
					GameRegistry.registerItem(b, modid + ":" + unlocName);
					
					continue;
				}
				
				if(f.getDeclaringClass().isAssignableFrom(TileEntity.class))
				{
					GameRegistry.registerTileEntity((Class<? extends TileEntity>) f.getDeclaringClass(), modid + ":" + unlocName);
					
					continue;
				}
			}
		
	}
	
}
