package com.examplemod;


import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.common.PreModInit;

public class PreInitExampleMod implements PreModInit {

    @Override
    public void onPreInit() {
        Constants.LOGGER.info("Hello from {}", PreInitExampleMod.class);
    }
}
