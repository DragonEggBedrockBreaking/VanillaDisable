package uk.debb.vanilla_disable.mixin.spawn_limits;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(MobCategory.class)
public abstract class MixinMobCategory {
    @Unique
    private static final Object2ObjectMap<MobCategory, GameRules.Key<GameRules.IntegerValue>> spawnGroupMap = new Object2ObjectOpenHashMap<>();

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addOptionsToMap() {
        spawnGroupMap.put(MobCategory.MONSTER, Gamerules.MONSTER_MOBCAP);
        spawnGroupMap.put(MobCategory.CREATURE, Gamerules.CREATURE_MOBCAP);
        spawnGroupMap.put(MobCategory.AMBIENT, Gamerules.AMBIENT_MOBCAP);
        spawnGroupMap.put(MobCategory.AXOLOTLS, Gamerules.AXOLOTL_MOBCAP);
        spawnGroupMap.put(MobCategory.UNDERGROUND_WATER_CREATURE, Gamerules.GLOWSQUID_MOBCAP);
        spawnGroupMap.put(MobCategory.WATER_AMBIENT, Gamerules.WATER_AMBIENT_MOBCAP);
        spawnGroupMap.put(MobCategory.WATER_CREATURE, Gamerules.WATER_CREATURE_MOBCAP);
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getMaxInstancesPerChunk", at = @At("RETURN"))
    public int getMaxInstancesPerChunk(int original) {
        if (VDServer.getServer() == null) return original;
        if (spawnGroupMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.IntegerValue> gameRule = spawnGroupMap.get(this);
        if (gameRule != null) {
            return GameruleHelper.getInt(gameRule);
        }
        return original;
    }
}