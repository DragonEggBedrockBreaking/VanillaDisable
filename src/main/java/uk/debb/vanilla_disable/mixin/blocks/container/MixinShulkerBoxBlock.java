package uk.debb.vanilla_disable.mixin.blocks.container;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(ShulkerBoxBlock.class)
public abstract class MixinShulkerBoxBlock {
    /**
     * @author DragonEggBedrockBreaking
     * @param blockState the state of the shulker box
     * @param level the level the shulker box is in
     * @param blockPos the position of the shulker box
     * @param shulkerBoxBlockEntity the block entity code for the shulker box
     * @param cir the returnable callback info (Boolean)
     */
    @Inject(method = "canOpen", at = @At("HEAD"), cancellable = true)
    private static void canAlwaysOpen(BlockState blockState, Level level, BlockPos blockPos, ShulkerBoxBlockEntity shulkerBoxBlockEntity, CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.CONTAINER_OPENING_BLOCKED)) {
            cir.setReturnValue(true);
        }
    }
}
