package uk.debb.vanilla_disable.mixin.knockback;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper {
    /**
     * @author DragonEggBedrockBreaking
     * @reason cancel knockback enchantment
     * @param entity the entity to knock back
     * @param cir the returnable callback info
     */
    @Inject(method = "getKnockback", at = @At("HEAD"), cancellable = true)
    private static void cancelKnockback(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        if (entity instanceof ServerPlayerEntity &&
            (!entity.getWorld().getGameRules().getBoolean(RegisterGamerules.KNOCKBACK_ENCHANTMENT) ||
             !entity.getWorld().getGameRules().getBoolean(RegisterGamerules.KNOCKBACK_ENABLED))) {
            cir.setReturnValue(0);
        }
    }
}
