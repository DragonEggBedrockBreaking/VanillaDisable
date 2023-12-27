package uk.debb.vanilla_disable.mixin.command.item.other;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(AbstractCauldronBlock.class)
public abstract class MixinAbstractCauldronBlock {
    @Inject(method = "useItemOn", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult, CallbackInfoReturnable<ItemInteractionResult> cir) {
        String name = CommandDataHandler.getKeyFromItemRegistry(itemStack.getItem());
        if (!CommandDataHandler.getCachedBoolean("items", name, "cauldron_interaction")) {
            cir.setReturnValue(ItemInteractionResult.FAIL);
        }
    }
}
