package uk.debb.vanilla_disable.mixin.entity;

import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class MixinAbstractFurnaceBlockEntity {
    @Inject(method = "createExperience", at = @At("HEAD"), cancellable = true)
    private static void cancelExperienceCreation(CallbackInfo ci) {
        if (!Gamerules.FURNACES_DROP_XP.getBool()) {
            ci.cancel();
        }
    }
}
