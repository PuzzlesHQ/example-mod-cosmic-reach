package org.example.exmod.items;

import com.github.puzzle.core.Identifier;
import com.github.puzzle.core.Puzzle;
import com.github.puzzle.core.resources.ResourceLocation;
import com.github.puzzle.game.items.IModItem;
import com.github.puzzle.game.items.ITickingItem;
import com.github.puzzle.game.items.data.DataTag;
import com.github.puzzle.game.items.data.DataTagManifest;
import com.github.puzzle.game.items.data.attributes.IntDataAttribute;
import com.github.puzzle.game.items.data.attributes.ListDataAttribute;
import com.github.puzzle.game.util.DataTagUtil;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.ItemEntity;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.world.Zone;
import org.example.exmod.Constants;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ExampleCyclingItem implements IModItem, ITickingItem {

    DataTagManifest tagManifest = new DataTagManifest();
    Identifier id = new Identifier(Constants.MOD_ID, "example_cycling_item");

    @Override
    public String toString() {
        return id.toString();
    }

    int texture_count = 0;

    public ExampleCyclingItem() {
        addTexture(
                IModItem.MODEL_2_5D_ITEM,
                new ResourceLocation(Puzzle.MOD_ID, "textures/items/null_stick.png"),
                new ResourceLocation("base", "textures/items/axe_stone.png"),
                new ResourceLocation("base", "textures/items/pickaxe_stone.png"),
                new ResourceLocation("base", "textures/items/shovel_stone.png"),
                new ResourceLocation("base", "textures/items/medkit.png"),
                new ResourceLocation(Puzzle.MOD_ID, "textures/items/block_wrench.png"),
                new ResourceLocation(Puzzle.MOD_ID, "textures/items/checker_board.png"),
                new ResourceLocation(Puzzle.MOD_ID, "textures/items/checker_board1.png"),
                new ResourceLocation(Puzzle.MOD_ID, "textures/items/checker_board2.png")
        );

        addTexture(
                IModItem.MODEL_2D_ITEM,
                new ResourceLocation(Puzzle.MOD_ID, "textures/items/null_stick.png"),
                new ResourceLocation("base", "textures/items/axe_stone.png"),
                new ResourceLocation("base", "textures/items/pickaxe_stone.png"),
                new ResourceLocation("base", "textures/items/shovel_stone.png"),
                new ResourceLocation("base", "textures/items/medkit.png"),
                new ResourceLocation(Puzzle.MOD_ID, "textures/items/block_wrench.png"),
                new ResourceLocation(Puzzle.MOD_ID, "textures/items/checker_board.png"),
                new ResourceLocation(Puzzle.MOD_ID, "textures/items/checker_board1.png"),
                new ResourceLocation(Puzzle.MOD_ID, "textures/items/checker_board2.png")
        );

        texture_count = ((ListDataAttribute) getTagManifest().getTag("textures").attribute).getValue().size() - 1;
    }

    @Override
    public boolean isTool() {
        return true;
    }

    @Override
    public void use(ItemSlot slot, Player player) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("https://discord.gg/VeEnVHwRXN"));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
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

    int getCurrentEntry(ItemStack stack) {
        DataTagManifest manifest = DataTagUtil.getManifestFromStack(stack);
        if (!manifest.hasTag("currentEntry")) manifest.addTag(new DataTag<>("currentEntry", new IntDataAttribute(0)));
        return manifest.getTag("currentEntry").getTagAsType(Integer.class).getValue();
    }

    void setCurrentEntry(ItemStack stack, int entry) {
        DataTagManifest manifest = DataTagUtil.getManifestFromStack(stack);
        manifest.addTag(new DataTag<>("currentEntry", new IntDataAttribute(entry)));
        DataTagUtil.setManifestOnStack(manifest, stack);
    }

    @Override
    public void tickStack(float fixedUpdateTimeStep, ItemStack stack, boolean isBeingHeld) {
        int textureEntry = getCurrentEntry(stack);
        textureEntry = textureEntry >= texture_count ? 0 : textureEntry + 1;
        setCurrentEntry(stack, textureEntry);
    }

    @Override
    public void tickEntity(Zone zone, double deltaTime, ItemEntity entity, ItemStack stack) {
        int textureEntry = getCurrentEntry(stack);
        textureEntry = textureEntry >= texture_count ? 0 : textureEntry + 1;
        setCurrentEntry(stack, textureEntry);
    }
}
