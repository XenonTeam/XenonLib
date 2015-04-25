/**
 * 
 */
package com.xenonteam.xenonlib.server.inventory.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public abstract class GenericContainer extends Container {


	/**
	 * 
	 * @param playerInventory the player inventory
	 * @param x Start x pos
	 * @param y Start y pos
	 */
    protected void addPlayerSlots(InventoryPlayer playerInventory, int x, int y){
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, x + j * 18, y + i * 18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            addSlotToContainer(new Slot(playerInventory, i, x + i * 18, y + 58));
        }
    }
	
}
