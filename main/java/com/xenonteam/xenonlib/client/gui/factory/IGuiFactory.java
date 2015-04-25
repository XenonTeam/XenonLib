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
	
	public boolean addElement(String ID, IGuiElement element);
	
	public boolean removeElement(String ID);

	public IGuiElement getElement(String ID);
	
	
	public boolean setElementPos(String id, int x, int y);
	
	public boolean setElementPos(String id, Point p);
	
}
