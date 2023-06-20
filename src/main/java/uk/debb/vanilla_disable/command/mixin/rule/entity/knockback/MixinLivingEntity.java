package uk.debb.vanilla_disable.command.mixin.rule.entity.knockback;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Shadow
    private @Nullable LivingEntity lastHurtByMob;

    @Inject(method = "knockback", at = @At("HEAD"), cancellable = true)
    public void knockback(CallbackInfo ci) {
        String target = CommandDataHandler.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        String source = CommandDataHandler.getKeyFromEntityTypeRegistry(Objects.requireNonNull(this.lastHurtByMob).getType());
        if (!CommandDataHandler.getCachedBoolean("entities", target, CommandDataHandler.lightCleanup(source) + "_knockback")) {
            ci.cancel();
        }
    }
}
