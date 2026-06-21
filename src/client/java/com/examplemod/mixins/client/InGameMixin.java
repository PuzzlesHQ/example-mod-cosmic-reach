package com.examplemod.mixins.client;

import com.examplemod.InitExampleMod;
import com.examplemod.api.IPlayer;
import com.examplemod.networking.PlayerViewDirectionPacket;
import finalforeach.cosmicreach.ClientZoneLoader;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.networking.client.ClientNetworkManager;
import finalforeach.cosmicreach.ui.UI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGame.class)
public class InGameMixin {

    @Shadow
    public static Player getLocalPlayer() {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    @Inject(method = "update", at = @At("TAIL"))
    public void update(CallbackInfo ci){
        if (ClientZoneLoader.currentInstance != null) {
            if(ClientNetworkManager.isConnected() && InitExampleMod.serverHasMod) {
                ClientNetworkManager.sendAsClient(new PlayerViewDirectionPacket(getLocalPlayer().getEntity().viewDirection));
            }
        }
    }

}
