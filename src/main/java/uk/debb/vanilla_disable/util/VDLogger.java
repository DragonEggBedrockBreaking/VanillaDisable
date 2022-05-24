package uk.debb.vanilla_disable.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Unique;

public class VDLogger {
    // Creates a logger for this mod to use
    private static Logger logger = LoggerFactory.getLogger("Vanilla Disable");
    @Unique public static Logger getLogger() {
        return logger;
    }
}
