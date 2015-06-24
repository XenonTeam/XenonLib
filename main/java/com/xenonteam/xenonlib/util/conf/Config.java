package com.xenonteam.xenonlib.util.conf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xenonteam.xenonlib.util.conf.ConfInterpreter.InterpreterException;
import com.xenonteam.xenonlib.util.conf.ConfInterpreter.Struct;
import com.xenonteam.xenonlib.util.conf.ConfLexer.IDS;
import com.xenonteam.xenonlib.util.conf.ConfLexer.Token;

public class Config
{

	public static class ConfigException extends Exception
	{
		public ConfigException()
		{
			super("Trying to use errored config!");
		}
	}
	
	public static interface IConfigIterator
	{
		public void iter(boolean inList, String path, String key, Object obj);
	}

	protected boolean m_error;
	protected ConfInterpreter m_inter;

	private Map<String, Object> m_std = new HashMap<String, Object>();
	private Map<String, String> m_comments = new HashMap<String, String>();

	public Config(ConfInterpreter inter)
	{
		m_inter = inter;
	}

	public Config(String s)
	{
		try
		{
			m_inter = ConfInterpreter.interpret(s);
		} catch(InterpreterException e)
		{
			e.printStackTrace();
			m_error = true;
			m_inter = null;
		}
	}

	public Config(Reader r)
	{
		BufferedReader re = new BufferedReader(r);

		String s = "";
		String b;

		try
		{
			while ((b = re.readLine()) != null)
				s += b;
		} catch(IOException e)
		{
			e.printStackTrace();
			m_error = true;
			m_inter = null;
			return;
		}

		try
		{
			m_inter = ConfInterpreter.interpret(s);
		} catch(InterpreterException e)
		{
			e.printStackTrace();
			m_error = true;
			m_inter = null;
		}
	}

	public Config(File f) throws FileNotFoundException
	{
		this(new FileReader(f));
	}

	public Config(InputStream s)
	{
		this(new InputStreamReader(s));
	}

	public void setStd(InputStream in)
	{
		BufferedReader re = new BufferedReader(new InputStreamReader(in));

		String s = "";
		String b;

		try
		{

			while ((b = re.readLine()) != null)
			{
				s += b;
			}
		} catch(IOException e)
		{
			e.printStackTrace();
			return;
		}

		setStd(s);
	}

	public void setStd(String s)
	{
		m_std = new Config(s).getVars();
		
		List<Token> toks = ConfLexer.lax(s);
		String path = "";
		String lastVar = "";

		for (int i = 0; i < toks.size(); i++)
		{
			Token t = toks.get(i);

			if(toks.get(i).getID() == IDS.COMMENT)
			{

				if(toks.get(i + 1).getID() == IDS.VAR && toks.get(i + 2).getID() == IDS.LITERAL)
				{
					m_comments.put(path + toks.get(i + 2).getData().toString(), toks.get(i).getData().toString());
				}
			}

			if(t.getID() == IDS.VAR)
			{
				i++;
				lastVar = toks.get(i).getData().toString();

			}

			if(t.getID() == IDS.CURLY_OPEN)
				path += lastVar + "/";

			if(t.getID() == IDS.CURLY_CLOSE)
			{
				path = path.substring(0, path.length());

				int sub = path.lastIndexOf("/");

				if(sub < 0)
				{
					sub = 0;
				}

				path = path.substring(0, sub);
			}
		}
	}

	public void addComment(String path, String comment)
	{
		m_comments.put(path, comment);
	}
	
	public static void mapIterate(Map<String, Object> map, String path, IConfigIterator iter)
	{
		for(Map.Entry<String, Object> ent : map.entrySet())
		{
			iter.iter(false, path, ent.getKey(), ent.getValue());
			
			if(ent.getValue() instanceof Struct)
			{
				mapIterate(((Struct) ent.getValue()).getVars(), new String(path + (path.isEmpty() ? "" : "/") + ent.getKey()), iter);
			}
			else if(ent.getValue() instanceof List)
			{
				listIterate((List) ent.getValue(), new String(path + (path.isEmpty() ? "" : "/") + ent.getKey()), iter);
			}
		}
	}
	
	public static void listIterate(List<Object> ls, String path, IConfigIterator iter)
	{
		for(int i = 0; i < ls.size(); i++)
		{
			iter.iter(true, path, "" + i, ls.get(i));
			
			if(ls.get(i) instanceof Struct)
			{
				mapIterate(((Struct) ls.get(i)).getVars(), new String(path + (path.isEmpty() ? "" : "/") + i), iter);
			}
			else if(ls.get(i) instanceof List)
			{
				listIterate((List) ls.get(i), new String(path + (path.isEmpty() ? "" : "/") + i), iter);
			}
		}
	}

	public void iterate(IConfigIterator iter)
	{
		mapIterate(getVars(), "", iter);
	}
	
	public static Object mapGet(Map<String, Object> map, String path)
	{

		String[] strs = path.split("/");

		boolean inLs = false;

		Map<String, Object> reg = map;
		List<Object> ls = null;

		for (int i = 0; i < strs.length; i++)
		{
			String s = strs[i];

			if(i == strs.length - 1)
			{
				if(inLs)
				{
					if(isInt(s))
					{
						if(ls.size() > Integer.parseInt(s))
							return ls.get(Integer.parseInt(s));

					}

					return null;
				} else
				{
					if(reg.containsKey(s))
						return reg.get(s);

					return null;
				}

			} else
			{
				Object o = null;

				if(inLs)
				{
					if(isInt(s))
					{
						if(ls.size() > Integer.parseInt(s))
						{
							o = ls.get(Integer.parseInt(s));

						} else
						{
							return null;
						}

					} else
					{
						return null;
					}

				} else
				{
					if(reg.containsKey(s))
					{
						o = reg.get(s);

					} else
					{
						return null;
					}
				}

				if(o instanceof Struct)
				{
					inLs = false;
					reg = ((Struct) o).getVars();
					continue;
				} else if(o instanceof List)
				{
					inLs = true;
					ls = (List) o;
					continue;
				} else
				{
					return null;
				}
			}
		}

		return null;
	}
	
	public static boolean mapSet(Map<String, Object> map, String path, Object set)
	{

		String[] strs = path.split("/");

		boolean inLs = false;

		Map<String, Object> reg = map;
		List<Object> ls = null;

		for (int i = 0; i < strs.length; i++)
		{
			String s = strs[i];

			if(i == strs.length - 1)
			{
				if(inLs)
				{
					if(isInt(s))
					{
						if(ls.size() > Integer.parseInt(s))
						{
							ls.set(Integer.parseInt(s), set);
						}

					}

					return false;
				} else
				{
					if(reg.containsKey(s))
					{
						return reg.put(s, set) != null;
					}

					return false;
				}

			} else
			{
				Object o = null;

				if(inLs)
				{
					if(isInt(s))
					{
						if(ls.size() > Integer.parseInt(s))
						{
							o = ls.get(Integer.parseInt(s));

						} else
						{
							return false;
						}

					} else
					{
						return false;
					}

				} else
				{
					if(reg.containsKey(s))
					{
						o = reg.get(s);

					} else
					{
						return false;
					}
				}

				if(o instanceof Struct)
				{
					inLs = false;
					reg = ((Struct) o).getVars();
					continue;
				} else if(o instanceof List)
				{
					inLs = true;
					ls = (List) o;
					continue;
				} else
				{
					return false;
				}
			}
		}

		return false;
	}

	public static boolean mapHas(Map<String, Object> map, String path)
	{
		return mapGet(map, path) != null;
	}
	
	public Object get(String path) throws ConfigException
	{
		if(m_error)
		{
			throw new ConfigException();
		}
		
		return mapGet(m_inter.getAll(), path);
	}
	
	public boolean set(String path, Object set) throws ConfigException
	{
		if(m_error)
		{
			throw new ConfigException();
		}
		
		return mapSet(m_inter.getAll(), path, set);
	}
	
	public boolean has(String path) throws ConfigException
	{
		if(m_error)
		{
			throw new ConfigException();
		}
		
		return mapHas(m_inter.getAll(), path);
	}

	public ConfInterpreter getInterpreter()
	{
		return m_inter;
	}

	public Map<String, Object> getVars()
	{
		return m_inter.getAll();
	}

	public void forceReset(String path) throws InterpreterException, ConfigException
	{
		if(m_error)
		{
			throw new ConfigException();
		}
		
		if(m_std == null)
		{
			throw new NullPointerException("No standard config set!");
		}
		
		String key = null;
		
		if(mapHas(m_std, path))
		{
			set(path, mapGet(m_std, path));
		}
	}

	public String regenerate()
	{
		if(m_std == null)
		{
			throw new NullPointerException("No standard config set!");
		}

		List<Token> toks = ConfWriter.mapToTL(ConfWriter.getRegeneratedMap(getVars(), m_std));
		
		String path = "";
		String lastVar = null;

		for (int i = 0; i < toks.size(); i++)
		{
			Token t = toks.get(i);

			if(t.getID() == IDS.VAR)
			{
				i++;
				lastVar = toks.get(i).getData().toString();

				if(m_comments.containsKey(path + lastVar))
					toks = insertAndShift(toks, new Token(IDS.COMMENT, m_comments.get(path + lastVar)), i - 1);
			}

			if(t.getID() == IDS.CURLY_OPEN)
				path += lastVar + "/";

			if(t.getID() == IDS.CURLY_CLOSE)
			{
				path = path.substring(0, path.length());

				int sub = path.lastIndexOf("/");

				if(sub < 0)
				{
					sub = 0;
				}

				path = path.substring(0, sub);
			}

		}

		return ConfWriter.write(toks);
	}
	
	/**
	 * Checks if the String is an instance of an Integer
	 * 
	 * @param str
	 *            The String you want to check
	 * @return If the String is an instance of Integer
	 */
	public static boolean isInt(String str)
	{
		try
		{
			int f = Integer.parseInt(str);
		} catch(Exception e)
		{
			return false;
		}

		return true;
	}

	/**
	 * Checks if the String is an instance of a Float
	 * 
	 * @param str
	 *            The String you want to check
	 * @return If the String is an instance of Float
	 */
	public static boolean isFloat(String str)
	{
		try
		{
			float f = Float.parseFloat(str);
		} catch(Exception e)
		{
			return false;
		}

		return true;
	}

	/**
	 * Checks if the String is an instance of a Double
	 * 
	 * @param str
	 *            The String you want to check
	 * @return If the String is an instance of Double
	 */
	public static boolean isDouble(String str)
	{
		try
		{
			double f = Double.parseDouble(str);
		} catch(Exception e)
		{
			return false;
		}

		return true;
	}
	
	public static <T> List<T> insertAndShift(List<T> origin, T elem, int insertion)
	{
		if(origin.size() < insertion)
		{
			return origin;
		}
		
		List<T> sub = new ArrayList<T>(origin.subList(insertion, origin.size()));
		
		
		
		List<T> sub2 = origin.subList(0, insertion);
		
		sub2.add(elem);
		
		sub2.addAll(sub);
		
		origin = sub2;
		
		return origin;
	}

}
