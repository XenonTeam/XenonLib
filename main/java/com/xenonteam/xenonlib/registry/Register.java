/**
 * 
 */
package com.xenonteam.xenonlib.registry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;

/**
 * @author tim4242
 * @author philipas
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Register
{

	String modid();
	String unlocName();
	
	String itemBlock() default "default";
	
	
	Class<?extends TileEntity> tileenity() default DefaultTE.class;
	public static final class DefaultTE extends TileEntity{}
	
	
	
}
