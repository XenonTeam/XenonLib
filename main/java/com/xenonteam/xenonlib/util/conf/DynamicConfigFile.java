package com.xenonteam.xenonlib.util.conf;

import java.io.File;
import java.io.FileNotFoundException;

public class DynamicConfigFile extends ConfigFile
{

	public DynamicConfigFile(File f) throws FileNotFoundException
	{
		super(f);
	}
	
	public void reload()
	{
		try
		{
			load();
		} catch(FileNotFoundException e)
		{
			e.printStackTrace();
			m_inter = null;
			m_error = true;
		}
	}

	public String regenerate()
	{
		throw new UnsupportedOperationException("Can't regenerate dynamic files!");
	}
	
}
