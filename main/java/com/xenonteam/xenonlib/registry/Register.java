/**
 * 
 */
package com.xenonteam.xenonlib.registry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author tim4242
 * @author philipas
 *
 * <br>
 * <br>
 * 
 * This {@link java.lang.annotation.Annotation Annotation} is used to automatically register everything from <br> 
 * {@link net.minecraft.block.Block Blocks} to {@link net.minecraft.tileentity.TileEntity TileEntitys}.
 * It should be located in a {@link java.lang.Class Class} that is returned by {@link com.xenonteam.xenonlib.main.IXenonMod#getRegisterClasses() IXenonMod.getRegisterClasses()} because otherwise it won't be called
 *
 * <br>
 * <br>
 * 
 * This can be put above a static Field of one of the following types:
 * <br>
 * <ol>
 * 	<li> {@link net.minecraft.block.Block Block} </li>
 * 	<li> {@link net.minecraft.item.Item Item} </li>
 * 	<li> {@link net.minecraft.tileentity.TileEntity TileEntity} </li>
 * 	<li> {@link com.xenonteam.xenonlib.common.networking.packet.MessageBase MessageBase}
 * </ol>
 * 
 * 
 * The content of the Field will be registered in an appropriate way.<br>
 * But if the Field is equal to null it will be created with default parameters
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Register
{
	/**
	 * 
	 * @return
	 */
	String modid();
	String unlocName();
	
	String itemBlock() default DefaultBI;
	public static final String DefaultBI = "default";
	
	Class<?extends TileEntity> tileenity() default DefaultTE.class;
	public static final class DefaultTE extends TileEntity{}
	
	Side side() default Side.SERVER;
	
	
	
}
