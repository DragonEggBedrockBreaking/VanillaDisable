package uk.debb.vanilla_disable.command.mixin.rule.entity.spawning.spawning;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(Villager.class)
public abstract class MixinVillager {
    @ModifyReturnValue(method = "wantsToSpawnGolem", at = @At("RETURN"))
    private boolean wantsToSpawnGolem(boolean original) {
        if (!Gamerules.VILLAGERS_SPAWN_GOLEMS.getBool()) {
            return false;
        }
        return original && DataHandler.getBoolean("entities", "minecraft:iron_golem", "spawned_by_villagers");
    }
}
