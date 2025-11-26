package com.examplemod.exmod;

import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.server.ServerModInit;

public class ServerExampleMod implements ServerModInit {

    @Override
    public void onServerInit() {
        Constants.LOGGER.info("Hello from ServerInit in {}", ServerExampleMod.class);
    }

}
