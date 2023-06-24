package uk.debb.vanilla_disable.mixin.command.entity.conversions;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Zombie.class)
public abstract class MixinZombie {
    @ModifyExpressionValue(
            method = "killedEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;getDifficulty()Lnet/minecraft/world/Difficulty;"
            )
    )
    private Difficulty getWrongDifficulty(Difficulty original) {
        if (!CommandDataHandler.getCachedBoolean("entities", "minecraft:zombified_villager", "can_be_converted_to")) {
            return Difficulty.PEACEFUL;
        }
        return original;
    }
}
