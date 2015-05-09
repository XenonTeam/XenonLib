/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import java.awt.Point;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public abstract class GuiElement implements IGuiElement
{
	protected int xpos, ypos, priority, width, height;
	
	protected IGuiContainer m_parent;

	protected GuiElement(IGuiContainer parent, int x, int y, int w, int h, int prio)
	{
		m_parent = parent;
		
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		priority = prio;
	}
	
	protected GuiElement(IGuiContainer parent)
	{
		this(parent, 0, 0, 0, 0, 0);
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
		this.width = with;
	}

	@Override
	public void setHeight(int hight)
	{
		this.height = hight;
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
		return width;
	}

	@Override
	public int getHeight()
	{
		return height;
	}

	@Override
	public IGuiElement getParent()
	{
		return m_parent;
	}
}
