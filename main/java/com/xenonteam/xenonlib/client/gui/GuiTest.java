/**
 * 
 */
package com.xenonteam.xenonlib.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import com.xenonteam.xenonlib.server.inventory.container.TestContainer;
import com.xenonteam.xenonlib.tileentity.TETest;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class GuiTest extends GuiContainer
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
		this.drawCenteredString(fontRendererObj, "test", this.width / 2, this.height / 2, 1);
	}

}
