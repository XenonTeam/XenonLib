package com.xenonteam.xenonlib.util.conf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigFile extends Config
{

	private File m_file;

	public ConfigFile(File f) throws FileNotFoundException
	{
		super(f);

		m_file = f;
	}
	
	public void load() throws FileNotFoundException
	{
		m_inter = new Config(m_file).m_inter;
	}

	public String regenerate()
	{
		String s = super.regenerate();

		if(m_file.exists())
			m_file.delete();
		try
		{
			m_file.createNewFile();

			BufferedWriter wr = new BufferedWriter(new FileWriter(m_file));

			String[] split = s.split("\n");
			
			
			for(String str : split)
			{
				wr.write(str);
				wr.newLine();
			}
			

			wr.close();
		} catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}

		return s;
	}

}
