/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.entity.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.decoration.PaintingVariants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.config.data.DataDefinitions;
import uk.debb.vanilla_disable.config.data.DataUtils;
import uk.debb.vanilla_disable.config.data.SqlManager;

@Mixin(Painting.class)
public abstract class MixinPainting {
    @ModifyReturnValue(method = "getVariant()Lnet/minecraft/core/Holder;", at = @At("RETURN"))
    private Holder<PaintingVariant> vanillaDisable$getVariant(Holder<PaintingVariant> original) {
        if (SqlManager.isConnectionNull()) {
            return original;
        }
        if (!SqlManager.getBoolean("entities", "minecraft:painting", DataUtils.lightCleanup(original.value().assetId()) + "_painting")) {
            return new Holder.Direct<>(DataDefinitions.paintingVariantRegistry.getValueOrThrow(PaintingVariants.KEBAB));
        }
        return original;
    }
}
