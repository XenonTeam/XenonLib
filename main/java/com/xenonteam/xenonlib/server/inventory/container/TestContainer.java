/**
 * 
 */
package com.xenonteam.xenonlib.server.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

import com.xenonteam.xenonlib.tileentity.TETest;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class TestContainer extends GenericContainer
{
	
	/**
	 * 
	 */
	TETest test;
	InventoryPlayer inv;
	
	public TestContainer(InventoryPlayer inv, TETest test)
	{
		this.inv = inv;
		this.test = test;
		this.addPlayerSlots(inv, 0, 0);
	}
	
	

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return true;
	}

}
