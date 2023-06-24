package uk.debb.vanilla_disable.mixin.command.entity.other;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.ZombieVillager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(ZombieVillager.class)
public abstract class MixinZombieVillager {
    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void cureMob(CallbackInfoReturnable<InteractionResult> cir) {
        if (!CommandDataHandler.getCachedBoolean("entities", "minecraft:villager", "can_be_converted_to")) {
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }
}
