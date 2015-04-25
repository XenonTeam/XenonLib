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

	public void registerObjects(Class<?> c)
	{
		
		List<Field> toRegister = Arrays.asList(c.getDeclaredFields());
		
		String modid;
		String unlocName;
		
		for(Field f : toRegister)
		{
			f.setAccessible(true);
			if(f.isAnnotationPresent(Register.class))
			{
				modid = f.getAnnotation(Register.class).modid();
				unlocName = f.getAnnotation(Register.class).unlocName();
				
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
					
					return;
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
					
					return;
				}
				
				if(f.getDeclaringClass().isAssignableFrom(TileEntity.class))
				{
					GameRegistry.registerTileEntity((Class<? extends TileEntity>) f.getDeclaringClass(), modid + ":" + unlocName);
					
					return;
				}
			}
		}
		
		
	}
	
}
