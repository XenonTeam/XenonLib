/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.factory;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public interface IGuiFactory {

	public void generate(Object obj);
	
	public void addElement(String ID, IGuiElement element);
	
	public IGuiElement getElement(String ID);
	
}
