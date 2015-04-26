/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import java.awt.Point;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

import com.xenonteam.xenonlib.client.gui.factory.IGuiFactory;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class GuiElementImage implements IGuiElement
{

	private int xpos, ypos, priority, with, hight;
	private ResourceLocation reslocation;
	private GuiContainer container;
	private SpriteSheet m_sheet;
	private String m_spriteID;

	public GuiElementImage(GuiContainer container, ResourceLocation reslocation, SpriteSheet sheet, String spriteID)
	{
		this.container = container;
		this.setResource(reslocation);
		this.setHeight(sheet.getSprite(spriteID).m_h);
		this.setWidth(sheet.getSprite(spriteID).m_w);
		m_sheet = sheet;
		m_spriteID = spriteID;
	}

	@Override
	public void draw(IGuiFactory factory)
	{
		m_sheet.drawSprite(this, m_spriteID, container);
	}

	@Override
	public void setXPos(int XPos)
	{
		this.xpos = XPos;
	}

	@Override
	public void setYPos(int YPos)
	{
		this.ypos = YPos;
	}

	@Override
	public void setPos(int x, int y)
	{
		this.setXPos(x);
		this.setYPos(y);
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
	public int getXPos()
	{
		return xpos;
	}

	@Override
	public int getYPos()
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
	public ResourceLocation getResource()
	{
		return reslocation;
	}

	@Override
	public void setResource(ResourceLocation loc)
	{
		this.reslocation = loc;
	}

}
