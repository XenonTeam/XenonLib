/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiActionProvider;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class GuiElementButton extends GuiElement implements IGuiActionProvider
{

	@Override
	public boolean addActionHandler(IGuiActionHandler handler)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeActionHandler(IGuiActionHandler handler)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#getInt()
	 */
	@Override
	public int getInt()
	{
		return this.getPriority();
	}

}
