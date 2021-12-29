package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(ZombieEntity.class)
public abstract class MixinZombieEntity extends HostileEntity {
    public MixinZombieEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason stop villagers from turning into zombie villagers
     * @param world the world
     * @param serverWorld the world
     * @param other the entity
     * @return the difficulty
     */
    @Redirect(
        method = "onKilledOther",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/world/ServerWorld;getDifficulty()Lnet/minecraft/world/Difficulty;"
        )
    )
    public Difficulty getWrongDifficulty(ServerWorld world, ServerWorld serverWorld, LivingEntity other) {
        if (world.getGameRules().getBoolean(RegisterGamerules.VILLAGERS_CONVERT_TO_ZILLAGERS)) {
            return world.getDifficulty();
        } else {
            return Difficulty.PEACEFUL;
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason stop zombies from converting into drowned
     * @param ci the callback info
     */
    @Inject(method = "convertInWater", at = @At("HEAD"), cancellable = true)
    private void cancelConversionInWater(CallbackInfo ci) {
        if (!this.world.getGameRules().getBoolean(RegisterGamerules.ZOMBIES_CONVERT_TO_DROWNED)) {
            ci.cancel();
        }
    }
}
