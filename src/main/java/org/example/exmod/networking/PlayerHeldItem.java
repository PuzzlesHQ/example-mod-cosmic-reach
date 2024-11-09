package org.example.exmod.networking;

import finalforeach.cosmicreach.networking.GamePacket;
import finalforeach.cosmicreach.networking.NetworkIdentity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.example.exmod.api.PlayerExtension;

public class PlayerHeldItem extends GamePacket {

    int index;

    public PlayerHeldItem(){};

    public PlayerHeldItem(int index) {
        this.index = index;
    }

    @Override
    public void receive(ByteBuf in) {
        this.index = this.readInt(in);
    }

    @Override
    public void write() {
        this.writeInt(this.index);
    }

    @Override
    public void handle(NetworkIdentity identity, ChannelHandlerContext ctx) {
        if (identity.isServer()) {
            ((PlayerExtension) identity.getPlayer())
                    .setHeldItem(identity.getPlayer().inventory.getSlot(this.index));
        }
    }
}
