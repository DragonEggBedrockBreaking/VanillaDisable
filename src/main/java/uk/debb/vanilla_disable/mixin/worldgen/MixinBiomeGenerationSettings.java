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
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

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
        featureToGameruleMap.put(AquaticPlacements.KELP_COLD.toString(), Gamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.KELP_WARM.toString(), Gamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEA_PICKLE.toString(), Gamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_COLD.toString(), Gamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_DEEP.toString(), Gamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_DEEP_COLD.toString(), Gamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_DEEP_WARM.toString(), Gamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_NORMAL.toString(), Gamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_RIVER.toString(), Gamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_SIMPLE.toString(), Gamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_SWAMP.toString(), Gamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.SEAGRASS_WARM.toString(), Gamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(AquaticPlacements.WARM_OCEAN_VEGETATION.toString(), Gamerules.OCEAN_VEGETATION);

        featureToGameruleMap.put(CavePlacements.MONSTER_ROOM.toString(), Gamerules.DUNGEON_GENERATION);
        featureToGameruleMap.put(CavePlacements.MONSTER_ROOM_DEEP.toString(), Gamerules.DUNGEON_GENERATION);
        featureToGameruleMap.put(CavePlacements.FOSSIL_LOWER.toString(), Gamerules.FOSSIL_GENERATION);
        featureToGameruleMap.put(CavePlacements.FOSSIL_UPPER.toString(), Gamerules.FOSSIL_GENERATION);
        featureToGameruleMap.put(CavePlacements.DRIPSTONE_CLUSTER.toString(), Gamerules.DRIPSTONE_GENERATION);
        featureToGameruleMap.put(CavePlacements.LARGE_DRIPSTONE.toString(), Gamerules.DRIPSTONE_GENERATION);
        featureToGameruleMap.put(CavePlacements.POINTED_DRIPSTONE.toString(), Gamerules.DRIPSTONE_GENERATION);
        featureToGameruleMap.put(CavePlacements.UNDERWATER_MAGMA.toString(), Gamerules.MAGMA_GENERATION);
        featureToGameruleMap.put(CavePlacements.GLOW_LICHEN.toString(), Gamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.ROOTED_AZALEA_TREE.toString(), Gamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.CAVE_VINES.toString(), Gamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.LUSH_CAVES_CEILING_VEGETATION.toString(), Gamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.LUSH_CAVES_CLAY.toString(), Gamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.LUSH_CAVES_VEGETATION.toString(), Gamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.SPORE_BLOSSOM.toString(), Gamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.CLASSIC_VINES.toString(), Gamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(CavePlacements.AMETHYST_GEODE.toString(), Gamerules.AMETHYST_GEODE_GENERATION);

        featureToGameruleMap.put(EndPlacements.END_SPIKE.toString(), Gamerules.END_FEATURES_GENERATION);
        featureToGameruleMap.put(EndPlacements.END_GATEWAY_RETURN.toString(), Gamerules.END_FEATURES_GENERATION);
        featureToGameruleMap.put(EndPlacements.END_ISLAND_DECORATED.toString(), Gamerules.END_FEATURES_GENERATION);
        featureToGameruleMap.put(EndPlacements.CHORUS_PLANT.toString(), Gamerules.END_VEGETATION);

        featureToGameruleMap.put(NetherPlacements.DELTA.toString(), Gamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.SMALL_BASALT_COLUMNS.toString(), Gamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.LARGE_BASALT_COLUMNS.toString(), Gamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.BASALT_BLOBS.toString(), Gamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.BASALT_PILLAR.toString(), Gamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.BLACKSTONE_BLOBS.toString(), Gamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.GLOWSTONE.toString(), Gamerules.GLOWSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.GLOWSTONE_EXTRA.toString(), Gamerules.GLOWSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.SPRING_CLOSED.toString(), Gamerules.SPRING_GENERATION);
        featureToGameruleMap.put(NetherPlacements.SPRING_CLOSED_DOUBLE.toString(), Gamerules.SPRING_GENERATION);
        featureToGameruleMap.put(NetherPlacements.SPRING_DELTA.toString(), Gamerules.SPRING_GENERATION);
        featureToGameruleMap.put(NetherPlacements.SPRING_OPEN.toString(), Gamerules.SPRING_GENERATION);
        featureToGameruleMap.put(NetherPlacements.PATCH_FIRE.toString(), Gamerules.NETHER_FIRE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.PATCH_SOUL_FIRE.toString(), Gamerules.NETHER_FIRE_GENERATION);
        featureToGameruleMap.put(NetherPlacements.CRIMSON_FOREST_VEGETATION.toString(), Gamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(NetherPlacements.WARPED_FOREST_VEGETATION.toString(), Gamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(NetherPlacements.NETHER_SPROUTS.toString(), Gamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(NetherPlacements.TWISTING_VINES.toString(), Gamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(NetherPlacements.WEEPING_VINES.toString(), Gamerules.NETHER_VEGETATION);

        featureToGameruleMap.put(MiscOverworldPlacements.ICE_PATCH.toString(), Gamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.ICE_SPIKE.toString(), Gamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.ICEBERG_BLUE.toString(), Gamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.ICEBERG_PACKED.toString(), Gamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.BLUE_ICE.toString(), Gamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.FREEZE_TOP_LAYER.toString(), Gamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.LAKE_LAVA_SURFACE.toString(), Gamerules.LAVA_LAKE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND.toString(), Gamerules.LAVA_LAKE_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.DISK_CLAY.toString(), Gamerules.OCEAN_FLOOR_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.DISK_GRAVEL.toString(), Gamerules.OCEAN_FLOOR_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.DISK_SAND.toString(), Gamerules.OCEAN_FLOOR_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.VOID_START_PLATFORM.toString(), Gamerules.END_FEATURES_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.DESERT_WELL.toString(), Gamerules.DESERT_WELL_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.SPRING_LAVA.toString(), Gamerules.SPRING_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.SPRING_LAVA_FROZEN.toString(), Gamerules.SPRING_GENERATION);
        featureToGameruleMap.put(MiscOverworldPlacements.SPRING_WATER.toString(), Gamerules.SPRING_GENERATION);

        featureToGameruleMap.put(TreePlacements.CRIMSON_FUNGI.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.WARPED_FUNGI.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.OAK_CHECKED.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.DARK_OAK_CHECKED.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.BIRCH_CHECKED.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.ACACIA_CHECKED.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.SPRUCE_CHECKED.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.PINE_ON_SNOW.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.SPRUCE_ON_SNOW.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.PINE_CHECKED.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.JUNGLE_TREE_CHECKED.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.FANCY_OAK_CHECKED.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.MEGA_JUNGLE_TREE_CHECKED.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.MEGA_SPRUCE_CHECKED.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.MEGA_PINE_CHECKED.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.JUNGLE_BUSH.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.SUPER_BIRCH_BEES_0002.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.SUPER_BIRCH_BEES.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.OAK_BEES_0002.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.OAK_BEES_002.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.BIRCH_BEES_0002_PLACED.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.BIRCH_BEES_002.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.FANCY_OAK_BEES_0002.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.FANCY_OAK_BEES_002.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacements.FANCY_OAK_BEES.toString(), Gamerules.TREE_GENERATION);

        featureToGameruleMap.put(VillagePlacements.PILE_HAY_VILLAGE.toString(), Gamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PILE_ICE_VILLAGE.toString(), Gamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PILE_MELON_VILLAGE.toString(), Gamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PILE_PUMPKIN_VILLAGE.toString(), Gamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PILE_SNOW_VILLAGE.toString(), Gamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.OAK_VILLAGE.toString(), Gamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.ACACIA_VILLAGE.toString(), Gamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.SPRUCE_VILLAGE.toString(), Gamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PINE_VILLAGE.toString(), Gamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PATCH_BERRY_BUSH_VILLAGE.toString(), Gamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PATCH_CACTUS_VILLAGE.toString(), Gamerules.VILLAGE_GENERATION);
        featureToGameruleMap.put(VillagePlacements.PATCH_TAIGA_GRASS_VILLAGE.toString(), Gamerules.VILLAGE_GENERATION);

        featureToGameruleMap.put(OrePlacements.ORE_ANCIENT_DEBRIS_LARGE.toString(), Gamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_ANDESITE_LOWER.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_ANDESITE_UPPER.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_BLACKSTONE.toString(), Gamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_CLAY.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_COAL_LOWER.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_COAL_UPPER.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_COPPER.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_COPPER_LARGE.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_ANCIENT_DEBRIS_SMALL.toString(), Gamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_DIAMOND.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_DIAMOND_BURIED.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_DIAMOND_LARGE.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_DIORITE_LOWER.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_DIORITE_UPPER.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_DIRT.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_EMERALD.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GOLD.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GOLD_DELTAS.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GOLD_EXTRA.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GOLD_LOWER.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GOLD_NETHER.toString(), Gamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GRANITE_LOWER.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GRANITE_UPPER.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GRAVEL.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_GRAVEL_NETHER.toString(), Gamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_INFESTED.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_IRON_MIDDLE.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_IRON_SMALL.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_IRON_UPPER.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_LAPIS.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_LAPIS_BURIED.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_MAGMA.toString(), Gamerules.MAGMA_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_QUARTZ_DELTAS.toString(), Gamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_QUARTZ_NETHER.toString(), Gamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_REDSTONE.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_REDSTONE_LOWER.toString(), Gamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_SOUL_SAND.toString(), Gamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacements.ORE_TUFF.toString(), Gamerules.ORE_GENERATION);

        featureToGameruleMap.put(VegetationPlacements.BAMBOO.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.BAMBOO_LIGHT.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.BAMBOO_VEGETATION.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.VINES.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_SUNFLOWER.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_PUMPKIN.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_BADLANDS.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_FOREST.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_JUNGLE.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_NORMAL.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_PLAIN.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_SAVANNA.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_TAIGA.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_GRASS_TAIGA_2.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.GRASS_BONEMEAL.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_DEAD_BUSH.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_DEAD_BUSH_2.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_DEAD_BUSH_BADLANDS.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_MELON.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_MELON_SPARSE.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_BERRY_COMMON.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_BERRY_RARE.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_WATERLILY.toString(), Gamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_TALL_GRASS.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_TALL_GRASS_2.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_LARGE_FERN.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_CACTUS_DECORATED.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_CACTUS_DESERT.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_SUGAR_CANE.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_SUGAR_CANE_BADLANDS.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_SUGAR_CANE_DESERT.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.PATCH_SUGAR_CANE_SWAMP.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.BROWN_MUSHROOM_NETHER.toString(), Gamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.BROWN_MUSHROOM_NORMAL.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.BROWN_MUSHROOM_OLD_GROWTH.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.BROWN_MUSHROOM_SWAMP.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.BROWN_MUSHROOM_TAIGA.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.RED_MUSHROOM_NETHER.toString(), Gamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.RED_MUSHROOM_NORMAL.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.RED_MUSHROOM_OLD_GROWTH.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.RED_MUSHROOM_SWAMP.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.RED_MUSHROOM_TAIGA.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.FLOWER_DEFAULT.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.FLOWER_FLOWER_FOREST.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.FLOWER_FOREST_FLOWERS.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.FLOWER_MEADOW.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.FLOWER_PLAINS.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.FLOWER_SWAMP.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.FLOWER_WARM.toString(), Gamerules.OVERWORLD_VEGETATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_BADLANDS.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_BIRCH.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_BIRCH_AND_OAK.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_FLOWER_FOREST.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_GROVE.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_JUNGLE.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_MEADOW.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_OLD_GROWTH_PINE_TAIGA.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_OLD_GROWTH_SPRUCE_TAIGA.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_PLAINS.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_SAVANNA.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_SNOWY.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_SPARSE_JUNGLE.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_SWAMP.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_TAIGA.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_WATER.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_WINDSWEPT_FOREST.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_WINDSWEPT_HILLS.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.TREES_WINDSWEPT_SAVANNA.toString(), Gamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacements.MUSHROOM_ISLAND_VEGETATION.toString(), Gamerules.OVERWORLD_VEGETATION);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason don't allow disabled features
     * @param feature the feature that is being checked for
     * @param cir the returnable callback info (Boolean)
     */
    @Inject(method = "hasFeature", at = @At("HEAD"), cancellable = true)
    private void cancelHavingFeature(PlacedFeature feature, CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (featureToGameruleMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = featureToGameruleMap.get(String.format("%s", feature));
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            cir.setReturnValue(false);
        }
    }
}
