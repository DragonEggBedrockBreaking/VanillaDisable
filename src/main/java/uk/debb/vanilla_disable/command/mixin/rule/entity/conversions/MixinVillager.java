package uk.debb.vanilla_disable.command.mixin.rule.entity.conversions;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

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
        if (!DataHandler.getCachedBoolean("entities", "minecraft:witch", "can_be_converted_to")) {
            return Difficulty.PEACEFUL;
        }
        return original;
    }
}
