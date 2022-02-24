package uk.debb.vanilla_disable.mixin.spawning;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
    @Inject(method = "spawn", at = @At(value = "HEAD"), cancellable = true)
    private void cancelSpawning(ServerLevel level, ItemStack stack, Player player, BlockPos pos, MobSpawnType spawnReason, boolean alignPosition, boolean invertY, CallbackInfoReturnable<Entity> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (!RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.SPAWN_EGGS)) {
            cir.cancel();
        }
    }
}
