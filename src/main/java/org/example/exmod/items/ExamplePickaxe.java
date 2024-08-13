package org.example.exmod.items;

import com.github.puzzle.core.Identifier;
import com.github.puzzle.core.resources.ResourceLocation;
import com.github.puzzle.game.items.IModItem;
import com.github.puzzle.game.items.data.DataTagManifest;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.items.ItemStack;
import org.example.exmod.Constants;

public class ExamplePickaxe implements IModItem {

    DataTagManifest tagManifest = new DataTagManifest();
    Identifier id = new Identifier(Constants.MOD_ID, "example_pickaxe");

    public ExamplePickaxe() {
        tagManifest.addTag(IModItem.TEXTURE_LOCATION_PRESET.createTag(new ResourceLocation(Constants.MOD_ID, "textures/items/example_pickaxe.png")));
        tagManifest.addTag(IModItem.MODEL_ID_PRESET.createTag(IModItem.MODEL_2_5D_ITEM));
    }

    @Override
    public boolean isTool() {
        return true;
    }

    @Override
    public float getEffectiveBreakingSpeed(ItemStack stack) {
        return stack.getItem().equals(this) ? 2.0f : 1.0f;
    }

    @Override
    public boolean isEffectiveBreaking(ItemStack itemStack, BlockState blockState) {
        return blockState.getBlockId().equals("base:aluminium_panel")
                || blockState.getBlockId().equals("base:asphalt")
                || blockState.getBlockId().equals("base:boombox")
                || blockState.getBlockId().equals("base:c4")
                || blockState.getBlockId().equals("base:hazard")
                || blockState.getBlockId().equals("base:light")
                || blockState.getBlockId().equals("base:magma")
                || blockState.getBlockId().equals("base:metal_panel")
                || blockState.getBlockId().equals("base:stone_basalt")
                || blockState.getBlockId().equals("base:stone_gabbro")
                || blockState.getBlockId().equals("base:stone_limestone")
                || blockState.getBlockId().equals("base:lunar_soil_packed");
    }

    @Override
    public Identifier getIdentifier() {
        return id;
    }

    @Override
    public DataTagManifest getTagManifest() {
        return tagManifest;
    }

    @Override
    public boolean isCatalogHidden() {
        return false;
    }
}
