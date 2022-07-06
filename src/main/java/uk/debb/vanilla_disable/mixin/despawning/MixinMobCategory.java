package uk.debb.vanilla_disable.mixin.despawning;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(MobCategory.class)
public abstract class MixinMobCategory {
    @Unique
    private static final Object2ObjectMap<MobCategory, GameRules.Key<GameRules.IntegerValue>> spawnGroupImmediateMap = new Object2ObjectOpenHashMap<>();
    @Unique
    private static final Object2ObjectMap<MobCategory, GameRules.Key<GameRules.IntegerValue>> spawnGroupStartMap = new Object2ObjectOpenHashMap<>();

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addImmediateOptionsToMap() {
        spawnGroupImmediateMap.put(MobCategory.MONSTER, Gamerules.MONSTER_MAX_DESPAWN);
        spawnGroupImmediateMap.put(MobCategory.CREATURE, Gamerules.CREATURE_MAX_DESPAWN);
        spawnGroupImmediateMap.put(MobCategory.AMBIENT, Gamerules.AMBIENT_MAX_DESPAWN);
        spawnGroupImmediateMap.put(MobCategory.AXOLOTLS, Gamerules.AXOLOTL_MAX_DESPAWN);
        spawnGroupImmediateMap.put(MobCategory.UNDERGROUND_WATER_CREATURE, Gamerules.GLOWSQUID_MAX_DESPAWN);
        spawnGroupImmediateMap.put(MobCategory.WATER_AMBIENT, Gamerules.WATER_AMBIENT_MAX_DESPAWN);
        spawnGroupImmediateMap.put(MobCategory.WATER_CREATURE, Gamerules.WATER_CREATURE_MIN_DESPAWN);
    }

    @Unique
    private void addStartOptionsToMap() {
        spawnGroupStartMap.put(MobCategory.MONSTER, Gamerules.MONSTER_MIN_DESPAWN);
        spawnGroupStartMap.put(MobCategory.CREATURE, Gamerules.CREATURE_MIN_DESPAWN);
        spawnGroupStartMap.put(MobCategory.AMBIENT, Gamerules.AMBIENT_MIN_DESPAWN);
        spawnGroupStartMap.put(MobCategory.AXOLOTLS, Gamerules.AXOLOTL_MIN_DESPAWN);
        spawnGroupStartMap.put(MobCategory.UNDERGROUND_WATER_CREATURE, Gamerules.GLOWSQUID_MIN_DESPAWN);
        spawnGroupStartMap.put(MobCategory.WATER_AMBIENT, Gamerules.WATER_AMBIENT_MIN_DESPAWN);
        spawnGroupStartMap.put(MobCategory.WATER_CREATURE, Gamerules.WATER_CREATURE_MAX_DESPAWN);
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getDespawnDistance", at = @At("RETURN"))
    public int editDespawnDistance(int original) {
        if (VDServer.getServer() == null) return original;
        if (spawnGroupImmediateMap.isEmpty()) {
            addImmediateOptionsToMap();
        }
        GameRules.Key<GameRules.IntegerValue> gameRule = spawnGroupImmediateMap.get(this);
        if (gameRule != null) {
            return GameruleHelper.getInt(gameRule);
        }
        return original;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getNoDespawnDistance", at = @At("RETURN"))
    public int editNoDespawnDistance(int original) {
        if (VDServer.getServer() == null) return original;
        if (spawnGroupStartMap.isEmpty()) {
            addStartOptionsToMap();
        }
        GameRules.Key<GameRules.IntegerValue> gameRule = spawnGroupStartMap.get(this);
        if (gameRule != null) {
            return GameruleHelper.getInt(gameRule);
        }
        return original;
    }
}