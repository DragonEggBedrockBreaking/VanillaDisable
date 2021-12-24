package uk.debb.vanilla_disable.mixin.knockback;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(ServerWorld.class)
public abstract class MixinServerWorld extends World {
    @Shadow final List<ServerPlayerEntity> players = Lists.newArrayList();
    protected MixinServerWorld(MutableWorldProperties properties, RegistryKey<World> registryRef, DimensionType dimensionType, Supplier<Profiler> profiler, boolean isClient, boolean debugWorld, long seed) {
        super(properties, registryRef, dimensionType, profiler, isClient, debugWorld, seed);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason Prevent knockback from explosions
     * @return The explosion
     */
    @Overwrite
    public Explosion createExplosion(@Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior behavior, double x, double y, double z, float power, boolean createFire, Explosion.DestructionType destructionType) {
        Explosion explosion = new Explosion(this, entity, damageSource, behavior, x, y, z, power, createFire, destructionType);
        explosion.collectBlocksAndDamageEntities();
        explosion.affectWorld(false);
        if (destructionType == Explosion.DestructionType.NONE) {
            explosion.clearAffectedBlocks();
        }
        if (this.getGameRules().getBoolean(RegisterGamerules.KNOCKBACK_ENABLED) &&
            this.getGameRules().getBoolean(RegisterGamerules.EXPLOSION_KNOCKBACK)) {
            for (ServerPlayerEntity serverPlayerEntity : this.players) {
                if (!(serverPlayerEntity.squaredDistanceTo(x, y, z) < 4096.0)) continue;
                serverPlayerEntity.networkHandler.sendPacket(new ExplosionS2CPacket(x, y, z, power, explosion.getAffectedBlocks(), explosion.getAffectedPlayers().get(serverPlayerEntity)));
            }
        }
        return explosion;
    }
}
