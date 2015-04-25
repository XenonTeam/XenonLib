/**
 * 
 */
package com.xenonteam.xenonlib.common.networking.packet;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import com.xenonteam.xenonlib.config.Refs;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class NetworkHandler {
	private static SimpleNetworkWrapper INSTANCE;
	private static int lastID = -1;

	public static void init() {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Refs.MOD_ID);
	}

	
	/**
	 * 
	 * @param messageHandler class that extends MessegeBase or MessageXYZ
	 * @param side The receiver side
	 */
	public static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(
			Class<? extends IMessageHandler<REQ, REPLY>> messageHandler,
			Side side) {
		lastID++;
		Class<REQ> requestMessageType = (Class<REQ>) messageHandler;
		INSTANCE.registerMessage(messageHandler, requestMessageType, lastID,
				side);
	}

	public static void sendToServer(IMessage message) {
		INSTANCE.sendToServer(message);
	}

	public static void sendTo(IMessage message, EntityPlayerMP player) {
		INSTANCE.sendTo(message, player);
	}

	public static void sendToAllAround(IMessage message, TargetPoint point) {
		INSTANCE.sendToAllAround(message, point);
	}

	public static void sendToAll(IMessage message) {
		INSTANCE.sendToAll(message);
	}

	public static void sendToDimension(IMessage message, int dimensionId) {
		INSTANCE.sendToDimension(message, dimensionId);
	}

}