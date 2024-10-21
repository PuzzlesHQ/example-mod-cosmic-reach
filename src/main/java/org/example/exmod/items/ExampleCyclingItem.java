package org.example.exmod.items;

import com.github.puzzle.game.items.IModItem;
import com.github.puzzle.game.items.ITickingItem;
import com.github.puzzle.game.items.data.DataTagManifest;
import com.github.puzzle.game.items.data.attributes.ListDataAttribute;
import finalforeach.cosmicreach.entities.ItemEntity;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.util.Identifier;
import finalforeach.cosmicreach.world.Zone;
import org.example.exmod.Constants;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ExampleCyclingItem implements IModItem, ITickingItem {

    DataTagManifest tagManifest = new DataTagManifest();
    Identifier id = Identifier.of(Constants.MOD_ID, "example_cycling_item");
    static final String PuzzleID = com.github.puzzle.core.Constants.MOD_ID;

    @Override
    public String toString() {
        return id.toString();
    }

    int texture_count = 0;

    public ExampleCyclingItem() {
        addTexture(
                IModItem.MODEL_2_5D_ITEM,
                Identifier.of(PuzzleID, "null_stick.png"),
                Identifier.of("base", "axe_stone.png"),
                Identifier.of("base", "pickaxe_stone.png"),
                Identifier.of("base", "shovel_stone.png"),
                Identifier.of("base", "medkit.png"),
                Identifier.of(PuzzleID, "block_wrench.png"),
                Identifier.of(PuzzleID, "checker_board.png"),
                Identifier.of(PuzzleID, "checker_board1.png"),
                Identifier.of(PuzzleID, "checker_board2.png")
        );

        addTexture(
                IModItem.MODEL_2D_ITEM,
                Identifier.of(PuzzleID, "null_stick.png"),
                Identifier.of("base", "axe_stone.png"),
                Identifier.of("base", "pickaxe_stone.png"),
                Identifier.of("base", "shovel_stone.png"),
                Identifier.of("base", "medkit.png"),
                Identifier.of(PuzzleID, "block_wrench.png"),
                Identifier.of(PuzzleID, "checker_board.png"),
                Identifier.of(PuzzleID, "checker_board1.png"),
                Identifier.of(PuzzleID, "checker_board2.png")
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

    @Override
    public String getName() {
        return "Example Cycling Item";
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
