package uk.debb.vanilla_disable.mixin.despawning;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Mob.class)
public abstract class MixinMob extends Entity {
    public MixinMob(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private static final Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> spawnGroupDespawnMap = new Object2ObjectOpenHashMap<>();

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addOptionsToMap() {
        spawnGroupDespawnMap.put(Monster.class, Gamerules.MONSTERS_DESPAWN);
        spawnGroupDespawnMap.put(Animal.class, Gamerules.CREATURES_DESPAWN);
        spawnGroupDespawnMap.put(AmbientCreature.class, Gamerules.AMBIENT_DESPAWN);
        spawnGroupDespawnMap.put(Axolotl.class, Gamerules.AXOLOTLS_DESPAWN);
        spawnGroupDespawnMap.put(GlowSquid.class, Gamerules.GLOWSQUIDS_DESPAWN);
        spawnGroupDespawnMap.put(AbstractFish.class, Gamerules.WATER_AMBIENT_DESPAWN);
        spawnGroupDespawnMap.put(WaterAnimal.class, Gamerules.WATER_CREATURES_DESPAWN);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @return Whether the additional restrictions are met
     */
    @Unique
    private boolean additionalRestrictionsMet() {
        if (this instanceof Bucketable bucketable) {
            return !this.hasCustomName() && !bucketable.fromBucket();
        }
        return true;
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason Change whether the mob despawns based on gamerules
     */
    @Inject(method = "removeWhenFarAway", at = @At("HEAD"), cancellable = true)
    private void cancelRemovalWhenFarAway(double distanceSquared, CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (spawnGroupDespawnMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = spawnGroupDespawnMap.get(this.getClass());
        if (gameRule != null && !(((Object) this) instanceof AbstractVillager)) {
            cir.setReturnValue(GameruleHelper.getBool(gameRule) && additionalRestrictionsMet());
        }
    }
}
