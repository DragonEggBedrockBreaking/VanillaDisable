package uk.debb.vanilla_disable.mixin.spawn_limits;

import net.minecraft.entity.SpawnGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(SpawnGroup.class)
public abstract class MixinSpawnGroup {
    /**
     * @author DragonEggBedrockBreaking
     * @reason modify vanilla spawn caps
     * @param cir the returnable callback info
     */
    @Inject(method = "getCapacity", at = @At("HEAD"), cancellable = true)
    public void getCapacity(CallbackInfoReturnable<Integer> cir) {
        if ((SpawnGroup) (Object) this == SpawnGroup.MONSTER) {
            cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getInt(RegisterGamerules.MONSTER_MOBCAP));
        }
        if ((SpawnGroup) (Object) this == SpawnGroup.CREATURE) {
            cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getInt(RegisterGamerules.CREATURE_MOBCAP));
        }
        if ((SpawnGroup) (Object) this == SpawnGroup.AMBIENT) {
            cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getInt(RegisterGamerules.AMBIENT_MOBCAP));
        }
        if ((SpawnGroup) (Object) this == SpawnGroup.AXOLOTLS) {
            cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getInt(RegisterGamerules.AXOLOTL_MOBCAP));
        }
        if ((SpawnGroup) (Object) this == SpawnGroup.UNDERGROUND_WATER_CREATURE) {
            cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getInt(RegisterGamerules.GLOWSQUID_MOBCAP));
        }
        if ((SpawnGroup) (Object) this == SpawnGroup.WATER_CREATURE) {
            cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getInt(RegisterGamerules.WATER_CREATURE_MOBCAP));
        }
        if ((SpawnGroup) (Object) this == SpawnGroup.WATER_AMBIENT) {
            cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getInt(RegisterGamerules.WATER_AMBIENT_MOBCAP));
        }
    }
}
