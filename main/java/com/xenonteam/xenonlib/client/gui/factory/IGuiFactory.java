/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.factory;

import java.awt.Point;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;

import com.xenonteam.xenonlib.client.gui.element.IGuiContainer;
import com.xenonteam.xenonlib.client.gui.element.IGuiElement;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public interface IGuiFactory extends IGuiContainer{

	public void generate(Object obj);
	
	public GuiScreen getContainer();
	
	public int getMouseX();
	
	public int getMouseY();
	
	public boolean getMouseClicked();
	
}
