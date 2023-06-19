package uk.debb.vanilla_disable.gamerules.mixin.spawn_limits;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.entity.MobCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.gamerules.Gamerules;

@Mixin(MobCategory.class)
public abstract class MixinMobCategory {
    private static final Object2ObjectMap<MobCategory, Gamerules> mobCategoryMobCategoryMapMobcap = new Object2ObjectOpenHashMap<>() {{
        put(MobCategory.MONSTER, Gamerules.MONSTER_MOBCAP);
        put(MobCategory.CREATURE, Gamerules.CREATURE_MOBCAP);
        put(MobCategory.AMBIENT, Gamerules.AMBIENT_MOBCAP);
        put(MobCategory.AXOLOTLS, Gamerules.AXOLOTL_MOBCAP);
        put(MobCategory.UNDERGROUND_WATER_CREATURE, Gamerules.GLOWSQUID_MOBCAP);
        put(MobCategory.WATER_AMBIENT, Gamerules.WATER_AMBIENT_MOBCAP);
        put(MobCategory.WATER_CREATURE, Gamerules.WATER_CREATURE_MOBCAP);
    }};

    @ModifyReturnValue(method = "getMaxInstancesPerChunk", at = @At("RETURN"))
    public int getMaxInstancesPerChunk(int original) {
        Gamerules gameRule = mobCategoryMobCategoryMapMobcap.get(this);
        if (gameRule != null) {
            return gameRule.getInt();
        }
        return original;
    }
}