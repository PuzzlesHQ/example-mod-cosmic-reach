package com.examplemod.exmod;

import com.examplemod.exmod.block_entities.ExampleBlockEntity;
import com.examplemod.exmod.commands.SetBlockCommand;
import com.examplemod.exmod.items.ExampleCyclingItem;
import com.examplemod.exmod.items.ExamplePickaxe;
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
import io.github.puzzle.cosmic.impl.event.EventLoadingQueue;
import io.github.puzzle.cosmic.item.AbstractCosmicItem;
import io.github.puzzle.cosmic.item.BasicItem;
import io.github.puzzle.cosmic.item.BasicTool;
import net.neoforged.bus.api.SubscribeEvent;

import static com.examplemod.exmod.Constants.MOD_ID;

public class InitExampleMod implements ModInit {

    public InitExampleMod(){
        GameRegistries.COSMIC_EVENT_BUS.register(this);
    }

    @Override
    public void onInit() {
        Constants.LOGGER.info("Hello from {}", InitExampleMod.class);
        GamePacket.registerPacket(PlayerHeldItem.class);

    }

    @SubscribeEvent
    public void onEvent(EventLoadingQueue event){
        event.registerToQueue(() -> {
            Block.loadBlock(GameAssetLoader.loadAsset(Identifier.of(MOD_ID, "blocks/diamond_block.json")));
            Block.loadBlock(GameAssetLoader.loadAsset(Identifier.of(MOD_ID, "blocks/block_entities.json")));
        });
        event.registerToQueue(() -> {
            ExampleBlockEntity.register();
        });
        event.registerToQueue(() -> {
            AbstractCosmicItem.register(new ExamplePickaxe());
            AbstractCosmicItem.register(new ExampleCyclingItem());
            AbstractCosmicItem.register(new BasicItem(Identifier.of(Constants.MOD_ID, "example_item")) {
            });
            AbstractCosmicItem.register(new BasicTool(Identifier.of(Constants.MOD_ID, "stone_sword")) {
            });
        });
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
