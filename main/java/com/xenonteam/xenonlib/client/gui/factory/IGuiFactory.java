/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.factory;

import java.awt.Point;

import net.minecraft.client.gui.inventory.GuiContainer;

import com.xenonteam.xenonlib.client.gui.element.IGuiElement;

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
	
	public GuiContainer getContainer();
	
	public int getMouseX();
	
	public int getMouseY();
	
	public boolean getMouseClicked();
	
}
