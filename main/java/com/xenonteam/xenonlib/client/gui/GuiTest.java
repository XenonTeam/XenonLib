/**est
 * 
 */
package com.xenonteam.xenonlib.client.gui;

import net.minecraft.entity.player.InventoryPlayer;

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
		
		GuiElementLable lable = new GuiElementLable(this, "Test String");
		lable.setPos(100, 50);
		
		super.addGuiElement("lable1", lable);
		
		Log.info("Added image1 and lable1");
	}

}
