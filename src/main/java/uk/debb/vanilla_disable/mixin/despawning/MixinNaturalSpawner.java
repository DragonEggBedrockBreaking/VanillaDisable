package uk.debb.vanilla_disable.mixin.despawning;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

import java.util.Objects;

@Mixin(NaturalSpawner.class)
public abstract class MixinNaturalSpawner {
    /**
     * @param original        the original value
     * @param level           the world to spawn in
     * @param chunk           the chunk to spawn in
     * @param pos             the position to spawn at
     * @param squaredDistance the squared distance from the position
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "isRightDistanceToPlayerAndSpawnPoint", at = @At("RETURN"))
    private static boolean mayMeRightDistanceToPlayerAndSpawnPoint(boolean original, ServerLevel level, ChunkAccess chunk, BlockPos.MutableBlockPos pos, double squaredDistance) {
        if (squaredDistance <= Math.pow(GameruleHelper.getInt(Gamerules.MIN_SPAWN_DISTANCE), 2)) {
            return false;
        } else if (level.getSharedSpawnPos().closerToCenterThan(new Vec3((double) pos.getX() + 0.5, pos.getY(), (double) pos.getZ() + 0.5), GameruleHelper.getInt(Gamerules.MIN_SPAWN_DISTANCE))) {
            return false;
        } else {
            return Objects.equals(new ChunkPos(pos), chunk.getPos()) || level.isPositionEntityTicking(pos);
        }
    }
}