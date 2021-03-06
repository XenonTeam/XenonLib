/**
 * 
 */
package com.xenonteam.xenonlib.common.networking.packet;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import com.xenonteam.xenonlib.common.networking.DescriptionHandler.XSide;
import com.xenonteam.xenonlib.config.Refs;
import com.xenonteam.xenonlib.util.Log;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public final class NetworkHandler
{

	private static SimpleNetworkWrapper INSTANCE;
	private static int lastID = -1;
	private static boolean hasInit = false;

	/**
	 * Just the init of the networkWrapper do not use this
	 */
	@Deprecated
	public static void init()
	{
		if (!hasInit)
		{
			INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Refs.MOD_ID);
			hasInit = true;
		}
	}

	/**
	 * 
	 * @param messageHandler
	 *            class that extends MessegeBase or MessageXYZ
	 * @param side
	 *            The receiver side
	 */
	public static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, XSide side)
	{
		lastID++;
		Class<REQ> requestMessageType = (Class<REQ>) messageHandler;

		for (Side s : side.getSides())
			INSTANCE.registerMessage(messageHandler, requestMessageType, lastID, s);
		// Not necessary already done in Log.debug(Object...)
		Log.debug("Class " + requestMessageType.toString() + " successfuly registerd");
	}

	/**
	 * DO NOT USE THIS METHOD IT'S UNSAFE AND JUST FOR INTERNAL USE!!!
	 */
	@Deprecated
	public static <REQ extends IMessage, REPLY extends IMessage> void registerMessage0(Class<? extends IMessageHandler<? extends IMessage, ? extends IMessage>> messageHandler, XSide side)
	{
		registerMessage((Class<? extends IMessageHandler<REQ, REPLY>>) messageHandler, side);
	}

	/**
	 * Send Message to the server
	 * 
	 * @param message
	 *            An instance of MessageBase or messageXYZ
	 */
	public static void sendToServer(IMessage message)
	{
		INSTANCE.sendToServer(message);
	}

	/**
	 * Send Message to a Player
	 * 
	 * @param message
	 *            An instance of MessageBase or messageXYZ
	 * @param player
	 *            The player you want to send the Message to
	 */
	public static void sendTo(IMessage message, EntityPlayerMP player)
	{
		INSTANCE.sendTo(message, player);
	}

	/**
	 * send the given Message to every player within 64 blocks of the XYZ of the
	 * XYZ packet.
	 * 
	 * @param message
	 * @param world
	 */
	public static void sendToAllAround(MessageXYZ message, World world)
	{
		INSTANCE.sendToAllAround(message, new TargetPoint(world.provider.getDimensionId(), message.x, message.y, message.z, 64D));
	}
	
	/**
	 * send the given Message to every player within a specified range of blocks of the XYZ of the
	 * XYZ packet.
	 * 
	 * @param message
	 * @param world
	 */
	public static void sendToAllInRange(MessageXYZ message, World world, int range)
	{
		INSTANCE.sendToAllAround(message, new TargetPoint(world.provider.getDimensionId(), message.x, message.y, message.z, range));
	}

	/**
	 * Send Message to all in the area
	 * 
	 * @param message
	 *            An instance of MessageBase or messageXYZ
	 * @param point
	 *            The area you want to send the Message to
	 */
	public static void sendToAllAround(IMessage message, TargetPoint point)
	{
		INSTANCE.sendToAllAround(message, point);
	}

	/**
	 * Send Message to all the players
	 * 
	 * @param message
	 *            An instance of MessageBase or messageXYZ
	 */
	public static void sendToAll(IMessage message)
	{
		INSTANCE.sendToAll(message);
	}

	/**
	 * Send Message to all the players in the dimension
	 * 
	 * @param message
	 *            An instance of MessageBase or messageXYZ
	 * @param dimensionId
	 *            The ID of the dimension you want to send the Message to
	 */
	public static void sendToDimension(IMessage message, int dimensionId)
	{
		INSTANCE.sendToDimension(message, dimensionId);
	}

}