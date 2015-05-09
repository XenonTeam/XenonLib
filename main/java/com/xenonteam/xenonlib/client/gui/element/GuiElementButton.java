/**
 * 
 */
package com.xenonteam.xenonlib.client.gui.element;

import com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiActionProvider;
import com.xenonteam.xenonlib.client.gui.factory.IGuiFactory;

/**
 * @author tim4242
 * @author philipas
 *
 */
public class GuiElementButton extends GuiElement implements IGuiActionProvider
{
	
	/**
	 * @param parent
	 */
	protected GuiElementButton(IGuiContainer parent)
	{
		super(parent);
	}

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

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement#draw(com.xenonteam.xenonlib.client.gui.factory.IGuiFactory)
	 */
	@Override
	public void draw(IGuiFactory factory)
	{
		// TODO Auto-generated method stub
		
	}

}
