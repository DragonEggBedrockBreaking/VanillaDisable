package uk.debb.vanilla_disable.gamerules.mixin.mobs;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(Villager.class)
public abstract class MixinVillager {
    @ModifyExpressionValue(
            method = "thunderHit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;getDifficulty()Lnet/minecraft/world/Difficulty;"
            )
    )
    private Difficulty getWrongDifficulty(Difficulty original) {
        if (!Gamerules.VILLAGERS_CONVERT_TO_WITCHES.getBool()) {
            return Difficulty.PEACEFUL;
        }
        return original;
    }

    @ModifyReturnValue(method = "wantsToSpawnGolem", at = @At("RETURN"))
    private boolean doesNotWantToSpawnGolem(boolean original) {
        if (!Gamerules.VILLAGERS_SPAWN_GOLEMS.getBool()) {
            return false;
        }
        return original;
    }
}