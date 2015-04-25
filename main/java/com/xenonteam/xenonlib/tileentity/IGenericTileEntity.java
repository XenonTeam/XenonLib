/**
 * 
 */
package com.xenonteam.xenonlib.tileentity;

import io.netty.buffer.ByteBuf;

/**
 * @author tim4242
 * @author philipas
 * 
 */
public interface IGenericTileEntity {
    public void writeToPacket(ByteBuf buf);

    public void readFromPacket(ByteBuf buf);

    public void onGuiButtonPress(int id);

    public void onGuiTextfieldUpdate(int id, String text);
    
}
