package uk.debb.vanilla_disable.command.mixin.rule.item.other;

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

    @ModifyReturnValue(method = "getMaxDamage", at = @At("RETURN"))
    private int getMaxDamage(int original) {
        if (DataHandler.isConnectionNull()) return original;
        if (this.asItem() instanceof BlockItem) return original;
        if (!this.asItem().canBeDepleted()) return original;
        String item = DataHandler.getKeyFromItemRegistry(this.asItem());
        return DataHandler.getInt("items", item, "durability");
    }

    @ModifyReturnValue(method = "isFireResistant", at = @At("RETURN"))
    private boolean isFireResistant(boolean original) {
        if (this.asItem() instanceof BlockItem) return original;
        String item = DataHandler.getKeyFromItemRegistry(this.asItem());
        return !DataHandler.getBoolean("items", item, "burns");
    }

    @ModifyReturnValue(method = "canBeHurtBy", at = @At("RETURN"))
    private boolean canBeHurtBy(boolean original, DamageSource damageSource) {
        if (this.asItem() instanceof BlockItem) return original;
        String item = DataHandler.getKeyFromItemRegistry(this.asItem());
        if (damageSource.is(DamageTypeTags.IS_FIRE)) {
            return DataHandler.getBoolean("items", item, "burns");
        }
        return original;
    }
}
