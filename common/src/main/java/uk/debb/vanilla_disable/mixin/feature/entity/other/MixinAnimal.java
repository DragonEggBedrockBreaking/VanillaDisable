package uk.debb.vanilla_disable.mixin.feature.entity.other;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.config.data.DataUtils;
import uk.debb.vanilla_disable.config.data.SqlManager;

@Mixin(Animal.class)
public abstract class MixinAnimal {
    @WrapWithCondition(
            method = "finalizeSpawnChildFromBreeding",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"
            )
    )
    private boolean vanillaDisable$addFreshEntity(ServerLevel instance, Entity entity) {
        String entityName = DataUtils.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        return SqlManager.getBoolean("entities", entityName, "breeding_can_drop_xp");
    }
}
