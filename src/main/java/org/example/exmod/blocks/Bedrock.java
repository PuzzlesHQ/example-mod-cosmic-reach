package org.example.exmod.blocks;

import com.github.puzzle.game.block.IModBlock;
import com.github.puzzle.game.block.generators.BlockEventGenerator;
import com.github.puzzle.game.block.generators.BlockGenerator;
import finalforeach.cosmicreach.blockevents.BlockEventArgs;
import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.ui.UI;
import finalforeach.cosmicreach.util.Identifier;
import org.example.exmod.Constants;
import org.example.exmod.block_entities.ExampleBlockEntity;

import java.util.List;
import java.util.Map;

public class Bedrock implements IModBlock {

    public static final Identifier BLOCK_ID = Identifier.of(Constants.MOD_ID, "bedrock");

    public static final Identifier ALL_TEXTURE = Identifier.of("base", "textures/blocks/lunar_soil.png");

    @Override
    public Identifier getIdentifier() {
        return BLOCK_ID;
    }

    @Override
    public void onBreak(BlockEventArgs args) {
        ItemSlot slot = UI.hotbar.getSelectedSlot();
        if(slot == null) return;
        if(slot.itemStack != null) {
            Item selected = slot.itemStack.getItem();
            String itemId = selected.getID();
            if(itemId.startsWith(BLOCK_ID.toString())) {
                // make the block breakable when the player holds bedrock
                IModBlock.super.onBreak(args);
            }
        }
        // make the block unbreakable, by omitting the super call here
    }

    @Override
    public BlockGenerator getBlockGenerator() {
        BlockGenerator generator = new BlockGenerator(BLOCK_ID);
        generator.createBlockState("default", "model", true, Identifier.of("puzzle-loader", "base_block_model_generator"), "events", true);
        generator.addBlockEntity(ExampleBlockEntity.id.toString(), Map.of());
        return generator;
    }

    @Override
    public List<BlockEventGenerator> getBlockEventGenerators(Identifier blockId) {
        BlockEventGenerator generator = new BlockEventGenerator(blockId, "events");
        return List.of(generator);
    }
}