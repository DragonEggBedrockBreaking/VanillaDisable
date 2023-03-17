package uk.debb.vanilla_disable.mixin.items;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Item.class)
public abstract class MixinItem implements Maps {
    @Shadow
    @Final
    private int maxDamage;

    @Shadow
    public abstract Item asItem();

    @ModifyReturnValue(method = "getMaxDamage", at = @At("RETURN"))
    private int modifyMaxDamage(int original) {
        Gamerules gameRule = itemItemMap.get(this.asItem());
        if (gameRule != null && original == gameRule.getDefaultInt()) {
            return gameRule.getInt();
        }
        return original;
    }

    @ModifyReturnValue(method = "canBeDepleted", at = @At("RETURN"))
    private boolean modifyDepletionAllowance(boolean original) {
        Gamerules gameRule = itemItemMap.get(this.asItem());
        if (gameRule != null && this.maxDamage == gameRule.getDefaultInt()) {
            return gameRule.getInt() > 0;
        }
        return original;
    }

    @ModifyReturnValue(method = "isFireResistant", at = @At("RETURN"))
    private boolean modifyFireResistance(boolean original) {
        if (original) {
            return !Gamerules.NETHERITE_BURNS.getBool();
        }
        return false;
    }

    @ModifyReturnValue(method = "canBeHurtBy", at = @At("RETURN"))
    private boolean modifyHurtingPotential(boolean original, DamageSource damageSource) {
        if (!original && damageSource.is(DamageTypeTags.IS_FIRE)) {
            return Gamerules.NETHERITE_BURNS.getBool();
        }
        return original;
    }
}
