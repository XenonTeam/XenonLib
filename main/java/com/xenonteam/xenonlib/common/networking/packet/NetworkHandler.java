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
	private static boolean hasInit = false;
	
	/**
	 * Just the init of the networkWrapper do not use this
	 */
	@Deprecated
	public static void init() {
		if (!hasInit) INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Refs.MOD_ID);
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

	/**
	 * Send Message to the server
	 * @param message An instance of MessageBase or messageXYZ
	 */
	public static void sendToServer(IMessage message) {
		INSTANCE.sendToServer(message);
	}

	/**
	 * Send Message to a Player
	 * @param message An instance of MessageBase or messageXYZ
	 * @param player The player you want to send the Message to
	 */
	public static void sendTo(IMessage message, EntityPlayerMP player) {
		INSTANCE.sendTo(message, player);
	}

	/**
	 * Send Message to all in the area
	 * @param message An instance of MessageBase or messageXYZ
	 * @param point The area you want to send the Message to
	 */
	public static void sendToAllAround(IMessage message, TargetPoint point) {
		INSTANCE.sendToAllAround(message, point);
	}
	
	/**
	 * Send Message to all the players
	 * @param message An instance of MessageBase or messageXYZ
	 */
	public static void sendToAll(IMessage message) {
		INSTANCE.sendToAll(message);
	}

	/**
	 * Send Message to all the players in the dimension
	 * @param message An instance of MessageBase or messageXYZ
	 * @param dimensionId The ID of the dimension you want to send the Message to
	 */
	public static void sendToDimension(IMessage message, int dimensionId) {
		INSTANCE.sendToDimension(message, dimensionId);
	}

}