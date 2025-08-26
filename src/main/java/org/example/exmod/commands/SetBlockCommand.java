package org.example.exmod.commands;

import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.blocks.MissingBlockStateResult;
import finalforeach.cosmicreach.chat.IChat;
import finalforeach.cosmicreach.chat.commands.Command;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.world.BlockSetter;
import finalforeach.cosmicreach.world.Zone;

public class SetBlockCommand extends Command {

    @Override
    public void run(IChat chat) {
        super.run(chat);

        int x = Integer.parseInt(this.getNextArg());
        int y = Integer.parseInt(this.getNextArg());
        int z = Integer.parseInt(this.getNextArg());
        String blockState = this.getNextArg();

        Player player = this.getCallingPlayer();
        Zone zone = null;
        if (player != null) {
            zone = player.getZone();
        }

        BlockSetter.get().replaceBlock(zone, BlockState.getInstance(blockState, MissingBlockStateResult.MISSING_OBJECT), x, y, z);
    }

    @Override
    public String getShortDescription() {
        return "Sets the block at xyz to the give blockState";
    }
}
