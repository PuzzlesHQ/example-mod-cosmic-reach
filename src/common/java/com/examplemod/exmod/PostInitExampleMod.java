package com.examplemod.exmod;

import com.examplemod.exmod.items.ExampleCyclingItem;
import com.examplemod.exmod.items.ExamplePickaxe;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.common.PostModInit;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.item.AbstractCosmicItem;
import io.github.puzzle.cosmic.item.BasicItem;
import io.github.puzzle.cosmic.item.BasicTool;

public class PostInitExampleMod implements PostModInit {

    @Override
    public void onPostInit() {
        Constants.LOGGER.info("Hello from {}", PostInitExampleMod.class);
        AbstractCosmicItem.register(new ExamplePickaxe());
        AbstractCosmicItem.register(new ExampleCyclingItem());
        AbstractCosmicItem.register(new BasicItem(Identifier.of(Constants.MOD_ID, "example_item")) {
        });
        AbstractCosmicItem.register(new BasicTool(Identifier.of(Constants.MOD_ID, "stone_sword")) {
        });
    }
}
