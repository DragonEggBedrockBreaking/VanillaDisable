package uk.debb.vanilla_disable.mixin.despawning;

import java.util.Objects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(SpawnHelper.class)
public abstract class MixinSpawnHelper {
    /**
     * @author DragonEggBedrockBreaking
     * @param world the world to spawn in
     * @param chunk the chunk to spawn in
     * @param pos the position to spawn at
     * @param squaredDistance the squared distance from the position
     * @param cir the returnable callback info
     */
    @Inject(method = "isAcceptableSpawnPosition", at = @At("HEAD"), cancellable = true)
    private static void mayBeAcceptableSpawnPosition(ServerWorld world, Chunk chunk, BlockPos.Mutable pos, double squaredDistance, CallbackInfoReturnable<Boolean> cir) {
        if (squaredDistance <= Math.pow(RegisterGamerules.getServer().getGameRules().getInt(RegisterGamerules.MIN_SPAWN_DISTANCE), 2)) {
            cir.setReturnValue(false);
        } else if (world.getSpawnPos().isWithinDistance(new Vec3d((double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5), RegisterGamerules.getServer().getGameRules().getInt(RegisterGamerules.MIN_SPAWN_DISTANCE))) {
            cir.setReturnValue(false);
        } else {
            cir.setReturnValue(Objects.equals(new ChunkPos(pos), chunk.getPos()) || world.shouldTickEntity(pos));
        }
    }
}
