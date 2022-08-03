package uk.debb.vanilla_disable.mixin.arrow;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

import java.util.Set;

@Mixin(Arrow.class)
public abstract class MixinArrow extends AbstractArrow implements Maps {
    @Shadow
    @Final
    private static EntityDataAccessor<Integer> ID_EFFECT_COLOR;
    @Shadow
    private Potion potion;
    @Shadow
    @Final
    private Set<MobEffectInstance> effects;

    public MixinArrow(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "setEffectsFromItem", at = @At("HEAD"), cancellable = true)
    private void clearEffectsFromItem(ItemStack itemStack, CallbackInfo ci) {
        if (itemStack.is(Items.TIPPED_ARROW)) {
            Gamerules gameRule = arrowPotionMap.get(PotionUtils.getPotion(itemStack));
            if (!Gamerules.TIPPED_ARROWS_ENABLED.getBool() || (gameRule != null && !gameRule.getBool())) {
                this.potion = Potions.EMPTY;
                this.effects.clear();
                this.entityData.set(ID_EFFECT_COLOR, -1);
                ci.cancel();
            }
        }
    }
}
