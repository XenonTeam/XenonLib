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

	private String m_sheetID;
	private String m_spriteID;

	public GuiElementImage(IGuiContainer parent, String spriteSheetID, String spriteID)
	{
		super(parent);
		
		this.setHeight(SpriteSheet.getSpriteSheet(spriteSheetID).getSprite(spriteID).getHeight());
		this.setWidth(SpriteSheet.getSpriteSheet(spriteSheetID).getSprite(spriteID).getWidth());
		m_sheetID = spriteSheetID;
		m_spriteID = spriteID;
		
	}

	@Override
	public void draw(IGuiFactory factory)
	{
		SpriteSheet.drawSprite(m_sheetID, this, m_spriteID, m_parent.getGuiScreen());
	}

	public String getType()
	{
		return "image";
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#setSpriteID(java.lang.String, int)
	 */
	@Override
	public void setSpriteID(String ID, String key)
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

}
