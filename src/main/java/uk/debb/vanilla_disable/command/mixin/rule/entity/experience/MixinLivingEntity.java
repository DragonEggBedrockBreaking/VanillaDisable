package uk.debb.vanilla_disable.command.mixin.rule.entity.experience;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Inject(method = "dropExperience", at = @At("HEAD"), cancellable = true)
    private void dropExperience(CallbackInfo ci) {
        String entity = DataHandler.getKeyFromEntityTypeRegistry(((LivingEntity) (Object) this).getType());
        if (!DataHandler.getBoolean("entities", entity, "can_drop_xp")) {
            ci.cancel();
        }
    }
}
