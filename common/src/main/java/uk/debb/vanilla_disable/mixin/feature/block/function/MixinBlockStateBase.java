/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.block.function;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.config.data.DataUtils;
import uk.debb.vanilla_disable.config.data.SqlManager;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class MixinBlockStateBase {
    @Shadow
    public abstract Block getBlock();

    @WrapMethod(method = "entityInside")
    private void vanillaDisable$entityInside(Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier, Operation<Void> original) {
        String block = DataUtils.getKeyFromBlockRegistry(this.getBlock());
        if (SqlManager.getBoolean("blocks", block, "works")) {
            original.call(level, pos, entity, insideBlockEffectApplier);
        }
    }

    @ModifyReturnValue(method = "getSignal", at = @At("RETURN"))
    private int vanillaDisable$getSignal(int original) {
        String block = DataUtils.getKeyFromBlockRegistry(this.getBlock());
        if (!SqlManager.getBoolean("blocks", block, "works")) {
            return 0;
        }
        return original;
    }

    @ModifyReturnValue(method = "getDirectSignal", at = @At("RETURN"))
    private int vanillaDisable$getDirectSignal(int original) {
        String block = DataUtils.getKeyFromBlockRegistry(this.getBlock());
        if (!SqlManager.getBoolean("blocks", block, "works")) {
            return 0;
        }
        return original;
    }

    @ModifyReturnValue(method = "getAnalogOutputSignal", at = @At("RETURN"))
    private int vanillaDisable$getAnalogOutputSignal(int original) {
        String block = DataUtils.getKeyFromBlockRegistry(this.getBlock());
        if (!SqlManager.getBoolean("blocks", block, "works")) {
            return 0;
        }
        return original;
    }
}
