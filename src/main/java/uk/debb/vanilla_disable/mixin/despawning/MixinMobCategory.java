package uk.debb.vanilla_disable.mixin.despawning;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(MobCategory.class)
public abstract class MixinMobCategory {
    /**
     * @author DragonEggBedrockBreaking
     * @reason map of many mob groups to their gamerules
     */
    @Unique
    private static final Map<MobCategory, GameRules.Key<GameRules.IntegerValue>> spawnGroupImmediateMap = new HashMap<MobCategory, GameRules.Key<GameRules.IntegerValue>>();
    @Unique
    private static final Map<MobCategory, GameRules.Key<GameRules.IntegerValue>> spawnGroupStartMap = new HashMap<MobCategory, GameRules.Key<GameRules.IntegerValue>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addImmediateOptionsToMap() {
        spawnGroupImmediateMap.put(MobCategory.MONSTER, RegisterGamerules.MONSTER_MAX_DESPAWN);
        spawnGroupImmediateMap.put(MobCategory.CREATURE, RegisterGamerules.CREATURE_MAX_DESPAWN);
        spawnGroupImmediateMap.put(MobCategory.AMBIENT, RegisterGamerules.AMBIENT_MAX_DESPAWN);
        spawnGroupImmediateMap.put(MobCategory.AXOLOTLS, RegisterGamerules.AXOLOTL_MAX_DESPAWN);
        spawnGroupImmediateMap.put(MobCategory.UNDERGROUND_WATER_CREATURE, RegisterGamerules.GLOWSQUID_MAX_DESPAWN);
        spawnGroupImmediateMap.put(MobCategory.WATER_AMBIENT, RegisterGamerules.WATER_AMBIENT_MAX_DESPAWN);
        spawnGroupImmediateMap.put(MobCategory.WATER_CREATURE, RegisterGamerules.WATER_CREATURE_MIN_DESPAWN);
    }
    @Unique
    private void addStartOptionsToMap() {
        spawnGroupStartMap.put(MobCategory.MONSTER, RegisterGamerules.MONSTER_MIN_DESPAWN);
        spawnGroupStartMap.put(MobCategory.CREATURE, RegisterGamerules.CREATURE_MIN_DESPAWN);
        spawnGroupStartMap.put(MobCategory.AMBIENT, RegisterGamerules.AMBIENT_MIN_DESPAWN);
        spawnGroupStartMap.put(MobCategory.AXOLOTLS, RegisterGamerules.AXOLOTL_MIN_DESPAWN);
        spawnGroupStartMap.put(MobCategory.UNDERGROUND_WATER_CREATURE, RegisterGamerules.GLOWSQUID_MIN_DESPAWN);
        spawnGroupStartMap.put(MobCategory.WATER_AMBIENT, RegisterGamerules.WATER_AMBIENT_MIN_DESPAWN);
        spawnGroupStartMap.put(MobCategory.WATER_CREATURE, RegisterGamerules.WATER_CREATURE_MAX_DESPAWN);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason edit immediate despawn range
     * @param cir returnable callback info
     */
    @Inject(method = "getDespawnDistance", at = @At("HEAD"), cancellable = true)
    public void editDespawnDistance(CallbackInfoReturnable<Integer> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (spawnGroupImmediateMap.isEmpty()) {
            addImmediateOptionsToMap();
        }
       GameRules.Key<GameRules.IntegerValue> gameRule = spawnGroupImmediateMap.get((MobCategory) (Object) this);
       if (gameRule != null) {
           cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getInt(gameRule));
       }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason edit immediate despawn range
     * @param cir returnable callback info
     */
    @Inject(method = "getNoDespawnDistance", at = @At("HEAD"), cancellable = true)
    public void editNoDespawnDistance(CallbackInfoReturnable<Integer> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (spawnGroupStartMap.isEmpty()) {
            addStartOptionsToMap();
        }
        GameRules.Key<GameRules.IntegerValue> gameRule = spawnGroupStartMap.get((MobCategory) (Object) this);
        if (gameRule != null) {
            cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getInt(gameRule));
        }
    }
}
