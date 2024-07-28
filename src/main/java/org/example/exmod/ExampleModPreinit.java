package org.example.exmod;

import dev.crmodders.puzzle.loader.entrypoint.interfaces.PreInitModInitializer;

public class ExampleModPreinit implements PreInitModInitializer {

    @Override
    public void onPreInit() {
        Constants.LOGGER.info("Hello From PRE-INIT");
    }
}
