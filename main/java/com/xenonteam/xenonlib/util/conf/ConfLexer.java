/**
 * 
 */
package com.xenonteam.xenonlib.util.conf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author tim4242
 *
 */
public class ConfLexer
{

	public static enum IDS
	{
		SEPERATOR, EOF, COMMENT, CURLY_OPEN, CURLY_CLOSE, SQUARE_OPEN, SQUARE_CLOSE, EQUAL, LITERAL, HASH, COMMA, VAR;

		final Token m_def;
		
		IDS()
		{
			m_def = new Token(this);
		}
		
		public int id()
		{
			return this.ordinal();
		}
		
		public Token def()
		{
			return m_def;
		}
	}

	public static class Token
	{

		private final IDS m_id;
		public Object data;

		public Token(IDS id, Object _data)
		{
			m_id = id;
			data = _data;
		}

		public Token(IDS id)
		{
			this(id, null);
		}

		public IDS getID()
		{
			return m_id;
		}
		
		public Object getData()
		{
			return data;
		}

		public boolean equals(Object o)
		{
			if(o instanceof Token && ((Token) o).m_id == m_id && ((Token) o).data == data)
				return true;

			return false;
		}

		public String toString()
		{
			return m_id.name() + (data != null ? ": \"" + data  + "\"": "");
		}
	}

	public static List<Token> lax(String toLex)
	{
		List<Token> res = new ArrayList<Token>();

		String buffer = "";
		String strBuffer = "";
		
		List<Token> tokBuffer = new ArrayList<Token>();

		boolean inString = false;

		int len = toLex.toCharArray().length;
		Character[] str = new Character[len];

		for (int i = 0; i < len; i++)
		{
			str[i] = Character.valueOf(toLex.charAt(i));
		}

		main: for (int i = 0; i < len; i++)
		{
			Character c = str[i];

			if(c.equals('\\'))
			{
				i++;
				
				if(!inString)
				{
					inString = true;
				}
				
				strBuffer += str[i];
				
				
				if(i + 1 != len)
				continue main;
			}
			else if(c.equals('\n') || c.equals(';'))
			{
				tokBuffer.add(IDS.SEPERATOR.def());
			}
			else if(c.equals('/') && (i + 1) < len && str[i + 1].equals('*'))
			{
				i += 2;

				comment: while (true)
				{

					if(i >= len || str[i].equals('*') && (i + 1) < len && str[i + 1].equals('/'))
					{
						break comment;
					}

					buffer += str[i];
					i++;
				}

				tokBuffer.add(new Token(IDS.COMMENT, buffer));
				buffer = "";
				
				i++;
			}
			else if(c.equals('/') && (i + 1) < len && str[i + 1].equals('/'))
			{
				i += 2;

				comment: while (true)
				{
					if(i >= len || str[i].equals('\n') || str[i].equals(';'))
					{
						break comment;
					}

					buffer += str[i];
					i++;
				}

				tokBuffer.add(new Token(IDS.COMMENT, buffer));
				tokBuffer.add(IDS.SEPERATOR.def());
				buffer = "";
			}
			else if(c.equals('='))
			{
				tokBuffer.add(IDS.EQUAL.def());
			}
			else if(c.equals('$'))
			{
				tokBuffer.add(IDS.VAR.def());
			}
			else if(c.equals('{'))
			{
				tokBuffer.add(IDS.CURLY_OPEN.def());
			}
			else if(c.equals('}'))
			{
				tokBuffer.add(IDS.CURLY_CLOSE.def());
			}
			else if(c.equals('['))
			{
				tokBuffer.add(IDS.SQUARE_OPEN.def());
			}
			else if(c.equals(']'))
			{
				tokBuffer.add(IDS.SQUARE_CLOSE.def());
			}
			else if(c.equals('#'))
			{
				tokBuffer.add(IDS.HASH.def());
			}
			else if(c.equals(','))
			{
				tokBuffer.add(IDS.COMMA.def());
			}
			else if(c.equals('"'))
			{
				i++;
				comment: while (true)
				{
					
					if(i >= len)
					{
						break comment;
					}
					if(str[i].equals('\\'))
					{
						i += 1;
					}
					else if(str[i].equals('"'))
					{
						break comment;
					}

					buffer += str[i];
					i++;
				}
				
				tokBuffer.add(new Token(IDS.LITERAL, buffer));
				buffer = "";
			}
			else
			{
				if(!inString)
				{
					inString = true;
				}
				
				strBuffer += c;
				
				
				if(i + 1 != len)
				continue main;
			}

			if(inString)
			{
				strBuffer = strBuffer.trim();

				if(!strBuffer.isEmpty())
				{
					tokBuffer.add(new Token(IDS.LITERAL, strBuffer));
					Collections.reverse(tokBuffer);
				}
				
				strBuffer = "";

				inString = false;
			}
			
			res.addAll(tokBuffer);
			tokBuffer.clear();
		}

		res.add(IDS.EOF.def());

		return res;
	}
}
