package uk.debb.vanilla_disable.gamerules.mixin.dispenser;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.gamerules.util.maps.Maps;

@Mixin(DispenserBlock.class)
public abstract class MixinDispenserBlock implements Maps {
    @ModifyReturnValue(method = "getDispenseMethod", at = @At("RETURN"))
    private DispenseItemBehavior modifyDispenseMethod(DispenseItemBehavior original, ItemStack itemStack) {
        Gamerules gameRule = dispenserBlockItemMap.get(itemStack.getItem());
        if (gameRule != null && !gameRule.getBool()) {
            return new DefaultDispenseItemBehavior();
        }
        return original;
    }
}
