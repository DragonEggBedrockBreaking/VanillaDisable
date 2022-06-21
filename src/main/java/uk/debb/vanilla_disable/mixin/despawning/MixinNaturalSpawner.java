package uk.debb.vanilla_disable.mixin.despawning;

import java.util.Objects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(NaturalSpawner.class)
public abstract class MixinNaturalSpawner {
    /**
     * @author DragonEggBedrockBreaking
     * @param level the world to spawn in
     * @param chunk the chunk to spawn in
     * @param pos the position to spawn at
     * @param squaredDistance the squared distance from the position
     * @param cir the returnable callback info (Boolean)
     */
    @Inject(method = "isRightDistanceToPlayerAndSpawnPoint", at = @At("HEAD"), cancellable = true)
    private static void mayMeRightDistanceToPlayerAndSpawnPoint(ServerLevel level, ChunkAccess chunk, BlockPos.MutableBlockPos pos, double squaredDistance, CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (squaredDistance <= Math.pow(GameruleHelper.getInt(Gamerules.MIN_SPAWN_DISTANCE), 2)) {
            cir.setReturnValue(false);
        } else if (level.getSharedSpawnPos().closerToCenterThan(new Vec3((double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5), GameruleHelper.getInt(Gamerules.MIN_SPAWN_DISTANCE))) {
            cir.setReturnValue(false);
        } else {
            cir.setReturnValue(Objects.equals(new ChunkPos(pos), chunk.getPos()) || level.isPositionEntityTicking(pos));
        }
    }
}
