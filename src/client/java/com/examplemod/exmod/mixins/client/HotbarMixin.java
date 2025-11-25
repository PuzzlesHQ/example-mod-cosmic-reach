package com.examplemod.exmod.mixins.client;

import com.examplemod.exmod.api.PlayerExtension;
import com.examplemod.exmod.networking.PlayerHeldItem;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.items.ISlotContainer;
import finalforeach.cosmicreach.networking.client.ClientNetworkManager;
import finalforeach.cosmicreach.ui.Hotbar;
import finalforeach.cosmicreach.ui.UI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Hotbar.class)
public abstract class HotbarMixin {

    @Shadow public abstract short getSelectedSlotNum();

    @Shadow private ISlotContainer container;

    @Inject(method = "scrolled", at=@At(value = "RETURN"))
    private void onSoc(float amountX, float amountY, CallbackInfoReturnable<Boolean> cir) {
        if(this.container == null) return;
        if(ClientNetworkManager.isConnected()) {
            ClientNetworkManager.sendAsClient(new PlayerHeldItem(this.getSelectedSlotNum()));
        } else ((PlayerExtension) InGame.getLocalPlayer())
                .setHeldItem(InGame.getLocalPlayer().inventory.getSlot(UI.hotbar.getSelectedSlotNum()));
    }
}