/**
 * 
 */
package com.xenonteam.xenonlib.config;

/**
 * @author tim4242
 *
 */
public class XenonConfigValueBase
{

	private final int m_type;
	private final Object m_value;
	
	protected XenonConfigValueBase(int type, Object val)
	{
		m_type = type;
		m_value = val;
	}
	
	public int getType()
	{
		return m_type;
	}
	
	public Object getValue()
	{
		return m_value;
	}
	
}
