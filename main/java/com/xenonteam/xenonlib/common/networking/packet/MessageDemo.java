/**
 * 
 */
package com.xenonteam.xenonlib.common.networking.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author tim4242
 * @author philipas
 *
 */
public final class MessageDemo extends MessageBase<MessageDemo>{

	
	/**
	 * fromBytes
	 */
	@Override
	public void fromBytes(ByteBuf buf) {
	}

	/**
	 * write to bytes
	 */
	@Override
	public void toBytes(ByteBuf buf) {
	}

	
	/**
	 * handles the client side
	 */
	@Override
	public void handleClientSide(MessageDemo message, EntityPlayer player) {
	}

	/**
	 * handles the server side
	 */
	@Override
	public void handleServerSide(MessageDemo message, EntityPlayer player) {
	}

}
