package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(ZombieVillagerEntity.class)
public abstract class MixinZombieVillagerEntity {
    /**
     * @author DragonEggBedrockBreaking
     * @reason stop curing of zombie villagers
     * @param player the player curing
     * @param hand the hand of the player
     * @param cir the returnable callback info
     */
    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void cureMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!player.getWorld().getGameRules().getBoolean(RegisterGamerules.CURABLE_ZILLAGERS)) {
            cir.setReturnValue(ActionResult.CONSUME);
        }
    }
}
