/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.block.function;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import uk.debb.vanilla_disable.config.data.SqlManager;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @WrapMethod(method = "onAboveBubbleColumn")
    private void vanillaDisable$onAboveBubbleColumn(boolean downwards, BlockPos blockPos, Operation<Void> original) {
        if (SqlManager.getBoolean("blocks", "minecraft:bubble_column", "works")) {
            original.call(downwards, blockPos);
        }
    }

    @WrapMethod(method = "onInsideBubbleColumn")
    private void vanillaDisable$onInsideBubbleColumn(boolean downwards, Operation<Void> original) {
        if (SqlManager.getBoolean("blocks", "minecraft:bubble_column", "works")) {
            original.call(downwards);
        }
    }
}
