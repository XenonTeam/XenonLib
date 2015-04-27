/**
 * 
 */
package com.xenonteam.xenonlib.api;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;

import com.xenonteam.xenonlib.api.main.IXenonMod;
import com.xenonteam.xenonlib.main.XenonLib;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class XenonAPI
{

	public static void addXenonMod(IXenonMod mod)
	{
		if(!Loader.instance().hasReachedState(LoaderState.POSTINITIALIZATION))
		{
			XenonLib.addXenonMod(mod);
		}
	}
	
}
