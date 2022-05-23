package uk.debb.vanilla_disable.util;

import net.minecraft.server.MinecraftServer;

public class VDServer {
    // Defining the minecraft server that I can use to get gamerules anywhere
    private static MinecraftServer server;
    public static MinecraftServer getServer() {
        return server;
    }
    public static void setServer(MinecraftServer serverArg) {
        VDServer.server = serverArg;
    }
}
