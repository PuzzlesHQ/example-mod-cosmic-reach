package org.example.exmod;

import com.github.puzzle.core.loader.provider.mod.entrypoint.impls.ModInitializer;
import com.github.puzzle.core.localization.ILanguageFile;
import com.github.puzzle.core.localization.LanguageManager;
import com.github.puzzle.core.localization.files.LanguageFileVersion1;
import com.github.puzzle.game.PuzzleRegistries;
import com.github.puzzle.game.block.DataModBlock;
import com.github.puzzle.game.events.OnPreLoadAssetsEvent;
import com.github.puzzle.game.events.OnRegisterBlockEvent;
import com.github.puzzle.game.events.OnRegisterZoneGenerators;
import com.github.puzzle.game.items.IModItem;
import com.github.puzzle.game.items.impl.BasicItem;
import com.github.puzzle.game.items.impl.BasicTool;
import com.github.puzzle.game.resources.PuzzleGameAssetLoader;
import finalforeach.cosmicreach.networking.GamePacket;
import finalforeach.cosmicreach.util.Identifier;
import meteordevelopment.orbit.EventHandler;
import org.example.exmod.block_entities.ExampleBlockEntity;
import org.example.exmod.blocks.Bedrock;
import org.example.exmod.commands.Commands;
import org.example.exmod.items.ExampleCyclingItem;
import org.example.exmod.items.ExamplePickaxe;
import org.example.exmod.networking.PlayerHeldItem;
import org.example.exmod.worldgen.ExampleZoneGenerator;

import java.io.IOException;
import java.util.Objects;

public class ExampleMod implements ModInitializer {
    @Override
    public void onInit() {

        PuzzleRegistries.EVENT_BUS.subscribe(this);

        Constants.LOGGER.info("Hello From INIT");
        Commands.register();
        ExampleBlockEntity.register();
        GamePacket.registerPacket(PlayerHeldItem.class);

        IModItem.registerItem(new ExamplePickaxe());
        IModItem.registerItem(new ExampleCyclingItem());
        IModItem.registerItem(new BasicItem(Identifier.of(Constants.MOD_ID, "example_item")));
        IModItem.registerItem(new BasicTool(Identifier.of(Constants.MOD_ID, "stone_sword")));
    }

    @EventHandler
    public void onEvent(OnRegisterBlockEvent event) {
        event.registerBlock(() -> new DataModBlock(Identifier.of(Constants.MOD_ID, "diamond_block.json")));
        event.registerBlock(Bedrock::new);
    }

    @EventHandler
    public void onEvent(OnRegisterZoneGenerators event) {
        event.registerGenerator(ExampleZoneGenerator::new);
    }

    @EventHandler
    public void onEvent(OnPreLoadAssetsEvent event) {
        ILanguageFile lang = null;
        try {
            lang = LanguageFileVersion1.loadLanguageFile(
                    Objects.requireNonNull(PuzzleGameAssetLoader.locateAsset(Identifier.of(Constants.MOD_ID, "languages/en-US.json")))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LanguageManager.registerLanguageFile(lang);
    }
}
