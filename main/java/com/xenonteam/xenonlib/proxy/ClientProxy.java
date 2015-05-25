/**
 * 
 */
package com.xenonteam.xenonlib.proxy;

import com.xenonteam.xenonlib.client.render.SpriteSheet;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class ClientProxy implements IXenonProxy
{

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.proxy.IXenonProxy#preInit(net.minecraftforge.fml.common.event.FMLPreInitializationEvent)
	 */
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.proxy.IXenonProxy#init(net.minecraftforge.fml.common.event.FMLInitializationEvent)
	 */
	@Override
	public void init(FMLInitializationEvent event)
	{
		SpriteSheet.addSpriteSheet("test", new ResourceLocation("xenon_lib:textures/gui/sprites/test.png"));
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.proxy.IXenonProxy#postInit(net.minecraftforge.fml.common.event.FMLPostInitializationEvent)
	 */
	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		// TODO Auto-generated method stub
		
	}

}
