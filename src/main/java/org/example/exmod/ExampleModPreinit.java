package org.example.exmod;


import dev.puzzleshq.puzzleloader.cosmic.core.modInitialises.PreModInit;

public class ExampleModPreinit implements PreModInit {

    @Override
    public void onPreInit() {
        Constants.LOGGER.info("Hello From PRE-INIT");
    }
}
