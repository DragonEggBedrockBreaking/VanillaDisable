package uk.debb.vanilla_disable.mixin.mob;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(value = Zombie.class, priority = 1001)
public abstract class MixinZombie {
    /**
     * @param original the original value
     * @return the difficulty
     * @author DragonEggBedrockBreaking
     * @reason stop villagers from turning into zombie villagers
     */
    @ModifyExpressionValue(
            method = "wasKilled",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;getDifficulty()Lnet/minecraft/world/Difficulty;"
            )
    )
    private Difficulty getWrongDifficulty(Difficulty original) {
        if (VDServer.getServer() == null) {
            return original;
        }
        if (!GameruleHelper.getBool(Gamerules.VILLAGERS_CONVERT_TO_ZILLAGERS)) {
            return Difficulty.PEACEFUL;
        }
        return original;
    }

    /**
     * @param ci the callback info
     * @author DragonEggBedrockBreaking
     * @reason stop zombies from converting into drowned
     */
    @Inject(method = "doUnderWaterConversion", at = @At("HEAD"), cancellable = true)
    private void cancelUnderWaterConversion(CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.ZOMBIES_CONVERT_TO_DROWNED)) {
            ci.cancel();
        }
    }
}