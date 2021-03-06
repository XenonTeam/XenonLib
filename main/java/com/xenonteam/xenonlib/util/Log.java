package com.xenonteam.xenonlib.util;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.xenonteam.xenonlib.config.Refs;

/**
 * @author tim4242
 * @author philipas
 * 
 * <br>
 * <br>
 * 
 * This is a general Logging class. <br>
 * Is's a static wrapper for the <br>
 * {@link org.apache.logging.log4j.Logger Logger} for this mod 
 */
public final class Log {
	
	private static final Logger m_log = LogManager.getLogger(Refs.MOD_NAME);
	
	/**
	 * A wrapper method for {@link org.apache.logging.log4j.Logger#info(java.lang.Object) Logger.info())} <br>
	 * every new Object gets displayed on a new line.
	 * 
	 * @param mes The messages to be displayed.
	 */
	public static final void info(Object... mes)
	{
		for(Object s : mes)
		m_log.info(s);
	}
	
	/**
	 * A wrapper method for {@link org.apache.logging.log4j.Logger#warn(java.lang.Object) Logger.info())} <br>
	 * every new Object gets displayed on a new line.
	 * 
	 * @param mes The messages to be displayed.
	 */
	public static final void warn(Object... mes)
	{
		for(Object s : mes)
		m_log.warn(s);
	}
	
	/**
	 * A wrapper method for {@link org.apache.logging.log4j.Logger#error(java.lang.Object) Logger.info())} <br>
	 * every new Object gets displayed on a new line.
	 * 
	 * @param mes The messages to be displayed.
	 */
	public static final void error(Object... mes)
	{
		for(Object s : mes)
		m_log.error(s);
	}
	
	/**
	 * A wrapper method for {@link org.apache.logging.log4j.Logger#info(java.lang.Object) Logger.info())} <br>
	 * every new Object gets displayed on a new line. <br>
	 * This only gets displayed if {@link com.xenonteam.xenonlib.config.Refs#DEBUG Refs.DEBUG} is true.
	 * 
	 * @param mes The messages to be displayed.
	 */
	public static final void debug(Object... mes)
	{
		if(Refs.DEBUG)
		{
			for(Object s : mes)
			m_log.info("[DEBUG] " + s);
		}
	}

	/**
	 * Logs a complete {@link net.minecraft.nbt.NBTTagCompound NBTTagCompound}
	 * in a tree structure to the console
	 * 
	 * @param name
	 *            The compound name for logging purposes
	 * @param comp
	 *            The {@link net.minecraft.nbt.NBTTagCompound NBTTagCompound} to
	 *            log
	 */
	public static void logNBTCompound(String name, NBTTagCompound comp)
	{
		if (comp == null || comp.hasNoTags())
			return;

		for (Object key : comp.getKeySet())
		{
			NBTBase nbt = comp.getTag((String) key);

			if (nbt.getId() == NBTHelper.COMP_ID)
			{
				logNBTCompound(name + "/" + key, (NBTTagCompound) nbt);
			} else
			{
				Log.info(name + "/" + key + "[" + NBTHelper.getIdAsString(nbt.getId()) + "]" + ":" + nbt);
			}
		}
	}
	
}
