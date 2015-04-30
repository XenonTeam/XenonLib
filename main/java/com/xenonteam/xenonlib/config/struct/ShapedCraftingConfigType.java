/**
 * 
 */
package com.xenonteam.xenonlib.config.struct;

import com.xenonteam.xenonlib.util.XUtils;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class ShapedCraftingConfigType implements IConfigType<ShapedRecipes>
{

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.config.struct.IConfigType#get(java.lang.String, java.lang.String, java.lang.Object, java.lang.String)
	 */
	@Override
	public ShapedRecipes get(Configuration config, String category, String key, String defaultValue, String comment)
	{
		
		
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.config.struct.IConfigType#get(java.lang.String, java.lang.String, java.lang.Object[], java.lang.String)
	 */
	@Override
	public ShapedRecipes get(Configuration config, String category, String key, String[] defaultValues, String comment)
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.config.struct.IConfigType#parse(java.lang.String)
	 */
	@Override
	public ShapedRecipes parse(String value)
	{
		value.replaceAll("[\\s]", "");
		String[] lines = value.split(",");
		
		ItemStack[] input = new ItemStack[3 * 3];
		ItemStack output;
		
		if(lines.length != 4)
		{
			return null;
		}
		
		if(XUtils.getFromBlockReg(lines[3]) != null)
		{
			output = new ItemStack(XUtils.getFromBlockReg(lines[3]));
		}
		else if(XUtils.getFromItemReg(lines[3]) != null)
		{
			output = new ItemStack(XUtils.getFromItemReg(lines[3]));
		}
		else
		{
			return null;
		}
		
		for(int i = 0; i < 3; i++)
		{
			String line = lines[3];
			
			String[] items = line.split("|");
			
			if(items.length != 3)
			{
				return null;
			}
			
			for(int j = 0; j < 3; j++)
			{
				ItemStack s = null;
				
				if(XUtils.getFromBlockReg(items[j]) != null)
				{
					s = new ItemStack(XUtils.getFromBlockReg(items[j]));
				}
				else if(XUtils.getFromItemReg(items[j]) != null)
				{
					s = new ItemStack(XUtils.getFromItemReg(items[j]));
				}
				else
				{
					return null;
				}
				
				input[i * 3 + j] = s;
			}
		}
		
		return new ShapedRecipes(3, 3, input, output);
	}

}
