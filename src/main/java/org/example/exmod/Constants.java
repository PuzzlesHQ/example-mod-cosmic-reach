package org.example.exmod;

import finalforeach.cosmicreach.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {

    public static final String MOD_ID = "example-mod";
    public static final Identifier MOD_NAME = Identifier.of(MOD_ID, "Example Mod");
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

}
