/**
 * 
 */
package com.xenonteam.xenonlib.common.networking;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

import com.xenonteam.xenonlib.config.Refs;
import com.xenonteam.xenonlib.tileentity.GenericTileEntity;
import com.xenonteam.xenonlib.util.GenericUtils;

/**
 * @author tim4242
 * @author philipas
 * 
 */

@Sharable
public class DescriptionHandler extends SimpleChannelInboundHandler<FMLProxyPacket>{
    public static final String CHANNEL = Refs.MOD_ID + "Description";

    static {
        NetworkRegistry.INSTANCE.newChannel(CHANNEL, new DescriptionHandler());
    }

    public static void init(){
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FMLProxyPacket msg) throws Exception{
        ByteBuf buf = msg.payload();
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();
        TileEntity te = GenericUtils.getClientPlayer().worldObj.getTileEntity(new BlockPos(x, y, z));
        if(te instanceof GenericTileEntity) {
            ((GenericTileEntity)te).readFromPacket(buf);
        }
    }

}