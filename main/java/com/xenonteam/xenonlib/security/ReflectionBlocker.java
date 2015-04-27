/**
 * 
 */
package com.xenonteam.xenonlib.security;

import java.security.Permission;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class ReflectionBlocker extends SecurityManager
{

	@Override
	public void checkPermission(Permission perm)
	{
		if (perm.getName().equals("suppressAccessChecks"))
		{
			throw new SecurityException("Can not change the permission.");
		}
	}

}
