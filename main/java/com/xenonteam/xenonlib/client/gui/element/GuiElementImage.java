/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import com.xenonteam.xenonlib.client.gui.factory.IGuiFactory;
import com.xenonteam.xenonlib.client.render.SpriteSheet;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class GuiElementImage extends GuiElement implements IGuiElement.IGuiSpriteHandler
{

	private int xpos, ypos, priority, with, hight;
	private String m_sheetID;
	private String m_spriteID;
	
	private IGuiContainer m_parent;

	public GuiElementImage(IGuiContainer parent, String spriteSheetID, String spriteID)
	{
		this.setHeight(SpriteSheet.getSpriteSheet(spriteSheetID).getSprite(spriteID).getHeight());
		this.setWidth(SpriteSheet.getSpriteSheet(spriteSheetID).getSprite(spriteID).getWidth());
		m_sheetID = spriteSheetID;
		m_spriteID = spriteID;
		
		m_parent = parent;
	}

	@Override
	public void draw(IGuiFactory factory)
	{
		SpriteSheet.getSpriteSheet(m_sheetID).drawSprite(this, m_spriteID, m_parent.getGuiScreen());
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
		this.m_sheetID = sheetId;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiSpriteHandler#getSpriteSheet()
	 */
	@Override
	public String getSpriteSheet()
	{
		return m_sheetID;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#getInt()
	 */
	@Override
	public int getInt()
	{
		return this.getPriority();
	}

}
