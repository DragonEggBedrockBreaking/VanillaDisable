package uk.debb.vanilla_disable.mixin.spawn_limits;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(MobCategory.class)
public abstract class MixinMobCategory implements Maps {
    @ModifyReturnValue(method = "getMaxInstancesPerChunk", at = @At("RETURN"))
    public int getMaxInstancesPerChunk(int original) {
        GameRules.Key<GameRules.IntegerValue> gameRule = mobCategoryMobCategoryMapMobcap.get(this);
        if (gameRule != null) {
            return GameruleHelper.getInt(gameRule);
        }
        return original;
    }
}