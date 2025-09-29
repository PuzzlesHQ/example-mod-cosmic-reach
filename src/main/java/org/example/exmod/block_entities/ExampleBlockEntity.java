package org.example.exmod.block_entities;

import finalforeach.cosmicreach.blocks.blockentities.BlockEntityCreator;
import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.util.Identifier;
import finalforeach.cosmicreach.world.BlockSetter;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.blockentity.AbstractCosmicBlockEntity;
import org.example.exmod.Constants;

public class ExampleBlockEntity extends AbstractCosmicBlockEntity {

    public static void register() {
        id = Identifier.of(Constants.MOD_ID, "example_entity");
        BlockEntityCreator.registerBlockEntityCreator(id.toString(), (block, zone, x, y, z) -> new ExampleBlockEntity(zone, x, y, z));
    }

    public ExampleBlockEntity(Zone zone, int x, int y, int z) {
        super(zone, x, y, z);
    }

    @Override
    public void onCreate(BlockState blockState) {
        super.onCreate(blockState);
        setTicking(true);
    }

    @Override
    public void onRemove() {
        super.onRemove();
        setTicking(false);
    }

    @Override
    public String getBlockEntityId() {
        return id.toString();
    }

    @Override
    public void onTick() {
        BlockPosition above = BlockPosition.ofGlobal(zone, x, y, z).getOffsetBlockPos(zone, 0, 1, 0);
        BlockState current = above.getBlockState();
        if(current.getBlock() == Block.AIR) {
            BlockSetter.get().replaceBlock(zone, Block.GRASS.getDefaultBlockState(), above);
        }
    }

}