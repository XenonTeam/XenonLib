/**
 * 
 */
package com.xenonteam.xenonlib.common.packets;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
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

	    public static void init(){
	        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Refs.MOD_ID);
	    }
	    
	    public static void register(Class<MessageBase> c, Side Reciver) {
	    	INSTANCE.registerMessage(c, c, 0, Reciver);
	    }

	    public static void sendToServer(IMessage message){
	        INSTANCE.sendToServer(message);
	    }

	    public static void sendTo(IMessage message, EntityPlayerMP player){
	        INSTANCE.sendTo(message, player);
	    }

	    public static void sendToAllAround(IMessage message, TargetPoint point){
	        INSTANCE.sendToAllAround(message, point);
	    }

	    public static void sendToAll(IMessage message){
	        INSTANCE.sendToAll(message);
	    }

	    public static void sendToDimension(IMessage message, int dimensionId){
	        INSTANCE.sendToDimension(message, dimensionId);
	    }
}
