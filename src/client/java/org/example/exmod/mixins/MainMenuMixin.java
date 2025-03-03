package org.example.exmod.mixins;

import finalforeach.cosmicreach.gamestates.MainMenu;
import org.example.exmod.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MainMenu.class)
public class MainMenuMixin {

    @Inject(method = "create", at = @At("HEAD"))
    private void create0(CallbackInfo ci) {
        Constants.LOGGER.info("THE START OF THE MAIN MENU's create()");
    }

    @Inject(method = "create", at = @At("TAIL"))
    private void create1(CallbackInfo ci) {
        Constants.LOGGER.info("THE END OF THE MAIN MENU's create()");
    }

    @Redirect(method = "create", at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/lang/Lang;get(Ljava/lang/String;)Ljava/lang/String;", ordinal = 0))
    private String setText(String key) {
        return "Better Button?";
    }



}
