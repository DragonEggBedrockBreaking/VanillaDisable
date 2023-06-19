package uk.debb.vanilla_disable.command.mixin.rule.entity.conversions;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin({Husk.class, Zombie.class})
public abstract class MultipleMixinUnderwaterConversion {
    @Inject(method = "doUnderWaterConversion", at = @At("HEAD"), cancellable = true)
    private void doUnderWaterConversion(CallbackInfo ci) {
        String entity = ((Entity) (Object) this).getType().equals(EntityType.HUSK) ? "minecraft:zombie" : "minecraft:drowned";
        if (!CommandDataHandler.getCachedBoolean("entities", entity, "can_be_converted_to")) {
            ci.cancel();
        }
    }
}
