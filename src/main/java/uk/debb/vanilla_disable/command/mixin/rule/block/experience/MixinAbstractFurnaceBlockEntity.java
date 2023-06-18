package uk.debb.vanilla_disable.command.mixin.rule.block.experience;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.command.data.DataHandler;

import java.util.List;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class MixinAbstractFurnaceBlockEntity {
    @Inject(method = "getRecipesToAwardAndPopExperience", at = @At("HEAD"), cancellable = true)
    private void getRecipesToAwardAndPopExperience(CallbackInfoReturnable<List<Recipe<?>>> cir) {
        String block = DataHandler.getKeyFromBlockRegistry(((BlockEntity)(Object)this).getBlockState().getBlock());
        if (!DataHandler.getCachedBoolean("blocks", block, "can_drop_xp")) {
            cir.setReturnValue(new ObjectArrayList<>());
        }
    }
}
