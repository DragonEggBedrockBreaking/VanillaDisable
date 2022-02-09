package uk.debb.vanilla_disable.mixin.despawning;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(SpawnGroup.class)
public abstract class MixinSpawnGroup {
    /**
     * @author DragonEggBedrockBreaking
     * @reason map of many mob groups to their gamerules
     */
    @Unique
    private static final Map<SpawnGroup, GameRules.Key<GameRules.IntRule>> spawnGroupImmediateMap = new HashMap<SpawnGroup, GameRules.Key<GameRules.IntRule>>();
    @Unique
    private static final Map<SpawnGroup, GameRules.Key<GameRules.IntRule>> spawnGroupStartMap = new HashMap<SpawnGroup, GameRules.Key<GameRules.IntRule>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addImmediateOptionsToMap() {
        spawnGroupImmediateMap.put(SpawnGroup.MONSTER, RegisterGamerules.MONSTER_MAX_DESPAWN);
        spawnGroupImmediateMap.put(SpawnGroup.CREATURE, RegisterGamerules.CREATURE_MAX_DESPAWN);
        spawnGroupImmediateMap.put(SpawnGroup.AMBIENT, RegisterGamerules.AMBIENT_MAX_DESPAWN);
        spawnGroupImmediateMap.put(SpawnGroup.AXOLOTLS, RegisterGamerules.AXOLOTL_MAX_DESPAWN);
        spawnGroupImmediateMap.put(SpawnGroup.UNDERGROUND_WATER_CREATURE, RegisterGamerules.GLOWSQUID_MAX_DESPAWN);
        spawnGroupImmediateMap.put(SpawnGroup.WATER_AMBIENT, RegisterGamerules.WATER_AMBIENT_MAX_DESPAWN);
        spawnGroupImmediateMap.put(SpawnGroup.WATER_CREATURE, RegisterGamerules.WATER_CREATURE_MIN_DESPAWN);
    }
    @Unique
    private void addStartOptionsToMap() {
        spawnGroupStartMap.put(SpawnGroup.MONSTER, RegisterGamerules.MONSTER_MIN_DESPAWN);
        spawnGroupStartMap.put(SpawnGroup.CREATURE, RegisterGamerules.CREATURE_MIN_DESPAWN);
        spawnGroupStartMap.put(SpawnGroup.AMBIENT, RegisterGamerules.AMBIENT_MIN_DESPAWN);
        spawnGroupStartMap.put(SpawnGroup.AXOLOTLS, RegisterGamerules.AXOLOTL_MIN_DESPAWN);
        spawnGroupStartMap.put(SpawnGroup.UNDERGROUND_WATER_CREATURE, RegisterGamerules.GLOWSQUID_MIN_DESPAWN);
        spawnGroupStartMap.put(SpawnGroup.WATER_AMBIENT, RegisterGamerules.WATER_AMBIENT_MIN_DESPAWN);
        spawnGroupStartMap.put(SpawnGroup.WATER_CREATURE, RegisterGamerules.WATER_CREATURE_MAX_DESPAWN);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason edit immediate despawn range
     * @param cir returnable callback info
     */
    @Inject(method = "getImmediateDespawnRange", at = @At("HEAD"), cancellable = true)
    public void editImmediateDespawnRange(CallbackInfoReturnable<Integer> cir) {
        if (spawnGroupImmediateMap.isEmpty()) {
            addImmediateOptionsToMap();
        }
       GameRules.Key<GameRules.IntRule> gameRule = spawnGroupImmediateMap.get((SpawnGroup) (Object) this);
       if (gameRule != null) {
           cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getInt(gameRule));
       }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason edit immediate despawn range
     * @param cir returnable callback info
     */
    @Inject(method = "getDespawnStartRange", at = @At("HEAD"), cancellable = true)
    public void editDespawnStartRange(CallbackInfoReturnable<Integer> cir) {
        if (spawnGroupStartMap.isEmpty()) {
            addStartOptionsToMap();
        }
        GameRules.Key<GameRules.IntRule> gameRule = spawnGroupStartMap.get((SpawnGroup) (Object) this);
        if (gameRule != null) {
            cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getInt(gameRule));
        }
    }
}
