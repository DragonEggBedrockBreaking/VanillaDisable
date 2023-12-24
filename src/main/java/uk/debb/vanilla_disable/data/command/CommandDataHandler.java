package uk.debb.vanilla_disable.data.command;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.commands.Commands;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.packs.VanillaHusbandryAdvancements;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.LevelResource;
import org.apache.commons.io.FileUtils;
import uk.debb.vanilla_disable.config.global.VanillaDisableConfig;
import uk.debb.vanilla_disable.data.gamerule.GameruleMigrationDataHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static uk.debb.vanilla_disable.data.command.DataType.*;

public class CommandDataHandler {
    public static final Object2ObjectMap<String, Object2ObjectMap<String, DataType>> cols = new Object2ObjectOpenHashMap<>();

    public static final Object2ObjectMap<String, Object2ObjectMap<String, String>> entities = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, String>> blocks = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, String>> items = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, String>> enchantments = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, String>> commands = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, String>> advancements = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, String>> mobCategories = new Object2ObjectOpenHashMap<>();

    public static final Object2ObjectMap<String, Object2ObjectMap<String, Component>> entityData = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, Component>> blockData = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, Component>> itemData = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, Component>> enchantmentData = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, Component>> commandData = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, Component>> advancementData = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, Component>> mobCategoryData = new Object2ObjectOpenHashMap<>();

    public static final Object2IntMap<String> intRowMaximums = new Object2IntArrayMap<>();
    public static final Object2DoubleMap<String> doubleRowMaximums = new Object2DoubleArrayMap<>();
    public static final Object2ObjectMap<String, List<String>> stringColSuggestions = new Object2ObjectOpenHashMap<>();
    public static final ObjectList<String> differentDataTypes = new ObjectArrayList<>();
    public static MinecraftServer server;
    public static boolean populationDone = false;
    public static RegistryAccess registryAccess;
    public static Registry<PaintingVariant> paintingVariantRegistry;
    public static Registry<VillagerProfession> villagerProfessionRegistry;
    public static Registry<VillagerType> villagerTypeRegistry;
    public static Registry<Enchantment> enchantmentRegistry;
    public static Registry<Potion> potionRegistry;
    public static Registry<DamageType> damageTypeRegistry;
    public static Registry<MobEffect> mobEffectRegistry;
    public static Registry<StatType<?>> statTypeRegistry;
    public static boolean migrated = false;
    public static boolean shouldMigrate = true;
    private static Cache<String, Object> cache = null;
    private static Connection connection;
    private static Statement statement;
    private static String PATH;
    private static ObjectList<EntityType<?>> undeadMobs;
    private static Registry<Block> blockRegistry;
    private static Registry<Item> itemRegistry;
    private static Registry<EntityType<?>> entityTypeRegistry;
    private static Registry<BlockEntityType<?>> blockEntityRegistry;
    private static Registry<ResourceLocation> customStatRegistry;

    /**
     * Cleans up data for display (removes underscores, 'namespace:' prefixes, 'group/' prefixes)
     *
     * @param o The object to be cleaned up
     * @return The cleaned up object as a string
     */
    private static String cleanup(Object o) {
        String s = o.toString().replace("_", " ");
        if (s.contains(":")) {
            s = s.split(":")[1];
        }
        if (s.contains("/")) {
            s = s.split("/")[1];
        }
        return s;
    }

    /**
     * Cleans up data for display (removes 'namespace:' prefixes)
     *
     * @param o The object to be cleaned up
     * @return The cleaned up object as a string
     */
    public static String lightCleanup(Object o) {
        String s = o.toString();
        if (s.contains(":")) {
            s = s.split(":")[1];
        }
        return s;
    }

    /**
     * Populates the data for all registries.
     */
    public static void populateRegistries() {
        registryAccess = server.registryAccess();
        blockRegistry = registryAccess.registryOrThrow(Registries.BLOCK);
        itemRegistry = registryAccess.registryOrThrow(Registries.ITEM);
        entityTypeRegistry = registryAccess.registryOrThrow(Registries.ENTITY_TYPE);
        paintingVariantRegistry = registryAccess.registryOrThrow(Registries.PAINTING_VARIANT);
        villagerProfessionRegistry = registryAccess.registryOrThrow(Registries.VILLAGER_PROFESSION);
        villagerTypeRegistry = registryAccess.registryOrThrow(Registries.VILLAGER_TYPE);
        blockEntityRegistry = registryAccess.registryOrThrow(Registries.BLOCK_ENTITY_TYPE);
        enchantmentRegistry = registryAccess.registryOrThrow(Registries.ENCHANTMENT);
        potionRegistry = registryAccess.registryOrThrow(Registries.POTION);
        damageTypeRegistry = registryAccess.registryOrThrow(Registries.DAMAGE_TYPE);
        mobEffectRegistry = registryAccess.registryOrThrow(Registries.MOB_EFFECT);
        statTypeRegistry = registryAccess.registryOrThrow(Registries.STAT_TYPE);
        customStatRegistry = registryAccess.registryOrThrow(Registries.CUSTOM_STAT);
    }

    /**
     * Populates the data maps with the data dynamically pulled from registries, and custom data.
     * This includes names of columns corresponding to data type, default value for each row, and descriptions.
     */
    public static void populate() {
        undeadMobs = ObjectList.of(EntityType.SKELETON_HORSE, EntityType.ZOMBIE_HORSE, EntityType.SKELETON, EntityType.PHANTOM, EntityType.WITHER, EntityType.ZOGLIN, EntityType.ZOMBIE);

        cols.put("entities", new Object2ObjectOpenHashMap<>() {{
            put("can_be_on_fire", BOOLEAN);
            put("can_sprint", BOOLEAN);
            put("can_crouch", BOOLEAN);
            put("can_swim", BOOLEAN);
            put("can_jump", BOOLEAN);
            put("can_be_invisible", BOOLEAN);
            put("flying_speed", REAL);
            put("beta_hunger", BOOLEAN);

            statTypeRegistry.forEach(statType -> {
                if (statType.equals(Stats.CUSTOM)) return;
                put(lightCleanup(Objects.requireNonNull(statTypeRegistry.getKey(statType))) + "_stat_type", BOOLEAN);
            });
            customStatRegistry.keySet().forEach(customStat -> put(lightCleanup(customStat) + "_custom_stat", BOOLEAN));

            damageTypeRegistry.keySet().forEach(damageType -> {
                put(lightCleanup(damageType) + "_damage", BOOLEAN);
                put(lightCleanup(damageType) + "_death", BOOLEAN);
            });

            entityTypeRegistry.keySet().forEach(entityType -> put(lightCleanup(entityType) + "_knockback", BOOLEAN));
            put("explosion_knockback", BOOLEAN);

            mobEffectRegistry.keySet().forEach(mobEffect -> put(lightCleanup(mobEffect) + "_effect", BOOLEAN));
            paintingVariantRegistry.keySet().forEach(painting -> put(lightCleanup(painting) + "_painting", BOOLEAN));
            villagerProfessionRegistry.keySet().forEach(profession -> put(lightCleanup(profession) + "_profession", BOOLEAN));
            villagerTypeRegistry.keySet().forEach(type -> put(lightCleanup(type) + "_type", BOOLEAN));

            put("can_despawn", BOOLEAN);
            put("despawn_time", INTEGER);
            put("can_spawn", BOOLEAN);
            put("spawn_egg", BOOLEAN);
            put("spawner", BOOLEAN);
            put("can_breed", BOOLEAN);
            put("can_exist", BOOLEAN);
            put("can_be_converted_to", BOOLEAN);
            put("burns_in_sunlight", BOOLEAN);
            put("spawned_by_villagers", BOOLEAN);
            put("can_drop_xp", BOOLEAN);
            put("ai", BOOLEAN);
            put("can_trade", BOOLEAN);
            put("can_infinitely_trade", BOOLEAN);
            put("daily_restocks", INTEGER);
            put("can_player_interact", BOOLEAN);
            put("can_be_lit_by_fire_aspect", BOOLEAN);
            put("despawn_on_player_death", BOOLEAN);
            put("min_despawn_distance", INTEGER);
            put("instant_despawn_distance", INTEGER);

            itemRegistry.keySet().forEach(item ->
                    put("can_breed_with_" + lightCleanup(item), BOOLEAN));

            put("alpha_behaviour", BOOLEAN);
            put("can_shoot_fireballs", BOOLEAN);
            put("can_be_summoned", BOOLEAN);
        }});
        cols.put("blocks", new Object2ObjectOpenHashMap<>() {{
            put("can_place_in_overworld", BOOLEAN);
            put("can_place_in_nether", BOOLEAN);
            put("can_place_in_end", BOOLEAN);
            put("can_break", BOOLEAN);
            put("can_interact", BOOLEAN);
            put("works", BOOLEAN);
            put("friction_factor", REAL);
            put("speed_factor", REAL);
            put("jump_factor", REAL);
            put("can_be_filled_by_dripstone", BOOLEAN);
            put("can_be_filled_by_precipitation", BOOLEAN);
            put("fluid_reaches_far", BOOLEAN);
            put("fluid_reaches_far_in_nether", BOOLEAN);
            put("fluid_speed", INTEGER);
            put("fluid_speed_in_nether", INTEGER);
            put("redstone_delay", INTEGER);
            put("redstone_duration", INTEGER);
            put("can_drop_xp", BOOLEAN);
            put("can_fall", BOOLEAN);
            put("can_be_trampled", BOOLEAN);
            put("alpha_behaviour", BOOLEAN);
            put("opening_blockable", BOOLEAN);
            put("cooldown", INTEGER);
            put("push_behaviour", CLOB);
            put("ignited_by_lava", BOOLEAN);
            put("destroy_speed", REAL);
            put("requires_correct_tool_for_drops", BOOLEAN);
            put("burn_odds", INTEGER);
            put("ignite_odds", INTEGER);
            put("can_be_placed_by_command", BOOLEAN);
        }});
        cols.put("items", new Object2ObjectOpenHashMap<>() {{
            put("works", BOOLEAN);
            put("durability", INTEGER);
            put("burns", BOOLEAN);
            put("can_spam", BOOLEAN);
            put("nutrition", INTEGER);
            put("saturation", REAL);
            put("can_break_blocks_in_creative", BOOLEAN);

            potionRegistry.keySet().forEach(potion ->
                    put(lightCleanup(potion) + "_effect", BOOLEAN));

            put("dispenser_interaction", BOOLEAN);
            put("cauldron_interaction", BOOLEAN);
            put("fuel_duration", INTEGER);
            put("can_be_given_by_command", BOOLEAN);
        }});
        cols.put("enchantments", new Object2ObjectOpenHashMap<>() {{
            itemRegistry.keySet().forEach(item ->
                    put("can_enchant_" + lightCleanup(item), BOOLEAN));

            enchantmentRegistry.keySet().forEach(enchantment ->
                    put("compatible_with_" + lightCleanup(enchantment), BOOLEAN));
        }});
        cols.put("commands", new Object2ObjectOpenHashMap<>() {{
            put("enabled", BOOLEAN);
        }});
        cols.put("advancements", new Object2ObjectOpenHashMap<>() {{
            put("enabled", BOOLEAN);
        }});
        cols.put("mob_categories", new Object2ObjectOpenHashMap<>() {{
            put("mobcap", INTEGER);
        }});

        entityTypeRegistry.forEach((entityType) ->
                entities.put(Objects.requireNonNull(entityTypeRegistry.getKey(entityType)).toString(), new Object2ObjectOpenHashMap<>() {{
                    Entity entity = entityType.create(server.overworld());

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

                    if (entity instanceof LivingEntity || entityType.equals(EntityType.PLAYER)) {
                        entityTypeRegistry.keySet().forEach(entityType ->
                                put(lightCleanup(entityType) + "_knockback", "true"));

                        mobEffectRegistry.forEach(mobEffect ->
                                put(lightCleanup(Objects.requireNonNull(mobEffectRegistry.getKey(mobEffect))) + "_effect", String.valueOf(
                                        !((mobEffect.equals(MobEffects.WITHER) &&
                                                (entityType.equals(EntityType.WITHER) || entityType.equals(EntityType.WITHER_SKELETON))) ||
                                                (mobEffect.equals(MobEffects.POISON) && entityType.equals(EntityType.SPIDER)) ||
                                                ((mobEffect.equals(MobEffects.REGENERATION) || mobEffect.equals(MobEffects.POISON)) &&
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
                            boolean villagerWants = Villager.WANTED_ITEMS.contains(item);
                            put("can_breed_with_" +
                                            lightCleanup(Objects.requireNonNull(itemRegistry.getKey(item)).toString()),
                                    String.valueOf(villagerWants));
                        });
                    }

                    if (VanillaHusbandryAdvancements.BREEDABLE_ANIMALS.contains(entityType) ||
                            VanillaHusbandryAdvancements.INDIRECTLY_BREEDABLE_ANIMALS.contains(entityType)) {
                        put("can_breed", "true");
                        if (entity != null) {
                            Class<?> animalClass = entity.getClass();
                            Method isFood;
                            try {
                                isFood = animalClass.getMethod("method_6481", ItemStack.class);
                            } catch (NoSuchMethodException ignored) {
                                try {
                                    isFood = animalClass.getMethod("isFood", ItemStack.class);
                                } catch (NoSuchMethodException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Method finalIsFood = isFood;
                            itemRegistry.stream().forEach(item -> {
                                try {
                                    boolean itemIsFood = (boolean) finalIsFood.invoke(entity, new ItemStack(item));
                                    put("can_breed_with_" +
                                                    lightCleanup(Objects.requireNonNull(itemRegistry.getKey(item)).toString()),
                                            String.valueOf(itemIsFood));
                                } catch (InvocationTargetException | IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }
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

                    if (entityType.equals(EntityType.BOAT)) {
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

        blockRegistry.forEach((block) ->
                blocks.put(Objects.requireNonNull(blockRegistry.getKey(block)).toString(), new Object2ObjectOpenHashMap<>() {{
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

                    if (!blockEntityRegistry.containsKey(blockRegistry.getKey(block))) {
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

        itemRegistry.forEach((item) ->
                items.put(Objects.requireNonNull(itemRegistry.getKey(item)).toString(), new Object2ObjectOpenHashMap<>() {{
                    if (!blockRegistry.stream().map(Block::toString).toList().contains(item.toString())) {
                        String name = item.toString();

                        put("works", "true");

                        put("burns", String.valueOf(!(name.contains("netherite") || name.contains("debris"))));

                        if (item.equals(Items.BOW) || item.equals(Items.CROSSBOW)) {
                            put("can_spam", "false");
                        }

                        FoodProperties foodProperties = item.getFoodProperties();
                        if (foodProperties != null) {
                            put("nutrition", String.valueOf(foodProperties.getNutrition()));
                            put("saturation", String.valueOf(foodProperties.getSaturationModifier()));
                        }

                        if (item.equals(Items.POTION) || item.equals(Items.SPLASH_POTION) ||
                                item.equals(Items.LINGERING_POTION) || item.equals(Items.TIPPED_ARROW)) {
                            potionRegistry.keySet().forEach((potion) -> put(lightCleanup(potion) + "_effect", "true"));
                        }

                        if (DispenserBlock.DISPENSER_REGISTRY.containsKey(item)) {
                            put("dispenser_interaction", "true");
                        }

                        if (CauldronInteraction.EMPTY.map.containsKey(item) || CauldronInteraction.WATER.map.containsKey(item) ||
                                CauldronInteraction.LAVA.map.containsKey(item) || CauldronInteraction.POWDER_SNOW.map.containsKey(item)) {
                            put("cauldron_interaction", "true");
                        }

                        Map<Item, Integer> fuels = AbstractFurnaceBlockEntity.getFuel();
                        if (fuels.containsKey(item)) {
                            put("fuel_duration", String.valueOf(fuels.get(item)));
                        }

                        put("can_break_blocks_in_creative", String.valueOf(!(item instanceof SwordItem)));
                        put("can_be_given_by_command", "true");

                        if (item.canBeDepleted()) {
                            put("durability", String.valueOf(item.getMaxDamage()));
                        }
                    }
                }}));

        enchantmentRegistry.forEach((enchantment) ->
                enchantments.put(Objects.requireNonNull(enchantmentRegistry.getKey(enchantment)).toString(), new Object2ObjectOpenHashMap<>() {{
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
                                String.valueOf(enchantment.isCompatibleWith(enchantment1) && enchantment1.isCompatibleWith(enchantment)));
                    });
                }}));

        new Commands(Commands.CommandSelection.ALL, Commands.createValidationContext(VanillaRegistries.createLookup())).getDispatcher().getRoot().getChildren().forEach(commandNode ->
                commands.put("/" + commandNode.getName(), new Object2ObjectOpenHashMap<>() {{
                    put("enabled", "true");
                }}));

        server.getAdvancements().getAllAdvancements().forEach(advancement -> {
            String name = advancement.id().toString();
            if (!name.contains("recipe")) {
                advancements.put(name, new Object2ObjectOpenHashMap<>() {{
                    put("enabled", "true");
                }});
            }
        });
        Arrays.stream(MobCategory.values()).forEach(mobCategory ->
                mobCategories.put(mobCategory.getName(), new Object2ObjectOpenHashMap<>() {{
                    put("mobcap", String.valueOf(mobCategory.getMaxInstancesPerChunk()));
                }}));

        entityData.put("stats", new Object2ObjectOpenHashMap<>() {{
            statTypeRegistry.forEach(statType -> {
                if (statType.equals(Stats.CUSTOM)) return;
                put(lightCleanup(Objects.requireNonNull(statTypeRegistry.getKey(statType))) + "_stat_type",
                        Component.translatable("vd.commandRule.entities.statTypes", cleanup(statType)));
            });
            customStatRegistry.keySet().forEach(customStat ->
                    put(lightCleanup(customStat) + "_custom_stat", Component.translatable("vd.commandRule.entities.customStats", cleanup(customStat))));
        }});
        entityData.put("damage", new Object2ObjectOpenHashMap<>() {{
            damageTypeRegistry.keySet().forEach(damageType ->
                    put(lightCleanup(damageType) + "_damage", Component.translatable("vd.commandRule.entities.damageTypes", cleanup(damageType))));
        }});
        entityData.put("knockback", new Object2ObjectOpenHashMap<>() {{
            entityTypeRegistry.keySet().forEach(entityType ->
                    put(lightCleanup(entityType) + "_knockback", Component.translatable("vd.commandRule.entities.knockbackTypes", cleanup(entityType))));
            put("explosion_knockback", Component.translatable("vd.commandRule.entities.explosionKnockback"));
        }});
        entityData.put("effects", new Object2ObjectOpenHashMap<>() {{
            mobEffectRegistry.keySet().forEach(mobEffect ->
                    put(lightCleanup(mobEffect) + "_effect", Component.translatable("vd.commandRule.entities.mobEffect", cleanup(mobEffect))));
        }});
        entityData.put("death", new Object2ObjectOpenHashMap<>() {{
            damageTypeRegistry.keySet().forEach(damageType ->
                    put(lightCleanup(damageType) + "_death", Component.translatable("vd.commandRule.entities.deathTypes", cleanup(damageType))));
        }});
        entityData.put("painting", new Object2ObjectOpenHashMap<>() {{
            paintingVariantRegistry.keySet().forEach(painting ->
                    put(lightCleanup(painting) + "_painting", Component.translatable("vd.commandRule.entities.paintingVariants", cleanup(painting))));
        }});
        entityData.put("biome_type", new Object2ObjectOpenHashMap<>() {{
            villagerTypeRegistry.keySet().forEach(villagerType ->
                    put(lightCleanup(villagerType) + "_type", Component.translatable("vd.commandRule.entities.villagerTypes", cleanup(villagerType))));
        }});
        entityData.put("profession", new Object2ObjectOpenHashMap<>() {{
            villagerProfessionRegistry.keySet().forEach(villagerProfession ->
                    put(lightCleanup(villagerProfession) + "_profession", Component.translatable("vd.commandRule.entities.villagerProfessions", cleanup(villagerProfession))));
        }});
        entityData.put("player", new Object2ObjectOpenHashMap<>() {{
            put("can_be_on_fire", Component.translatable("vd.commandRule.entities.canBeOnFire"));
            put("can_sprint", Component.translatable("vd.commandRule.entities.canSprint"));
            put("can_crouch", Component.translatable("vd.commandRule.entities.canCrouch"));
            put("can_swim", Component.translatable("vd.commandRule.entities.canSwim"));
            put("can_jump", Component.translatable("vd.commandRule.entities.canJump"));
            put("can_be_invisible", Component.translatable("vd.commandRule.entities.canBeInvisible"));
            put("flying_speed", Component.translatable("vd.commandRule.entities.flyingSpeed"));
            put("beta_hunger", Component.translatable("vd.commandRule.entities.betaHunger"));
        }});
        entityData.put("spawning", new Object2ObjectOpenHashMap<>() {{
            put("can_despawn", Component.translatable("vd.commandRule.entities.canDespawn"));
            put("despawn_time", Component.translatable("vd.commandRule.entities.despawnTime"));
            put("can_spawn", Component.translatable("vd.commandRule.entities.canSpawn"));
            put("spawn_egg", Component.translatable("vd.commandRule.entities.spawnEgg"));
            put("spawner", Component.translatable("vd.commandRule.entities.spawner"));
            put("despawn_on_player_death", Component.translatable("vd.commandRule.entities.despawnOnPlayerDeath"));
            put("can_breed", Component.translatable("vd.commandRule.entities.canBreed"));
            put("spawned_by_villagers", Component.translatable("vd.commandRule.entities.spawnedByVillagers"));
            put("min_despawn_distance", Component.translatable("vd.commandRule.entities.minDespawnDistance"));
            put("instant_despawn_distance", Component.translatable("vd.commandRule.entities.instantDespawnDistance"));
            put("can_be_summoned", Component.translatable("vd.commandRule.entities.canBeSummoned"));
        }});
        entityData.put("breeding_ingredient", new Object2ObjectOpenHashMap<>() {{
            itemRegistry.keySet().forEach(item ->
                    put("can_breed_with_" + lightCleanup(item), Component.translatable("vd.commandRule.entities.breedingIngredient", cleanup(item))));
        }});
        entityData.put("other", new Object2ObjectOpenHashMap<>() {{
            put("can_exist", Component.translatable("vd.commandRule.entities.canExist"));
            put("can_be_converted_to", Component.translatable("vd.commandRule.entities.canBeConvertedTo"));
            put("burns_in_sunlight", Component.translatable("vd.commandRule.entities.burnsInSunlight"));
            put("can_drop_xp", Component.translatable("vd.commandRule.entities.canDropXp"));
            put("ai", Component.translatable("vd.commandRule.entities.ai"));
            put("can_trade", Component.translatable("vd.commandRule.entities.canTrade"));
            put("can_infinitely_trade", Component.translatable("vd.commandRule.entities.canInfinitelyTrade"));
            put("daily_restocks", Component.translatable("vd.commandRule.entities.dailyRestocks"));
            put("can_player_interact", Component.translatable("vd.commandRule.entities.canPlayerInteract"));
            put("can_be_lit_by_fire_aspect", Component.translatable("vd.commandRule.entities.canBeLitByFireAspect"));
            put("alpha_behaviour", Component.translatable("vd.commandRule.entities.alphaBehaviour"));
            put("can_shoot_fireballs", Component.translatable("vd.commandRule.entities.canShootFireballs"));
        }});

        blockData.put("fluid", new Object2ObjectOpenHashMap<>() {{
            put("fluid_reaches_far", Component.translatable("vd.commandRule.blocks.fluidReachesFar"));
            put("fluid_reaches_far_in_nether", Component.translatable("vd.commandRule.blocks.fluidReachesFarInNether"));
            put("fluid_speed", Component.translatable("vd.commandRule.blocks.fluidSpeed"));
            put("fluid_speed_in_nether", Component.translatable("vd.commandRule.blocks.fluidSpeedInNether"));
        }});
        blockData.put("other", new Object2ObjectOpenHashMap<>() {{
            put("can_place_in_overworld", Component.translatable("vd.commandRule.blocks.canPlaceInOverworld"));
            put("can_place_in_nether", Component.translatable("vd.commandRule.blocks.canPlaceInNether"));
            put("can_place_in_end", Component.translatable("vd.commandRule.blocks.canPlaceInEnd"));
            put("can_break", Component.translatable("vd.commandRule.blocks.canBreak"));
            put("can_interact", Component.translatable("vd.commandRule.blocks.canInteract"));
            put("works", Component.translatable("vd.commandRule.blocks.works"));
            put("friction_factor", Component.translatable("vd.commandRule.blocks.frictionFactor"));
            put("speed_factor", Component.translatable("vd.commandRule.blocks.speedFactor"));
            put("jump_factor", Component.translatable("vd.commandRule.blocks.jumpFactor"));
            put("can_be_filled_by_dripstone", Component.translatable("vd.commandRule.blocks.canBeFilledByDripstone"));
            put("can_be_filled_by_precipitation", Component.translatable("vd.commandRule.blocks.canBeFilledByPrecipitation"));
            put("redstone_delay", Component.translatable("vd.commandRule.blocks.redstoneDelay"));
            put("redstone_duration", Component.translatable("vd.commandRule.blocks.redstoneDuration"));
            put("can_drop_xp", Component.translatable("vd.commandRule.blocks.canDropXp"));
            put("can_fall", Component.translatable("vd.commandRule.blocks.canFall"));
            put("can_be_trampled", Component.translatable("vd.commandRule.blocks.canBeTrampled"));
            put("alpha_behaviour", Component.translatable("vd.commandRule.blocks.alphaBehaviour"));
            put("opening_blockable", Component.translatable("vd.commandRule.blocks.openingBlockable"));
            put("cooldown", Component.translatable("vd.commandRule.blocks.cooldown"));
            put("push_behaviour", Component.translatable("vd.commandRule.blocks.pushBehaviour"));
            put("ignited_by_lava", Component.translatable("vd.commandRule.blocks.ignitedByLava"));
            put("destroy_speed", Component.translatable("vd.commandRule.blocks.destroySpeed"));
            put("requires_correct_tool_for_drops", Component.translatable("vd.commandRule.blocks.requiresCorrectToolForDrops"));
            put("burn_odds", Component.translatable("vd.commandRule.blocks.burnOdds"));
            put("ignite_odds", Component.translatable("vd.commandRule.blocks.igniteOdds"));
            put("can_be_placed_by_command", Component.translatable("vd.commandRule.blocks.canBePlacedByCommand"));
        }});

        itemData.put("potion", new Object2ObjectOpenHashMap<>() {{
            potionRegistry.keySet().forEach(potion ->
                    put(lightCleanup(potion) + "_effect", Component.translatable("vd.commandRule.items.potionEffects", cleanup(potion))));
        }});
        itemData.put("other", new Object2ObjectOpenHashMap<>() {{
            put("works", Component.translatable("vd.commandRule.items.works"));
            put("durability", Component.translatable("vd.commandRule.items.durability"));
            put("burns", Component.translatable("vd.commandRule.items.burns"));
            put("can_spam", Component.translatable("vd.commandRule.items.canSpam"));
            put("nutrition", Component.translatable("vd.commandRule.items.nutrition"));
            put("saturation", Component.translatable("vd.commandRule.items.saturation"));
            put("can_break_blocks_in_creative", Component.translatable("vd.commandRule.items.canBreakBlocksInCreative"));
            put("dispenser_interaction", Component.translatable("vd.commandRule.items.dispenserInteraction"));
            put("cauldron_interaction", Component.translatable("vd.commandRule.items.cauldronInteraction"));
            put("fuel_duration", Component.translatable("vd.commandRule.items.fuelDuration"));
            put("can_be_given_by_command", Component.translatable("vd.commandRule.items.canBeGivenByCommand"));
        }});

        enchantmentData.put("item", new Object2ObjectOpenHashMap<>() {{
            itemRegistry.keySet().forEach(item ->
                    put("can_enchant_" + lightCleanup(item), Component.translatable("vd.commandRule.enchantments.enchantItem", cleanup(item))));
        }});
        enchantmentData.put("enchantment_compatibility", new Object2ObjectOpenHashMap<>() {{
            enchantmentRegistry.keySet().forEach(enchantment1 ->
                    put("compatible_with_" + lightCleanup(enchantment1), Component.translatable("vd.commandRule.enchantments.enchantmentCompatibility", cleanup(enchantment1))));
        }});

        commandData.put("other", new Object2ObjectOpenHashMap<>() {{
            put("enabled", Component.translatable("vd.commandRule.commands.enabled"));
        }});

        advancementData.put("other", new Object2ObjectOpenHashMap<>() {{
            put("enabled", Component.translatable("vd.commandRule.advancements.enabled"));
        }});

        mobCategoryData.put("other", new Object2ObjectOpenHashMap<>() {{
            put("mobcap", Component.translatable("vd.commandRule.mobCategory.mobcap"));
        }});

        intRowMaximums.put("nutrition", 20);
        doubleRowMaximums.put("saturation", 9.9);
        stringColSuggestions.put("push_behaviour", Arrays.stream(PushReaction.values()).map(Enum::name).toList());
        differentDataTypes.add("player");
        differentDataTypes.add("spawning");
        differentDataTypes.add("fluid");
        differentDataTypes.add("other");

        populationDone = true;
    }

    /**
     * Get the name of a block from the registry.
     *
     * @param block The block to get the name of.
     * @return The name of the block.
     */
    public static String getKeyFromBlockRegistry(Block block) {
        return Objects.requireNonNull(blockRegistry.getKey(block)).toString();
    }

    /**
     * Get the name of an item from the registry.
     *
     * @param item The item to get the name of.
     * @return The name of the block.
     */
    public static String getKeyFromItemRegistry(Item item) {
        return Objects.requireNonNull(itemRegistry.getKey(item)).toString();
    }

    /**
     * Get the name of an entity type from the registry.
     *
     * @param entityType The entity type to get the name of.
     * @return The name of the entity type.
     */
    public static String getKeyFromEntityTypeRegistry(EntityType<?> entityType) {
        return Objects.requireNonNull(entityTypeRegistry.getKey(entityType)).toString();
    }

    /**
     * Get the name of an enchantment from the registry.
     *
     * @param enchantment The enchantment to get the name of.
     * @return The name of the enchantment.
     */
    public static String getKeyFromEnchantmentRegistry(Enchantment enchantment) {
        return Objects.requireNonNull(enchantmentRegistry.getKey(enchantment)).toString();
    }

    /**
     * Generates the default data for the database.
     *
     * @param create Whether to create the tables or not.
     * @param tables The tables to generate data for.
     */
    private static void generateData(boolean create, String tables) {
        try {
            if (create) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS entities(id CLOB NOT NULL, " +
                        cols.get("entities").entrySet().stream().map(entry -> "\"" + entry.getKey() + "\" " + entry.getValue())
                                .collect(Collectors.joining(", ")) + ");");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS blocks(id CLOB NOT NULL, " +
                        cols.get("blocks").entrySet().stream().map(entry -> "\"" + entry.getKey() + "\" " + entry.getValue())
                                .collect(Collectors.joining(", ")) + ");");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS items(id CLOB NOT NULL, " +
                        cols.get("items").entrySet().stream().map(entry -> "\"" + entry.getKey() + "\" " + entry.getValue())
                                .collect(Collectors.joining(", ")) + ");");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS enchantments(id CLOB NOT NULL, " +
                        cols.get("enchantments").entrySet().stream().map(entry -> "\"" + entry.getKey() + "\" " + entry.getValue())
                                .collect(Collectors.joining(", ")) + ");");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS commands(id CLOB NOT NULL, " +
                        cols.get("commands").entrySet().stream().map(entry -> "\"" + entry.getKey() + "\" " + entry.getValue())
                                .collect(Collectors.joining(", ")) + ");");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS advancements(id CLOB NOT NULL, " +
                        cols.get("advancements").entrySet().stream().map(entry -> "\"" + entry.getKey() + "\" " + entry.getValue())
                                .collect(Collectors.joining(", ")) + ");");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS mob_categories(id CLOB NOT NULL, " +
                        cols.get("mob_categories").entrySet().stream().map(entry -> "\"" + entry.getKey() + "\" " + entry.getValue())
                                .collect(Collectors.joining(", ")) + ");");
            }

            if (tables.equals("*") || tables.equals("entities")) {
                entities.forEach((key, value) -> {
                    try {
                        statement.executeUpdate("INSERT INTO entities (id, \"" + String.join("\", \"", value.keySet()) + "\") VALUES ('" + key + "', " + String.join(", ", value.values()) + ");");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            if (tables.equals("*") || tables.equals("blocks")) {
                blocks.forEach((key, value) -> {
                    try {
                        statement.executeUpdate("INSERT INTO blocks (id, \"" + String.join("\", \"", value.keySet()) + "\") VALUES ('" + key + "', " + String.join(", ", value.values()) + ");");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            if (tables.equals("*") || tables.equals("items")) {
                items.forEach((key, value) -> {
                    try {
                        statement.executeUpdate("INSERT INTO items (id, \"" + String.join("\", \"", value.keySet()) + "\") VALUES ('" + key + "', " + String.join(", ", value.values()) + ");");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            if (tables.equals("*") || tables.equals("enchantments")) {
                enchantments.forEach((key, value) -> {
                    try {
                        statement.executeUpdate("INSERT INTO enchantments (id, \"" + String.join("\", \"", value.keySet()) + "\") VALUES ('" + key + "', " + String.join(", ", value.values()) + ");");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            if (tables.equals("*") || tables.equals("commands")) {
                commands.forEach((key, value) -> {
                    try {
                        statement.executeUpdate("INSERT INTO commands (id, \"" + String.join("\", \"", value.keySet()) + "\") VALUES ('" + key + "', " + String.join(", ", value.values()) + ");");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            if (tables.equals("*") || tables.equals("advancements")) {
                advancements.forEach((key, value) -> {
                    try {
                        statement.executeUpdate("INSERT INTO advancements (id, \"" + String.join("\", \"", value.keySet()) + "\") VALUES ('" + key + "', " + String.join(", ", value.values()) + ");");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            if (tables.equals("*") || tables.equals("mob_categories")) {
                mobCategories.forEach((key, value) -> {
                    try {
                        statement.executeUpdate("INSERT INTO mob_categories (id, \"" + String.join("\", \"", value.keySet()) + "\") VALUES ('" + key + "', " + String.join(", ", value.values()) + ");");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inserts default values into the database the first time, updates the database between versions.
     */
    public static void handleDatabase() {
        PATH = server.getWorldPath(LevelResource.ROOT) + "/vanilla_disable_command.sql";

        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:vd");
            statement = connection.createStatement();

            generateData(true, "*");

            if (!new File(PATH).exists() && shouldMigrate && VanillaDisableConfig.autoMigration) {
                GameruleMigrationDataHandler.updateSql();
                migrated = true;
            }
            if (new File(PATH).exists()) {
                Scanner scanner = new Scanner(new File(PATH));
                while (scanner.hasNext()) {
                    try {
                        statement.execute(scanner.nextLine());
                    } catch (SQLException ignored) {
                    }
                }
            } else {
                if (!new File(PATH).exists()) {
                    if (!new File(PATH).createNewFile()) {
                        throw new RuntimeException("Could not create file " + PATH);
                    }
                }
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        cache = Caffeine.newBuilder().maximumSize(VanillaDisableConfig.cache).build();
    }

    /**
     * Shuts down the database connection when necessary.
     */
    public static void closeConnection() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if the connection is null.
     *
     * @return Whether the connection is null.
     */
    public static boolean isConnectionNull() {
        return connection == null;
    }

    /**
     * Saves commands to an .sql file for persistence.
     *
     * @param command The command to save.
     */
    public static void writeToFile(String command) {
        try {
            FileWriter writer = new FileWriter(PATH, true);
            writer.write(command + "\n");
            writer.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Sets a single row-column pair to a value.
     *
     * @param table    The table in which to set the value.
     * @param row      The row in which to set the value.
     * @param column   The column in which to set the value.
     * @param value    The value to set.
     * @param isString Whether the value is a string.
     */
    public static void setValue(String table, String row, String column, String value, boolean isString) {
        if (table.isEmpty() || row.isEmpty() || column.isEmpty() || value.isEmpty()) return;
        invalidateCaches();
        if (isString) {
            value = "'" + value + "'";
        }
        try {
            String query = "UPDATE " + table + " SET \"" + column + "\" = " + value + " WHERE id = '" + row + "';";
            statement.executeUpdate(query);
            writeToFile(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets all rows in a column to a value.
     *
     * @param table    The table in which to set the value.
     * @param column   The column in which to set the value.
     * @param value    The value to set.
     * @param isString Whether the value is a string.
     */
    public static void setAll(String table, String column, String value, boolean isString) {
        if (table.isEmpty() || column.isEmpty() || value.isEmpty()) return;
        invalidateCaches();
        if (isString) {
            value = "'" + value + "'";
        }
        try {
            String query = "UPDATE " + table + " SET \"" + column + "\" = " + value + " WHERE \"" + column + "\" IS NOT NULL;";
            statement.executeUpdate(query);
            writeToFile(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets all rows which match a pattern in a column to a value.
     *
     * @param table    The table in which to set the value.
     * @param column   The column in which to set the value.
     * @param value    The value to set.
     * @param isString Whether the value is a string.
     * @param pattern  The pattern to match.
     */
    public static void setMatching(String table, String column, String value, boolean isString, String pattern) {
        if (table.isEmpty() || column.isEmpty() || value.isEmpty() || pattern.isEmpty()) return;
        invalidateCaches();
        if (pattern.contains(";") || pattern.contains("SELECT") || pattern.contains("ALTER")) {
            throw new RuntimeException("SQL injection attempted. Command not executed.");
        }
        if (isString) {
            value = "'" + value + "'";
        }
        try {
            String query = "UPDATE " + table + " SET \"" + column + "\" = " + value + " WHERE \"" + column + "\" IS NOT NULL AND id LIKE '" + pattern + "';";
            statement.executeUpdate(query);
            writeToFile(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the default value for a row-column pair.
     *
     * @param table  The table from which to get the value.
     * @param row    The row from which to get the value.
     * @param column The column from which to get the value.
     * @return The value.
     */
    public static String getDefault(String table, String row, String column) {
        return switch (table) {
            case "blocks" -> blocks.get(row).get(column);
            case "entities" -> entities.get(row).get(column);
            case "items" -> items.get(row).get(column);
            case "enchantments" -> enchantments.get(row).get(column);
            case "commands" -> commands.get(row).get(column);
            case "advancements" -> advancements.get(row).get(column);
            case "mob_categories" -> mobCategories.get(row).get(column);
            default -> "true";
        };
    }

    /**
     * Get a boolean value from the database.
     *
     * @param table  The table from which to get the value.
     * @param row    The row from which to get the value.
     * @param column The column from which to get the value.
     * @return The value.
     */
    private static boolean getBoolean(String table, String row, String column) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT \"" + column + "\" FROM " + table + " WHERE id = '" + row + "';");
            resultSet.next();
            boolean bool = resultSet.getBoolean(column);
            resultSet.close();
            return bool;
        } catch (SQLException | NullPointerException ignored) {
        }
        invalidateCaches();
        return Boolean.parseBoolean(getDefault(table, row, column));
    }

    /**
     * Get a boolean value from the database, using memoization to cache results.
     *
     * @param table  The table from which to get the value.
     * @param row    The row from which to get the value.
     * @param column The column from which to get the value.
     * @return The value.
     */
    public static boolean getCachedBoolean(String table, String row, String column) {
        String cacheKey = table + "-" + row + "-" + column;
        return (boolean) cache.get(cacheKey, key -> getBoolean(table, row, column));
    }

    /**
     * Get an integer value from the database.
     *
     * @param table  The table from which to get the value.
     * @param row    The row from which to get the value.
     * @param column The column from which to get the value.
     * @return The value.
     */
    private static int getInt(String table, String row, String column) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT \"" + column + "\" FROM " + table + " WHERE id = '" + row + "';");
            resultSet.next();
            int integer = resultSet.getInt(column);
            resultSet.close();
            return integer;
        } catch (SQLException | NullPointerException ignored) {
        }
        invalidateCaches();
        return Integer.parseInt(getDefault(table, row, column));
    }

    /**
     * Get an integer value from the database, using memoization to cache results.
     *
     * @param table  The table from which to get the value.
     * @param row    The row from which to get the value.
     * @param column The column from which to get the value.
     * @return The value.
     */
    public static int getCachedInt(String table, String row, String column) {
        String cacheKey = table + "-" + row + "-" + column;
        return (int) cache.get(cacheKey, key -> getInt(table, row, column));
    }

    /**
     * Get a double value from the database.
     *
     * @param table  The table from which to get the value.
     * @param row    The row from which to get the value.
     * @param column The column from which to get the value.
     * @return The value.
     */
    private static double getDouble(String table, String row, String column) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT \"" + column + "\" FROM " + table + " WHERE id = '" + row + "';");
            resultSet.next();
            double dbl = resultSet.getDouble(column);
            resultSet.close();
            return dbl;
        } catch (SQLException | NullPointerException ignored) {
        }
        invalidateCaches();
        return Double.parseDouble(getDefault(table, row, column));
    }

    /**
     * Get a double value from the database, using memoization to cache results.
     *
     * @param table  The table from which to get the value.
     * @param row    The row from which to get the value.
     * @param column The column from which to get the value.
     * @return The value.
     */
    public static double getCachedDouble(String table, String row, String column) {
        String cacheKey = table + "-" + row + "-" + column;
        return (double) cache.get(cacheKey, key -> getDouble(table, row, column));
    }

    /**
     * Get a string value from the database.
     *
     * @param table  The table from which to get the value.
     * @param row    The row from which to get the value.
     * @param column The column from which to get the value.
     * @return The value.
     */
    private static String getString(String table, String row, String column) {
        if (connection == null) return "";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT \"" + column + "\" FROM " + table + " WHERE id = '" + row + "';");
            resultSet.next();
            String str = resultSet.getString(column);
            resultSet.close();
            return str;
        } catch (SQLException | NullPointerException ignored) {
        }
        invalidateCaches();
        return getDefault(table, row, column);
    }

    /**
     * Get a string value from the database, using memoization to cache results.
     *
     * @param table  The table from which to get the value.
     * @param row    The row from which to get the value.
     * @param column The column from which to get the value.
     * @return The value.
     */
    public static String getCachedString(String table, String row, String column) {
        String cacheKey = table + "-" + row + "-" + column;
        return (String) cache.get(cacheKey, key -> getString(table, row, column));
    }

    /**
     * Get a list of all the items that an entity can breed with.
     *
     * @param row The name of the entity.
     * @return The list of items.
     */
    private static Ingredient getBreedingItems(String row) {
        ObjectSet<Item> items = new ObjectArraySet<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM entities WHERE id = '" + row + "';");
            if (resultSet.next()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = resultSetMetaData.getColumnName(i);
                    if (columnName.startsWith("can_breed_with_")) {
                        if (resultSet.getBoolean(columnName)) {
                            items.add(Objects.requireNonNull(itemRegistry.get(ResourceLocation.of(
                                    columnName.replace("can_breed_with_", "minecraft:"), ':'))));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (items.contains(Items.CARROT)) {
            items.add(Items.CARROT_ON_A_STICK);
        }
        if (items.contains(Items.WARPED_FUNGUS)) {
            items.add(Items.WARPED_FUNGUS_ON_A_STICK);
        }
        return Ingredient.of(items.stream().map(ItemStack::new));
    }

    /**
     * Get a list of all the items that an entity can breed with, using memoization to cache results.
     *
     * @param row The row from which to get the value.
     * @return The value.
     */
    public static Ingredient getCachedBreedingItems(String row) {
        return (Ingredient) cache.get(row, key -> getBreedingItems(row));
    }

    /**
     * Reset all tables in the database by deleting the sql script and resetting the in-memory db.
     */
    public static void resetAll() {
        invalidateCaches();
        if (!new File(PATH).exists()) return;
        if (!new File(PATH).delete()) {
            throw new RuntimeException("Could not delete file " + PATH);
        }
        try {
            if (!new File(PATH).createNewFile()) {
                throw new RuntimeException("Could not create file " + PATH);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        closeConnection();
        handleDatabase();
    }

    /**
     * Reset a single table in the database by removing its commands from the sql script and resetting the in-memory db.
     *
     * @param db The table to reset.
     */
    public static void resetOne(String db) {
        invalidateCaches();
        if (!new File(PATH).exists()) return;
        try {
            List<String> lines = FileUtils.readLines(new File(PATH), Charset.defaultCharset())
                    .stream().filter(line -> !line.contains("UPDATE " + db)).toList();
            FileUtils.writeLines(new File(PATH), lines, false);

            statement.executeUpdate("DELETE FROM " + db + ";");
            generateData(false, db);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reset some columns in a database by removing them from the sql script and resetting the in-memory db.
     *
     * @param db   The table to reset.
     * @param cols The columns to reset.
     */
    public static void resetPartial(String db, ObjectSet<String> cols) {
        invalidateCaches();
        if (!new File(PATH).exists()) return;
        try {
            List<String> lines = FileUtils.readLines(new File(PATH), Charset.defaultCharset())
                    .stream().filter(line -> {
                        if (line.contains("UPDATE " + db)) {
                            return cols.stream().noneMatch(line::contains);
                        }
                        return true;
                    }).toList();
            FileUtils.writeLines(new File(PATH), lines, false);

            statement.executeUpdate("DELETE FROM " + db + ";");
            generateData(false, db);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Undo the last n commands in the sql script and reset the in-memory db.
     * @param count The number of commands to undo.
     */
    public static void undo(int count) {
        invalidateCaches();
        if (!new File(PATH).exists()) return;
        try {
            List<String> lines = FileUtils.readLines(new File(PATH), Charset.defaultCharset());
            for (int i = 0; i < count; i++) {
                if (lines.isEmpty()) break;
                lines.remove(lines.size() - 1);
            }
            FileUtils.writeLines(new File(PATH), lines, false);

            closeConnection();
            handleDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Invalidate caches if they aren't null.
     */
    private static void invalidateCaches() {
        if (cache != null) {
            cache.invalidateAll();
        }
    }
}
