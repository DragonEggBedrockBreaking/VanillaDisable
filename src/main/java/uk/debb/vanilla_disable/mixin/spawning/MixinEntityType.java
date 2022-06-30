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
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(EntityType.class)
public abstract class MixinEntityType {
    /**
     * @author DragonEggBedrockBreaking
     * @reason Disable spawn eggs
     * @param level the level
     * @param stack The item stack
     * @param player The player
     * @param pos The position
     * @param spawnReason The reason for the spawn
     * @param alignPosition The position to align to
     * @param invertY ???
     * @param cir The returnable callback info (net.minecraft.world.entity.Entity)
     */
    @Inject(method = "spawn(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/MobSpawnType;ZZ)Lnet/minecraft/world/entity/Entity;", at = @At(value = "HEAD"))
    private void cancelSpawning(ServerLevel level, ItemStack stack, Player player, BlockPos pos, MobSpawnType spawnReason, boolean alignPosition, boolean invertY, CallbackInfoReturnable<Entity> cir) {
        if (VDServer.getServer() == null) return;
        if (!GameruleHelper.getBool(Gamerules.SPAWN_EGGS)) {
            cir.cancel();
        }
    }
}
