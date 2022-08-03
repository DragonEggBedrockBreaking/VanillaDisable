package uk.debb.vanilla_disable.mixin.despawning;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Mob.class)
public abstract class MixinMob extends Entity implements Maps {
    public MixinMob(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private boolean additionalRestrictionsMet() {
        if (this instanceof Bucketable bucketable) {
            return !this.hasCustomName() && !bucketable.fromBucket();
        }
        return true;
    }

    @ModifyReturnValue(method = "removeWhenFarAway", at = @At("RETURN"))
    private boolean cancelRemovalWhenFarAway(boolean original) {
        Gamerules gameRule = mobClassMapDespawn.get(this.getClass());
        if (gameRule != null && !(this.getType().equals(EntityType.VILLAGER))) {
            return gameRule.getBool() && additionalRestrictionsMet();
        }
        return original;
    }
}