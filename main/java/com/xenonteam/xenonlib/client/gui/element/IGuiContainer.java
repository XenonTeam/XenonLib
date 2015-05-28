/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import java.awt.Point;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;

/**
 * @author tim4242
 * @author philipas
 *
 */
public interface IGuiContainer extends IGuiElement
{
	public GuiScreen getGuiScreen();
	
	public boolean addGuiElement(String id, IGuiElement elm);
	
	public boolean removeGuiElement(String id);
	
	public IGuiElement getGuiElement(String id);
	
	public boolean setElementPos(String id, int x, int y);
	
	public boolean setElementPos(String id, Point p);
	
}
