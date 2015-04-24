/**
 * 
 */
package com.xenonteam.xenonlib.common.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class MessageBase extends MessageReciver<MessageBase>{

	@Override
	public void fromBytes(ByteBuf buf) {
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
	}

	@Override
	public void handleClientSide(MessageBase message, EntityPlayer player) {
		
	}

	@Override
	public void handleServerSide(MessageBase message, EntityPlayer player) {
		
	}

}
