package org.example.exmod;

import dev.crmodders.puzzle.loader.entrypoint.interfaces.ModInitializer;

public class ExampleMod implements ModInitializer {

    @Override
    public void onInit() {
        Constants.LOGGER.info("Hello From INIT");
    }

}
