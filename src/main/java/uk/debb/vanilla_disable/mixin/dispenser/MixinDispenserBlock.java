package uk.debb.vanilla_disable.mixin.dispenser;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(DispenserBlock.class)
public abstract class MixinDispenserBlock implements Maps {
    @ModifyReturnValue(method = "getDispenseMethod", at = @At("RETURN"))
    private DispenseItemBehavior modifyDispenseMethod(DispenseItemBehavior original, ItemStack itemStack) {
        BooleanGamerules gameRule = dispenserBlockItemMap.get(itemStack.getItem());
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            return new DefaultDispenseItemBehavior();
        }
        return original;
    }
}
