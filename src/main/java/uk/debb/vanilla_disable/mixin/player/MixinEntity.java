package uk.debb.vanilla_disable.mixin.player;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow
    public abstract EntityType<?> getType();

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "isOnFire", at = @At("RETURN"))
    private boolean cannotBeOnFire(boolean original) {
        if (this.getType() == EntityType.PLAYER && !GameruleHelper.getBool(Gamerules.PLAYER_CAN_BE_ON_FIRE)) {
            return false;
        }
        return original;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "isShiftKeyDown", at = @At("RETURN"))
    private boolean cannotCrouch(boolean original) {
        if (this.getType() == EntityType.PLAYER && !GameruleHelper.getBool(Gamerules.PLAYER_CAN_CROUCH)) {
            return false;
        }
        return original;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "isSprinting", at = @At("RETURN"))
    private boolean cannotSprint(boolean original) {
        if (this.getType() == EntityType.PLAYER && !GameruleHelper.getBool(Gamerules.PLAYER_CAN_SPRINT)) {
            return true;
        }
        return original;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "isSwimming", at = @At("RETURN"))
    private boolean cannotSwim(boolean original) {
        if (this.getType() == EntityType.PLAYER && !GameruleHelper.getBool(Gamerules.PLAYER_CAN_SWIM)) {
            return false;
        }
        return original;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "isInvisible", at = @At("RETURN"))
    private boolean cannotBeInvisible(boolean original) {
        if (this.getType() == EntityType.PLAYER && !GameruleHelper.getBool(Gamerules.PLAYER_CAN_BE_INVISIBLE)) {
            return false;
        }
        return original;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getBlockJumpFactor", at = @At("RETURN"))
    private float cannotJump(float original) {
        if (this.getType() == EntityType.PLAYER && !GameruleHelper.getBool(Gamerules.PLAYER_CAN_JUMP)) {
            return 0.0F;
        }
        return original;
    }
}