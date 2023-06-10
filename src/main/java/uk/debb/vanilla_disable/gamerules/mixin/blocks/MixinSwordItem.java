package uk.debb.vanilla_disable.gamerules.mixin.blocks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;

@Mixin(SwordItem.class)
public abstract class MixinSwordItem {
    @ModifyReturnValue(method = "canAttackBlock", at = @At("RETURN"))
    private boolean canAlwaysAttackBlock(boolean original) {
        if (Gamerules.CREATIVE_SWORD_CAN_BREAK_BLOCKS.getBool()) {
            return true;
        }
        return original;
    }
}