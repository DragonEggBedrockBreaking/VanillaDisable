package uk.debb.vanilla_disable.mixin.command.block.experience;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import java.util.List;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class MixinAbstractFurnaceBlockEntity {
    @Inject(method = "getRecipesToAwardAndPopExperience", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$getRecipesToAwardAndPopExperience(CallbackInfoReturnable<List<Recipe<?>>> cir) {
        String block = CommandDataHandler.getKeyFromBlockRegistry(((BlockEntity) (Object) this).getBlockState().getBlock());
        if (!CommandDataHandler.getCachedBoolean("blocks", block, "can_drop_xp")) {
            cir.setReturnValue(new ObjectArrayList<>());
        }
    }
}
