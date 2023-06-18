package uk.debb.vanilla_disable.command.mixin.rule.item.function;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @ModifyReturnValue(method = "checkTotemDeathProtection", at = @At("RETURN"))
    private boolean checkTotemDeathProtection(boolean original) {
        return original && DataHandler.getCachedBoolean("items", "minecraft:totem_of_undying", "works");
    }
}
