package uk.debb.vanilla_disable.mixin.player;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
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
     * @param cir the returnable callback info (Boolean)
     * @author DragonEggBedrockBreaking
     * @reason stop players from being set on fire
     */
    @Inject(method = "isOnFire", at = @At("HEAD"), cancellable = true)
    private void cannotBeOnFire(CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if ((Object) this instanceof Player && !GameruleHelper.getBool(Gamerules.PLAYER_CAN_BE_ON_FIRE)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * @param cir the returnable callback info (Boolean)
     * @author DragonEggBedrockBreaking
     * @reason stop players from crouching under slabs
     */
    @Inject(method = "isShiftKeyDown", at = @At("HEAD"), cancellable = true)
    private void cannotCrouch(CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if ((Object) this instanceof Player && !GameruleHelper.getBool(Gamerules.PLAYER_CAN_CROUCH)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * @param cir the returnable callback info (Boolean)
     * @author DragonEggBedrockBreaking
     * @reason stop players from sprinting
     */
    @Inject(method = "isSprinting", at = @At("HEAD"), cancellable = true)
    private void cannotSprint(CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if ((Object) this instanceof Player && !GameruleHelper.getBool(Gamerules.PLAYER_CAN_SPRINT)) {
            cir.setReturnValue(true);
        }
    }

    /**
     * @param cir the returnable callback info (Boolean)
     * @author DragonEggBedrockBreaking
     * @reason stop players from swimming
     */
    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    private void cannotSwim(CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if ((Object) this instanceof Player && !GameruleHelper.getBool(Gamerules.PLAYER_CAN_SWIM)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * @param cir the returnable callback info (Boolean)
     * @author DragonEggBedrockBreaking
     * @reason stop players from being invisible
     */
    @Inject(method = "isInvisible", at = @At("HEAD"), cancellable = true)
    private void cannotBeInvisible(CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if ((Object) this instanceof Player && !GameruleHelper.getBool(Gamerules.PLAYER_CAN_BE_INVISIBLE)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * @param cir the returnable callback info (Float)
     * @author DragonEggBedrockBreaking
     * @reason stop players from jumping
     */
    @Inject(method = "getBlockJumpFactor", at = @At("HEAD"), cancellable = true)
    private void cannotJump(CallbackInfoReturnable<Float> cir) {
        if (VDServer.getServer() == null) return;
        if ((Object) this instanceof Player && !GameruleHelper.getBool(Gamerules.PLAYER_CAN_JUMP)) {
            cir.setReturnValue(0.0F);
        }
    }
}