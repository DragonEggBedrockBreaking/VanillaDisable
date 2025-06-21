/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.item.potion;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.config.data.DataUtils;

@Mixin(PotionContents.class)
public abstract class MixinPotionContents {
    @WrapWithCondition(
            method = "onConsume",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/alchemy/PotionContents;applyToLivingEntity(Lnet/minecraft/world/entity/LivingEntity;F)V"
            )
    )
    private boolean vanillaDisable$onConsume(PotionContents instance, LivingEntity livingEntity, float f, Level level, LivingEntity entity, ItemStack stack, Consumable consumable) {
        String item = DataUtils.getKeyFromItemRegistry(stack.getItem());
        return !DataUtils.getPotionContents(instance, item).equals(PotionContents.EMPTY);
    }
}
