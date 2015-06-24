/**
 * 
 */
package com.xenonteam.xenonlib.util.conf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.xenonteam.xenonlib.util.conf.ConfLexer.IDS;
import com.xenonteam.xenonlib.util.java.JavaHelper;

import com.xenonteam.xenonlib.util.conf.ConfLexer.Token;

/**
 * @author tim4242
 *
 */
public class ConfInterpreter
{
	public static class InterpreterException extends Exception
	{
		private static final long serialVersionUID = -418912855068279446L;

		public InterpreterException(String s)
		{
			super(s);
		}
	}

	public static class Struct
	{
		private Map<String, Object> m_vars = new HashMap<String, Object>();

		public void setValue(String id, Object val)
		{
			m_vars.put(id, val);
		}

		public Object getValue(String id)
		{
			if(m_vars.containsKey(id))
			{
				return m_vars.get(id);
			}

			return null;
		}

		public Map<String, Object> getVars()
		{
			return m_vars;
		}

		public String toString()
		{
			return m_vars.toString();
		}
	}

	public static interface ConfCodec
	{
		public Object transform(String s);

		public String transform(Object o);

		public boolean canTransform(String s);

		public boolean canTransform(Object o);
	}

	private static int m_temp;

	private List<Token> m_tokens;

	private static List<ConfCodec> m_registry = new ArrayList<ConfCodec>();

	public static boolean addToRegistry(ConfCodec prov)
	{
		return m_registry.add(prov);
	}

	public static boolean removeFromRegistry(ConfCodec prov)
	{
		return m_registry.remove(prov);
	}

	public static List<ConfCodec> getRegistry()
	{
		return ImmutableList.copyOf(m_registry);
	}

	public ConfInterpreter(List<Token> t) throws InterpreterException
	{
		m_tokens = t;

		firstPass();
	}

	public static ConfInterpreter interpret(String s) throws InterpreterException
	{
		return new ConfInterpreter(ConfLexer.lax(s));
	}

	private Map<String, Object> m_varReg = new HashMap<String, Object>();

	private void firstPass() throws InterpreterException
	{
		addVars(m_varReg, m_tokens);
	}

	public Map<String, Object> getAll()
	{
		return m_varReg;
	}

	public List<Token> getTokens()
	{
		return m_tokens;
	}

	public static void addVars(Map<String, Object> vars, List<Token> toks) throws InterpreterException
	{
		for (int pos = 0; pos < toks.size(); pos++)
		{

			if(toks.get(pos).getID() == IDS.VAR)
			{

				if(pos + 1 < toks.size() && toks.get(pos + 1).getID() == IDS.LITERAL)
				{
					pos++;
					String id = (String) toks.get(pos).data;

					if(vars.containsKey(id))
						return;

					vars.put(id, null);

					if(pos + 2 < toks.size() && toks.get(pos + 1).getID() == IDS.EQUAL)
					{
						pos = pos += 2;

						if(toks.get(pos).getID() == IDS.LITERAL)
						{
							vars.put(id, fromString((String) toks.get(pos).data));
						} else if(toks.get(pos).getID() == IDS.CURLY_OPEN)
						{
							vars.put(id, buildStruct(toks, pos));
							pos = m_temp;
						} else if(toks.get(pos).getID() == IDS.SQUARE_OPEN)
						{
							vars.put(id, buildList(toks, pos));
							pos = m_temp;
						}

					}
				}

			}
		}
	}

	public static Struct buildStruct(List<Token> toks, int pos) throws InterpreterException
	{
		Struct res = new Struct();

		internal: while (true)
		{
			if(pos + 1 < toks.size())
			{
				pos++;

				if(toks.get(pos).getID() == IDS.VAR)
				{
					if(pos + 1 < toks.size() && toks.get(pos + 1).getID() == IDS.LITERAL)
					{
						pos++;
						String id = (String) toks.get(pos).data;

						if(res.m_vars.containsKey(id))
							continue internal;

						res.m_vars.put(id, null);

						if(pos + 2 < toks.size() && toks.get(pos + 1).getID() == IDS.EQUAL)
						{
							pos = pos += 2;

							if(toks.get(pos).getID() == IDS.LITERAL)
							{
								res.m_vars.put(id, fromString((String) toks.get(pos).data));
							} else if(toks.get(pos).getID() == IDS.CURLY_OPEN)
							{
								res.m_vars.put(id, buildStruct(toks, pos));
								pos = m_temp;
							} else if(toks.get(pos).getID() == IDS.SQUARE_OPEN)
							{
								res.m_vars.put(id, buildList(toks, pos));
								pos = m_temp;
							}

						}

					}
				} else if(toks.get(pos).getID() == IDS.CURLY_CLOSE)
				{
					break internal;
				}
			} else
			{
				if(toks.get(pos).getID() != IDS.COMMA)
				{
					throw new InterpreterException("Unfinished Struct");
				}
			}
		}

		m_temp = pos;
		return res;
	}

	public static List<Object> buildList(List<Token> toks, int pos) throws InterpreterException
	{

		List<Object> l = new ArrayList<Object>();

		boolean sVar = true;

		internal: while (true)
		{
			if(pos + 1 < toks.size())
			{
				pos++;

				if(toks.get(pos).getID() == IDS.SQUARE_CLOSE)
				{
					break internal;
				}

				if(sVar)
				{
					if(toks.get(pos).getID() == IDS.LITERAL)
					{
						l.add(fromString((String) toks.get(pos).data));
					} else if(toks.get(pos).getID() == IDS.CURLY_OPEN)
					{
						l.add(buildStruct(toks, pos));
						pos = m_temp;
					} else if(toks.get(pos).getID() == IDS.SQUARE_OPEN)
					{
						l.add(buildList(toks, pos));
						pos = m_temp;
					}
				} else
				{
					if(toks.get(pos).getID() != IDS.COMMA)
					{
						throw new InterpreterException("Unexpected Token");
					}
				}

				sVar = !sVar;
			} else
			{
				throw new InterpreterException("Unfinished List");
			}
		}

		m_temp = pos;
		return l;
	}

	public static Object fromString(String s)
	{

		if(JavaHelper.isInt(s))
		{
			return Integer.parseInt(s);
		} else if(JavaHelper.isDouble(s))
		{
			return Double.parseDouble(s);
		} else if(s.trim().equalsIgnoreCase("true"))
		{
			return Boolean.TRUE;
		} else if(s.trim().equalsIgnoreCase("false"))
		{
			return Boolean.FALSE;
		}

		for (ConfCodec prov : m_registry)
		{
			if(prov.canTransform(s))
			{
				return prov.transform(s);
			}
		}

		return s;
	}
}
