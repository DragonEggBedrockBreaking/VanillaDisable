package uk.debb.vanilla_disable.command.mixin.rule.item.creative_breaking;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(SwordItem.class)
public abstract class MixinSwordItem {
    @ModifyReturnValue(method = "canAttackBlock", at = @At("RETURN"))
    private boolean canAttackBlock(boolean original) {
        String name = CommandDataHandler.getKeyFromItemRegistry((Item)(Object)this);
        return original || CommandDataHandler.getCachedBoolean("items", name, "can_break_blocks_in_creative");
    }
}
