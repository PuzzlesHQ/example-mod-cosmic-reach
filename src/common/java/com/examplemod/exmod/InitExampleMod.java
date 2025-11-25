package com.examplemod.exmod;

import com.examplemod.exmod.block_entities.ExampleBlockEntity;
import com.examplemod.exmod.commands.SetBlockCommand;
import com.examplemod.exmod.networking.PlayerHeldItem;
import com.examplemod.exmod.worldgen.ExampleZoneGenerator;
import dev.puzzleshq.puzzleloader.cosmic.game.GameRegistries;
import dev.puzzleshq.puzzleloader.cosmic.game.events.command.EventRegisterCommand;
import dev.puzzleshq.puzzleloader.cosmic.game.events.zone.EventRegisterZoneGenerator;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.common.ModInit;
import finalforeach.cosmicreach.GameAssetLoader;
import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.networking.GamePacket;
import finalforeach.cosmicreach.util.Identifier;
import net.neoforged.bus.api.SubscribeEvent;

import static com.examplemod.exmod.Constants.MOD_ID;

public class InitExampleMod implements ModInit {
    @Override
    public void onInit() {

        GameRegistries.COSMIC_EVENT_BUS.register(this);

        Constants.LOGGER.info("Hello from {}", InitExampleMod.class);
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
