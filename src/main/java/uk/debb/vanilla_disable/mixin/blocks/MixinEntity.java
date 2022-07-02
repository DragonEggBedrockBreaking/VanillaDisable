package uk.debb.vanilla_disable.mixin.blocks;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Entity.class)
public abstract class MixinEntity {
    /**
     * @param cir the returnable callback info (Integer)
     * @author DragonEggBedrockBreaking
     * @reason change nether portal cooldown for entities
     */
    @Inject(method = "getDimensionChangingDelay", at = @At("HEAD"), cancellable = true)
    private void modifyDimensionChangingDelay(CallbackInfoReturnable<Integer> cir) {
        if (VDServer.getServer() == null) return;
        cir.setReturnValue(GameruleHelper.getInt(Gamerules.NETHER_PORTAL_COOLDOWN));
    }
}