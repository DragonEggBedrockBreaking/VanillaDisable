package uk.debb.vanilla_disable.util;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Unique;

public class VDServer {
    // Defining the minecraft server that I can use to get gamerules anywhere
    private static MinecraftServer server;
    @Unique public static MinecraftServer getServer() {
        return server;
    }
    @Unique public static void setServer(MinecraftServer serverArg) {
        VDServer.server = serverArg;
    }
}