package uk.debb.vanilla_disable.mixin.spawn_limits;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(SpawnGroup.class)
public abstract class MixinSpawnGroup {
    /**
     * @author DragonEggBedrockBreaking
     * @reason map of all spawn groups to their gamerules
     */
    private static final Map<SpawnGroup, GameRules.Key<GameRules.IntRule>> spawnGroupMap = new HashMap<SpawnGroup, GameRules.Key<GameRules.IntRule>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    private void addOptionsToMap() {
        spawnGroupMap.put(SpawnGroup.MONSTER, RegisterGamerules.MONSTER_MOBCAP);
        spawnGroupMap.put(SpawnGroup.CREATURE, RegisterGamerules.CREATURE_MOBCAP);
        spawnGroupMap.put(SpawnGroup.AMBIENT, RegisterGamerules.AMBIENT_MOBCAP);
        spawnGroupMap.put(SpawnGroup.AXOLOTLS, RegisterGamerules.AXOLOTL_MOBCAP);
        spawnGroupMap.put(SpawnGroup.UNDERGROUND_WATER_CREATURE, RegisterGamerules.GLOWSQUID_MOBCAP);
        spawnGroupMap.put(SpawnGroup.WATER_AMBIENT, RegisterGamerules.WATER_AMBIENT_MOBCAP);
        spawnGroupMap.put(SpawnGroup.WATER_CREATURE, RegisterGamerules.WATER_CREATURE_MOBCAP);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason modify vanilla spawn caps
     * @param cir the returnable callback info
     */
    @Inject(method = "getCapacity", at = @At("HEAD"), cancellable = true)
    public void getCapacity(CallbackInfoReturnable<Integer> cir) {
        if (spawnGroupMap.isEmpty()) {
            this.addOptionsToMap();
        }
        GameRules.Key<GameRules.IntRule> gameRule = spawnGroupMap.get((SpawnGroup) (Object) this);
        if (gameRule != null) {
            cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getInt(gameRule));
        }
    }
}
