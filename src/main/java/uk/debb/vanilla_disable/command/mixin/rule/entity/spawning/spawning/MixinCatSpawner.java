package uk.debb.vanilla_disable.command.mixin.rule.entity.spawning.spawning;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.CatSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(CatSpawner.class)
public abstract class MixinCatSpawner {
    @ModifyExpressionValue(
            method = "spawnCat",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/EntityType;create(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;"
            )
    )
    private Entity create(Entity original) {
        return DataHandler.getCachedBoolean("entities", "minecraft:cat", "spawned_by_villagers") ? original : null;
    }
}
