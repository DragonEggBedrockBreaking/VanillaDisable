package uk.debb.vanilla_disable.mixin.util.worldgen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.server.WorldStem;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import uk.debb.vanilla_disable.config.worldgen.WorldgenConfigScreen;
import uk.debb.vanilla_disable.data.worldgen.WorldgenDataHandler;

import java.time.Instant;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    @Shadow
    public abstract void setScreen(@Nullable Screen screen);

    @Inject(
            method = "doWorldLoad",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/server/IntegratedServer;isReady()Z"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void doWorldLoad(String string, LevelStorageSource.LevelStorageAccess levelStorageAccess, PackRepository packRepository, WorldStem worldStem, boolean bl, CallbackInfo ci, Instant instant, LevelLoadingScreen levelLoadingScreen) {
        if (!WorldgenDataHandler.continueGeneration) {
            this.setScreen(new WorldgenConfigScreen(levelLoadingScreen));
        }
    }
}
