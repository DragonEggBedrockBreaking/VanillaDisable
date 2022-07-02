package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(value = Zombie.class, priority = 1001)
public abstract class MixinZombie {
    /**
     * @param level       the level
     * @param serverWorld the world
     * @param other       the entity
     * @return the difficulty
     * @author DragonEggBedrockBreaking
     * @reason stop villagers from turning into zombie villagers
     */
    @Redirect(
            method = "wasKilled",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;getDifficulty()Lnet/minecraft/world/Difficulty;"
            )
    )
    public Difficulty getWrongDifficulty(ServerLevel level, ServerLevel serverWorld, LivingEntity other) {
        if (VDServer.getServer() == null) {
            return level.getDifficulty();
        }
        if (GameruleHelper.getBool(Gamerules.VILLAGERS_CONVERT_TO_ZILLAGERS)) {
            return level.getDifficulty();
        } else {
            return Difficulty.PEACEFUL;
        }
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