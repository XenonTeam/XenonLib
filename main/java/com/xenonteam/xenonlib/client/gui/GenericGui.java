package com.xenonteam.xenonlib.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import com.xenonteam.xenonlib.config.Refs;
/**
 * @author tim4242
 * @author philipas
 * 
 * <br>
 * <br>
 * 
 * A generic GUI class to be extended by custom guis.
 */
public abstract class GenericGui extends GuiContainer{
    private final ResourceLocation guiTexture;
    private final IInventory inventory;

    public GenericGui(Container container, String guiTextureName, IInventory inventory){
        super(container);
        guiTexture = new ResourceLocation(Refs.MOD_ID + ":textures/gui/" + guiTextureName + ".png");
        this.inventory = inventory;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY){
        mc.getTextureManager().bindTexture(guiTexture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        String s = (String) (inventory.hasCustomName() ? inventory.getDisplayName() : I18n.format(inventory.getDisplayName().getFormattedText()));
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    public void onTextfieldUpdate(int id){}
}