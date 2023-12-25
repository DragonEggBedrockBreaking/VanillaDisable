package uk.debb.vanilla_disable.mixin.gamerule;

import net.minecraft.stats.ServerRecipeBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.gamerule.RegisterGamerules;

@Mixin(ServerRecipeBook.class)
public class MixinServerRecipeBook {
    @Inject(method = "loadRecipes", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$loadRecipes(CallbackInfo ci) {
        if (RegisterGamerules.server == null) return;
        if (!RegisterGamerules.server.getGameRules().getBoolean(RegisterGamerules.RECIPE_BOOK_ENABLED)) {
            ci.cancel();
        }
    }

    @Inject(method = "sendRecipes", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$sendRecipes(CallbackInfo ci) {
        if (RegisterGamerules.server == null) return;
        if (!RegisterGamerules.server.getGameRules().getBoolean(RegisterGamerules.RECIPE_BOOK_ENABLED)) {
            ci.cancel();
        }
    }
}
