package uk.debb.vanilla_disable.command.mixin.rule.block.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(Item.class)
public abstract class MixinItem {
    @Shadow public abstract Item asItem();

    @ModifyReturnValue(method = "isFireResistant", at = @At("RETURN"))
    private boolean isFireResistant(boolean original) {
        if (this.asItem() instanceof BlockItem blockItem) {
            String item = DataHandler.getKeyFromBlockRegistry(blockItem.getBlock());
            return !DataHandler.getBoolean("blocks", item, "burns_as_item");
        }
        return original;
    }

    @ModifyReturnValue(method = "canBeHurtBy", at = @At("RETURN"))
    private boolean canBeHurtBy(boolean original, DamageSource damageSource) {
        if (this.asItem() instanceof BlockItem blockItem) {
            String item = DataHandler.getKeyFromBlockRegistry(blockItem.getBlock());
            if (damageSource.is(DamageTypeTags.IS_FIRE)) {
                return DataHandler.getBoolean("blocks", item, "burns_as_item");
            }
        }
        return original;
    }

    @ModifyReturnValue(method = "canAttackBlock", at = @At("RETURN"))
    private boolean canAttackBlock(boolean original) {
        if (this.asItem() instanceof BlockItem blockItem) {
            String name = DataHandler.getKeyFromBlockRegistry(blockItem.getBlock());
            return DataHandler.getBoolean("blocks", name, "item_can_break_blocks_in_creative");
        };
        return original;
    }
}
