/**est
 * 
 */
package com.xenonteam.xenonlib.client.gui;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;

import com.xenonteam.xenonlib.client.gui.element.GuiElementImage;
import com.xenonteam.xenonlib.client.gui.factory.GuiFactory;
import com.xenonteam.xenonlib.server.inventory.container.TestContainer;
import com.xenonteam.xenonlib.tileentity.TETest;

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
		this.drawCenteredString(fontRendererObj, "test", this.width / 2, this.height / 2, 1);
		GuiElementImage image = new GuiElementImage(this, "test", "test1");
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		image.setPos(100, 100);
		image.setWidth(32);
		image.setHeight(32);
		image.draw(this);
	}

	@Override
	public void generate(Object obj)
	{

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
