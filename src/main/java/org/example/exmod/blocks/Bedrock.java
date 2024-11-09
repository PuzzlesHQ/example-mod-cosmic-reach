package org.example.exmod.blocks;

import com.github.puzzle.core.loader.launch.Piece;
import com.github.puzzle.core.loader.meta.EnvType;
import com.github.puzzle.game.block.IModBlock;
import com.github.puzzle.game.block.generators.BlockEventGenerator;
import com.github.puzzle.game.block.generators.BlockGenerator;
import com.github.puzzle.game.block.generators.model.BlockModelGenerator;
import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.blockevents.BlockEventArgs;
import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import finalforeach.cosmicreach.util.Identifier;
import org.example.exmod.Constants;
import org.example.exmod.api.PlayerExtension;
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
        IModBlock.super.onPlace(args);
        ItemSlot slot = ((PlayerExtension) args.srcIdentity.getPlayer()).getHeldItem();

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
        generator.createBlockState("default", "model", true, "base_block_model_generator", true);
        generator.addBlockEntity(ExampleBlockEntity.id.toString(), Map.of());
        return generator;
    }

    @Override
    public List<BlockModelGenerator> getBlockModelGenerators(Identifier blockId) {
        BlockModelGenerator generator = new BlockModelGenerator(blockId, "model");
        generator.createTexture("all", ALL_TEXTURE);
        generator.createCuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F, "all");
        return List.of(generator);
    }

    @Override
    public List<BlockEventGenerator> getBlockEventGenerators(Identifier blockId) {
        BlockEventGenerator generator = new BlockEventGenerator(blockId, "base_block_model_generator");
        return List.of(generator);
    }
}