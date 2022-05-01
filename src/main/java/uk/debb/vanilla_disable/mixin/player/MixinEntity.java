package uk.debb.vanilla_disable.mixin.player;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(Entity.class)
public abstract class MixinEntity {
    /**
     * @author DragonEggBedrockBreaking
     * @reason stop players from being set on fire
     * @param cir the returnable callback info (Boolean)
     */
    @Inject(method = "isOnFire", at = @At("HEAD"), cancellable = true)
    private void cannotBeOnFire(CallbackInfoReturnable<Boolean> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if ((Object)this instanceof Player && !RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PLAYER_CAN_BE_ON_FIRE)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason stop players from crouching under slabs
     * @param cir the returnable callback info (Boolean)
     */
    @Inject(method = "isShiftKeyDown", at = @At("HEAD"), cancellable = true)
    private void cannotCrouch(CallbackInfoReturnable<Boolean> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if ((Object)this instanceof Player && !RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PLAYER_CAN_CROUCH)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason stop players from sprinting
     * @param cir the returnable callback info (Boolean)
     */
    @Inject(method = "isSprinting", at = @At("HEAD"), cancellable = true)
    private void cannotSprint(CallbackInfoReturnable<Boolean> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if ((Object)this instanceof Player && !RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PLAYER_CAN_SPRINT)) {
            cir.setReturnValue(true);
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason stop players from swimming
     * @param cir the returnable callback info (Boolean)
     */
    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    private void cannotSwim(CallbackInfoReturnable<Boolean> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if ((Object)this instanceof Player && !RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PLAYER_CAN_SWIM)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason stop players from being invisible
     * @param cir
     */
    @Inject(method = "isInvisible", at = @At("HEAD"), cancellable = true)
    private void cannotBeInvisible(CallbackInfoReturnable<Boolean> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if ((Object)this instanceof Player && !RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PLAYER_CAN_BE_INVISIBLE)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason stop players from jumping
     * @param cir
     */
    @Inject(method = "getBlockJumpFactor", at = @At("HEAD"), cancellable = true)
    private void cannotJump(CallbackInfoReturnable<Float> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if ((Object)this instanceof Player && !RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.PLAYER_CAN_JUMP)) {
            cir.setReturnValue(0.0F);
        }
    }
}
