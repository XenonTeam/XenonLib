/**
 * 
 */
package com.xenonteam.xenonlib.java;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author tim4242
 * 
 * 
 *
 */
public class FilenameFilters
{

	public static final class AllFilter implements FilenameFilter
	{
		@Override
		public boolean accept(File dir, String name)
		{
			return true;
		}
	}
	
	public static final class RegexFilter implements FilenameFilter
	{
		private String m_regex;
		
		public RegexFilter(String regex)
		{
			m_regex = regex;
		}
		
		@Override
		public boolean accept(File dir, String name)
		{
			if(name.matches(m_regex))
			return true;
			
			return false;
		}
	}
	
	public static final class StartsWith implements FilenameFilter
	{
		private String m_regex;
		
		public StartsWith(String regex)
		{
			m_regex = regex;
		}
		
		@Override
		public boolean accept(File dir, String name)
		{
			if(name.startsWith(m_regex))
			return true;
			
			return false;
		}
	}
	
	public static final class Contains implements FilenameFilter
	{
		private String m_regex;
		
		public Contains(String regex)
		{
			m_regex = regex;
		}
		
		@Override
		public boolean accept(File dir, String name)
		{
			if(name.contains(m_regex))
			return true;
			
			return false;
		}
	}
	
	public static final class EndsWith implements FilenameFilter
	{
		private String m_regex;
		
		public EndsWith(String regex)
		{
			m_regex = regex;
		}
		
		@Override
		public boolean accept(File dir, String name)
		{
			if(name.endsWith(m_regex))
			return true;
			
			return false;
		}
	}
	
}
