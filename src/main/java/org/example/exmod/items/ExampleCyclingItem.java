package org.example.exmod.items;

import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.CosmicConstants;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.entity.IEntity;
import io.github.puzzle.cosmic.api.entity.player.IPlayer;
import io.github.puzzle.cosmic.api.item.IItemSlot;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.api.item.ITickingItem;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.item.AbstractCosmicItem;
import io.github.puzzle.cosmic.item.ItemDataPointSpecs;
import io.github.puzzle.cosmic.util.APISide;
import org.example.exmod.Constants;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ExampleCyclingItem extends AbstractCosmicItem implements ITickingItem {

    static final String CosmicAPIID = CosmicConstants.MOD_ID;

    int texture_count = 0;

    public ExampleCyclingItem() {
        super(Identifier.of(Constants.MOD_ID, "example_cycling_item"));

        addTexture(
                ItemModelType.ITEM_MODEL_3D,
                Identifier.of(CosmicAPIID, "null_stick.png"),
                Identifier.of("base", "axe_stone.png"),
                Identifier.of("base", "pickaxe_stone.png"),
                Identifier.of("base", "shovel_stone.png"),
                Identifier.of("base", "medkit.png"),
                Identifier.of(CosmicAPIID, "block_wrench.png"),
                Identifier.of(CosmicAPIID, "checker_board.png"),
                Identifier.of(CosmicAPIID, "checker_board1.png"),
                Identifier.of(CosmicAPIID, "checker_board2.png")
        );

        addTexture(
                ItemModelType.ITEM_MODEL_2D,
                Identifier.of(CosmicAPIID, "null_stick.png"),
                Identifier.of("base", "axe_stone.png"),
                Identifier.of("base", "pickaxe_stone.png"),
                Identifier.of("base", "shovel_stone.png"),
                Identifier.of("base", "medkit.png"),
                Identifier.of(CosmicAPIID, "block_wrench.png"),
                Identifier.of(CosmicAPIID, "checker_board.png"),
                Identifier.of(CosmicAPIID, "checker_board1.png"),
                Identifier.of(CosmicAPIID, "checker_board2.png")
        );

        texture_count = pGetPointManifest().get(ItemDataPointSpecs.TEXTURE_DICT).getValue().size() - 1;
    }

    @Override
    public boolean pUse(APISide side, IItemSlot itemSlot, IPlayer player, IBlockPosition targetPlaceBlockPos, IBlockPosition targetBreakBlockPos, boolean isLeftClick) {
        if ((side == APISide.REMOTE_CLIENT || side == APISide.SINGLE_PLAYER_CLIENT) && Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("https://discord.gg/XeVud4RC9U"));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean pIsTool() {
        return true;
    }

    @Override
    public String getName() {
        return "Example Cycling Item";
    }

    @Override
    public void tickStack(float fixedUpdateTimeStep, IItemStack itemStack, boolean isBeingHeld) {
        int currentEntry = getCurrentEntry(itemStack);
        currentEntry = currentEntry >= texture_count ? 0 : currentEntry + 1;
        setCurrentEntry(itemStack, currentEntry);
    }

    @Override
    public void tickEntity(IZone zone, double deltaTime, IEntity entity, IItemStack itemStack) {
        int currentEntry = getCurrentEntry(itemStack);
        currentEntry = currentEntry >= texture_count ? 0 : currentEntry + 1;
        setCurrentEntry(itemStack, currentEntry);
    }
}
