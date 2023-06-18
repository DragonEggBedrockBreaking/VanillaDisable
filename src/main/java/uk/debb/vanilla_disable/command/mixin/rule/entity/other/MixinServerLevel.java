package uk.debb.vanilla_disable.command.mixin.rule.entity.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(ServerLevel.class)
public abstract class MixinServerLevel {
    @ModifyReturnValue(method = "shouldDiscardEntity", at = @At("RETURN"))
    private boolean shouldDiscardEntity(boolean original, Entity entity) {
        String entityName = DataHandler.getKeyFromEntityTypeRegistry(entity.getType());
        return original || !DataHandler.getBoolean("entities", entityName, "can_exist");
    }
}
