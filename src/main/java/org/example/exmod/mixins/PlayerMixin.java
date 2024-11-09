package org.example.exmod.mixins;

import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.ItemSlot;
import org.example.exmod.api.PlayerExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Player.class)
public class PlayerMixin implements PlayerExtension {

    @Unique
    private ItemSlot heldItem;

    public ItemSlot getHeldItem() {
        return heldItem;
    }

    public void setHeldItem(ItemSlot itemSlot) {
        this.heldItem = itemSlot;
    }
}
