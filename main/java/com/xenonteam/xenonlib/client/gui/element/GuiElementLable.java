/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import net.minecraft.client.gui.FontRenderer;

import com.xenonteam.xenonlib.client.gui.factory.IGuiFactory;
import com.xenonteam.xenonlib.client.render.SpriteSheet;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class GuiElementLable extends GuiElement
{
	private boolean m_shouldRenderBack;
	
	private int m_color;
	private String m_lable;

	private boolean m_shouldBack;
	
	public GuiElementLable(IGuiContainer parent, String lable, int color, boolean shouldBack)
	{
		super(parent);
		
		setLable(lable);
		setColor(color);
		setShouldRenderBackground(shouldBack);
	}
	
	public GuiElementLable(IGuiContainer parent, String lable, int color)
	{
		this(parent, lable, color, false);
	}
	
	public GuiElementLable(IGuiContainer parent, String lable)
	{
		this(parent, lable, ElementHelper.COLOR_WHITE);
	}
	
	private void calcSize()
	{
		FontRenderer font = ElementHelper.getFontRenderer();
		
		setWidth(font.getStringWidth(getLable()) + 4);
		setHeight(font.FONT_HEIGHT + 4);
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#draw(com.xenonteam.xenonlib.client.gui.factory.IGuiFactory)
	 */
	@Override
	public void draw(IGuiFactory factory)
	{
		if(getShouldRenderBackground())
			SpriteSheet.drawSprite("test", this, "button_off", factory.getContainer());
		
		ElementHelper.drawString(this, 2, 2, factory.getContainer(), getLable(), getColor());
		
	}
	
	public String getLable()
	{
		return m_lable;
	}
	
	public void setLable(String lable)
	{
		m_lable = lable;
		calcSize();
	}
	
	public int getColor()
	{
		return m_color;
	}
	
	public void setColor(int color)
	{
		m_color = color;
	}
	
	public void setShouldRenderBackground(boolean shouldBack)
	{
		m_shouldBack = shouldBack;
	}
	
	public boolean getShouldRenderBackground()
	{
		return m_shouldBack;
	}
	
}
