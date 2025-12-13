package com.examplemod.exmod;

import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.common.PostModInit;

public class PostInitExampleMod implements PostModInit {

    @Override
    public void onPostInit() {
        Constants.LOGGER.info("Hello from {}", PostInitExampleMod.class);
    }
}
