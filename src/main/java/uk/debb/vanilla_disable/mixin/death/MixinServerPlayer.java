package uk.debb.vanilla_disable.mixin.death;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer {
    @Unique
    private static final Object2ObjectMap<String, GameRules.Key<GameRules.BooleanValue>> deathToGameruleMap = new Object2ObjectOpenHashMap<>();

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addOptionsToMap() {
        deathToGameruleMap.put("inFire", Gamerules.IN_FIRE_DEATH);
        deathToGameruleMap.put("lightningBolt", Gamerules.LIGHTNING_BOLT_DEATH);
        deathToGameruleMap.put("onFire", Gamerules.ON_FIRE_DEATH);
        deathToGameruleMap.put("lava", Gamerules.LAVA_DEATH);
        deathToGameruleMap.put("hotFloor", Gamerules.HOT_FLOOR_DEATH);
        deathToGameruleMap.put("inWall", Gamerules.IN_WALL_DEATH);
        deathToGameruleMap.put("cramming", Gamerules.CRAMMING_DEATH);
        deathToGameruleMap.put("drown", Gamerules.DROWNING_DEATH);
        deathToGameruleMap.put("starve", Gamerules.STARVATION_DEATH);
        deathToGameruleMap.put("cactus", Gamerules.CACTUS_DEATH);
        deathToGameruleMap.put("fall", Gamerules.FALLING_DEATH);
        deathToGameruleMap.put("flyIntoWall", Gamerules.FLY_INTO_WALL_DEATH);
        deathToGameruleMap.put("outOfWorld", Gamerules.OUT_OF_WORLD_DEATH);
        deathToGameruleMap.put("magic", Gamerules.MAGIC_DEATH);
        deathToGameruleMap.put("wither", Gamerules.WITHER_DEATH);
        deathToGameruleMap.put("anvil", Gamerules.ANVIL_DEATH);
        deathToGameruleMap.put("fallingBlock", Gamerules.FALLING_BLOCK_DEATH);
        deathToGameruleMap.put("dragonBreath", Gamerules.DRAGON_BREATH_DEATH);
        deathToGameruleMap.put("sweetBerryBush", Gamerules.SWEET_BERRY_BUSH_DEATH);
        deathToGameruleMap.put("freeze", Gamerules.FREEZING_DEATH);
        deathToGameruleMap.put("fallingStalactite", Gamerules.FALLING_STALACTITE_DEATH);
        deathToGameruleMap.put("stalagmite", Gamerules.STALAGMITE_DEATH);
        deathToGameruleMap.put("explosion", Gamerules.EXPLOSION_DEATH);
        deathToGameruleMap.put("explosion.player", Gamerules.EXPLOSION_DEATH);
        deathToGameruleMap.put("sting", Gamerules.STINGING_DEATH);
        deathToGameruleMap.put("mob", Gamerules.MOB_DEATH);
        deathToGameruleMap.put("player", Gamerules.PLAYER_DEATH);
        deathToGameruleMap.put("thorns", Gamerules.THORNS_DEATH);
        deathToGameruleMap.put("sonicBoom", Gamerules.SONIC_BOOM_DEATH);
    }

    /**
     * @param damageSource the source of the damage causing the death
     * @param ci           the callback info
     * @author DragonEggBedrockBreaking
     */
    @Inject(method = "die", at = @At("HEAD"), cancellable = true)
    private void cancelDeath(DamageSource damageSource, CallbackInfo ci) {
        if (deathToGameruleMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = deathToGameruleMap.get(damageSource.getMsgId());
        if (!GameruleHelper.getBool(Gamerules.DEATH_ENABLED) || (gameRule != null && !GameruleHelper.getBool(gameRule))) {
            ((Player) (Object) this).setHealth(((Player) (Object) this).getHealth() + 1);
            ci.cancel();
        }
    }
}
