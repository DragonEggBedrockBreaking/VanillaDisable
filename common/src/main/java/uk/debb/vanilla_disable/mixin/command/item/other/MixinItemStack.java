package uk.debb.vanilla_disable.mixin.command.item.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {
    @Shadow
    public abstract Item getItem();

    @ModifyReturnValue(method = "getMaxDamage", at = @At("RETURN"))
    private int vanillaDisable$getMaxDamage(int original) {
        if (CommandDataHandler.isConnectionNull()) return original;
        if (original == 0) return original;
        String item = CommandDataHandler.getKeyFromItemRegistry(this.getItem());
        return CommandDataHandler.getCachedInt("items", item, "durability");
    }

    @ModifyReturnValue(method = "canBeHurtBy", at = @At("RETURN"))
    private boolean vanillaDisable$canBeHurtBy(boolean original, DamageSource damageSource) {
        String item = CommandDataHandler.getKeyFromItemRegistry(this.getItem());
        if (damageSource.is(DamageTypeTags.IS_FIRE)) {
            return CommandDataHandler.getCachedBoolean("items", item, "burns");
        }
        return original;
    }
}
