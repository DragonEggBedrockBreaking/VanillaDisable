package uk.debb.vanilla_disable.mixin.worldgen.biomes;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.commands.TimeCommand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.levelgen.DebugLevelSource;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.storage.LevelResource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(PlayerList.class)
public abstract class MixinPlayerList {
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
        String worldPath = RegisterGamerules.getServer().getWorldPath(LevelResource.ROOT).toString();
        RegisterGamerules.getServer().getPlayerList().broadcastMessage(new TranslatableComponent("closing.in.twenty.seconds"), ChatType.CHAT, UUID.randomUUID());
        RegisterGamerules.getServer().getPlayerList().broadcastMessage(new TranslatableComponent("deleting.region.files"), ChatType.CHAT, UUID.randomUUID());
        RegisterGamerules.getServer().getPlayerList().broadcastMessage(new TranslatableComponent("dont.delete.region.files"), ChatType.CHAT, UUID.randomUUID());

        final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(() -> {
            RegisterGamerules.getServer().getCommands().performCommand(
                RegisterGamerules.getServer().createCommandSourceStack(), "/kick @a \"Applying datapack settings.\"");
            deleteRegionFiles(worldPath);
        }, 20, TimeUnit.SECONDS);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason turning off the daylight cycle shouldn't break the mod
     */
    @Unique
    private void patchTime() {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(GameRules.RULE_DAYLIGHT) &&
            RegisterGamerules.getServer().overworld().getDayTime() < 100) {
            TimeCommand.addTime(RegisterGamerules.getServer().createCommandSourceStack(), 24000);
        }
    }
    /**
     * @author DragonEggBedrockBreaking
     * @reason kick the player, then delete the region files, only if it is the first load
     * @param connection the client's connection to the game
     * @param player the player who connected
     * @param ci the callback info
     * @throws InterruptedException
     */
    @Inject(method = "placeNewPlayer", at = @At("RETURN"), cancellable = true)
    private void onPlacingNewPlayer(Connection connection, ServerPlayer player, CallbackInfo ci) throws InterruptedException {
        if (RegisterGamerules.getServer() == null) return;
        GameRules gameRules = RegisterGamerules.getServer().getGameRules();
        if (!(RegisterGamerules.getServer().overworld().getChunkSource().getGenerator() instanceof FlatLevelSource) &&
            !(RegisterGamerules.getServer().overworld().getChunkSource().getGenerator() instanceof DebugLevelSource) &&
            (gameRules.getBoolean(RegisterGamerules.REMOVE_OVERWORLD_BIOMES) || gameRules.getBoolean(RegisterGamerules.REMOVE_NETHER_BIOMES) ||
             gameRules.getBoolean(RegisterGamerules.REMOVE_END_BIOMES)) && player.getLevel().getDayTime() < 100) {
            patchTime();
            deleteRegionFilesAndClose();
        } else {
            patchTime();
        }
    }
}
