/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.factory;

import java.awt.Point;

import net.minecraft.util.ResourceLocation;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public interface IGuiElement {
	
	public void update(IGuiFactory factory);

	public void setXPos(int XPos);
	
	public void setYPos(int YPos);
	
	public void setPos(int x, int y);
	
	public void setPos(Point p);
	
	public void setPriority(int priority);
	
	public void setXSize(int XSize);
	
	public void setYSize(int YSize);
	
	public int getXPos();
	
	public int getYPos();
	
	public int getPriority();
	
	public int getXSize();
	
	public int getYSize();
	
	public ResourceLocation getResource();
	
	public void setResource(ResourceLocation loc);
	
}
