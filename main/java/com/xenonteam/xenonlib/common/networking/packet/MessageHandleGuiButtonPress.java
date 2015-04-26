/**
 * 
 */
package com.xenonteam.xenonlib.common.networking.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

import com.xenonteam.xenonlib.common.networking.DescriptionHandler.XSide;
import com.xenonteam.xenonlib.registry.Register;
import com.xenonteam.xenonlib.tileentity.GenericTileEntity;

/**
 * @author tim4242
 * @author philipas
 * 
 */

@Register(side = XSide.SERVER)
public final class MessageHandleGuiButtonPress extends MessageXYZ<MessageHandleGuiButtonPress>
{
	private int id;

	public MessageHandleGuiButtonPress()
	{
	}

	public MessageHandleGuiButtonPress(GenericTileEntity te, int id)
	{
		super(te);
		this.id = id;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		super.fromBytes(buf);
		id = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		super.toBytes(buf);
		buf.writeInt(id);
	}

	@Override
	public void handleClientSide(MessageHandleGuiButtonPress message, EntityPlayer player)
	{

	}

	@Override
	public void handleServerSide(MessageHandleGuiButtonPress message, EntityPlayer player)
	{
		TileEntity te = message.getTileEntity(player.worldObj);
		if (te instanceof GenericTileEntity)
		{
			((GenericTileEntity) te).onGuiButtonPress(message.id);
		}
	}

}