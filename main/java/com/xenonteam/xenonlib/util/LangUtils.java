/**
 * 
 */
package com.xenonteam.xenonlib.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringTranslate;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
import net.minecraftforge.fml.relauncher.Side;

import org.apache.logging.log4j.Level;

import com.google.common.base.Charsets;
import com.xenonteam.xenonlib.util.java.ReflectionHelper;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class LangUtils
{

	public static HashMap<String, Properties> modLanguageData()
	{
		Object obj = ReflectionHelper.getFieldAccesseble(LanguageRegistry.class, "modLanguageData");
		return (HashMap<String, Properties>) obj;
	}

	public static LanguageRegistry instance()
	{
		return LanguageRegistry.instance();
	}

	private static final Pattern assetENUSLang = Pattern.compile("assets/(.*)/lang/(?:.+/|)([\\w_-]+).lang");

	public String getStringLocalization(String key)
	{
		return getStringLocalization(key, FMLCommonHandler.instance().getCurrentLanguage());
	}

	public String getStringLocalization(String key, String lang)
	{
		String localizedString = "";
		Properties langPack = modLanguageData().get(lang);

		if (langPack != null)
		{
			if (langPack.getProperty(key) != null)
			{
				localizedString = langPack.getProperty(key);
			}
		}

		return localizedString;
	}

	/**
	 * Deprecated for removal in 1.8. Use the assets lang system
	 */
	
	public void addStringLocalization(String key, String value)
	{
		addStringLocalization(key, "en_US", value);
	}

	/**
	 * Deprecated for removal in 1.8. Use the assets lang system
	 */
	
	public void addStringLocalization(String key, String lang, String value)
	{
		Properties langPack = modLanguageData().get(lang);
		if (langPack == null)
		{
			langPack = new Properties();
			modLanguageData().put(lang, langPack);
		}
		langPack.put(key, value);
	}

	/**
	 * Deprecated for removal in 1.8. Use the assets lang system
	 */
	
	public void addStringLocalization(Properties langPackAdditions)
	{
		addStringLocalization(langPackAdditions, "en_US");
	}

	/**
	 * Deprecated for removal in 1.8. Use the assets lang system
	 */
	
	public void addStringLocalization(Properties langPackAdditions, String lang)
	{
		Properties langPack = modLanguageData().get(lang);
		if (langPack == null)
		{
			langPack = new Properties();
			modLanguageData().put(lang, langPack);
		}
		if (langPackAdditions != null)
		{
			langPack.putAll(langPackAdditions);
		}
	}

	/**
	 * Deprecated for removal in 1.8. Use the assets lang system
	 */
	
	public void addNameForObject(Object objectToName, String lang, String name)
	{
		String objectName;
		if (objectToName instanceof Item)
		{
			objectName = ((Item) objectToName).getUnlocalizedName();
		} else if (objectToName instanceof Block)
		{
			objectName = ((Block) objectToName).getUnlocalizedName();
		} else if (objectToName instanceof ItemStack)
		{
			objectName = ((ItemStack) objectToName).getItem().getUnlocalizedName((ItemStack) objectToName);
		} else
		{
			throw new IllegalArgumentException(String.format("Illegal object for naming %s", objectToName));
		}
		objectName += ".name";
		addStringLocalization(objectName, lang, name);
	}

	/**
	 * Deprecated for removal in 1.8. Use the assets lang system
	 */
	
	public static void addName(Object objectToName, String name)
	{
		instance().addNameForObject(objectToName, "en_US", name);
	}

	/**
	 * Deprecated for removal in 1.8. Use the assets lang system
	 */
	@SuppressWarnings("unchecked")
	
	public void mergeLanguageTable(@SuppressWarnings("rawtypes") Map properties, String lang)
	{
		Properties langPack = modLanguageData().get(lang);
		if (langPack != null)
		{
			mergeWithoutOverwrite(langPack, properties);
		}
		Properties usPack = modLanguageData().get("en_US");
		if (usPack != null)
		{
			mergeWithoutOverwrite(usPack, properties);
		}
	}

	
	private <K, V> void mergeWithoutOverwrite(Map<? extends K, ? extends V> from, Map<K, V> to)
	{
		for (Entry<? extends K, ? extends V> e : from.entrySet())
		{
			if (!to.containsKey(e.getKey()))
			{
				to.put(e.getKey(), e.getValue());
			}
		}
	}

	/**
	 * Deprecated for removal in 1.8. Use the assets lang system
	 */
	
	public void loadLocalization(String localizationFile, String lang, boolean isXML)
	{
		URL urlResource = this.getClass().getResource(localizationFile);
		if (urlResource != null)
		{
			loadLocalization(urlResource, lang, isXML);
		} else
		{
			ModContainer activeModContainer = Loader.instance().activeModContainer();
			if (activeModContainer != null)
			{
				FMLLog.log(activeModContainer.getModId(), Level.ERROR, "The language resource %s cannot be located on the classpath. This is a programming error.", localizationFile);
			} else
			{
				FMLLog.log(Level.ERROR, "The language resource %s cannot be located on the classpath. This is a programming error.", localizationFile);
			}
		}
	}

	/**
	 * Deprecated for removal in 1.8. Use the assets lang system
	 */
	
	public void loadLocalization(URL localizationFile, String lang, boolean isXML)
	{
		InputStream langStream = null;
		Properties langPack = new Properties();

		try
		{
			langStream = localizationFile.openStream();

			if (isXML)
			{
				langPack.loadFromXML(langStream);
			} else
			{
				langPack.load(new InputStreamReader(langStream, Charsets.UTF_8));
			}

			addStringLocalization(langPack, lang);
		} catch (IOException e)
		{
			FMLLog.log(Level.ERROR, e, "Unable to load localization from file %s", localizationFile);
		} finally
		{
			try
			{
				if (langStream != null)
				{
					langStream.close();
				}
			} catch (IOException ex)
			{
				// HUSH
			}
		}
	}

	public void injectLanguage(String language, HashMap<String, String> parsedLangFile)
	{

		Properties p = modLanguageData().get(language);
		if (p == null)
		{
			p = new Properties();
			modLanguageData().put(language, p);
		}
		p.putAll(parsedLangFile);
	}

	public void loadLanguagesFor(ModContainer container, Side side)
	{
		File source = container.getSource();
		try
		{
			if (source.isDirectory())
			{
				searchDirForLanguages(source, "", side);
			} else
			{
				searchZipForLanguages(source, side);
			}
		} catch (IOException ioe)
		{

		}
	}

	private void searchZipForLanguages(File source, Side side) throws IOException
	{
		ZipFile zf = new ZipFile(source);
		for (ZipEntry ze : Collections.list(zf.entries()))
		{
			Matcher matcher = assetENUSLang.matcher(ze.getName());
			if (matcher.matches())
			{
				String lang = matcher.group(2);
				FMLLog.fine("Injecting found translation data for lang %s in zip file %s at %s into language system", lang, source.getName(), ze.getName());
				LanguageRegistry.instance().injectLanguage(lang, StringTranslate.parseLangFile(zf.getInputStream(ze)));
				// Ensure en_US is available to StringTranslate on the server
				if ("en_US".equals(lang) && side == Side.SERVER)
				{
					StringTranslate.inject(zf.getInputStream(ze));
				}
			}
		}
		zf.close();
	}

	private void searchDirForLanguages(File source, String path, Side side) throws IOException
	{
		for (File file : source.listFiles())
		{
			String currPath = path + file.getName();
			if (file.isDirectory())
			{
				searchDirForLanguages(file, currPath + '/', side);
			}
			Matcher matcher = assetENUSLang.matcher(currPath);
			if (matcher.matches())
			{
				String lang = matcher.group(2);
				FMLLog.fine("Injecting found translation assets for lang %s at %s into language system", lang, currPath);
				LanguageRegistry.instance().injectLanguage(lang, StringTranslate.parseLangFile(new FileInputStream(file)));
				// Ensure en_US is available to StringTranslate on the server
				if ("en_US".equals(lang) && side == Side.SERVER)
				{
					StringTranslate.inject(new FileInputStream(file));
				}
			}
		}
	}

}
