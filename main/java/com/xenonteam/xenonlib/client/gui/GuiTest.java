/**est
 * 
 */
package com.xenonteam.xenonlib.client.gui;

import net.minecraft.entity.player.InventoryPlayer;

import com.xenonteam.xenonlib.client.gui.element.ElementHelper;
import com.xenonteam.xenonlib.client.gui.element.GuiElementButton;
import com.xenonteam.xenonlib.client.gui.element.GuiElementImage;
import com.xenonteam.xenonlib.client.gui.element.GuiElementLable;
import com.xenonteam.xenonlib.client.gui.factory.GuiFactory;
import com.xenonteam.xenonlib.server.inventory.container.TestContainer;
import com.xenonteam.xenonlib.tileentity.TETest;
import com.xenonteam.xenonlib.util.Log;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class GuiTest extends GuiFactory
{
	

	/**
	 * @param p_i1072_1_
	 */
	public GuiTest(InventoryPlayer inv, TETest test)
	{
		super(new TestContainer(inv, test));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		super.draw(this);
	}

	@Override
	public void generate(Object obj)
	{
		GuiElementImage image = new GuiElementImage(this, "test", "test1");
		image.setPos(100, 100);
		
		super.addGuiElement("image1", image);
		
		GuiElementLable lable = new GuiElementLable(this, "Test String", ElementHelper.COLOR_GREEN, true);
		lable.setPos(100, 50);
		
		lable.setPriority(0);
		
		GuiElementButton button = new GuiElementButton(this, "Button text", ElementHelper.COLOR_WHITE, this);
		button.setPos(150, 75);
		
		super.addGuiElement("lable1", lable);
		super.addGuiElement("button", button);
	}

	/* (non-Javadoc)
	 * @see com.xenonteam.xenonlib.client.gui.element.IGuiElement.IGuiActionHandler#handleAction(int, java.lang.Object[])
	 */
	@Override
	public void handleAction(int action, Object... args)
	{
		Log.info("ID: " + action + ":");
		Log.info(args);
	}

}
