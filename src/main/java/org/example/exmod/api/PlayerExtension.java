package org.example.exmod.api;

import finalforeach.cosmicreach.items.ItemSlot;

public interface PlayerExtension {

    ItemSlot getHeldItem();

    void setHeldItem(ItemSlot itemSlot);
}
