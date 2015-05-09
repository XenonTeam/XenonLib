/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import com.xenonteam.xenonlib.client.gui.factory.IGuiFactory;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class GuiElementLable extends GuiElement
{
	private String m_lable;
	
	public GuiElementLable(IGuiContainer parent, String lable)
	{
		super(parent);
		
		m_lable = lable;
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#draw(com.xenonteam.xenonlib.client.gui.factory.IGuiFactory)
	 */
	@Override
	public void draw(IGuiFactory factory)
	{
		ElementHelper.drawString(this, this.getXOff(), this.getYOff(), factory.getContainer(), getLable(), 4210752);
	}
	
	public String getLable()
	{
		return m_lable;
	}
	
	public void setLable(String lable)
	{
		m_lable = lable;
	}
	
}
