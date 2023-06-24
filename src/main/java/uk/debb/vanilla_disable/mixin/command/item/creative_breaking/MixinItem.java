package uk.debb.vanilla_disable.mixin.command.item.creative_breaking;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Item.class)
public abstract class MixinItem {
    @Shadow
    public abstract Item asItem();

    @ModifyReturnValue(method = "canAttackBlock", at = @At("RETURN"))
    private boolean canAttackBlock(boolean original) {
        String name = CommandDataHandler.getKeyFromItemRegistry(this.asItem());
        return CommandDataHandler.getCachedBoolean("items", name, "can_break_blocks_in_creative");
    }
}
