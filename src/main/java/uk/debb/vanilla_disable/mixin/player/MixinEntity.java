package uk.debb.vanilla_disable.mixin.player;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow
    public abstract EntityType<?> getType();

    @ModifyReturnValue(method = "isOnFire", at = @At("RETURN"))
    private boolean cannotBeOnFire(boolean original) {
        if (this.getType().equals(EntityType.PLAYER) && !Gamerules.PLAYER_CAN_BE_ON_FIRE.getBool()) {
            return false;
        }
        return original;
    }

    @ModifyReturnValue(method = "isShiftKeyDown", at = @At("RETURN"))
    private boolean cannotCrouch(boolean original) {
        if (this.getType().equals(EntityType.PLAYER) && !Gamerules.PLAYER_CAN_CROUCH.getBool()) {
            return false;
        }
        return original;
    }

    @ModifyReturnValue(method = "isSprinting", at = @At("RETURN"))
    private boolean cannotSprint(boolean original) {
        if (this.getType().equals(EntityType.PLAYER) && !Gamerules.PLAYER_CAN_SPRINT.getBool()) {
            return true;
        }
        return original;
    }

    @ModifyReturnValue(method = "isSwimming", at = @At("RETURN"))
    private boolean cannotSwim(boolean original) {
        if (this.getType().equals(EntityType.PLAYER) && !Gamerules.PLAYER_CAN_SWIM.getBool()) {
            return false;
        }
        return original;
    }

    @ModifyReturnValue(method = "isInvisible", at = @At("RETURN"))
    private boolean cannotBeInvisible(boolean original) {
        if (this.getType().equals(EntityType.PLAYER) && !Gamerules.PLAYER_CAN_BE_INVISIBLE.getBool()) {
            return false;
        }
        return original;
    }

    @ModifyReturnValue(method = "getBlockJumpFactor", at = @At("RETURN"))
    private float cannotJump(float original) {
        if (this.getType().equals(EntityType.PLAYER) && !Gamerules.PLAYER_CAN_JUMP.getBool()) {
            return 0.0F;
        }
        return original;
    }
}