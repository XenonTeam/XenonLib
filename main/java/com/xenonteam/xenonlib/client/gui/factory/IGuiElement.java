/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.factory;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public interface IGuiElement {

	public void setXPos(int XPos);
	
	public void setYPos(int YPos);
	
	public void setPriority(int priority);
	
	public void setXSize(int XSize);
	
	public void setYSize(int YSize);
	
	public int getXPos();
	
	public int getYPos();
	
	public int getPriority();
	
	public int getXSize();
	
	public int getYSize();
	
}
