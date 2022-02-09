package uk.debb.vanilla_disable.mixin.spawning;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(EntityType.class)
public abstract class MixinEntityType<T extends Entity> {
    /**
     * @author DragonEggBedrockBreaking
     * @reason Disable spawn eggs
     * @param world The world
     * @param stack The item stack
     * @param player The player
     * @param pos The position
     * @param spawnReason The reason for the spawn
     * @param alignPosition The position to align to
     * @param invertY ???
     * @param cir The returnable callback info
     */
    @Inject(method = "spawnFromItemStack", at = @At(value = "HEAD"), cancellable = true)
    private void cancelSpawningFromItemStack(ServerWorld world, ItemStack stack, PlayerEntity player, BlockPos pos, SpawnReason spawnReason, boolean alignPosition, boolean invertY, CallbackInfoReturnable<Entity> cir) {
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.SPAWN_EGGS)) {
            cir.cancel();
        }
    }
}
