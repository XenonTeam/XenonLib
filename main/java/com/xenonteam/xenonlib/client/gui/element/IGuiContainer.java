/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import net.minecraft.client.gui.inventory.GuiContainer;

/**
 * @author tim4242
 * @author philipas
 *
 */
public interface IGuiContainer extends IGuiElement
{
	public GuiContainer getGuiScreen();
	
	public boolean addGuiElement(String id, IGuiElement elm);
	
	public boolean removeGuiElement(String id);
	
	public IGuiElement getGuiElement(String id);
	
}
