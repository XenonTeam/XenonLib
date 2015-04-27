/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import java.awt.Point;

import net.minecraft.util.ResourceLocation;

import com.xenonteam.xenonlib.client.gui.factory.IGuiFactory;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public interface IGuiElement 
{
	
	public void draw(IGuiFactory factory);

	public void setXOff(int x);
	
	public void setYOff(int y);
	
	public void setPos(int x, int y);
	
	public void setPos(Point p);
	
	public void setPriority(int priority);
	
	public void setHeight(int height);
	
	public void setWidth(int width);
	
	public int getXOff();
	
	public int getYOff();
	
	public int getPriority();
	
	public int getHeight();
	
	public int getWidth();
	
	public IGuiElement getParent();
	
	public ResourceLocation getResource();
	
	public void setResource(ResourceLocation loc);
	
	
	public interface IGuiActionHandler extends IGuiElement
	{
		
		public void handleAction(int action, Object... args);
		
	}
}
