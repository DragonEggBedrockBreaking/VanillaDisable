package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(PiglinAi.class)
public abstract class MixinPiglinAi {
    /**
     * @author DragonEggBedrockBreaking
     * @reason don't allow bartering with any items
     * @param stack the stack that the player is trying to barter with
     * @param cir the returnable callback info (Boolean)
     */
    @Inject(method = "isBarterCurrency", at = @At("HEAD"), cancellable = true)
    private static void cancelBarter(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (!VDServer.getServer().getGameRules().getBoolean(Gamerules.PIGLIN_BARTERING_ENABLED)) {
            cir.setReturnValue(false);
        }
    }
}
