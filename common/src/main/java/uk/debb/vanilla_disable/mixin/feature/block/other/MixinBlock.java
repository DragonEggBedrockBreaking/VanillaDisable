/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.block.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.config.data.DataUtils;
import uk.debb.vanilla_disable.config.data.SqlManager;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Shadow
    protected abstract Block asBlock();

    @ModifyReturnValue(method = "getFriction", at = @At("RETURN"))
    private float vanillaDisable$getFriction(float original) {
        if (SqlManager.isConnectionNull()) return original;
        String block = DataUtils.getKeyFromBlockRegistry(this.asBlock());
        return (float) SqlManager.getDouble("blocks", block, "friction_factor");
    }

    @ModifyReturnValue(method = "getSpeedFactor", at = @At("RETURN"))
    private float vanillaDisable$getSpeedFactor(float original) {
        if (SqlManager.isConnectionNull()) return original;
        String block = DataUtils.getKeyFromBlockRegistry(this.asBlock());
        return (float) SqlManager.getDouble("blocks", block, "speed_factor");
    }

    @ModifyReturnValue(method = "getJumpFactor", at = @At("RETURN"))
    private float vanillaDisable$getJumpFactor(float original) {
        if (SqlManager.isConnectionNull()) return original;
        String block = DataUtils.getKeyFromBlockRegistry(this.asBlock());
        return (float) SqlManager.getDouble("blocks", block, "jump_factor");
    }


    @WrapMethod(method = "playerDestroy")
    private void vanillaDisable$playerDestroy(Level level, Player player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack tool, Operation<Void> original) {
        if (state.is(Blocks.TNT) && SqlManager.getBoolean("blocks", "minecraft:tnt", "alpha_behaviour")) {
            TntBlock.prime(level, pos);
        } else {
            original.call(level, player, pos, state, blockEntity, tool);
        }
    }
}
