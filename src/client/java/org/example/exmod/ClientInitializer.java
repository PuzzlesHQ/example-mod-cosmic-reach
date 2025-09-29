package org.example.exmod;


import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.client.ClientModInit;

public class ClientInitializer implements ClientModInit {

    @Override
    public void onClientInit() {
        Constants.LOGGER.info("Hello From Client INIT");
    }
}
