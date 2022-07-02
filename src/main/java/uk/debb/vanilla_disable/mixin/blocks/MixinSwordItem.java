package uk.debb.vanilla_disable.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(SwordItem.class)
public abstract class MixinSwordItem {
    /**
     * @param blockState the state of the block being targeted
     * @param level      the level
     * @param blockPos   the position of the block being targeted
     * @param player     the player targeting the block
     * @param cir        the returnable callback info (Boolean)
     * @author DragonEggBedrockBreaking
     * @reason make swords be able to break blocks in creative mode
     */
    @Inject(method = "canAttackBlock", at = @At("HEAD"), cancellable = true)
    private void canAlwaysAttackBlock(BlockState blockState, Level level, BlockPos blockPos, Player player, CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (GameruleHelper.getBool(Gamerules.CREATIVE_SWORD_CAN_BREAK_BLOCKS)) {
            cir.setReturnValue(true);
        }
    }
}