package org.example.exmod.block_entities;

import com.github.puzzle.game.util.BlockUtil;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blockentities.BlockEntityCreator;
import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.util.Identifier;
import finalforeach.cosmicreach.world.BlockSetter;
import finalforeach.cosmicreach.world.Zone;
import org.example.exmod.Constants;

public class ExampleBlockEntity extends BlockEntity {

    public static Identifier id = Identifier.of(Constants.MOD_ID, "example_entity");

    public static void register() {
        BlockEntityCreator.registerBlockEntityCreator(id.toString(), (block, zone, x, y, z) -> new ExampleBlockEntity(zone, x, y, z));
    }

    Zone zone;
    int x, y, z;

    public ExampleBlockEntity(Zone zone, int x, int y, int z) {
        super(zone, x, y, z);

        this.zone = zone;
        this.x = x;
        this.y = y;
        this.z = z;
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
        BlockPosition above = BlockUtil.getBlockPosAtVec(zone, x, y, z).getOffsetBlockPos(zone, 0, 1, 0);
        BlockState current = above.getBlockState();
        if(current.getBlock() == Block.AIR) {
            BlockSetter.get().replaceBlock(zone, Block.GRASS.getDefaultBlockState(), above);
        }
    }

}