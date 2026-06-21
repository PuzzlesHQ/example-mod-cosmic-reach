package com.examplemod;

import com.examplemod.block_entities.ExampleBlockEntity;
import com.examplemod.commands.SetBlockCommand;
import com.examplemod.items.ExampleCyclingItem;
import com.examplemod.items.ExamplePickaxe;
import com.examplemod.networking.PlayerViewDirectionPacket;
import dev.puzzleshq.puzzleloader.cosmic.game.GameRegistries;
import dev.puzzleshq.puzzleloader.cosmic.game.events.command.EventRegisterCommand;
import dev.puzzleshq.puzzleloader.cosmic.game.events.net.EventRegisterPacket;
import dev.puzzleshq.puzzleloader.cosmic.game.events.net.mods.EventReceiveClientModList;
import dev.puzzleshq.puzzleloader.cosmic.game.events.net.mods.EventReceiveServerModList;
import dev.puzzleshq.puzzleloader.cosmic.game.network.packet.packets.PacketModListExchange;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.common.ModInit;
import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.util.Identifier;
import finalforeach.cosmicreach.util.assets.GameAssetLoader;
import io.github.puzzle.cosmic.impl.event.EventLoadingQueue;
import io.github.puzzle.cosmic.item.AbstractCosmicItem;
import io.github.puzzle.cosmic.item.BasicItem;
import io.github.puzzle.cosmic.item.BasicTool;
import net.neoforged.bus.api.SubscribeEvent;

import java.util.Objects;
import java.util.Set;

import static com.examplemod.Constants.MOD_ID;

public class InitExampleMod implements ModInit {

    public static boolean vanillaServer = true;
    public static boolean serverHasMod = false;

    public static boolean vanillaClient = true;
    public static boolean clientHasMod = false;

    public InitExampleMod(){
        GameRegistries.COSMIC_EVENT_BUS.register(this);
        GameRegistries.NETWORK_EVENT_BUS.register(this);
    }

    @Override
    public void onInit() {
        Constants.LOGGER.info("Hello from {}", InitExampleMod.class);
    }

    @SubscribeEvent
    public void onEvent(EventLoadingQueue event){
        event.registerToQueue(() -> {
            Block.loadBlock(GameAssetLoader.loadAsset(Identifier.of(MOD_ID, "blocks/diamond_block.json")));
            Block.loadBlock(GameAssetLoader.loadAsset(Identifier.of(MOD_ID, "blocks/block_entities.json")));
        });
        event.registerToQueue(() -> {
            ExampleBlockEntity.register();
        });
        event.registerToQueue(() -> {
            AbstractCosmicItem.register(new ExamplePickaxe());
            AbstractCosmicItem.register(new ExampleCyclingItem());
            AbstractCosmicItem.register(new BasicItem(Identifier.of(Constants.MOD_ID, "example_item")) {
            });
            AbstractCosmicItem.register(new BasicTool(Identifier.of(Constants.MOD_ID, "stone_sword")) {
            });
        });
    }

    @SubscribeEvent
    public void onEvent(EventRegisterCommand event) {
        event.register(SetBlockCommand::new, "setblock");
    }

    @SubscribeEvent
    public void onEvent(EventRegisterPacket event) {
        event.registerPacket("player-held-item", 8000, PlayerViewDirectionPacket.class);
//        event.registerPacketLazy(); does not work with more than one mod
    }

    @SubscribeEvent
    public void onEvent(EventReceiveServerModList event) {
        vanillaServer = false;

        Set<PacketModListExchange.MiniModInfo> playerMods = event.getModList();

        for (PacketModListExchange.MiniModInfo modInfo : playerMods) {
            if (Objects.equals(modInfo.modId(), Constants.MOD_ID)) {
                serverHasMod = true;
                break;
            }
        }
    }

    @SubscribeEvent
    public void onEvent(EventReceiveClientModList event) {
        vanillaClient = false;

        Set<PacketModListExchange.MiniModInfo> playerMods = event.getModList();

        for (PacketModListExchange.MiniModInfo modInfo : playerMods) {
            if (Objects.equals(modInfo.modId(), Constants.MOD_ID)) {
                clientHasMod = true;
                break;
            }
        }
    }


}
