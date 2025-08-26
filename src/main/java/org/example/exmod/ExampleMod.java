package org.example.exmod;

import dev.puzzleshq.puzzleloader.cosmic.core.modInitialises.ModInit;
import dev.puzzleshq.puzzleloader.cosmic.game.GameRegistries;
import dev.puzzleshq.puzzleloader.cosmic.game.events.command.EventRegisterCommand;
import dev.puzzleshq.puzzleloader.cosmic.game.events.zone.EventRegisterZoneGenerator;
import finalforeach.cosmicreach.GameAssetLoader;
import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.networking.GamePacket;
import finalforeach.cosmicreach.util.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import org.example.exmod.block_entities.ExampleBlockEntity;
import org.example.exmod.commands.SetBlockCommand;
import org.example.exmod.networking.PlayerHeldItem;
import org.example.exmod.worldgen.ExampleZoneGenerator;

import static org.example.exmod.Constants.MOD_ID;

public class ExampleMod implements ModInit {
    @Override
    public void onInit() {

        GameRegistries.COSMIC_EVENT_BUS.register(this);

        Constants.LOGGER.info("Hello From INIT");
        ExampleBlockEntity.register();
        GamePacket.registerPacket(PlayerHeldItem.class);

        Block.loadBlock(GameAssetLoader.loadAsset(Identifier.of(MOD_ID, "blocks/diamond_block.json")));
        Block.loadBlock(GameAssetLoader.loadAsset(Identifier.of(MOD_ID, "blocks/block_entities.json")));
    }

    @SubscribeEvent
    public void onEvent(EventRegisterZoneGenerator event) {
        event.registerGenerator(ExampleZoneGenerator::new);
    }

    @SubscribeEvent
    public void onEvent(EventRegisterCommand event) {
        event.register(SetBlockCommand::new, "setblock");
    }

}
