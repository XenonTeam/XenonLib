package com.xenonteam.xenonlib.util;

import net.minecraftforge.fml.common.FMLLog;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.xenonteam.xenonlib.config.Refs;

public class Log {
	
	private static Logger m_log = LogManager.getLogger(Refs.MOD_NAME);
	
	public static final void info(String... mes)
	{
		for(String s : mes)
		m_log.info(s);
	}
	
	public static final void warn(String... mes)
	{
		for(String s : mes)
		m_log.warn(s);
	}
	
	public static final void error(String... mes)
	{
		for(String s : mes)
		m_log.error(s);
	}
	
	public static final void debug(String... mes)
	{
		if(Refs.DEBUG)
		{
			for(String s : mes)
			m_log.info("[DEBUG] " + s);
		}
	}

	
}
