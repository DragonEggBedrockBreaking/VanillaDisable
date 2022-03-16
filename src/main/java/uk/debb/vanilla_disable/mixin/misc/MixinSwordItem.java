package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(SwordItem.class)
public abstract class MixinSwordItem {
    /**
     * @author DragonEggBedrockBreaking
     * @reason make swords be able to break blocks in creative mode
     * @param blockState the state of the block being targeted
     * @param level the level
     * @param blockPos the position of the block being targeted
     * @param player the player targetting the block
     * @param cir the returnable callback info
     */
    @Inject(method = "canAttackBlock", at = @At("HEAD"), cancellable = true)
    private void canAlwaysAttackBlock(BlockState blockState, Level level, BlockPos blockPos, Player player, CallbackInfoReturnable<Boolean> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.CREATIVE_SWORD_CAN_BREAK_BLOCKS)) {
            cir.setReturnValue(true);
        }
    }
}
