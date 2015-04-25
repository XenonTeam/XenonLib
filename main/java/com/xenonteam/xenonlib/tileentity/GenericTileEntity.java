/**
 * 
 */
package com.xenonteam.xenonlib.tileentity;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

import com.xenonteam.xenonlib.common.networking.DescriptionHandler;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public class GenericTileEntity extends TileEntity implements IGenericTileEntity{

    @Override
    public Packet getDescriptionPacket(){
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
        writeToPacket(buf);
        return new FMLProxyPacket((PacketBuffer) buf, DescriptionHandler.CHANNEL);
    }
    

	@Override
	public void writeToPacket(ByteBuf buf) {
		
	}

	@Override
	public void readFromPacket(ByteBuf buf) {
		
	}

	@Override
	public void onGuiButtonPress(int id) {
		
	}

	@Override
	public void onGuiTextfieldUpdate(int id, String text) {
		
	}

}
