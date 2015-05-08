/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import java.awt.Point;

import net.minecraft.util.ResourceLocation;

import com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiActionHandler;
import com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiActionProvider;
import com.xenonteam.xenonlib.client.gui.factory.IGuiFactory;
import com.xenonteam.xenonlib.client.render.SpriteSheet;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class GuiElementButton implements IGuiActionProvider, IGuiElement.IGuiSpriteHandler
{

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#draw(com.xenonteam.xenonlib.client.gui.factory.IGuiFactory)
	 */
	@Override
	public void draw(IGuiFactory factory)
	{
		

	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#setXOff(int)
	 */
	@Override
	public void setXOff(int x)
	{
		

	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#setYOff(int)
	 */
	@Override
	public void setYOff(int y)
	{
		

	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#setPos(int, int)
	 */
	@Override
	public void setPos(int x, int y)
	{
		

	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#setPos(java.awt.Point)
	 */
	@Override
	public void setPos(Point p)
	{
		

	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#setPriority(int)
	 */
	@Override
	public void setPriority(int priority)
	{
		

	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#setHeight(int)
	 */
	@Override
	public void setHeight(int height)
	{
		

	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#setWidth(int)
	 */
	@Override
	public void setWidth(int width)
	{

	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#getXOff()
	 */
	@Override
	public int getXOff()
	{
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#getYOff()
	 */
	@Override
	public int getYOff()
	{
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#getPriority()
	 */
	@Override
	public int getPriority()
	{
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#getHeight()
	 */
	@Override
	public int getHeight()
	{
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#getWidth()
	 */
	@Override
	public int getWidth()
	{
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#getParent()
	 */
	@Override
	public IGuiElement getParent()
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiActionProvider#addActionHandler(com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiActionHandler)
	 */
	@Override
	public boolean addActionHandler(IGuiActionHandler handler)
	{
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiActionProvider#removeActionHandler(com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiActionHandler)
	 */
	@Override
	public boolean removeActionHandler(IGuiActionHandler handler)
	{
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#setSpriteID(java.lang.String, int)
	 */
	@Override
	public void setSpriteID(String ID, int i)
	{
		
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#setSpriteID(java.lang.String)
	 */
	@Override
	public void setSpriteID(String ID)
	{
		
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiSpriteHandler#setSpriteSheet(com.xenonteam.xenonlib.client.render.SpriteSheet)
	 */
	@Override
	public void setSpriteSheet(SpriteSheet sheet)
	{
		
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiSpriteHandler#setSpriteSheet(java.lang.String)
	 */
	@Override
	public void setSpriteSheet(String sheetId)
	{
		
	}

}
