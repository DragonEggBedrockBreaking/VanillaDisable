package uk.debb.vanilla_disable.mixin.misc;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow public World world;
    /**
     * @author DragonEggBedrockBreaking
     * @reason chnge nether portal cooldown for entities
     * @param cir the returnable callback info
     */
    @Inject(method = "getDefaultNetherPortalCooldown", at = @At("HEAD"), cancellable = true)
    private void modifyDefaultNetherPortalCooldown(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.world.getGameRules().getInt(RegisterGamerules.NETHER_PORTAL_COOLDOWN));
    }
}
