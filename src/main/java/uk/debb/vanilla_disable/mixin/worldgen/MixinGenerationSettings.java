package uk.debb.vanilla_disable.mixin.worldgen;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.GameRules;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.feature.EndPlacedFeatures;
import net.minecraft.world.gen.feature.MiscPlacedFeatures;
import net.minecraft.world.gen.feature.NetherPlacedFeatures;
import net.minecraft.world.gen.feature.OceanPlacedFeatures;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.TreePlacedFeatures;
import net.minecraft.world.gen.feature.UndergroundPlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.feature.VillagePlacedFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(GenerationSettings.class)
public abstract class MixinGenerationSettings {
    /**
     * @author DragonEggBedrockBreaking
     * @reason map of all placed features to their gamerules
     */
    @Unique
    private static final Map<String, GameRules.Key<GameRules.BooleanRule>> featureToGameruleMap = new HashMap<String, GameRules.Key<GameRules.BooleanRule>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addOptionsToMap() {
        featureToGameruleMap.put(OceanPlacedFeatures.KELP_COLD.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(OceanPlacedFeatures.KELP_WARM.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(OceanPlacedFeatures.SEA_PICKLE.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(OceanPlacedFeatures.SEAGRASS_COLD.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(OceanPlacedFeatures.SEAGRASS_DEEP.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(OceanPlacedFeatures.SEAGRASS_DEEP_COLD.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(OceanPlacedFeatures.SEAGRASS_DEEP_WARM.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(OceanPlacedFeatures.SEAGRASS_NORMAL.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(OceanPlacedFeatures.SEAGRASS_RIVER.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(OceanPlacedFeatures.SEAGRASS_SIMPLE.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(OceanPlacedFeatures.SEAGRASS_SWAMP.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(OceanPlacedFeatures.SEAGRASS_WARM.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(OceanPlacedFeatures.WARM_OCEAN_VEGETATION.toString(), RegisterGamerules.OCEAN_VEGETATION);

        featureToGameruleMap.put(UndergroundPlacedFeatures.MONSTER_ROOM.toString(), RegisterGamerules.DUNGEON_GENERATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.MONSTER_ROOM_DEEP.toString(), RegisterGamerules.DUNGEON_GENERATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.FOSSIL_LOWER.toString(), RegisterGamerules.FOSSIL_GENERATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.FOSSIL_UPPER.toString(), RegisterGamerules.FOSSIL_GENERATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.DRIPSTONE_CLUSTER.toString(), RegisterGamerules.DRIPSTONE_GENERATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.LARGE_DRIPSTONE.toString(), RegisterGamerules.DRIPSTONE_GENERATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.POINTED_DRIPSTONE.toString(), RegisterGamerules.DRIPSTONE_GENERATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.UNDERWATER_MAGMA.toString(), RegisterGamerules.UNDERWATER_MAGMA_GENERATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.GLOW_LICHEN.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.ROOTED_AZALEA_TREE.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.CAVE_VINES.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.LUSH_CAVES_CEILING_VEGETATION.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.LUSH_CAVES_CLAY.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.LUSH_CAVES_VEGETATION.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.SPORE_BLOSSOM.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.CLASSIC_VINES_CAVE_FEATURE.toString(), RegisterGamerules.UNDERGROUND_VEGETATION);
        featureToGameruleMap.put(UndergroundPlacedFeatures.AMETHYST_GEODE.toString(), RegisterGamerules.AMETHYST_GEODE_GENERATION);

        featureToGameruleMap.put(EndPlacedFeatures.END_SPIKE.toString(), RegisterGamerules.OBSIDIAN_END_PILLAR_GENERATION);
        featureToGameruleMap.put(EndPlacedFeatures.END_GATEWAY_RETURN.toString(), RegisterGamerules.RETURN_END_GATEWAY_GENERATION);
        featureToGameruleMap.put(EndPlacedFeatures.END_ISLAND_DECORATED.toString(), RegisterGamerules.SMALL_END_ISLAND_GENERATION);
        featureToGameruleMap.put(EndPlacedFeatures.CHORUS_PLANT.toString(), RegisterGamerules.END_VEGETATION);

        featureToGameruleMap.put(NetherPlacedFeatures.DELTA.toString(), RegisterGamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacedFeatures.SMALL_BASALT_COLUMNS.toString(), RegisterGamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacedFeatures.LARGE_BASALT_COLUMNS.toString(), RegisterGamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacedFeatures.BASALT_BLOBS.toString(), RegisterGamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacedFeatures.BASALT_PILLAR.toString(), RegisterGamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacedFeatures.BLACKSTONE_BLOBS.toString(), RegisterGamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacedFeatures.GLOWSTONE.toString(), RegisterGamerules.GLOWSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacedFeatures.GLOWSTONE_EXTRA.toString(), RegisterGamerules.GLOWSTONE_GENERATION);
        featureToGameruleMap.put(NetherPlacedFeatures.SPRING_CLOSED.toString(), RegisterGamerules.NETHER_SPRING_GENERATION);
        featureToGameruleMap.put(NetherPlacedFeatures.SPRING_CLOSED_DOUBLE.toString(), RegisterGamerules.NETHER_SPRING_GENERATION);
        featureToGameruleMap.put(NetherPlacedFeatures.SPRING_DELTA.toString(), RegisterGamerules.NETHER_SPRING_GENERATION);
        featureToGameruleMap.put(NetherPlacedFeatures.SPRING_OPEN.toString(), RegisterGamerules.NETHER_SPRING_GENERATION);
        featureToGameruleMap.put(NetherPlacedFeatures.PATCH_FIRE.toString(), RegisterGamerules.NETHER_FIRE_GENERATION);
        featureToGameruleMap.put(NetherPlacedFeatures.PATCH_SOUL_FIRE.toString(), RegisterGamerules.NETHER_FIRE_GENERATION);
        featureToGameruleMap.put(NetherPlacedFeatures.CRIMSON_FOREST_VEGETATION.toString(), RegisterGamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(NetherPlacedFeatures.WARPED_FOREST_VEGETATION.toString(), RegisterGamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(NetherPlacedFeatures.NETHER_SPROUTS.toString(), RegisterGamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(NetherPlacedFeatures.TWISTING_VINES.toString(), RegisterGamerules.NETHER_VEGETATION);
        featureToGameruleMap.put(NetherPlacedFeatures.WEEPING_VINES.toString(), RegisterGamerules.NETHER_VEGETATION);

        featureToGameruleMap.put(MiscPlacedFeatures.ICE_PATCH.toString(), RegisterGamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscPlacedFeatures.ICE_SPIKE.toString(), RegisterGamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscPlacedFeatures.ICEBERG_BLUE.toString(), RegisterGamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscPlacedFeatures.ICEBERG_PACKED.toString(), RegisterGamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscPlacedFeatures.BLUE_ICE.toString(), RegisterGamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscPlacedFeatures.FREEZE_TOP_LAYER.toString(), RegisterGamerules.ICE_GENERATION);
        featureToGameruleMap.put(MiscPlacedFeatures.LAKE_LAVA_SURFACE.toString(), RegisterGamerules.LAVA_LAKE_GENERATION);
        featureToGameruleMap.put(MiscPlacedFeatures.LAKE_LAVA_UNDERGROUND.toString(), RegisterGamerules.LAVA_LAKE_GENERATION);
        featureToGameruleMap.put(MiscPlacedFeatures.DISK_CLAY.toString(), RegisterGamerules.OCEAN_FLOOR_GENERATION);
        featureToGameruleMap.put(MiscPlacedFeatures.DISK_GRAVEL.toString(), RegisterGamerules.OCEAN_FLOOR_GENERATION);
        featureToGameruleMap.put(MiscPlacedFeatures.DISK_SAND.toString(), RegisterGamerules.OCEAN_FLOOR_GENERATION);
        featureToGameruleMap.put(MiscPlacedFeatures.VOID_START_PLATFORM.toString(), RegisterGamerules.OBSIDIAN_END_PILLAR_GENERATION);
        featureToGameruleMap.put(MiscPlacedFeatures.DESERT_WELL.toString(), RegisterGamerules.DESERT_WELL_GENERATION);
        featureToGameruleMap.put(MiscPlacedFeatures.SPRING_LAVA.toString(), RegisterGamerules.SPRING_GENERATION);
        featureToGameruleMap.put(MiscPlacedFeatures.SPRING_LAVA_FROZEN.toString(), RegisterGamerules.SPRING_GENERATION);
        featureToGameruleMap.put(MiscPlacedFeatures.SPRING_WATER.toString(), RegisterGamerules.SPRING_GENERATION);

        featureToGameruleMap.put(TreePlacedFeatures.CRIMSON_FUNGI.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.WARPED_FUNGI.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.OAK_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.DARK_OAK_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.BIRCH_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.ACACIA_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.SPRUCE_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.PINE_ON_SNOW.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.SPRUCE_ON_SNOW.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.PINE_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.JUNGLE_TREE.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.FANCY_OAK_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.MEGA_JUNGLE_TREE_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.MEGA_SPRUCE_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.MEGA_PINE_CHECKED.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.JUNGLE_BUSH.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.SUPER_BIRCH_BEES_0002.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.SUPER_BIRCH_BEES.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.OAK_BEES_0002.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.OAK_BEES_002.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.BIRCH_BEES_0002.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.BIRCH_BEES_002.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.FANCY_OAK_BEES_0002.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.FANCY_OAK_BEES_002.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(TreePlacedFeatures.FANCY_OAK_BEES.toString(), RegisterGamerules.TREE_GENERATION);

        featureToGameruleMap.put(VillagePlacedFeatures.PILE_HAY.toString(), RegisterGamerules.VILLAGE_MISC_FEATURE_GENERATION);
        featureToGameruleMap.put(VillagePlacedFeatures.PILE_ICE.toString(), RegisterGamerules.VILLAGE_MISC_FEATURE_GENERATION);
        featureToGameruleMap.put(VillagePlacedFeatures.PILE_MELON.toString(), RegisterGamerules.VILLAGE_MISC_FEATURE_GENERATION);
        featureToGameruleMap.put(VillagePlacedFeatures.PILE_PUMPKIN.toString(), RegisterGamerules.VILLAGE_MISC_FEATURE_GENERATION);
        featureToGameruleMap.put(VillagePlacedFeatures.PILE_SNOW.toString(), RegisterGamerules.VILLAGE_MISC_FEATURE_GENERATION);
        featureToGameruleMap.put(VillagePlacedFeatures.OAK.toString(), RegisterGamerules.VILLAGE_MISC_FEATURE_GENERATION);
        featureToGameruleMap.put(VillagePlacedFeatures.ACACIA.toString(), RegisterGamerules.VILLAGE_MISC_FEATURE_GENERATION);
        featureToGameruleMap.put(VillagePlacedFeatures.SPRUCE.toString(), RegisterGamerules.VILLAGE_MISC_FEATURE_GENERATION);
        featureToGameruleMap.put(VillagePlacedFeatures.PINE.toString(), RegisterGamerules.VILLAGE_MISC_FEATURE_GENERATION);
        featureToGameruleMap.put(VillagePlacedFeatures.PATCH_BERRY_BUSH.toString(), RegisterGamerules.VILLAGE_MISC_FEATURE_GENERATION);
        featureToGameruleMap.put(VillagePlacedFeatures.PATCH_CACTUS.toString(), RegisterGamerules.VILLAGE_MISC_FEATURE_GENERATION);
        featureToGameruleMap.put(VillagePlacedFeatures.PATCH_TAIGA_GRASS.toString(), RegisterGamerules.VILLAGE_MISC_FEATURE_GENERATION);

        featureToGameruleMap.put(OrePlacedFeatures.ORE_ANCIENT_DEBRIS_LARGE.toString(), RegisterGamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_ANDESITE_LOWER.toString(), RegisterGamerules.EXTRA_STONE_TYPE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_ANDESITE_UPPER.toString(), RegisterGamerules.EXTRA_STONE_TYPE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_BLACKSTONE.toString(), RegisterGamerules.BASALT_BLACKSTONE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_CLAY.toString(), RegisterGamerules.EXTRA_STONE_TYPE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_COAL_LOWER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_COAL_UPPER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_COPPER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_COPPER_LARGE.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_DEBRIS_SMALL.toString(), RegisterGamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_DIAMOND.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_DIAMOND_BURIED.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_DIAMOND_LARGE.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_DIORITE_LOWER.toString(), RegisterGamerules.EXTRA_STONE_TYPE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_DIORITE_UPPER.toString(), RegisterGamerules.EXTRA_STONE_TYPE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_DIRT.toString(), RegisterGamerules.EXTRA_STONE_TYPE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_EMERALD.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_GOLD.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_GOLD_DELTAS.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_GOLD_EXTRA.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_GOLD_LOWER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_GOLD_NETHER.toString(), RegisterGamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_GRANITE_LOWER.toString(), RegisterGamerules.EXTRA_STONE_TYPE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_GRANITE_UPPER.toString(), RegisterGamerules.EXTRA_STONE_TYPE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_GRAVEL.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_GRAVEL_NETHER.toString(), RegisterGamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_INFESTED.toString(), RegisterGamerules.EXTRA_STONE_TYPE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_IRON_MIDDLE.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_IRON_SMALL.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_IRON_UPPER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_LAPIS.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_LAPIS_BURIED.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_MAGMA.toString(), RegisterGamerules.MAGMA_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_QUARTZ_DELTAS.toString(), RegisterGamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_QUARTZ_NETHER.toString(), RegisterGamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_REDSTONE.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_REDSTONE_LOWER.toString(), RegisterGamerules.ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_SOUL_SAND.toString(), RegisterGamerules.NETHER_ORE_GENERATION);
        featureToGameruleMap.put(OrePlacedFeatures.ORE_TUFF.toString(), RegisterGamerules.EXTRA_STONE_TYPE_GENERATION);

        featureToGameruleMap.put(VegetationPlacedFeatures.BAMBOO.toString(), RegisterGamerules.BAMBOO_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.BAMBOO_LIGHT.toString(), RegisterGamerules.BAMBOO_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.BAMBOO_VEGETATION.toString(), RegisterGamerules.BAMBOO_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.VINES.toString(), RegisterGamerules.VINE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_SUNFLOWER.toString(), RegisterGamerules.FLOWER_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_PUMPKIN.toString(), RegisterGamerules.MELON_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_GRASS_BADLANDS.toString(), RegisterGamerules.GRASS_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_GRASS_FOREST.toString(), RegisterGamerules.GRASS_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_GRASS_JUNGLE.toString(), RegisterGamerules.GRASS_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_GRASS_NORMAL.toString(), RegisterGamerules.GRASS_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_GRASS_PLAIN.toString(), RegisterGamerules.GRASS_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_GRASS_SAVANNA.toString(), RegisterGamerules.GRASS_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_GRASS_TAIGA.toString(), RegisterGamerules.GRASS_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_GRASS_TAIGA_2.toString(), RegisterGamerules.GRASS_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.GRASS_BONEMEAL.toString(), RegisterGamerules.GRASS_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_DEAD_BUSH.toString(), RegisterGamerules.DEAD_BUSH_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_DEAD_BUSH_2.toString(), RegisterGamerules.DEAD_BUSH_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_DEAD_BUSH_BADLANDS.toString(), RegisterGamerules.DEAD_BUSH_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_MELON.toString(), RegisterGamerules.MELON_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_MELON_SPARSE.toString(), RegisterGamerules.MELON_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_BERRY_COMMON.toString(), RegisterGamerules.BERRY_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_BERRY_RARE.toString(), RegisterGamerules.BERRY_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_WATERLILY.toString(), RegisterGamerules.OCEAN_VEGETATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_TALL_GRASS.toString(), RegisterGamerules.GRASS_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_TALL_GRASS_2.toString(), RegisterGamerules.GRASS_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_LARGE_FERN.toString(), RegisterGamerules.GRASS_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_CACTUS_DECORATED.toString(), RegisterGamerules.CACTUS_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_CACTUS_DESERT.toString(), RegisterGamerules.CACTUS_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_SUGAR_CANE.toString(), RegisterGamerules.SUGAR_CANE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_SUGAR_CANE_BADLANDS.toString(), RegisterGamerules.SUGAR_CANE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_SUGAR_CANE_DESERT.toString(), RegisterGamerules.SUGAR_CANE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.PATCH_SUGAR_CANE_SWAMP.toString(), RegisterGamerules.SUGAR_CANE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.BROWN_MUSHROOM_NETHER.toString(), RegisterGamerules.MUSHROOM_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL.toString(), RegisterGamerules.MUSHROOM_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.BROWN_MUSHROOM_OLD_GROWTH.toString(), RegisterGamerules.MUSHROOM_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.BROWN_MUSHROOM_SWAMP.toString(), RegisterGamerules.MUSHROOM_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.BROWN_MUSHROOM_TAIGA.toString(), RegisterGamerules.MUSHROOM_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.RED_MUSHROOM_NETHER.toString(), RegisterGamerules.MUSHROOM_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.RED_MUSHROOM_NORMAL.toString(), RegisterGamerules.MUSHROOM_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.RED_MUSHROOM_OLD_GROWTH.toString(), RegisterGamerules.MUSHROOM_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.RED_MUSHROOM_SWAMP.toString(), RegisterGamerules.MUSHROOM_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.RED_MUSHROOM_TAIGA.toString(), RegisterGamerules.MUSHROOM_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.FLOWER_DEFAULT.toString(), RegisterGamerules.FLOWER_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.FLOWER_FLOWER_FOREST.toString(), RegisterGamerules.FLOWER_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.FLOWER_FOREST_FLOWERS.toString(), RegisterGamerules.FLOWER_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.FLOWER_MEADOW.toString(), RegisterGamerules.FLOWER_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.FLOWER_PLAIN.toString(), RegisterGamerules.FLOWER_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.FLOWER_SWAMP.toString(), RegisterGamerules.FLOWER_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.FLOWER_WARM.toString(), RegisterGamerules.FLOWER_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_BADLANDS.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_BIRCH.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_BIRCH_AND_OAK.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_FLOWER_FOREST.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_GROVE.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_JUNGLE.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_MEADOW.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_OLD_GROWTH_PINE_TAIGA.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_OLD_GROWTH_SPRUCE_TAIGA.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_PLAINS.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_SAVANNA.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_SNOWY.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_SPARSE_JUNGLE.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_SWAMP.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_TAIGA.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_WATER.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_WINDSWEPT_FOREST.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_WINDSWEPT_HILLS.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.TREES_WINDSWEPT_SAVANNA.toString(), RegisterGamerules.TREE_GENERATION);
        featureToGameruleMap.put(VegetationPlacedFeatures.MUSHROOM_ISLAND_VEGETATION.toString(), RegisterGamerules.MUSHROOM_GENERATION);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason don't allow disabled features
     * @param feature the feature that is being checked for
     * @param cir the returnable callback info
     */
    @Inject(method = "isFeatureAllowed", at = @At("HEAD"), cancellable = true)
    private void cancelFeature(PlacedFeature feature, CallbackInfoReturnable<Boolean> cir) {
        if (featureToGameruleMap.isEmpty()) {
            this.addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanRule> gameRule = featureToGameruleMap.get(String.format("%s", feature));
        if (gameRule != null && !RegisterGamerules.getServer().getGameRules().getBoolean(gameRule)) {
            cir.setReturnValue(false);
        }
    }
}
