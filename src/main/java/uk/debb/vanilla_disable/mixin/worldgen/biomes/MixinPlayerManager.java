package uk.debb.vanilla_disable.mixin.worldgen.biomes;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.MessageType;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.WorldSavePath;
import net.minecraft.world.GameRules;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(PlayerManager.class)
public abstract class MixinPlayerManager {
    /**
     * @author DragonEggBedrockBreaking
     * @reason recursively delete directories
     * @param file the directory to delete
     */
    @Unique
    private void deleteDirectory(File file) {
        for (File subFile : file.listFiles()) {
            if(subFile.isDirectory()) {
               deleteDirectory(subFile);
            } else {
               subFile.delete();
            }
         }
         file.delete();
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason delete the region files for each dimension for a given world
     * @param worldPath the path to the world
     */
    @Unique
    private void deleteRegionFiles(String worldPath) {
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.REMOVE_OVERWORLD_BIOMES)) {
            deleteDirectory(new File(worldPath + "/region"));
        }
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.REMOVE_NETHER_BIOMES)) {
            deleteDirectory(new File(worldPath + "/DIM-1"));
        }
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.REMOVE_END_BIOMES)) {
            deleteDirectory(new File(worldPath + "/DIM1"));
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason send a message to chat, then count 60 seconds on a different thread
     * @reason finally, kick the player, and then delete the region files
     * @throws InterruptedException
     */
    @Unique
    private void deleteRegionFilesAndClose() throws InterruptedException {
        String worldPath = RegisterGamerules.getServer().getSavePath(WorldSavePath.ROOT).toString();
        RegisterGamerules.getServer().getPlayerManager().broadcast(new TranslatableText("closing.in.one.minute"), MessageType.CHAT, UUID.randomUUID());

        final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(() -> {
            RegisterGamerules.getServer().getCommandManager().execute(
                RegisterGamerules.getServer().getCommandSource(), "/kick @a \"Applying datapack settings.\"");
            deleteRegionFiles(worldPath);
        }, 60, TimeUnit.SECONDS);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason kick the player, then delete the region files, only if it is the first load
     * @param connection the client's connection to the game
     * @param player the player who connected
     * @param ci the callback info
     * @throws InterruptedException
     */
    @Inject(method = "onPlayerConnect", at = @At("RETURN"), cancellable = true)
    private void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) throws InterruptedException {
        GameRules gameRules = RegisterGamerules.getServer().getGameRules();
        if ((gameRules.getBoolean(RegisterGamerules.REMOVE_OVERWORLD_BIOMES) ||
             gameRules.getBoolean(RegisterGamerules.REMOVE_NETHER_BIOMES) ||
             gameRules.getBoolean(RegisterGamerules.REMOVE_END_BIOMES)) &&
             player.getWorld().getTimeOfDay() < 100) {
            deleteRegionFilesAndClose();
        }
    }
}
