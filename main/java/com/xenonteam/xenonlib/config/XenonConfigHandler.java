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
 * 
 */
public abstract class XenonConfigHandler implements IXenonConfigHandler
{
	/**
	 * All the {@link net.minecraftforge.common.config.Configuration Configurations} handled by this configuration handler.
	 */
	protected Configuration[] m_config = null;
	protected Map<String, Map<String, Property>>[] m_values = null;

	/**
	 * 
	 * @param relPaths Paths to the {@link net.minecraftforge.common.config.Configuration Configurations} that should be handled.
	 */
	protected XenonConfigHandler(String... relPaths)
	{
		m_config = new Configuration[relPaths.length];

		for (int i = 0; i < relPaths.length; i++)
		{
			m_config[i] = new Configuration(new File(Refs.CONFIG_PATH
					+ relPaths[i]));
		}
		
		m_values = getValues();
	}

	/**
	 * 
	 * Returns the {@link net.minecraftforge.common.config.Configuration Configuration} at the specified index of {@link com.xenonteam.xenonlib.config.XenonConfigHandler#m_config m_config} 
	 * 
	 * @param i The index
	 * @return The {@link net.minecraftforge.common.config.Configuration Configuration} at the specified index
	 */
	protected Configuration getConfig(int i)
	{
		return m_config[i];
	}
	
	/**
	 * 
	 * Returns the {@link com.xenonteam.xenonlib.config.XenonConfigHandler#m_config m_config} of this config handler
	 * 
	 * @return An array of {@link net.minecraftforge.common.config.Configuration Configuration}
	 */
	protected Configuration[] getConfig()
	{
		return m_config;
	}
	
	/**
	 * 
	 * This method returns a {@link net.minecraftforge.common.config.Property Property} from the specified index in {@link com.xenonteam.xenonlib.config.XenonConfigHandler#m_config m_config} in the category and in the id
	 * 
	 * @param i The index
	 * @param cat The category
	 * @param id The id
	 * @return A {@link net.minecraftforge.common.config.Property Property}
	 */
	protected Property getProperty(int i, String cat, String id)
	{
		return m_values[i].get(cat).get(id);
	}
	
	/**
	 * 
	 * This method returns an array of {@link net.minecraftforge.common.config.Property Propertys} in the category and in the id
	 * 
	 * @param cat The category
	 * @param id The id
	 * @return A {@link net.minecraftforge.common.config.Property Property}
	 */
	protected Property[] getProperties(String cat, String id)
	{
		Property[] res = new Property[m_config.length];
		
		for(int i = 0; i < m_config.length; i++)
		{
			res[i] = getProperty(i, cat, id);
		}
		
		return res;
	}
	
	/**
	 * Creates a property
	 * 
	 * @param i The index
	 * @param cat The category
	 * @param id The id
	 * @param prop The default value
	 * @return The created properties
	 */
	protected Property createProperty(int i, String cat, String id, boolean prop)
	{
		return getConfig(i).get(cat, id, prop);
	}
	
	/**
	 * Creates a property
	 * 
	 * @param i The index
	 * @param cat The category
	 * @param id The id
	 * @param prop The default value
	 * @return The created properties
	 */
	protected Property createProperty(int i, String cat, String id, int prop)
	{
		return getConfig(i).get(cat, id, prop);
	}
	
	/**
	 * Creates a property
	 * 
	 * @param i The index
	 * @param cat The category
	 * @param id The id
	 * @param prop The default value
	 * @return The created properties
	 */
	protected Property createProperty(int i, String cat, String id, float prop)
	{
		return getConfig(i).get(cat, id, prop);
	}
	
	/**
	 * Creates a property
	 * 
	 * @param i The index
	 * @param cat The category
	 * @param id The id
	 * @param prop The default value
	 * @return The created properties
	 */
	protected Property createProperty(int i, String cat, String id, double prop)
	{
		return getConfig(i).get(cat, id, prop);
	}
	
	/**
	 * Creates a property
	 * 
	 * @param i The index
	 * @param cat The category
	 * @param id The id
	 * @param prop The default value
	 * @return The created properties
	 */
	protected Property createProperty(int i, String cat, String id, String prop)
	{
		return getConfig(i).get(cat, id, prop);
	}
	
	/**
	 * Creates a property
	 * 
	 * @param i The index
	 * @param cat The category
	 * @param id The id
	 * @param prop The default value
	 * @return The created properties
	 */
	protected Property createProperty(int i, String cat, String id, int[] prop)
	{
		return getConfig(i).get(cat, id, prop);
	}
	
	/**
	 * Creates a property
	 * 
	 * @param i The index
	 * @param cat The category
	 * @param id The id
	 * @param prop The default value
	 * @return The created properties
	 */
	protected Property createProperty(int i, String cat, String id, double[] prop)
	{
		return getConfig(i).get(cat, id, prop);
	}
	
	/**
	 * Creates a property
	 * 
	 * @param i The index
	 * @param cat The category
	 * @param id The id
	 * @param prop The default value
	 * @return The created properties
	 */
	protected Property createProperty(int i, String cat, String id, String[] prop)
	{
		return getConfig(i).get(cat, id, prop);
	}
	
	/**
	 * Calls {@link net.minecraftforge.common.config.Configuration#load() Configuration.load()} at the specified index of {@link com.xenonteam.xenonlib.config.XenonConfigHandler#m_config m_config}
	 * 
	 * @param i The index
	 */
	protected void load(int i)
	{
		m_config[i].load();
	}

	/**
	 * Calls {@link net.minecraftforge.common.config.Configuration#save() Configuration.save()} at the specified index of {@link com.xenonteam.xenonlib.config.XenonConfigHandler#m_config m_config}
	 * 
	 * @param i The index
	 */
	protected void save(int i)
	{
		m_config[i].save();
	}

	/**
	 * Calls {@link net.minecraftforge.common.config.Configuration#load() Configuration.save()} in every {@link net.minecraftforge.common.config.Configuration Configuration} in {@link com.xenonteam.xenonlib.config.XenonConfigHandler#m_config m_config}
	 * 
	 */
	protected void load()
	{
		for (int i = 0; i < m_config.length; i++)
			load(i);
	}

	/**
	 * Calls {@link net.minecraftforge.common.config.Configuration#save() Configuration.save()} in every {@link net.minecraftforge.common.config.Configuration Configuration} in {@link com.xenonteam.xenonlib.config.XenonConfigHandler#m_config m_config}
	 * 
	 */
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
	protected Map<String, Map<String, Property>>[] getValues()
	{
		Object[] res = new Object[m_config.length];

		for (int i = 0; i < m_config.length; i++)
		{
			res[i] = new HashMap<String, Map<String, Property>>();
			Set<String> catNames = m_config[i].getCategoryNames();

			for (String s : catNames)
			{
				ConfigCategory cat = m_config[i].getCategory(s);

				((Map) ((Map) res[i]).get(s)).putAll(cat.getValues());
			}

		}

		return (Map<String, Map<String, Property>>[]) res;
	}
}
