/**
 * 
 */
package com.xenonteam.xenonlib.util.conf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xenonteam.xenonlib.util.conf.ConfInterpreter.ConfCodec;
import com.xenonteam.xenonlib.util.conf.ConfInterpreter.InterpreterException;
import com.xenonteam.xenonlib.util.conf.ConfInterpreter.Struct;
import com.xenonteam.xenonlib.util.conf.ConfLexer.Token;
import com.xenonteam.xenonlib.util.conf.ConfLexer.IDS;

/**
 * @author tim4242
 *
 */
public class ConfWriter
{

	public static String write(List<Token> toks)
	{
		String res = "";

		int tabsN = 0;

		for (Token t : toks)
		{

			String tabs = "";

			for (int i = 1; i < tabsN; i++)
				tabs += "\t";

			if(t.getID() == IDS.VAR)
			{
				res += "\n" + tabs + "$";
			} else if(t.getID() == IDS.COMMENT)
			{
				res += "\n" + tabs + "/*" + t.data + "*/";
			} else if(t.getID() == IDS.LITERAL)
			{
				res += t.data;
			} else if(t.getID() == IDS.EQUAL)
			{
				res += " = ";
			} else if(t.getID() == IDS.COMMA)
			{
				res += ", ";
			} else if(t.getID() == IDS.HASH)
			{
				res += "#";
			} else if(t.getID() == IDS.CURLY_OPEN)
			{
				res += "{\n";
				tabsN++;
			} else if(t.getID() == IDS.CURLY_CLOSE)
			{
				res += "\n" + tabs + "}";
				tabsN--;
			} else if(t.getID() == IDS.SQUARE_OPEN)
			{
				res += "[ ";
			} else if(t.getID() == IDS.SQUARE_CLOSE)
			{
				res += " ]";
			} else if(t.getID() == IDS.SEPERATOR)
			{
				res += "\n";
			}
		}

		return res;
	}

	public static String write(ConfInterpreter inter)
	{
		return write(inter.getTokens());
	}

	public static List<Token> mapToTL(Map<String, Object> map)
	{
		List<Token> res = new ArrayList<Token>();

		for (Map.Entry<String, Object> ent : map.entrySet())
		{
			res.add(IDS.VAR.def());
			res.add(new Token(IDS.LITERAL, ent.getKey()));
			res.add(IDS.EQUAL.def());

			res.addAll(objToTL(ent.getValue()));

			res.add(IDS.SEPERATOR.def());
		}

		res.add(IDS.EOF.def());

		return res;
	}

	public static List<Token> objToTL(Object o)
	{
		List<Token> res = new ArrayList<Token>();

		if(o instanceof Struct)
		{
			res.addAll(structToTL((Struct) o));
		} else if(o instanceof List)
		{
			res.addAll(listToTL((List<Object>) o));
		} else
		{

			String s = null;
			for (ConfCodec c : ConfInterpreter.getRegistry())
			{
				if(c.canTransform(o))
					s = c.transform(o);
			}

			if(s == null)
				s = o.toString();

			res.add(new Token(IDS.LITERAL, s));
		}

		return res;
	}

	public static List<Token> listToTL(List<Object> ls)
	{
		List<Token> res = new ArrayList<Token>();

		res.add(IDS.SQUARE_OPEN.def());

		for (int i = 0; i < ls.size(); i++)
		{
			if(i != 0)
				res.add(IDS.COMMA.def());

			res.addAll(objToTL(ls.get(i)));
		}

		res.add(IDS.SQUARE_CLOSE.def());

		return res;
	}

	public static List<Token> structToTL(Struct s)
	{
		List<Token> res = new ArrayList<Token>();

		res.add(IDS.CURLY_OPEN.def());

		for (Map.Entry<String, Object> ent : s.getVars().entrySet())
		{
			res.add(IDS.VAR.def());
			res.add(new Token(IDS.LITERAL, ent.getKey()));
			res.add(IDS.EQUAL.def());

			res.addAll(objToTL(ent.getValue()));

			res.add(IDS.SEPERATOR.def());
		}

		res.add(IDS.CURLY_CLOSE.def());

		return res;
	}

	public static Map<String, Object> getRegeneratedMap(Map<String, Object> in, Map<String, Object> std)
	{

		Map<String, Object> out = new HashMap<String, Object>(std);

		System.out.println("In: " + in);
		System.out.println("Std: " + std);
		System.out.println("Is complete: " + Config.mapHas(out, "Development/Check Devs") + ", Out: " + out);

		Config.mapIterate(out, "", (boolean inList, String path, String key, Object obj) -> {

			System.out.println("inList: " + inList + ", path: " + path + ", key: " + key + ", obj: " + obj);
			System.out.println("In: " + (Config.mapHas(in, path + "/" + key) ? Config.mapGet(in, path + "/" + key) : ""));
			
			if(Config.mapHas(in, path + "/" + key))
			{
				Config.mapSet(out, path + "/" + key, Config.mapGet(in, path + "/" + key));
			}

		});

		System.out.println("In: " + in);
		System.out.println("Std: " + std);
		System.out.println("Is complete: " + Config.mapHas(out, "Development/Check Devs") + ", Out: " + out);

		return out;
	}

	public static String regenerate(Map<String, Object> in, Map<String, Object> std)
	{
		return write(mapToTL(getRegeneratedMap(in, std)));
	}

	public static String regenerate(ConfInterpreter in, ConfInterpreter std)
	{
		return regenerate(in.getAll(), std.getAll());
	}

	public static String regenerate(Config in, Config std)
	{
		return regenerate(in.getVars(), std.getVars());
	}

	public static String regenerate(Map<String, Object> in, InputStream std)
	{
		ConfInterpreter inter = null;

		BufferedReader re = new BufferedReader(new InputStreamReader(std));

		String s = "";
		String b;

		try
		{
			while ((b = re.readLine()) != null)
				s += b;
		} catch(IOException e)
		{
			e.printStackTrace();
		}

		try
		{
			inter = ConfInterpreter.interpret(s);
		} catch(InterpreterException e)
		{
			e.printStackTrace();
		}

		return regenerate(in, inter.getAll());
	}

}
