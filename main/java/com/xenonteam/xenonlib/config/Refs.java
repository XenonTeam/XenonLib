/**
 * 
 */
package com.xenonteam.xenonlib.config;

import static com.xenonteam.xenonlib.config.Refs.MOD_ID;
import static com.xenonteam.xenonlib.config.Refs.MOD_VERSION;

import java.io.File;

import com.xenonteam.xenonlib.api.interfaces.IXenonMod.ModInfo;


/**
 * @author tim4242
 * @author philipas
 *
 *
 * <br>
 * <br>
 *
 * This is a final class containing the basic XenonLib information
 */
public final class Refs
{

	/**
	 * The name of the XenonLib.
	 */
	public static final String MOD_NAME = "Xenon Lib";
	
	/**
	 * The modid of the XenonLib.
	 */
	public static final String MOD_ID = "xenon_lib";
	
	/**
	 * The version of the XenonLib.
	 */
	public static final String MOD_VERSION = "0.0.2";
	
	/**
	 * The dependecies of the XenonLib.
	 */
	public static final String MOD_DEPS = "";
	
	
	/**
	 * The authors of the XenonLib.
	 */
	public static final String[] MOD_AUTHORS = new String[] {"philipas", "tim4242"};
	
	public static final ModInfo MOD_INFO = new ModInfo(MOD_ID, MOD_VERSION);
	
	public static File CONFIG_PATH;
	
	/**
	 * If the XenonLib is in debug mode.
	 */
	public static boolean DEBUG = false;
	
	
	
}
