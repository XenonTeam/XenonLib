/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.factory;

import java.awt.Point;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public interface IGuiFactory {

	public void generate(Object obj);
	
	public void addElement(String ID, IGuiElement element);
	
	public IGuiElement getElement(String ID);
	
	public void setElementPos(String id, int x, int y);
	
	public void setElementPos(String id, Point p);
	
}
