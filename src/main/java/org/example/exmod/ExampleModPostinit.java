package org.example.exmod;


import com.github.puzzle.core.loader.provider.mod.entrypoint.impls.PostModInitializer;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.item.AbstractCosmicItem;
import io.github.puzzle.cosmic.item.BasicItem;
import io.github.puzzle.cosmic.item.BasicTool;
import org.example.exmod.items.ExampleCyclingItem;
import org.example.exmod.items.ExamplePickaxe;

public class ExampleModPostinit implements PostModInitializer {

    @Override
    public void onPostInit() {
        Constants.LOGGER.info("Hello From POST-INIT");
        AbstractCosmicItem.register(new ExamplePickaxe());
        AbstractCosmicItem.register(new ExampleCyclingItem());
        AbstractCosmicItem.register(new BasicItem(Identifier.of(Constants.MOD_ID, "example_item")));
        AbstractCosmicItem.register(new BasicTool(Identifier.of(Constants.MOD_ID, "stone_sword")));
    }
}
