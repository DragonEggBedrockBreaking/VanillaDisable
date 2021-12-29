package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(VillagerEntity.class)
public abstract class MixinVillagerEntity {
    /**
     * @author DragonEggBedrockBreaking
     * @reason stop villagers from turning into witches
     * @param world the world
     * @param serverWorld the world
     * @param entity the lightning
     * @return the difficulty
     */
    @Redirect(
        method = "onStruckByLightning",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/world/ServerWorld;getDifficulty()Lnet/minecraft/world/Difficulty;"
        )
    )
    public Difficulty getWrongDifficulty(ServerWorld world, ServerWorld serverWorld, LightningEntity entity) {
        if (world.getGameRules().getBoolean(RegisterGamerules.VILLAGERS_CONVERT_TO_WITCHES)) {
            return world.getDifficulty();
        } else {
            return Difficulty.PEACEFUL;
        }
    }
}
