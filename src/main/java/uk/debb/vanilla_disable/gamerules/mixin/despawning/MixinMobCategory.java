package uk.debb.vanilla_disable.gamerules.mixin.despawning;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.MobCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.gamerules.util.maps.Maps;

@Mixin(MobCategory.class)
public abstract class MixinMobCategory implements Maps {
    @ModifyReturnValue(method = "getDespawnDistance", at = @At("RETURN"))
    public int editDespawnDistance(int original) {
        Gamerules gameRule = mobCategoryMobCategoryMapMax.get(this);
        if (gameRule != null) {
            return gameRule.getInt();
        }
        return original;
    }

    @ModifyReturnValue(method = "getNoDespawnDistance", at = @At("RETURN"))
    public int editNoDespawnDistance(int original) {
        Gamerules gameRule = mobCategoryMobCategoryMapMin.get(this);
        if (gameRule != null) {
            return gameRule.getInt();
        }
        return original;
    }
}