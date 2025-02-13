/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.config.data;

import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.commands.Commands;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.packs.VanillaHusbandryAdvancements;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.entity.vehicle.AbstractBoat;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.material.PushReaction;
import uk.debb.vanilla_disable.platform.Services;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static uk.debb.vanilla_disable.config.data.DataType.*;
import static uk.debb.vanilla_disable.config.data.DataUtils.cleanup;
import static uk.debb.vanilla_disable.config.data.DataUtils.lightCleanup;

public class DataDefinitions {
    public static final Object2ObjectMap<String, Object2ObjectMap<String, Object2ObjectMap<String, ObjectObjectImmutablePair<DataType, Component>>>> colData = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, Object2ObjectMap<String, String>>> rowData = new Object2ObjectOpenHashMap<>();
    public static final Object2DoubleMap<String> numRowMaximums = new Object2DoubleArrayMap<>();
    public static final Object2ObjectMap<String, List<String>> stringColSuggestions = new Object2ObjectOpenHashMap<>();
    public static final ObjectSet<String> differentDataTypes = new ObjectOpenHashSet<>();
    public static final Object2ObjectMap<String, String> singularMap = new Object2ObjectOpenHashMap<>();
    public static MinecraftServer server;
    public static boolean populationDone = false;
    public static Registry<Biome> biomeRegistry;
    public static Registry<Block> blockRegistry;
    public static Registry<ResourceLocation> customStatRegistry;
    public static Registry<DamageType> damageTypeRegistry;
    public static Registry<Enchantment> enchantmentRegistry;
    public static Registry<EntityType<?>> entityTypeRegistry;
    public static Registry<Item> itemRegistry;
    public static Registry<MobEffect> mobEffectRegistry;
    public static Registry<PaintingVariant> paintingVariantRegistry;
    public static Registry<PlacedFeature> placedFeatureRegistry;
    public static Registry<Potion> potionRegistry;
    public static Registry<StatType<?>> statTypeRegistry;
    public static Registry<Structure> structureRegistry;
    public static Registry<VillagerProfession> villagerProfessionRegistry;
    public static Registry<VillagerType> villagerTypeRegistry;
    private static Registry<BlockEntityType<?>> blockEntityRegistry;

    public static void populateRegistries() {
        RegistryAccess registryAccess = server.registryAccess();
        blockRegistry = registryAccess.lookupOrThrow(Registries.BLOCK);
        biomeRegistry = registryAccess.lookupOrThrow(Registries.BIOME);
        blockEntityRegistry = registryAccess.lookupOrThrow(Registries.BLOCK_ENTITY_TYPE);
        customStatRegistry = registryAccess.lookupOrThrow(Registries.CUSTOM_STAT);
        damageTypeRegistry = registryAccess.lookupOrThrow(Registries.DAMAGE_TYPE);
        enchantmentRegistry = registryAccess.lookupOrThrow(Registries.ENCHANTMENT);
        entityTypeRegistry = registryAccess.lookupOrThrow(Registries.ENTITY_TYPE);
        itemRegistry = registryAccess.lookupOrThrow(Registries.ITEM);
        mobEffectRegistry = registryAccess.lookupOrThrow(Registries.MOB_EFFECT);
        paintingVariantRegistry = registryAccess.lookupOrThrow(Registries.PAINTING_VARIANT);
        placedFeatureRegistry = registryAccess.lookupOrThrow(Registries.PLACED_FEATURE);
        potionRegistry = registryAccess.lookupOrThrow(Registries.POTION);
        statTypeRegistry = registryAccess.lookupOrThrow(Registries.STAT_TYPE);
        structureRegistry = registryAccess.lookupOrThrow(Registries.STRUCTURE);
        villagerProfessionRegistry = registryAccess.lookupOrThrow(Registries.VILLAGER_PROFESSION);
        villagerTypeRegistry = registryAccess.lookupOrThrow(Registries.VILLAGER_TYPE);
    }

    public static void populateCols() {
        colData.put("entities", new Object2ObjectOpenHashMap<>() {{
            put("biome_type", new Object2ObjectOpenHashMap<>() {{
                villagerTypeRegistry.keySet().forEach(type -> put(lightCleanup(type) + "_type",
                        new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.villagerTypes", cleanup(type)))));
            }});
            put("breeding_ingredient", new Object2ObjectOpenHashMap<>() {{
                itemRegistry.keySet().forEach(item -> put("can_breed_with_" + lightCleanup(item),
                        new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.breedingIngredient", cleanup(item)))));
            }});
            put("damage", new Object2ObjectOpenHashMap<>() {{
                damageTypeRegistry.keySet().forEach(damageType ->
                        put(lightCleanup(damageType) + "_damage", new ObjectObjectImmutablePair<>(BOOLEAN,
                                Component.translatable("vd.commandRule.entities.damageTypes", cleanup(damageType)))));
            }});
            put("death", new Object2ObjectOpenHashMap<>() {{
                damageTypeRegistry.keySet().forEach(damageType ->
                        put(lightCleanup(damageType) + "_death", new ObjectObjectImmutablePair<>(BOOLEAN,
                                Component.translatable("vd.commandRule.entities.deathTypes", cleanup(damageType)))));
            }});
            put("effects", new Object2ObjectOpenHashMap<>() {{
                mobEffectRegistry.keySet().forEach(mobEffect -> put(lightCleanup(mobEffect) + "_effect",
                        new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.mobEffect", cleanup(mobEffect)))));
            }});
            put("knockback", new Object2ObjectOpenHashMap<>() {{
                entityTypeRegistry.keySet().forEach(entityType -> put(lightCleanup(entityType) + "_knockback",
                        new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.knockbackTypes", cleanup(entityType)))));
                put("explosion_knockback", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.explosionKnockback")));
            }});
            put("other", new Object2ObjectOpenHashMap<>() {{
                put("ai", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.ai")));
                put("alpha_behaviour", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.alphaBehaviour")));
                put("breeding_can_drop_xp", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.breedingCanDropXp")));
                put("burns_in_sunlight", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.burnsInSunlight")));
                put("can_be_converted_to", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canBeConvertedTo")));
                put("can_be_lit_by_fire_aspect", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canBeLitByFireAspect")));
                put("can_drop_xp", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canDropXp")));
                put("can_exist", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canExist")));
                put("can_infinitely_trade", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canInfinitelyTrade")));
                put("can_player_interact", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canPlayerInteract")));
                put("can_shoot_fireballs", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canShootFireballs")));
                put("can_trade", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canTrade")));
                put("daily_restocks", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.entities.dailyRestocks")));
                put("trading_can_drop_xp", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.tradingCanDropXp")));
            }});
            put("painting", new Object2ObjectOpenHashMap<>() {{
                paintingVariantRegistry.keySet().forEach(paintingVariant -> put(lightCleanup(paintingVariant) + "_painting",
                        new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.paintingVariants", cleanup(paintingVariant)))));
            }});
            put("player", new Object2ObjectOpenHashMap<>() {{
                put("can_be_on_fire", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canBeOnFire")));
                put("can_sprint", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canSprint")));
                put("can_crouch", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canCrouch")));
                put("can_swim", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canSwim")));
                put("can_jump", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canJump")));
                put("can_be_invisible", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canBeInvisible")));
                put("flying_speed", new ObjectObjectImmutablePair<>(REAL, Component.translatable("vd.commandRule.entities.flyingSpeed")));
                put("beta_hunger", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.betaHunger")));
            }});
            put("profession", new Object2ObjectOpenHashMap<>() {{
                villagerProfessionRegistry.keySet().forEach(profession -> put(lightCleanup(profession) + "_profession",
                        new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.villagerProfessions", cleanup(profession)))));
            }});
            put("spawning", new Object2ObjectOpenHashMap<>() {{
                put("can_be_summoned", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canBeSummoned")));
                put("can_breed", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canBreed")));
                put("can_despawn", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canDespawn")));
                put("can_spawn", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.canSpawn")));
                put("despawn_on_player_death", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.despawnOnPlayerDeath")));
                put("despawn_time", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.entities.despawnTime")));
                put("instant_despawn_distance", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.entities.instantDespawnDistance")));
                put("min_despawn_distance", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.entities.minDespawnDistance")));
                put("spawn_egg", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.spawnEgg")));
                put("spawned_by_villagers", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.spawnedByVillagers")));
                put("spawner", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.spawner")));
            }});
            put("stats", new Object2ObjectOpenHashMap<>() {{
                statTypeRegistry.forEach(statType -> {
                    if (statType.equals(Stats.CUSTOM)) return;
                    put(lightCleanup(Objects.requireNonNull(statTypeRegistry.getKey(statType))) + "_stat_type",
                            new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.statTypes", cleanup(statType))));
                });
                customStatRegistry.keySet().forEach(customStat -> put(lightCleanup(customStat) + "_custom_stat",
                        new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.entities.customStats", cleanup(customStat)))));
            }});
        }});
        colData.put("blocks", new Object2ObjectOpenHashMap<>() {{
            put("fluid", new Object2ObjectOpenHashMap<>() {{
                put("fluid_reaches_far", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.fluidReachesFar")));
                put("fluid_reaches_far_in_nether", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.fluidReachesFarInNether")));
                put("fluid_speed", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.blocks.fluidSpeed")));
                put("fluid_speed_in_nether", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.blocks.fluidSpeedInNether")));
            }});
            put("other", new Object2ObjectOpenHashMap<>() {{
                put("alpha_behaviour", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.alphaBehaviour")));
                put("burn_odds", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.blocks.burnOdds")));
                put("can_be_filled_by_dripstone", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.canBeFilledByDripstone")));
                put("can_be_filled_by_precipitation", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.canBeFilledByPrecipitation")));
                put("can_be_placed_by_command", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.canBePlacedByCommand")));
                put("can_be_trampled", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.canBeTrampled")));
                put("can_break", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.canBreak")));
                put("can_drop_xp", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.canDropXp")));
                put("can_fall", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.canFall")));
                put("can_interact", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.canInteract")));
                put("can_place_in_end", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.canPlaceInEnd")));
                put("can_place_in_nether", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.canPlaceInNether")));
                put("can_place_in_overworld", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.canPlaceInOverworld")));
                put("cooldown", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.cooldown")));
                put("destroy_speed", new ObjectObjectImmutablePair<>(REAL, Component.translatable("vd.commandRule.blocks.destroySpeed")));
                put("friction_factor", new ObjectObjectImmutablePair<>(REAL, Component.translatable("vd.commandRule.blocks.frictionFactor")));
                put("ignite_odds", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.blocks.igniteOdds")));
                put("ignited_by_lava", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.ignitedByLava")));
                put("jump_factor", new ObjectObjectImmutablePair<>(REAL, Component.translatable("vd.commandRule.blocks.jumpFactor")));
                put("opening_blockable", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.openingBlockable")));
                put("push_behaviour", new ObjectObjectImmutablePair<>(CLOB, Component.translatable("vd.commandRule.blocks.pushBehaviour")));
                put("redstone_delay", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.blocks.redstoneDelay")));
                put("redstone_duration", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.blocks.redstoneDuration")));
                put("requires_correct_tool_for_drops", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.requiresCorrectToolForDrops")));
                put("speed_factor", new ObjectObjectImmutablePair<>(REAL, Component.translatable("vd.commandRule.blocks.speedFactor")));
                put("works", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.blocks.works")));
            }});
        }});
        colData.put("items", new Object2ObjectOpenHashMap<>() {{
            put("potion", new Object2ObjectOpenHashMap<>() {{
                potionRegistry.keySet().forEach(potion -> put(lightCleanup(potion) + "_effect",
                        new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.items.potionEffects", cleanup(potion)))));
            }});

            put("other", new Object2ObjectOpenHashMap<>() {{
                put("burns", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.items.burns")));
                put("can_be_given_by_command", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.items.canBeGivenByCommand")));
                put("can_break_blocks_in_creative", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.items.canBreakBlocksInCreative")));
                put("can_spam", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.items.canSpam")));
                put("cauldron_interaction", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.items.cauldronInteraction")));
                put("dispenser_interaction", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.items.dispenserInteraction")));
                put("durability", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.items.durability")));
                put("fuel_duration", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.items.fuelDuration")));
                if (!Services.PLATFORM.isModLoaded("nostalgic_tweaks")) {
                    put("nutrition", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.items.nutrition")));
                    put("saturation", new ObjectObjectImmutablePair<>(REAL, Component.translatable("vd.commandRule.items.saturation")));
                }
                put("works", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.items.works")));
            }});
        }});
        colData.put("enchantments", new Object2ObjectOpenHashMap<>() {{
            put("item", new Object2ObjectOpenHashMap<>() {{
                itemRegistry.keySet().forEach(item -> put("can_enchant_" + lightCleanup(item),
                        new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.enchantments.enchantItem", cleanup(item)))));
            }});
            put("enchantment_compatibility", new Object2ObjectOpenHashMap<>() {{
                enchantmentRegistry.keySet().forEach(enchantment -> put("compatible_with_" + lightCleanup(enchantment),
                        new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.enchantments.enchantmentCompatibility", cleanup(enchantment)))));
            }});
        }});
        colData.put("commands", new Object2ObjectOpenHashMap<>() {{
            put("other", new Object2ObjectOpenHashMap<>() {{
                put("enabled", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.commands.enabled")));
            }});
        }});
        colData.put("advancements", new Object2ObjectOpenHashMap<>() {{
            put("other", new Object2ObjectOpenHashMap<>() {{
                put("enabled", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.advancements.enabled")));
                put("xp", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.advancements.xp")));
            }});
        }});
        colData.put("mob_categories", new Object2ObjectOpenHashMap<>() {{
            put("other", new Object2ObjectOpenHashMap<>() {{
                put("mobcap", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.mobCategory.mobcap")));
            }});
        }});
        colData.put("biomes", new Object2ObjectOpenHashMap<>() {{
            put("other", new Object2ObjectOpenHashMap<>() {{
                put("enabled", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.biomes.enabled")));
            }});
        }});
        colData.put("structures", new Object2ObjectOpenHashMap<>() {{
            put("other", new Object2ObjectOpenHashMap<>() {{
                put("enabled", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.structures.enabled")));
            }});
        }});
        colData.put("placed_features", new Object2ObjectOpenHashMap<>() {{
            put("other", new Object2ObjectOpenHashMap<>() {{
                put("enabled", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.placedFeatures.enabled")));
            }});
        }});
        colData.put("misc", new Object2ObjectOpenHashMap<>() {{
            put("other", new Object2ObjectOpenHashMap<>() {{
                put("enabled", new ObjectObjectImmutablePair<>(BOOLEAN, Component.translatable("vd.commandRule.misc.enabled")));
                put("raid_waves", new ObjectObjectImmutablePair<>(INTEGER, Component.translatable("vd.commandRule.misc.raidWaves")));
            }});
        }});
    }

    /**
     * Populates the data maps with the default value for each row per column.
     */
    @SuppressWarnings("ConstantConditions")
    private static void populateRows() {
        ObjectList<EntityType<?>> undeadMobs = ObjectList.of(EntityType.SKELETON_HORSE, EntityType.ZOMBIE_HORSE, EntityType.SKELETON, EntityType.PHANTOM, EntityType.WITHER, EntityType.ZOGLIN, EntityType.ZOMBIE);
        rowData.put("entities", new Object2ObjectOpenHashMap<>() {{
            entityTypeRegistry.forEach((entityType) ->
                    put(Objects.requireNonNull(entityTypeRegistry.getKey(entityType)).toString(), new Object2ObjectOpenHashMap<>() {{
                        Entity entity = null;
                        if (!entityType.equals(EntityType.ENDER_DRAGON)) {
                            entity = entityType.create(server.overworld(), EntitySpawnReason.COMMAND);
                        }

                        put("can_player_interact", "true");

                        if (entityType.equals(EntityType.PLAYER)) {
                            put("can_be_on_fire", "true");
                            put("can_sprint", "true");
                            put("can_crouch", "true");
                            put("can_swim", "true");
                            put("can_jump", "true");
                            put("can_be_invisible", "true");
                            put("flying_speed", "0.05");
                            put("beta_hunger", "false");

                            statTypeRegistry.forEach(statType -> {
                                if (statType.equals(Stats.CUSTOM)) return;
                                put(lightCleanup(Objects.requireNonNull(statTypeRegistry.getKey(statType))) + "_stat_type", "true");
                            });
                            customStatRegistry.keySet().forEach(customStat -> put(lightCleanup(customStat) + "_custom_stat", "true"));

                            damageTypeRegistry.keySet().forEach(damageType -> {
                                put(lightCleanup(damageType) + "_damage", "true");
                                put(lightCleanup(damageType) + "_death", "true");
                            });

                            put("explosion_knockback", "true");
                        }

                        if (entity instanceof LivingEntity || entityType.equals(EntityType.PLAYER) || entityType.equals(EntityType.ENDER_DRAGON)) {
                            entityTypeRegistry.keySet().forEach(entityType ->
                                    put(lightCleanup(entityType) + "_knockback", "true"));

                            mobEffectRegistry.forEach(mobEffect ->
                                    put(lightCleanup(Objects.requireNonNull(mobEffectRegistry.getKey(mobEffect))) + "_effect", String.valueOf(
                                            !((mobEffect.equals(MobEffects.WITHER.value()) &&
                                                    (entityType.equals(EntityType.WITHER) || entityType.equals(EntityType.WITHER_SKELETON))) ||
                                                    (mobEffect.equals(MobEffects.POISON.value()) && entityType.equals(EntityType.SPIDER)) ||
                                                    ((mobEffect.equals(MobEffects.REGENERATION.value()) || mobEffect.equals(MobEffects.POISON.value())) &&
                                                            undeadMobs.contains(entityType)))
                                    )));

                            put("can_despawn", String.valueOf(!entityType.getCategory().isPersistent()));
                            put("can_spawn", "true");
                            put("spawn_egg", "true");
                            put("spawner", "true");
                            put("can_drop_xp", "true");
                            put("ai", "true");
                        }

                        if (entityType.equals(EntityType.EXPERIENCE_BOTTLE)) {
                            put("can_drop_xp", "true");
                        }

                        if (!entityType.getCategory().equals(MobCategory.MISC)) {
                            put("min_despawn_distance", String.valueOf(entityType.getCategory().getNoDespawnDistance()));
                            put("instant_despawn_distance", String.valueOf(entityType.getCategory().getDespawnDistance()));
                        }

                        if (entityType.equals(EntityType.PAINTING)) {
                            paintingVariantRegistry.keySet().forEach(painting ->
                                    put(lightCleanup(painting) + "_painting", "true"));
                        }

                        if (entityType.equals(EntityType.VILLAGER)) {
                            villagerProfessionRegistry.keySet().forEach(profession ->
                                    put(lightCleanup(profession) + "_profession", "true"));
                            villagerTypeRegistry.keySet().forEach(type -> put(lightCleanup(type) + "_type", "true"));
                            put("can_breed", "true");
                            itemRegistry.forEach(item -> {
                                boolean villagerWants = item.getDefaultInstance().is(ItemTags.VILLAGER_PICKS_UP);
                                put("can_breed_with_" +
                                                lightCleanup(Objects.requireNonNull(itemRegistry.getKey(item)).toString()),
                                        String.valueOf(villagerWants));
                            });
                        }

                        if (VanillaHusbandryAdvancements.BREEDABLE_ANIMALS.contains(entityType) ||
                                VanillaHusbandryAdvancements.INDIRECTLY_BREEDABLE_ANIMALS.contains(entityType)) {
                            put("can_breed", "true");
                            if (entity instanceof Animal animal) {
                                itemRegistry.stream().forEach(item ->
                                        put("can_breed_with_" +
                                                        lightCleanup(Objects.requireNonNull(itemRegistry.getKey(item)).toString()),
                                                String.valueOf(animal.isFood(item.getDefaultInstance()))));
                            }
                            put("breeding_can_drop_xp", "true");
                        }

                        if (entityType.equals(EntityType.ZOMBIE_VILLAGER) || entityType.equals(EntityType.WITCH) ||
                                entityType.equals(EntityType.ZOMBIFIED_PIGLIN) || entityType.equals(EntityType.ZOGLIN) ||
                                entityType.equals(EntityType.DROWNED) || entityType.equals(EntityType.STRAY) ||
                                entityType.equals(EntityType.VILLAGER)) {
                            put("can_be_converted_to", "true");
                        }

                        if (entityType.equals(EntityType.SKELETON) || entityType.equals(EntityType.ZOMBIE) ||
                                entityType.equals(EntityType.PHANTOM) || entityType.equals(EntityType.STRAY) ||
                                entityType.equals(EntityType.DROWNED) || entityType.equals(EntityType.ZOMBIE_VILLAGER)) {
                            put("burns_in_sunlight", "true");
                        }

                        if (entityType.equals(EntityType.VILLAGER) || entityType.equals(EntityType.WANDERING_TRADER) ||
                                entityType.equals(EntityType.PIGLIN)) {
                            put("can_trade", "true");
                        }

                        if (entityType.equals(EntityType.VILLAGER) || entityType.equals(EntityType.WANDERING_TRADER)) {
                            put("can_infinitely_trade", "false");
                            put("daily_restocks", "2");
                            put("trading_can_drop_xp", "true");
                        }

                        if (entityType.equals(EntityType.ITEM)) {
                            put("despawn_time", "6000");
                        }

                        if (entityType.equals(EntityType.CREEPER)) {
                            put("can_be_lit_by_fire_aspect", "false");
                        }

                        if (entityType.equals(EntityType.ENDER_PEARL)) {
                            put("despawn_on_player_death", "true");
                        }

                        put("can_exist", "true");

                        if (entity instanceof AbstractBoat) {
                            put("alpha_behaviour", "false");
                        }

                        if (entityType.equals(EntityType.ENDER_DRAGON)) {
                            put("can_shoot_fireballs", "true");
                        }

                        if (entityType.equals(EntityType.IRON_GOLEM) || entityType.equals(EntityType.CAT)) {
                            put("spawned_by_villagers", "true");
                        }

                        put("can_be_summoned", "true");
                    }}));
        }});

        rowData.put("blocks", new Object2ObjectOpenHashMap<>() {{
            blockRegistry.forEach((block) ->
                    put(Objects.requireNonNull(blockRegistry.getKey(block)).toString(), new Object2ObjectOpenHashMap<>() {{
                        String name = block.toString();
                        BlockState blockState = block.defaultBlockState();

                        put("can_place_in_overworld", "true");
                        put("can_place_in_nether", String.valueOf(!block.equals(Blocks.WATER)));
                        put("can_place_in_end", "true");
                        put("can_break", "true");
                        put("can_interact", "true");
                        put("works", "true");

                        if (block.equals(Blocks.ICE) || block.equals(Blocks.PACKED_ICE) ||
                                block.equals(Blocks.BLUE_ICE)) {
                            put("friction_factor", "0.98");
                        } else if (block.equals(Blocks.SLIME_BLOCK)) {
                            put("friction_factor", "0.8");
                        } else {
                            put("friction_factor", "0.6");
                        }

                        boolean isHoneyBlock = block.equals(Blocks.HONEY_BLOCK);
                        if (block.equals(Blocks.SOUL_SAND) || isHoneyBlock) {
                            put("speed_factor", "0.4");
                        } else {
                            put("speed_factor", "1.0");
                        }

                        if (isHoneyBlock) {
                            put("jump_factor", "0.5");
                        } else {
                            put("jump_factor", "1.0");
                        }

                        if (block.equals(Blocks.CAULDRON)) {
                            put("can_be_filled_by_dripstone", "true");
                            put("can_be_filled_by_precipitation", "true");
                        }

                        if (block.equals(Blocks.WATER)) {
                            put("fluid_reaches_far", "true");
                            put("fluid_reaches_far_in_nether", "true");
                            put("fluid_speed", "5");
                            put("fluid_speed_in_nether", "5");
                        }

                        if (block.equals(Blocks.LAVA)) {
                            put("fluid_reaches_far", "false");
                            put("fluid_reaches_far_in_nether", "true");
                            put("fluid_speed", "30");
                            put("fluid_speed_in_nether", "10");
                        }

                        boolean isObserver = block.equals(Blocks.OBSERVER);
                        if (block.equals(Blocks.REPEATER) || block.equals(Blocks.COMPARATOR) || isObserver) {
                            put("redstone_delay", "2");
                        }

                        if (isObserver) {
                            put("redstone_duration", "2");
                        }

                        if (name.contains("button")) {
                            if (name.contains("stone")) {
                                put("redstone_duration", "20");
                            } else {
                                put("redstone_duration", "30");
                            }
                        }

                        if (name.contains("ore") || block.equals(Blocks.SPAWNER) ||
                                (name.contains("sculk") && !block.equals(Blocks.SCULK_VEIN)) ||
                                block.equals(Blocks.GRINDSTONE) || block instanceof AbstractFurnaceBlock) {
                            put("can_drop_xp", "true");
                        }

                        if (block instanceof FallingBlock || block.equals(Blocks.POINTED_DRIPSTONE)) {
                            put("can_fall", "true");
                        }

                        if (block.equals(Blocks.FARMLAND)) {
                            put("can_be_trampled", "true");
                        }

                        if (block.equals(Blocks.TNT)) {
                            put("alpha_behaviour", "false");
                        }

                        if (name.contains("chest") || name.contains("shulker_box")) {
                            put("opening_blockable", "true");
                        }

                        if (block.equals(Blocks.NETHER_PORTAL)) {
                            put("cooldown", "300");
                        }

                        if (!blockEntityRegistry.containsKey(Objects.requireNonNull(blockRegistry.getKey(block)))) {
                            if (block.equals(Blocks.OBSIDIAN) || block.equals(Blocks.CRYING_OBSIDIAN) ||
                                    block.equals(Blocks.REINFORCED_DEEPSLATE) || block.equals(Blocks.RESPAWN_ANCHOR)) {
                                put("push_behaviour", "'" + PushReaction.BLOCK + "'");
                            } else {
                                put("push_behaviour", "'" + blockState.getPistonPushReaction() + "'");
                            }
                        }

                        put("ignited_by_lava", String.valueOf(blockState.ignitedByLava()));
                        put("destroy_speed", String.valueOf(blockState.getDestroySpeed(null, null)));
                        put("requires_correct_tool_for_drops", String.valueOf(blockState.requiresCorrectToolForDrops()));
                        put("burn_odds", String.valueOf(((FireBlock) Blocks.FIRE).getBurnOdds(block.defaultBlockState())));
                        put("ignite_odds", String.valueOf(((FireBlock) Blocks.FIRE).getIgniteOdds(block.defaultBlockState())));
                        put("can_be_placed_by_command", "true");
                    }}));
        }});
        FuelValues fuelValues = server.overworld().fuelValues();
        rowData.put("items", new Object2ObjectOpenHashMap<>() {{
            itemRegistry.forEach((item) ->
                    put(Objects.requireNonNull(itemRegistry.getKey(item)).toString(), new Object2ObjectOpenHashMap<>() {{
                        ItemStack itemStack = new ItemStack(item);
                        if (!blockRegistry.stream().map(Block::toString).toList().contains(item.toString())) {
                            String name = item.toString();

                            put("works", "true");

                            put("burns", String.valueOf(!(name.contains("netherite") || name.contains("debris"))));

                            if (item.equals(Items.BOW) || item.equals(Items.CROSSBOW)) {
                                put("can_spam", "false");
                            }

                            FoodProperties foodProperties = itemStack.get(DataComponents.FOOD);
                            if (!Services.PLATFORM.isModLoaded("nostalgic_tweaks")) {
                                if (foodProperties != null) {
                                    put("nutrition", String.valueOf(foodProperties.nutrition()));
                                    put("saturation", String.valueOf(foodProperties.saturation()));
                                }
                                if (item.equals(Items.CAKE)) {
                                    put("nutrition", "2");
                                    put("saturation", "0.1");
                                }
                            }

                            if (item.equals(Items.POTION) || item.equals(Items.SPLASH_POTION) ||
                                    item.equals(Items.LINGERING_POTION) || item.equals(Items.TIPPED_ARROW)) {
                                potionRegistry.keySet().forEach((potion) -> put(lightCleanup(potion) + "_effect", "true"));
                            }

                            if (DispenserBlock.DISPENSER_REGISTRY.containsKey(item)) {
                                put("dispenser_interaction", "true");
                            }

                            if (CauldronInteraction.EMPTY.map().containsKey(item) || CauldronInteraction.WATER.map().containsKey(item) ||
                                    CauldronInteraction.LAVA.map().containsKey(item) || CauldronInteraction.POWDER_SNOW.map().containsKey(item)) {
                                put("cauldron_interaction", "true");
                            }

                            put("fuel_duration", String.valueOf(fuelValues.values.getOrDefault(item, 0)));

                            put("can_break_blocks_in_creative", String.valueOf(!(item instanceof SwordItem)));
                            put("can_be_given_by_command", "true");

                            if (itemStack.getMaxDamage() > 0) {
                                put("durability", String.valueOf(itemStack.getMaxDamage()));
                            }
                        }
                    }}));
        }});
        rowData.put("enchantments", new Object2ObjectOpenHashMap<>() {{
            enchantmentRegistry.forEach((enchantment) ->
                    put(Objects.requireNonNull(enchantmentRegistry.getKey(enchantment)).toString(), new Object2ObjectOpenHashMap<>() {{
                        itemRegistry.forEach(item -> {
                            if (enchantmentRegistry.stream().anyMatch(e -> e.canEnchant(item.getDefaultInstance()))) {
                                put("can_enchant_" + lightCleanup(Objects.requireNonNull(itemRegistry.getKey(item))),
                                        String.valueOf(enchantment.canEnchant(item.getDefaultInstance())));
                            }
                            put("can_enchant_enchanted_book", "true");
                        });

                        enchantmentRegistry.forEach(enchantment1 -> {
                            if (enchantment1.equals(enchantment)) return;
                            put("compatible_with_" + lightCleanup(Objects.requireNonNull(enchantmentRegistry.getKey(enchantment1))),
                                    String.valueOf(Enchantment.areCompatible(new Holder.Direct<>(enchantment), new Holder.Direct<>(enchantment1))));
                        });
                    }}));
        }});
        rowData.put("commands", new Object2ObjectOpenHashMap<>() {{
            new Commands(Commands.CommandSelection.ALL, Commands.createValidationContext(VanillaRegistries.createLookup())).getDispatcher().getRoot().getChildren().forEach(commandNode ->
                    put("/" + commandNode.getName(), new Object2ObjectOpenHashMap<>() {{
                        put("enabled", "true");
                    }}));
        }});
        rowData.put("advancements", new Object2ObjectOpenHashMap<>() {{
            server.getAdvancements().getAllAdvancements().forEach(advancement -> {
                String name = advancement.id().toString();
                if (!name.contains("recipe")) {
                    put(name, new Object2ObjectOpenHashMap<>() {{
                        put("enabled", "true");
                        put("xp", String.valueOf(advancement.value().rewards().experience()));
                    }});
                }
            });
        }});
        rowData.put("mob_categories", new Object2ObjectOpenHashMap<>() {{
            Arrays.stream(MobCategory.values()).forEach(mobCategory ->
                    put(mobCategory.getName(), new Object2ObjectOpenHashMap<>() {{
                        put("mobcap", String.valueOf(mobCategory.getMaxInstancesPerChunk()));
                    }}));
        }});
        rowData.put("biomes", new Object2ObjectOpenHashMap<>() {{
            biomeRegistry.keySet().forEach(biome -> {
                if ("minecraft:plains minecraft:nether minecraft:the_end".contains(biome.toString())) return;
                put(biome.toString(), new Object2ObjectOpenHashMap<>() {{
                    put("enabled", "true");
                }});
            });
        }});
        rowData.put("structures", new Object2ObjectOpenHashMap<>() {{
            structureRegistry.keySet().forEach(structure -> put(structure.toString(), new Object2ObjectOpenHashMap<>() {{
                put("enabled", "true");
            }}));
        }});
        rowData.put("placed_features", new Object2ObjectOpenHashMap<>() {{
            placedFeatureRegistry.keySet().forEach(placedFeature -> put(placedFeature.toString(), new Object2ObjectOpenHashMap<>() {{
                put("enabled", "true");
            }}));
            put("minecraft_unofficial:end_spike_cage", new Object2ObjectOpenHashMap<>() {{
                put("enabled", "true");
            }});
        }});
        rowData.put("misc", new Object2ObjectOpenHashMap<>() {{
            put("raid_waves_easy", new Object2ObjectOpenHashMap<>() {{
                put("raid_waves", "4");
            }});
            put("raid_waves_normal", new Object2ObjectOpenHashMap<>() {{
                put("raid_waves", "6");
            }});
            put("raid_waves_hard", new Object2ObjectOpenHashMap<>() {{
                put("raid_waves", "8");
            }});
            put("recipe_book", new Object2ObjectOpenHashMap<>() {{
                put("enabled", "true");
            }});
        }});
    }

    public static void populate() {
        populateCols();
        populateRows();
        numRowMaximums.put("nutrition", 20);
        numRowMaximums.put("saturation", 9.9);
        stringColSuggestions.put("push_behaviour", Arrays.stream(PushReaction.values()).map(Enum::name).toList());
        colData.forEach((col, colInfo) -> colInfo.forEach((group, groupInfo) -> {
            if (!groupInfo.values().stream().allMatch(pair -> pair.left().equals(groupInfo.values().iterator().next().left()))) {
                differentDataTypes.add(col);
            }
        }));
        singularMap.put("entities", "entity");
        singularMap.put("blocks", "block");
        singularMap.put("items", "item");
        singularMap.put("enchantments", "enchantment");
        singularMap.put("commands", "command");
        singularMap.put("advancements", "advancement");
        singularMap.put("mob_categories", "mob_category");
        singularMap.put("biomes", "biome");
        singularMap.put("structures", "structure");
        singularMap.put("placed_features", "placed_feature");
        singularMap.put("misc", "misc");
        populationDone = true;
    }
}
