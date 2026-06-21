package com.examplemod.networking;

import com.badlogic.gdx.math.Vector3;
import com.examplemod.Constants;
import com.examplemod.api.IPlayer;
import finalforeach.cosmicreach.networking.GamePacket;
import finalforeach.cosmicreach.networking.NetworkIdentity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class PlayerViewDirectionPacket extends GamePacket {

    Vector3 vector3;

    public PlayerViewDirectionPacket(Vector3 vector3) {
        this.vector3 = vector3;
    }

    @Override
    public void receive(ByteBuf in) {
        this.readVector3(in, this.vector3);
    }

    @Override
    public void write() {
        this.writeVector3(this.vector3);
    }

    @Override
    public void handle(NetworkIdentity identity, ChannelHandlerContext ctx) {
        if (identity.isServer()) {
            Constants.LOGGER.info(vector3);
        }
    }
}
