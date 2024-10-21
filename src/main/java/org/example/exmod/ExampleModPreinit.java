package org.example.exmod;


import com.github.puzzle.core.loader.provider.mod.entrypoint.impls.PreModInitializer;

public class ExampleModPreinit implements PreModInitializer {

    @Override
    public void onPreInit() {
        Constants.LOGGER.info("Hello From PRE-INIT");
    }
}
