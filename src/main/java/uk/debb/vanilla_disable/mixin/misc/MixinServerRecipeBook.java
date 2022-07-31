package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.stats.ServerRecipeBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;

@Mixin(ServerRecipeBook.class)
public class MixinServerRecipeBook {
    @Inject(method = "loadRecipes", at = @At("HEAD"), cancellable = true)
    private void cancelLoadingRecipes(CallbackInfo ci) {
        if (!BooleanGamerules.RECIPE_BOOK_ENABLED.getValue()) {
            ci.cancel();
        }
    }

    @Inject(method = "sendRecipes", at = @At("HEAD"), cancellable = true)
    private void cancelSendingRecipes(CallbackInfo ci) {
        if (!BooleanGamerules.RECIPE_BOOK_ENABLED.getValue()) {
            ci.cancel();
        }
    }
}
