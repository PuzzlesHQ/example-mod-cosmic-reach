package org.example.exmod.block_entities;

import com.badlogic.gdx.graphics.Camera;
import com.github.puzzle.core.Identifier;
import com.github.puzzle.game.blockentities.IRenderable;
import com.github.puzzle.game.util.BlockUtil;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blockentities.BlockEntityCreator;
import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.io.CRBinDeserializer;
import finalforeach.cosmicreach.world.Zone;
import org.example.exmod.Constants;

public class ExampleBlockEntity extends BlockEntity implements IRenderable {

    public static Identifier id = new Identifier(Constants.MOD_ID, "example_entity");

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
        setTicking(true);
        super.onCreate(blockState);
    }

    @Override
    public void onRemove() {
        setTicking(false);
        super.onRemove();
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
            above.setBlockState(Block.GRASS.getDefaultBlockState());
            above.flagTouchingChunksForRemeshing(zone, false);
        }
    }

    @Override
    public void onRender(Camera camera) {
        // add custom rendering logic here

    }
}