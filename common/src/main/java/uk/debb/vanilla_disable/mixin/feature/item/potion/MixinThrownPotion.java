/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.item.potion;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.projectile.ThrownSplashPotion;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.config.data.DataUtils;

@Mixin(ThrownSplashPotion.class)
public abstract class MixinThrownPotion {
    @ModifyExpressionValue(
            method = "onHitAsPotion",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;getOrDefault(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Ljava/lang/Object;",
                    ordinal = 0
            )
    )
    private Object vanillaDisable$getOrDefault(Object original) {
        String item = DataUtils.getKeyFromItemRegistry(((ThrownSplashPotion) (Object) this).getItem().getItem());
        return DataUtils.getPotionContents((PotionContents) original, item);
    }
}
