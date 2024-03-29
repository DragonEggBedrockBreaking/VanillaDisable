package uk.debb.vanilla_disable.mixin.command.entity.experience;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Inject(method = "dropExperience", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$dropExperience(CallbackInfo ci) {
        String entity = CommandDataHandler.getKeyFromEntityTypeRegistry(((LivingEntity) (Object) this).getType());
        if (!CommandDataHandler.getCachedBoolean("entities", entity, "can_drop_xp")) {
            ci.cancel();
        }
    }
}
