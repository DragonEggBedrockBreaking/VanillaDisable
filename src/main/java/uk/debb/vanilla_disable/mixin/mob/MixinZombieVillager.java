package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(ZombieVillager.class)
public abstract class MixinZombieVillager {
    /**
     * @author DragonEggBedrockBreaking
     * @reason stop curing of zombie villagers
     * @param player the player curing
     * @param hand the hand of the player
     * @param cir the returnable callback info (net.minecraft.world.InteractionResult)
     */
    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void cureMob(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.CURABLE_ZILLAGERS)) {
            cir.setReturnValue(InteractionResult.CONSUME);
        }
    }
}
