package uk.debb.vanilla_disable.mixin.blocks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(SwordItem.class)
public abstract class MixinSwordItem {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "canAttackBlock", at = @At("RETURN"))
    private boolean canAlwaysAttackBlock(boolean original) {
        if (VDServer.getServer() == null) return original;
        if (GameruleHelper.getBool(Gamerules.CREATIVE_SWORD_CAN_BREAK_BLOCKS)) {
            return true;
        }
        return original;
    }
}