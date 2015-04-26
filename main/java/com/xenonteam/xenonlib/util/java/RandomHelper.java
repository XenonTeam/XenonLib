/**
 * 
 */
package com.xenonteam.xenonlib.util.java;

import java.util.Random;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class RandomHelper
{

	private static Random m_rand = new Random();
	
	private static String[] chars = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i" ,"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "_", "!", "?", "=", "(", ")", "/"};
	private static int chars_len = 33;
	
	/**
	 * Generates a random {@link java.lang.String String} with the length of len
	 * 
	 * @param len The length
	 * @return
	 */
	public static String randomString(int len)
	{
		String res = "";
		
		for(int i = 0; i <= len; i++)
		{
			res += (chars[m_rand.nextInt(chars_len)]);
		}
		
		return res;
	}
	
}
