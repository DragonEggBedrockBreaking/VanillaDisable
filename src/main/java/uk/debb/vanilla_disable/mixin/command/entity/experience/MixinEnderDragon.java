package uk.debb.vanilla_disable.mixin.command.entity.experience;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(EnderDragon.class)
public abstract class MixinEnderDragon {
    @WrapWithCondition(
            method = "tickDeath",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ExperienceOrb;award(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/phys/Vec3;I)V"
            )
    )
    private boolean vanillaDisable$award(ServerLevel level, Vec3 pos, int amount) {
        return CommandDataHandler.getCachedBoolean("entities", "minecraft:ender_dragon", "can_drop_xp");
    }
}
