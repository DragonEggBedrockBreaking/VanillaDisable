package uk.debb.vanilla_disable.mixin.command.entity.spawning.spawning;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.CatSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(CatSpawner.class)
public abstract class MixinCatSpawner {
    @ModifyExpressionValue(
            method = "spawnCat",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/EntityType;create(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;"
            )
    )
    private Entity vanillaDisable$create(Entity original) {
        return CommandDataHandler.getCachedBoolean("entities", "minecraft:cat", "spawned_by_villagers") ? original : null;
    }
}
