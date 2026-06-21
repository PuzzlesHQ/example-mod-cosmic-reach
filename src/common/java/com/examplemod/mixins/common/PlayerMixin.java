package com.examplemod.mixins.common;

import com.badlogic.gdx.math.Vector3;
import com.examplemod.api.IPlayer;
import finalforeach.cosmicreach.entities.GameEntity;
import finalforeach.cosmicreach.entities.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Player.class)
public class PlayerMixin implements IPlayer {

    @Shadow
    private GameEntity entity;

    @Override
    public Vector3 getViewDirection() {
        return entity.viewDirection;
    }

    @Override
    public void setViewDirection(Vector3 viewDirection) {
        entity.viewDirection = viewDirection;
    }
}
