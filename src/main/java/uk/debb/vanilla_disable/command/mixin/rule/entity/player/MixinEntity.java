package uk.debb.vanilla_disable.command.mixin.rule.entity.player;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow
    public abstract EntityType<?> getType();

    @ModifyReturnValue(method = "isOnFire", at = @At("RETURN"))
    private boolean isOnFire(boolean original) {
        if (!this.getType().equals(EntityType.PLAYER)) return original;
        return original && CommandDataHandler.getCachedBoolean("entities", "minecraft:player", "can_be_on_fire");
    }

    @ModifyReturnValue(method = "isShiftKeyDown", at = @At("RETURN"))
    private boolean isShiftKeyDown(boolean original) {
        if (!this.getType().equals(EntityType.PLAYER)) return original;
        return original && CommandDataHandler.getCachedBoolean("entities", "minecraft:player", "can_crouch");
    }

    @ModifyReturnValue(method = "isSprinting", at = @At("RETURN"))
    private boolean isSprinting(boolean original) {
        if (!this.getType().equals(EntityType.PLAYER)) return original;
        return original || !CommandDataHandler.getCachedBoolean("entities", "minecraft:player", "can_sprint");
    }

    @ModifyReturnValue(method = "isSwimming", at = @At("RETURN"))
    private boolean isSwimming(boolean original) {
        if (!this.getType().equals(EntityType.PLAYER)) return original;
        return original && CommandDataHandler.getCachedBoolean("entities", "minecraft:player", "can_swim");
    }

    @ModifyReturnValue(method = "isInvisible", at = @At("RETURN"))
    private boolean isInvisible(boolean original) {
        if (!this.getType().equals(EntityType.PLAYER)) return original;
        return original && CommandDataHandler.getCachedBoolean("entities", "minecraft:player", "can_be_invisible");
    }

    @ModifyReturnValue(method = "getBlockJumpFactor", at = @At("RETURN"))
    private float getBlockJumpFactor(float original) {
        if (!this.getType().equals(EntityType.PLAYER)) return original;
        if (!CommandDataHandler.getCachedBoolean("entities", "minecraft:player", "can_jump")) {
            return 0.0F;
        }
        return original;
    }
}
