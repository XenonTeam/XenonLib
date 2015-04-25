/**
 * 
 */
package com.xenonteam.xenonlib.common.networking.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.xenonteam.xenonlib.client.gui.GenericGui;
import com.xenonteam.xenonlib.tileentity.GenericTileEntity;

/**
 * @author tim4242
 * @author philipas
 * 
 */

public class MessageHandleTextUpdate extends MessageXYZ<MessageHandleTextUpdate>{
    private int id;
    private String text;

    public MessageHandleTextUpdate(){}

    public MessageHandleTextUpdate(GenericTileEntity te, int id, String text){
        super(te);
        this.id = id;
        this.text = text;
    }

    @Override
    public void fromBytes(ByteBuf buf){
        super.fromBytes(buf);
        id = buf.readInt();
        text = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf){
        super.toBytes(buf);
        buf.writeInt(id);
        ByteBufUtils.writeUTF8String(buf, text);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleClientSide(MessageHandleTextUpdate message, EntityPlayer player){
        handleServerSide(message, player);
        GuiScreen gui = Minecraft.getMinecraft().currentScreen;
        if(gui instanceof GenericGui) {
            ((GenericGui)gui).onTextfieldUpdate(message.id);
        }
    }

    @Override
    public void handleServerSide(MessageHandleTextUpdate message, EntityPlayer player){
        TileEntity te = message.getTileEntity(player.worldObj);
        if(te instanceof GenericTileEntity) {
            ((GenericTileEntity)te).onGuiTextfieldUpdate(message.id, message.text);
        }
    }

}