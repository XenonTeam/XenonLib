/**
 * 
 */
package com.xenonteam.xenonlib.registry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tim4242
 * @author philipas
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Register
{

	String modid();
	String unlocName();
	
}
