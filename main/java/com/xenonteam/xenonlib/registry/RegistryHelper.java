/**
 * 
 */
package com.xenonteam.xenonlib.registry;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class RegistryHelper
{

	public void registerObjects(Class<?> c)
	{
		List<Annotation> toRegister = Arrays.asList(c.getAnnotations());
		
		
	}
	
}
