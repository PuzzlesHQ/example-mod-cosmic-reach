package com.examplemod.commands;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.blocks.MissingBlockStateResult;
import finalforeach.cosmicreach.chat.IChat;
import finalforeach.cosmicreach.chat.commands.Command;
import finalforeach.cosmicreach.chat.commands.parsing.ArgumentType;
import finalforeach.cosmicreach.chat.commands.parsing.CommandArgument;
import finalforeach.cosmicreach.chat.commands.parsing.CommandSignature;
import finalforeach.cosmicreach.chat.commands.parsing.Coordinate;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.world.BlockSetter;
import finalforeach.cosmicreach.world.Zone;

public class SetBlockCommand extends Command {

    @Override
    public void run(IChat chat, CommandSignature signature, Object[] parsedArgs) {
        int i = 0;
        Coordinate cx = (Coordinate)parsedArgs[i];
        Coordinate cy = (Coordinate)parsedArgs[i += 1];
        Coordinate cz = (Coordinate)parsedArgs[i += 1];
        String blockState = (String)parsedArgs[i += 1];

        Vector3 pos = this.getCallingPlayer().getPosition();
        int x = (int) cx.get(pos.x);
        int y = (int) cy.get(pos.y);
        int z = (int) cz.get(pos.z);

        BlockSetter.get().replaceBlock(this.getCurrentZone(), BlockState.getInstance(blockState, MissingBlockStateResult.MISSING_OBJECT), x, y, z);
    }

    @Override
    public CommandSignature[] getSignatures() {
        return new CommandSignature[]{
                new CommandSignature(
                        new CommandArgument(ArgumentType.COORD_X, "x"),
                        new CommandArgument(ArgumentType.COORD_Y, "y"),
                        new CommandArgument(ArgumentType.COORD_Z, "z"),
                        new CommandArgument(ArgumentType.STRING, "blockState")
                )
        };
    }

    @Override
    public String getShortDescription() {
        return "Sets the block at xyz to the give blockState";
    }
}
