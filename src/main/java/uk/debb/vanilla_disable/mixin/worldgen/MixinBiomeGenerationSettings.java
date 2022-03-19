package uk.debb.vanilla_disable.mixin.worldgen;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.EndPlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.data.worldgen.placement.VillagePlacements;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(BiomeGenerationSettings.class)
public abstract class MixinBiomeGenerationSettings {
    /**
     * @author DragonEggBedrockBreaking
     * @reason map of all placed features to their gamerules
     */
    @Unique
    private static final Object2ObjectMap<String, GameRules.Key<GameRules.BooleanValue>> featureToGameruleMap = new Object2ObjectOpenHashMap<String, GameRules.Key<GameRules.BooleanValue>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addOptionsToMap() {
        featureToGameruleMap.put(AquaticPlacements.KELP_COLD.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.KELP_WARM.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEA_PICKLE.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_COLD.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_DEEP.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_DEEP_COLD.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_DEEP_WARM.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_NORMAL.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_RIVER.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_SIMPLE.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_SWAMP.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_WARM.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.WARM_OCEAN_VEGETATION.toString(), RegisterGamerules.OCEAN_VEGETATION);

        featureToGameruleMap.put(CavePlacements.MONSTER_ROOM.toString(), RegisterGamerules.DUNGEON_GENERATION);
        featureToGameruleMap.put(CavePlacements.MONSTER_ROOM_DEEP.toString(), RegisterGamerules.DUNGEON_GENERATION);
        featureToGameruleMap.put(CavePlacements.FOSSIL_LOWER.toString(), RegisterGamerules.FOSSIL_GENERATION);
        featureToGameruleMap.put(CavePlacements.FOSSIL_UPPER.toString(), RegisterGamerules.FOSSIL_GENERATION);
        featureToGameruleMap.put(CavePlacements.DRIPSTONE_CLUSTER.toString(), RegisterGamerules.DRIPSTONE_GENERATION);
        featureToGameruleMap.put(CavePlacements.LARGE_DRIPSTONE.toString(), RegisterGamerules.DRIPSTONE_GENERATION);
        featureToGameruleMap.put(CavePlacements.POINTED_DRIPSTONE.toString(), RegisterGamerules.DRIPSTONE_GENERATION);
        featureToGameruleMap.put(CavePlacements.UNDERWATER_MAGMA.toString(), RegisterGamerules.MAGMA_GENERATION);
        featureToGameruleMap.put(CavePlacements.GLOW_LICHEN.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.ROOTED_AZALEA_TREE.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.CAVE_VINES.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.LUSH_CAVES_CEILING_VEGETATION.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.LUSH_CAVES_CLAY.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.LUSH_CAVES_VEGETATION.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.SPORE_BLOSSOM.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.CLASSIC_VINES.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.AMETHYST_GEODE.toString(), RegisterGamerules.AMETHYST_GEODE_GENERATION);

        featureToGameruleMap.put(EndPlacements.END_SPIKE.toString(), RegisterGamerules.END_FEATURES_GENERATION);
        featureToGameruleMap.put(EndPlacements.END_GATEWAY_RETURN.toString(), RegisterGamerules.END_FEATURES_GENERATION);
        featureToGameruleMap.put(EndPlacements.END_ISLAND_DECORATED.toString(), RegisterGamerules.END_FEATURES_GENERATION);
        featureToGameruleMap.put(EndPlacements.CHORUS_PLANT.toString(), RegisterGamerules.END_VEGETATION);

        featureToGameruleMap.put(NetherPlacements.DELTA.toString(), RegisterGamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.SMALL_BASALT_COLUMNS.toString(), RegisterGamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.LARGE_BASALT_COLUMNS.toString(), RegisterGamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.BASALT_BLOBS.toString(), RegisterGamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.BASALT_PILLAR.toString(), RegisterGamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.BLACKSTONE_BLOBS.toString(), RegisterGamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.GLOWSTONE.toString(), RegisterGamerules.GLOWSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.GLOWSTONE_EXTRA.toString(), RegisterGamerules.GLOWSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.SPRING_CLOSED.toString(), RegisterGamerules.SPRING_GENERATION);
        featureToGameruleMap.put(NetherPlacements.SPRING_CLOSED_DOUBLE.toString(), RegisterGamerules.SPRING_GENERATION);
        featureToGameruleMap.put(NetherPlacements.SPRING_DELTA.toString(), RegisterGamerules.SPRING_GENERATION);
        featureToGameruleMap.put(NetherPlacements.SPRING_OPEN.toString(), RegisterGamerules.SPRING_GENERATION);
        featureToGameruleMap.put(NetherPlacements.PATCH_FIRE.toString(), RegisterGamerules.NETHER_FIRE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.PATCH_SOUL_FIRE.toString(), RegisterGamerules.NETHER_FIRE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.CRIMSON_FOREST_VEGETATION.toString(), RegisterGamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(NetherPlacements.WARPED_FOREST_VEGETATION.toString(), RegisterGamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(NetherPlacements.NETHER_SPROUTS.toString(), RegisterGamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(NetherPlacements.TWISTING_VINES.toString(), RegisterGamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(NetherPlacements.WEEPING_VINES.toString(), RegisterGamerules.NETHER_VEGETATION);

        featureToGameruleMap.put(MiscOverworldPlacements.ICE_PATCH.toString(), RegisterGamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.ICE_SPIKE.toString(), RegisterGamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.ICEBERG_BLUE.toString(), RegisterGamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.ICEBERG_PACKED.toString(), RegisterGamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.BLUE_ICE.toString(), RegisterGamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.FREEZE_TOP_LAYER.toString(), RegisterGamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.LAKE_LAVA_SURFACE.toString(), RegisterGamerules.LAVA_LAKE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND.toString(), RegisterGamerules.LAVA_LAKE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.DISK_CLAY.toString(), RegisterGamerules.OCEAN_FLOOR_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.DISK_GRAVEL.toString(), RegisterGamerules.OCEAN_FLOOR_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.DISK_SAND.toString(), RegisterGamerules.OCEAN_FLOOR_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.VOID_START_PLATFORM.toString(), RegisterGamerules.END_FEATURES_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.DESERT_WELL.toString(), RegisterGamerules.DESERT_WELL_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.SPRING_LAVA.toString(), RegisterGamerules.SPRING_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.SPRING_LAVA_FROZEN.toString(), RegisterGamerules.SPRING_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.SPRING_WATER.toString(), RegisterGamerules.SPRING_GENERATION);

        featureToGameruleMap.put(TreePlacements.CRIMSON_FUNGI.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.WARPED_FUNGI.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.OAK_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.DARK_OAK_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.BIRCH_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.ACACIA_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.SPRUCE_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.PINE_ON_SNOW.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.SPRUCE_ON_SNOW.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.PINE_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.JUNGLE_TREE_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.FANCY_OAK_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.MEGA_JUNGLE_TREE_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.MEGA_SPRUCE_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.MEGA_PINE_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.JUNGLE_BUSH.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.SUPER_BIRCH_BEES_0002.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.SUPER_BIRCH_BEES.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.OAK_BEES_0002.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.OAK_BEES_002.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.BIRCH_BEES_0002_PLACED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.BIRCH_BEES_002.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.FANCY_OAK_BEES_0002.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.FANCY_OAK_BEES_002.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.FANCY_OAK_BEES.toString(), RegisterGamerules.TREE_GENERATION);

        featureToGameruleMap.put(VillagePlacements.PILE_HAY_VILLAGE.toString(), RegisterGamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PILE_ICE_VILLAGE.toString(), RegisterGamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PILE_MELON_VILLAGE.toString(), RegisterGamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PILE_PUMPKIN_VILLAGE.toString(), RegisterGamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PILE_SNOW_VILLAGE.toString(), RegisterGamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.OAK_VILLAGE.toString(), RegisterGamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.ACACIA_VILLAGE.toString(), RegisterGamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.SPRUCE_VILLAGE.toString(), RegisterGamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PINE_VILLAGE.toString(), RegisterGamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PATCH_BERRY_BUSH_VILLAGE.toString(), RegisterGamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PATCH_CACTUS_VILLAGE.toString(), RegisterGamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PATCH_TAIGA_GRASS_VILLAGE.toString(), RegisterGamerules.VILLAGE_GENERATION);

        featureToGameruleMap.put(OrePlacements.ORE_ANCIENT_DEBRIS_LARGE.toString(), RegisterGamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_ANDESITE_LOWER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_ANDESITE_UPPER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_BLACKSTONE.toString(), RegisterGamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_CLAY.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_COAL_LOWER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_COAL_UPPER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_COPPER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_COPPER_LARGE.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_ANCIENT_DEBRIS_SMALL.toString(), RegisterGamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_DIAMOND.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_DIAMOND_BURIED.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_DIAMOND_LARGE.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_DIORITE_LOWER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_DIORITE_UPPER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_DIRT.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_EMERALD.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GOLD.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GOLD_DELTAS.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GOLD_EXTRA.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GOLD_LOWER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GOLD_NETHER.toString(), RegisterGamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GRANITE_LOWER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GRANITE_UPPER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GRAVEL.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GRAVEL_NETHER.toString(), RegisterGamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_INFESTED.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_IRON_MIDDLE.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_IRON_SMALL.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_IRON_UPPER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_LAPIS.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_LAPIS_BURIED.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_MAGMA.toString(), RegisterGamerules.MAGMA_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_QUARTZ_DELTAS.toString(), RegisterGamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_QUARTZ_NETHER.toString(), RegisterGamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_REDSTONE.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_REDSTONE_LOWER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_SOUL_SAND.toString(), RegisterGamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_TUFF.toString(), RegisterGamerules.ORE_GENERATION);

        featureToGameruleMap.put(VegetationPlacements.BAMBOO.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.BAMBOO_LIGHT.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.BAMBOO_VEGETATION.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.VINES.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_SUNFLOWER.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_PUMPKIN.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_BADLANDS.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_FOREST.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_JUNGLE.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_NORMAL.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_PLAIN.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_SAVANNA.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_TAIGA.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_TAIGA_2.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.GRASS_BONEMEAL.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_DEAD_BUSH.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_DEAD_BUSH_2.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_DEAD_BUSH_BADLANDS.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_MELON.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_MELON_SPARSE.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_BERRY_COMMON.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_BERRY_RARE.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_WATERLILY.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_TALL_GRASS.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_TALL_GRASS_2.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_LARGE_FERN.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_CACTUS_DECORATED.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_CACTUS_DESERT.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_SUGAR_CANE.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_SUGAR_CANE_BADLANDS.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_SUGAR_CANE_DESERT.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_SUGAR_CANE_SWAMP.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.BROWN_MUSHROOM_NETHER.toString(), RegisterGamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.BROWN_MUSHROOM_NORMAL.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.BROWN_MUSHROOM_OLD_GROWTH.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.BROWN_MUSHROOM_SWAMP.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.BROWN_MUSHROOM_TAIGA.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.RED_MUSHROOM_NETHER.toString(), RegisterGamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.RED_MUSHROOM_NORMAL.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.RED_MUSHROOM_OLD_GROWTH.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.RED_MUSHROOM_SWAMP.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.RED_MUSHROOM_TAIGA.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.FLOWER_DEFAULT.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.FLOWER_FLOWER_FOREST.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.FLOWER_FOREST_FLOWERS.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.FLOWER_MEADOW.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.FLOWER_PLAINS.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.FLOWER_SWAMP.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.FLOWER_WARM.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_BADLANDS.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_BIRCH.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_BIRCH_AND_OAK.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_FLOWER_FOREST.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_GROVE.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_JUNGLE.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_MEADOW.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_OLD_GROWTH_PINE_TAIGA.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_OLD_GROWTH_SPRUCE_TAIGA.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_PLAINS.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_SAVANNA.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_SNOWY.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_SPARSE_JUNGLE.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_SWAMP.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_TAIGA.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_WATER.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_WINDSWEPT_FOREST.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_WINDSWEPT_HILLS.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_WINDSWEPT_SAVANNA.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.MUSHROOM_ISLAND_VEGETATION.toString(), RegisterGamerules.OVERWORLD_VEGETATION);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason don't allow disabled features
     * @param feature the feature that is being checked for
     * @param cir the returnable callback info
     */
    @Inject(method = "hasFeature", at = @At("HEAD"), cancellable = true)
    private void cancelHavingFeature(PlacedFeature feature, CallbackInfoReturnable<Boolean> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (featureToGameruleMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = featureToGameruleMap.get(String.format("%s", feature));
        if (gameRule != null && !RegisterGamerules.getServer().getGameRules().getBoolean(gameRule)) {
            cir.setReturnValue(false);
        }
    }
}
