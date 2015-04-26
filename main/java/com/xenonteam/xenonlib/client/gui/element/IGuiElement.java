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

	public void setXPos(int XPos);
	
	public void setYPos(int YPos);
	
	public void setPos(int x, int y);
	
	public void setPos(Point p);
	
	public void setPriority(int priority);
	
	public void setHeight(int XSize);
	
	public void setWidth(int YSize);
	
	public int getXPos();
	
	public int getYPos();
	
	public int getPriority();
	
	public int getHeight();
	
	public int getWidth();
	
	public ResourceLocation getResource();
	
	public void setResource(ResourceLocation loc);
	
	
	public interface Updateable
	{
		
		public void update(int action, Object... args);
		
	}
}
