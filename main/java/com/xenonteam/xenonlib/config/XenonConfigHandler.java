/**
 * 
 */
package com.xenonteam.xenonlib.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

/**
 * @author tim4242
 * @author philipas
 *
 */
public abstract class XenonConfigHandler implements IXenonConfigHandler
{
	protected Configuration[] m_config;

	protected XenonConfigHandler(String... relPaths)
	{
		m_config = new Configuration[relPaths.length];

		for (int i = 0; i < relPaths.length; i++)
		{
			m_config[i] = new Configuration(new File(Refs.CONFIG_PATH
					+ relPaths[i]));
		}
	}

	protected void load(int i)
	{
		m_config[i].load();
	}

	protected void save(int i)
	{
		m_config[i].save();
	}

	protected void load()
	{
		for (int i = 0; i < m_config.length; i++)
			load(i);
	}

	protected void save()
	{
		for (int i = 0; i < m_config.length; i++)
			save(i);
	}

	/**
	 * 
	 * This method returns all values from the config file
	 * 
	 * @return All values in the config file.
	 */
	protected Map<String, Entry<String, Property>>[] getValues()
	{
		Object[] res = new Object[m_config.length];

		for (int i = 0; i < m_config.length; i++)
		{
			res[i] = new HashMap<String, Entry<String, Property>>();
			Set<String> catNames = m_config[i].getCategoryNames();

			for (String s : catNames)
			{
				ConfigCategory cat = m_config[i].getCategory(s);

				for (String m : cat.getValues().keySet())
				{
					Entry<String, Property> entry = null;

					for (Entry<String, Property> e : cat.entrySet())
					{
						if (e.getKey().equals(m))
						{
							entry = e;
							break;
						}
					}

					
					((Map) res[i]).put(s, entry);
				}
			}

		}

		return (Map<String, Entry<String, Property>>[]) res;
	}
}
