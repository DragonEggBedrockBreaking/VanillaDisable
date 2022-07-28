package uk.debb.vanilla_disable.mixin.despawning;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
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
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(Mob.class)
public abstract class MixinMob extends Entity {
    @Unique
    private static final Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> spawnGroupDespawnMap = new Object2ObjectOpenHashMap<>();

    public MixinMob(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

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

    @Unique
    private boolean additionalRestrictionsMet() {
        if (this instanceof Bucketable bucketable) {
            return !this.hasCustomName() && !bucketable.fromBucket();
        }
        return true;
    }

    @ModifyReturnValue(method = "removeWhenFarAway", at = @At("RETURN"))
    private boolean cancelRemovalWhenFarAway(boolean original) {
        if (spawnGroupDespawnMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = spawnGroupDespawnMap.get(this.getClass());
        if (gameRule != null && !(this.getType() == EntityType.VILLAGER)) {
            return GameruleHelper.getBool(gameRule) && additionalRestrictionsMet();
        }
        return original;
    }
}