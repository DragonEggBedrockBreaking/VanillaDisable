package uk.debb.vanilla_disable.command.mixin.rule.entity.experience;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(ThrownExperienceBottle.class)
public abstract class MixinThrownExperienceBottle {
    @WrapWithCondition(
            method = "onHit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ExperienceOrb;award(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/phys/Vec3;I)V"
            )
    )
    private boolean award(ServerLevel serverLevel, Vec3 vec3, int i) {
        return DataHandler.getBoolean("entities", "minecraft:experience_bottle", "can_drop_xp");
    }
}
