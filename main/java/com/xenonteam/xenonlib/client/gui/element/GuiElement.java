/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import java.awt.Point;

import com.xenonteam.xenonlib.client.gui.factory.IGuiFactory;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public abstract class GuiElement implements IGuiElement
{
	private int xpos, ypos, priority, with, hight;
	
	private IGuiContainer m_parent;


	@Override
	public void draw(IGuiFactory factory)
	{
	}

	@Override
	public void setXOff(int XPos)
	{
		this.xpos = XPos;
	}

	@Override
	public void setYOff(int YPos)
	{
		this.ypos = YPos;
	}

	@Override
	public void setPos(int x, int y)
	{
		this.setXOff(x);
		this.setYOff(y);
	}

	@Override
	public void setPos(Point p)
	{
		this.setPos(p.x, p.y);
	}

	@Override
	public void setPriority(int priority)
	{
		this.priority = priority;
	}

	@Override
	public void setWidth(int with)
	{
		this.with = with;
	}

	@Override
	public void setHeight(int hight)
	{
		this.hight = hight;
	}

	@Override
	public int getXOff()
	{
		return xpos;
	}

	@Override
	public int getYOff()
	{
		return ypos;
	}

	@Override
	public int getPriority()
	{
		return priority;
	}

	@Override
	public int getWidth()
	{
		return with;
	}

	@Override
	public int getHeight()
	{
		return hight;
	}

	@Override
	public IGuiElement getParent()
	{
		return m_parent;
	}
}
