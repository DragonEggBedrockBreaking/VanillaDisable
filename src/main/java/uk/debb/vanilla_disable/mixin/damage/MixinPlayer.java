package uk.debb.vanilla_disable.mixin.damage;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Player.class)
public abstract class MixinPlayer {
    /**
     * @author DragonEggBedrockBreaking
     * @reason map of all damage sources to their gamerules
     */
    @Unique
    private static final Object2ObjectMap<DamageSource, GameRules.Key<GameRules.BooleanValue>> damageSourceMap = new Object2ObjectOpenHashMap<DamageSource, GameRules.Key<GameRules.BooleanValue>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addOptionsToMap() {
        damageSourceMap.put(DamageSource.LIGHTNING_BOLT, Gamerules.LIGHTNING_DAMAGE);
        damageSourceMap.put(DamageSource.IN_WALL, Gamerules.WALL_DAMAGE);
        damageSourceMap.put(DamageSource.CRAMMING, Gamerules.CRAMMING_DAMAGE);
        damageSourceMap.put(DamageSource.STARVE, Gamerules.STARVATION_DAMAGE);
        damageSourceMap.put(DamageSource.CACTUS, Gamerules.CACTUS_DAMAGE);
        damageSourceMap.put(DamageSource.FLY_INTO_WALL, Gamerules.FLY_INTO_WALL_DAMAGE);
        damageSourceMap.put(DamageSource.WITHER, Gamerules.WITHER_DAMAGE);
        damageSourceMap.put(DamageSource.ANVIL, Gamerules.ANVIL_DAMAGE);
        damageSourceMap.put(DamageSource.DRAGON_BREATH, Gamerules.DRAGON_DAMAGE);
        damageSourceMap.put(DamageSource.SWEET_BERRY_BUSH, Gamerules.SWEET_BERRY_BUSH_DAMAGE);
        damageSourceMap.put(DamageSource.FALLING_STALACTITE, Gamerules.FALLING_STALACTITE_DAMAGE);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason Removes damage sources
     * @return Opposite of gamerule
     */
    @Inject(method = "isInvulnerableTo", at = @At(value = "HEAD"), cancellable = true)
    private void isAlsoInvulnerableTo(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (damageSourceMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> damageGamerule = damageSourceMap.get(damageSource);
        if (!GameruleHelper.getBool(Gamerules.DAMAGE_ENABLED)) {
            cir.setReturnValue(true);
        } else if (damageGamerule != null && !GameruleHelper.getBool(damageGamerule)) {
            cir.setReturnValue(true);
        } else if (damageSource.isProjectile()) {
            cir.setReturnValue(!GameruleHelper.getBool(Gamerules.PROJECTILE_DAMAGE));
        } else if (damageSource.isExplosion()) {
            cir.setReturnValue(!GameruleHelper.getBool(Gamerules.EXPLOSION_DAMAGE));
        } else if (damageSource.isBypassInvul()) {
            cir.setReturnValue(!GameruleHelper.getBool(Gamerules.VOID_DAMAGE));
        } else if (damageSource.isMagic()) {
            cir.setReturnValue(!GameruleHelper.getBool(Gamerules.MAGIC_DAMAGE));
        } else if (damageSource.isCreativePlayer()) {
            cir.setReturnValue(!GameruleHelper.getBool(Gamerules.CREATIVE_PLAYER_DAMAGE));
        }
    }
}
