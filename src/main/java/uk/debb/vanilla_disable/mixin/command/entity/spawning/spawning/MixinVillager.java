package uk.debb.vanilla_disable.mixin.command.entity.spawning.spawning;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Villager.class)
public abstract class MixinVillager {
    @ModifyReturnValue(method = "wantsToSpawnGolem", at = @At("RETURN"))
    private boolean vanillaDisable$wantsToSpawnGolem(boolean original) {
        return original && CommandDataHandler.getCachedBoolean("entities", "minecraft:iron_golem", "spawned_by_villagers");
    }
}
