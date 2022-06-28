package uk.debb.vanilla_disable.mixin.mob_toggles;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Mob.class)
public abstract class MixinMob extends LivingEntity {
    public MixinMob(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason map of most mobs to their gamerules
     */
    Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> mobTypeMap = new Object2ObjectOpenHashMap<>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addOptionsToMap() {
        mobTypeMap.put(Allay.class, Gamerules.ALLAYS_ENABLED);
        mobTypeMap.put(Bat.class, Gamerules.BATS_ENABLED);
        mobTypeMap.put(Cat.class, Gamerules.CATS_ENABLED);
        mobTypeMap.put(Chicken.class, Gamerules.CHICKENS_ENABLED);
        mobTypeMap.put(Cod.class, Gamerules.CODS_ENABLED);
        mobTypeMap.put(Cow.class, Gamerules.COWS_ENABLED);
        mobTypeMap.put(Donkey.class, Gamerules.DONKEYS_ENABLED);
        mobTypeMap.put(Fox.class, Gamerules.FOXES_ENABLED);
        mobTypeMap.put(Frog.class, Gamerules.FROGS_ENABLED);
        mobTypeMap.put(Horse.class, Gamerules.HORSES_ENABLED);
        mobTypeMap.put(MushroomCow.class, Gamerules.MOOSHROOMS_ENABLED);
        mobTypeMap.put(Mule.class, Gamerules.MULES_ENABLED);
        mobTypeMap.put(Ocelot.class, Gamerules.OCELOTS_ENABLED);
        mobTypeMap.put(Parrot.class, Gamerules.PARROTS_ENABLED);
        mobTypeMap.put(Pig.class, Gamerules.PIGS_ENABLED);
        mobTypeMap.put(Pufferfish.class, Gamerules.PUFFERFISH_ENABLED);
        mobTypeMap.put(Rabbit.class, Gamerules.RABBITS_ENABLED);
        mobTypeMap.put(Salmon.class, Gamerules.SALMONS_ENABLED);
        mobTypeMap.put(Sheep.class, Gamerules.SHEEP_ENABLED);
        mobTypeMap.put(SkeletonHorse.class, Gamerules.SKELETON_HORSES_ENABLED);
        mobTypeMap.put(SnowGolem.class, Gamerules.SNOW_GOLEMS_ENABLED);
        mobTypeMap.put(Squid.class, Gamerules.SQUIDS_ENABLED);
        mobTypeMap.put(Strider.class, Gamerules.STRIDERS_ENABLED);
        mobTypeMap.put(Tadpole.class, Gamerules.TADPOLES_ENABLED);
        mobTypeMap.put(TropicalFish.class, Gamerules.TROPICAL_FISH_ENABLED);
        mobTypeMap.put(Turtle.class, Gamerules.TURTLES_ENABLED);
        mobTypeMap.put(Villager.class, Gamerules.VILLAGERS_ENABLED);
        mobTypeMap.put(WanderingTrader.class, Gamerules.WANDERING_TRADERS_ENABLED);
        mobTypeMap.put(Bee.class, Gamerules.BEES_ENABLED);
        mobTypeMap.put(CaveSpider.class, Gamerules.CAVE_SPIDERS_ENABLED);
        mobTypeMap.put(Dolphin.class, Gamerules.DOLPHINS_ENABLED);
        mobTypeMap.put(EnderMan.class, Gamerules.ENDERMEN_ENABLED);
        mobTypeMap.put(Goat.class, Gamerules.GOATS_ENABLED);
        mobTypeMap.put(IronGolem.class, Gamerules.IRON_GOLEMS_ENABLED);
        mobTypeMap.put(Llama.class, Gamerules.LLAMAS_ENABLED);
        mobTypeMap.put(Panda.class, Gamerules.PANDAS_ENABLED);
        mobTypeMap.put(Piglin.class, Gamerules.PIGLINS_ENABLED);
        mobTypeMap.put(PolarBear.class, Gamerules.POLAR_BEARS_ENABLED);
        mobTypeMap.put(Spider.class, Gamerules.SPIDERS_ENABLED);
        mobTypeMap.put(TraderLlama.class, Gamerules.TRADER_LLAMAS_ENABLED);
        mobTypeMap.put(Wolf.class, Gamerules.WOLVES_ENABLED);
        mobTypeMap.put(ZombifiedPiglin.class, Gamerules.ZOMBIFIED_PIGLINS_ENABLED);
        mobTypeMap.put(Blaze.class, Gamerules.BLAZES_ENABLED);
        mobTypeMap.put(Creeper.class, Gamerules.CREEPERS_ENABLED);
        mobTypeMap.put(Drowned.class, Gamerules.DROWNED_ENABLED);
        mobTypeMap.put(ElderGuardian.class, Gamerules.ELDER_GUARDIANS_ENABLED);
        mobTypeMap.put(Endermite.class, Gamerules.ENDERMITES_ENABLED);
        mobTypeMap.put(Evoker.class, Gamerules.EVOKERS_ENABLED);
        mobTypeMap.put(Ghast.class, Gamerules.GHASTS_ENABLED);
        mobTypeMap.put(Guardian.class, Gamerules.GUARDIANS_ENABLED);
        mobTypeMap.put(Hoglin.class, Gamerules.HOGLINS_ENABLED);
        mobTypeMap.put(Husk.class, Gamerules.HUSKS_ENABLED);
        mobTypeMap.put(MagmaCube.class, Gamerules.MAGMA_CUBES_ENABLED);
        mobTypeMap.put(Phantom.class, Gamerules.PHANTOMS_ENABLED);
        mobTypeMap.put(PiglinBrute.class, Gamerules.PIGLIN_BRUTES_ENABLED);
        mobTypeMap.put(Pillager.class, Gamerules.PILLAGERS_ENABLED);
        mobTypeMap.put(Ravager.class, Gamerules.RAVAGERS_ENABLED);
        mobTypeMap.put(Shulker.class, Gamerules.SHULKERS_ENABLED);
        mobTypeMap.put(Silverfish.class, Gamerules.SILVERFISH_ENABLED);
        mobTypeMap.put(Skeleton.class, Gamerules.SKELETONS_ENABLED);
        mobTypeMap.put(Slime.class, Gamerules.SLIMES_ENABLED);
        mobTypeMap.put(Stray.class, Gamerules.STRAYS_ENABLED);
        mobTypeMap.put(Vex.class, Gamerules.VEXES_ENABLED);
        mobTypeMap.put(Vindicator.class, Gamerules.VINDICATORS_ENABLED);
        mobTypeMap.put(Warden.class, Gamerules.WARDENS_ENABLED);
        mobTypeMap.put(Witch.class, Gamerules.WITCHES_ENABLED);
        mobTypeMap.put(WitherSkeleton.class, Gamerules.WITHER_SKELETONS_ENABLED);
        mobTypeMap.put(Zoglin.class, Gamerules.ZOGLINS_ENABLED);
        mobTypeMap.put(Zombie.class, Gamerules.ZOMBIES_ENABLED);
        mobTypeMap.put(ZombieVillager.class, Gamerules.ZOMBIE_VILLAGERS_ENABLED);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason stops mobs from spawning if they are disabled
     * @param ci the callback info
     */
    @Inject(method = "checkDespawn", at = @At("HEAD"), cancellable = true)
    private void checkIfEnabled(CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (mobTypeMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = mobTypeMap.get(this.getClass());
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            this.discard();
        }
    }
}
