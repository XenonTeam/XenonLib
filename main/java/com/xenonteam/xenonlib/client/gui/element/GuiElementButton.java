/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import java.util.ArrayList;

import com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiActionProvider;
import com.xenonteam.xenonlib.client.gui.factory.IGuiFactory;
import com.xenonteam.xenonlib.client.render.SpriteSheet;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class GuiElementButton extends GuiElementLable implements IGuiActionProvider
{

	protected ArrayList<IGuiActionHandler> m_handlers;

	/**
	 * @param parent
	 */
	public GuiElementButton(IGuiContainer parent, String lable, int color, IGuiActionHandler handler)
	{
		super(parent, lable, color);

		m_handlers = new ArrayList<IGuiActionHandler>();

		addActionHandler(handler);
	}

	public GuiElementButton(IGuiContainer parent, String lable, int color)
	{
		this(parent, lable, color, null);
	}

	public GuiElementButton(IGuiContainer parent, String lable)
	{
		this(parent, lable, ElementHelper.COLOR_WHITE);
	}
	
	@Override
	public String getType()
	{
		return "button";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xenonteam.xenonlib.client.gui.element.IGuiElement#draw(com.xenonteam
	 * .xenonlib.client.gui.factory.IGuiFactory)
	 */
	@Override
	public void draw(IGuiFactory factory)
	{
		String spriteID = "button_off";

		if (factory.getMouseX() > (getXOff() + getParent().getXOff()) && factory.getMouseX() < (getXOff() + getParent().getXOff() + getWidth()))
		{
			if (factory.getMouseY() > (getYOff() + getParent().getYOff()) && factory.getMouseY() < (getYOff() + getParent().getYOff() + getHeight()))
			{
				if (factory.getMouseClicked())
				{
					spriteID = "button_on";

					for (IGuiActionHandler hand : m_handlers)
					{
						hand.handleAction(0, getLable());
					}
				}
			}
		}

		SpriteSheet.drawSprite("test", this, spriteID, factory.getContainer());

		ElementHelper.drawString(this, 4, 4, factory.getContainer(), getLable(), getColor());
	}

	@Override
	public boolean addActionHandler(IGuiActionHandler handler)
	{
		if (handler != null)
			return m_handlers.add(handler);
		else
			return false;
	}

	@Override
	public boolean removeActionHandler(IGuiActionHandler handler)
	{
		if (handler != null)
			return m_handlers.remove(handler);
		else
			return false;
	}

}
