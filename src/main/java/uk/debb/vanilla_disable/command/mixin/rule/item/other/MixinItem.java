package uk.debb.vanilla_disable.command.mixin.rule.item.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(Item.class)
public abstract class MixinItem {
    @Shadow
    public abstract Item asItem();

    @ModifyReturnValue(method = "getMaxDamage", at = @At("RETURN"))
    private int getMaxDamage(int original) {
        if (CommandDataHandler.isConnectionNull()) return original;
        if (!this.asItem().canBeDepleted()) return original;
        String item = CommandDataHandler.getKeyFromItemRegistry(this.asItem());
        return CommandDataHandler.getCachedInt("items", item, "durability");
    }

    @ModifyReturnValue(method = "isFireResistant", at = @At("RETURN"))
    private boolean isFireResistant(boolean original) {
        String item = CommandDataHandler.getKeyFromItemRegistry(this.asItem());
        return !CommandDataHandler.getCachedBoolean("items", item, "burns");
    }

    @ModifyReturnValue(method = "canBeHurtBy", at = @At("RETURN"))
    private boolean canBeHurtBy(boolean original, DamageSource damageSource) {
        String item = CommandDataHandler.getKeyFromItemRegistry(this.asItem());
        if (damageSource.is(DamageTypeTags.IS_FIRE)) {
            return CommandDataHandler.getCachedBoolean("items", item, "burns");
        }
        return original;
    }
}
