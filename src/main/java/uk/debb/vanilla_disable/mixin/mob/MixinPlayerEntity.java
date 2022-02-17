package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {
    /**
     * @author DragonEggBedrockBreaking
     * @reason creepers ignite when clicked with flint and steel, fire aspect should be the same
     * @param target the entity the player is targetting
     * @param ci the callback info
     */
    @Inject(method = "attack", at = @At("RETURN"), cancellable = true)
    private void igniteCreeper(Entity target, CallbackInfo ci) {
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.FIRE_ASPECT_IGNITES_CREEPERS) &&
            target instanceof CreeperEntity && EnchantmentHelper.getFireAspect((PlayerEntity)(Object)this) > 0) {
            ((CreeperEntity)target).ignite();
        }
    }
}
