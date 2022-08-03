package uk.debb.vanilla_disable.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(AbstractCauldronBlock.class)
public abstract class MixinAbstractCauldronBlock implements Maps {
    /*@ModifyReceiver(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/core/cauldron/CauldronInteraction;interact(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/InteractionResult;"
            )
    )
    private CauldronInteraction modifyUse(CauldronInteraction original, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, ItemStack itemStack) {
        BooleanGamerules gameRule = abstractCauldronBlockItemMap.get(itemStack.getItem());
        if (gameRule != null && !gameRule.getBool()) {
            return (arg, arg2, arg3, arg4, arg5, arg6) -> InteractionResult.FAIL;
        }
        return original;
    }*/

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void cancelUse(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult, CallbackInfoReturnable<InteractionResult> cir) {
        Gamerules gameRule = abstractCauldronBlockItemMap.get(player.getItemInHand(interactionHand).getItem());
        if (gameRule != null && !gameRule.getBool()) {
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }
}
