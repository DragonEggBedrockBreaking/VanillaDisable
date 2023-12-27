package uk.debb.vanilla_disable.mixin.command.entity.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @ModifyReturnValue(method = "canBeAffected", at = @At("RETURN"))
    private boolean vanillaDisable$canBeAffected(boolean original, MobEffectInstance effectInstance) {
        if (CommandDataHandler.isConnectionNull()) return original;
        String entity = CommandDataHandler.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        return CommandDataHandler.getCachedBoolean("entities", entity,
                CommandDataHandler.lightCleanup(Objects.requireNonNull(CommandDataHandler.mobEffectRegistry.getKey(effectInstance.getEffect().value()))) + "_effect");
    }

    @WrapWithCondition(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;aiStep()V"
            )
    )
    private boolean vanillaDisable$aiStep(LivingEntity livingEntity) {
        String entity = CommandDataHandler.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        return CommandDataHandler.getCachedBoolean("entities", entity, "ai");
    }
}
