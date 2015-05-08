/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import java.awt.Point;

import net.minecraft.util.ResourceLocation;

import com.xenonteam.xenonlib.client.gui.factory.IGuiFactory;
import com.xenonteam.xenonlib.client.render.SpriteSheet;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class GuiElementImage implements IGuiElement, IGuiElement.IGuiSpriteHandler
{

	private int xpos, ypos, priority, with, hight;
	private String m_sheet;
	private String m_spriteID;
	
	private IGuiContainer m_parent;

	public GuiElementImage(IGuiContainer parent, SpriteSheet sheet, String spriteID)
	{
		this.setHeight(sheet.getSprite(spriteID).getHeight());
		this.setWidth(sheet.getSprite(spriteID).getWidth());
		m_sheet = sheet;
		m_spriteID = spriteID;
		
		m_parent = parent;
	}

	@Override
	public void draw(IGuiFactory factory)
	{
		m_sheet.drawSprite(this, m_spriteID, m_parent.getGuiScreen());
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

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#getParent()
	 */
	@Override
	public IGuiElement getParent()
	{
		return m_parent;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#setSpriteID(java.lang.String, int)
	 */
	@Override
	public void setSpriteID(String ID, int i)
	{
		this.m_spriteID = ID;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#setSpriteID(java.lang.String)
	 */
	@Override
	public void setSpriteID(String ID)
	{
		this.m_spriteID = ID;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiSpriteHandler#setSpriteSheet(java.lang.String)
	 */
	@Override
	public void setSpriteSheet(String sheetId)
	{
		setSpriteSheet(SpriteSheet.getSpriteSheet(sheetId));
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiSpriteHandler#getSpriteSheet()
	 */
	@Override
	public String getSpriteSheet()
	{
		return m_sheet;
	}

}
