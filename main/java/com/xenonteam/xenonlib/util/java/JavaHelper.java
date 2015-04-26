/**
 * 
 */
package com.xenonteam.xenonlib.util.java;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class JavaHelper
{
	
	/**
	 * Checks if the String is an instance of an Integer
	 * @param str The String you want to check
	 * @return If the String is an instance of Integer
	 */
	public static boolean isInt(String str) {
		try {
			int f = Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}

		return true;
	}
	
	/**
	 * Checks if the String is an instance of a Float
	 * @param str The String you want to check
	 * @return If the String is an instance of Float
	 */
	public static boolean isFloat(String str) {
		try {
			float f = Float.parseFloat(str);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Checks if the String is an instance of a Double
	 * @param str The String you want to check
	 * @return If the String is an instance of Double
	 */
	public static boolean isDouble(String str) {
		try {
			double f = Double.parseDouble(str);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
