package com.examplemod;


import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.client.ClientModInit;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.client.ClientPostModInit;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.client.ClientPreModInit;

public class ClientExampleMod implements ClientPreModInit, ClientModInit, ClientPostModInit {

    @Override
    public void onClientPreInit() {
        Constants.LOGGER.info("Hello from ClientPreInit in {}", ClientExampleMod.class);
    }

    @Override
    public void onClientInit() {
        Constants.LOGGER.info("Hello from ClientInit in {}", ClientExampleMod.class);
    }

    @Override
    public void onClientPostInit() {
        Constants.LOGGER.info("Hello from ClientPostInit in {}", ClientExampleMod.class);
    }

}
