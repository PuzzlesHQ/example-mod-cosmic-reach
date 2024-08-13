package org.example.exmod.block_entities;

import com.badlogic.gdx.graphics.Camera;
import com.github.puzzle.core.Identifier;
import com.github.puzzle.game.blockentities.ExtendedBlockEntity;
import com.github.puzzle.game.blockentities.IRenderable;
import com.github.puzzle.game.blockentities.ITickable;
import com.github.puzzle.game.util.BlockUtil;
import finalforeach.cosmicreach.blockentities.BlockEntityCreator;
import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.world.Zone;
import org.example.exmod.Constants;

public class ExampleBlockEntity extends ExtendedBlockEntity implements IRenderable, ITickable {

    static Identifier id = new Identifier(Constants.MOD_ID, "example_entity");

    public static void register() {
        BlockEntityCreator.registerBlockEntityCreator(id.toString(), (block, zone, x, y, z) -> new ExampleBlockEntity(zone, x, y, z));
    }

    public ExampleBlockEntity(Zone zone, int x, int y, int z) {
        super(zone, x, y, z);
    }

    @Override
    public String getBlockEntityId() {
        return id.toString();
    }

    @Override
    public void onTick(float tps) {
        BlockPosition above = BlockUtil.getBlockPosAtVec(zone, x, y, z).getOffsetBlockPos(zone, 0, 1, 0);
        BlockState current = above.getBlockState();
        if(current.getBlock() == Block.AIR) {
            above.setBlockState(Block.GRASS.getDefaultBlockState());
            above.flagTouchingChunksForRemeshing(zone, false);
        }
    }

    @Override
    public void onRender(Camera camera) {
        // add custom rendering logic here

    }
}