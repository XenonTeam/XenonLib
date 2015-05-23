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
public class GuiElementProgressBar extends GuiElementImage
{
	
	private boolean vertical_horizontal;
	private boolean reversed;

	public GuiElementProgressBar(IGuiContainer parent, String spriteSheetID, String spriteID, boolean vertical_horizontal, boolean reversed)
	{
		super(parent, spriteSheetID, spriteID);
		this.vertical_horizontal = vertical_horizontal;
		this.reversed = reversed;
	}

	@Override
	public void draw(IGuiFactory factory)
	{
		super.draw(factory);
	}
	
}
