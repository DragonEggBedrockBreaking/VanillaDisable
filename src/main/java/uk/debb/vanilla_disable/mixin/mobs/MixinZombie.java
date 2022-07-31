package uk.debb.vanilla_disable.mixin.mobs;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;

@Mixin(Zombie.class)
public abstract class MixinZombie {
    @ModifyExpressionValue(
            method = "wasKilled",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;getDifficulty()Lnet/minecraft/world/Difficulty;"
            )
    )
    private Difficulty getWrongDifficulty(Difficulty original) {
        if (!BooleanGamerules.VILLAGERS_CONVERT_TO_ZILLAGERS.getValue()) {
            return Difficulty.PEACEFUL;
        }
        return original;
    }

    @Inject(method = "doUnderWaterConversion", at = @At("HEAD"), cancellable = true)
    private void cancelUnderWaterConversion(CallbackInfo ci) {
        if (!BooleanGamerules.ZOMBIES_CONVERT_TO_DROWNED.getValue()) {
            ci.cancel();
        }
    }
}