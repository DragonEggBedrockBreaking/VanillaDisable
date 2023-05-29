package uk.debb.vanilla_disable.command.data;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.*;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.commands.Commands;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.packs.VanillaHusbandryAdvancements;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.storage.LevelResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Optional;
import java.util.stream.Collectors;

import static uk.debb.vanilla_disable.command.data.DataType.*;

public class DataHandler {
    public static final Object2ObjectMap<String, Object2ObjectMap<String, DataType>> cols = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Pair<ObjectList<String>, ObjectList<String>>> entities = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Pair<ObjectList<String>, ObjectList<String>>> blocks = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Pair<ObjectList<String>, ObjectList<String>>> items = new Object2ObjectOpenHashMap<>();
    public static final ObjectSet<String> others = new ObjectArraySet<>();

    public static final Object2ObjectMap<String, ObjectList<Pair<String, String>>> entityData = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, ObjectList<Pair<String, String>>> blockData = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, ObjectList<Pair<String, String>>> itemData = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, String> otherData = new Object2ObjectOpenHashMap<>();

    public static final Object2IntMap<String> intRowMaximums = new Object2IntArrayMap<>();
    public static final Object2DoubleMap<String> doubleRowMaximums = new Object2DoubleArrayMap<>();

    public static MinecraftServer server;
    private static Connection connection;
    private static Statement statement;

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

    public static void populate() {
        RegistryAccess registryAccess = server.registryAccess();
        cols.put("entities", new Object2ObjectOpenHashMap<>() {{
            put("can_be_on_fire", BOOLEAN);
            put("can_sprint", BOOLEAN);
            put("can_crouch", BOOLEAN);
            put("can_swim", BOOLEAN);
            put("can_jump", BOOLEAN);
            put("can_be_invisible", BOOLEAN);
            put("flying_speed", REAL);

            put("item_stats", BOOLEAN);
            put("entity_stats", BOOLEAN);
            put("time_stats", BOOLEAN);
            put("distance_stats", BOOLEAN);
            put("damage_stats", BOOLEAN);
            put("block_gui_interaction_stats", BOOLEAN);
            put("block_general_interaction_stats", BOOLEAN);
            put("general_stats", BOOLEAN);

            registryAccess.registryOrThrow(Registries.DAMAGE_TYPE).keySet().forEach(damageType -> {
                put(damageType + "_damage", BOOLEAN);
                put(damageType + "_death", BOOLEAN);
            });

            put("fireball_knockback", BOOLEAN);
            put("wither_skull_knockback", BOOLEAN);
            put("dragon_knockback", BOOLEAN);
            put("arrow_knockback", BOOLEAN);
            put("trident_knockback", BOOLEAN);
            put("llama_spit_knockback", BOOLEAN);
            put("shulker_bullet_knockback", BOOLEAN);
            put("mob_attack_knockback", BOOLEAN);
            put("player_attack_knockback", BOOLEAN);
            put("explosion_knockback", BOOLEAN);

            registryAccess.registryOrThrow(Registries.MOB_EFFECT).keySet().forEach(mobEffect ->
                    put(mobEffect + "_effect", BOOLEAN));

            registryAccess.registryOrThrow(Registries.PAINTING_VARIANT).keySet().forEach(painting ->
                    put(painting + "_painting", BOOLEAN));

            registryAccess.registryOrThrow(Registries.VILLAGER_PROFESSION).keySet().forEach(profession ->
                    put(profession + "_profession", BOOLEAN));
            registryAccess.registryOrThrow(Registries.VILLAGER_TYPE).keySet().forEach(type ->
                    put(type + "_type", BOOLEAN));

            put("can_despawn", BOOLEAN);
            put("despawn_time", INTEGER);

            put("can_spawn", BOOLEAN);
            put("spawn_egg", BOOLEAN);
            put("spawner", BOOLEAN);
            put("can_breed", BOOLEAN);
            put("can_exist", BOOLEAN);
            put("can_be_cured", BOOLEAN);
            put("can_be_converted_to", BOOLEAN);
            put("burns_in_sunlight", BOOLEAN);
            put("spawned_by_villagers", BOOLEAN);
            put("can_drop_xp", BOOLEAN);
            put("ai", BOOLEAN);
            put("can_trade", BOOLEAN);
            put("daily_restocks", INTEGER);
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
            put("fluid_reaches_far", BOOLEAN);
            put("fluid_reaches_far_in_nether", BOOLEAN);
            put("fluid_speed", INTEGER);
            put("fluid_speed_in_nether", INTEGER);
            put("redstone_delay", INTEGER);
            put("redstone_duration", INTEGER);
            put("can_drop_xp", BOOLEAN);
            put("dispenser_interaction", BOOLEAN);
            put("can_fall", BOOLEAN);
        }});
        cols.put("items", new Object2ObjectOpenHashMap<>() {{
            put("can_use", BOOLEAN);
            put("durability", INTEGER);
            put("burns", BOOLEAN);
            put("can_spam", BOOLEAN);
            put("nutrition", INTEGER);
            put("saturation", REAL);

            registryAccess.registryOrThrow(Registries.ENCHANTMENT).keySet().forEach(enchantment ->
                    put(enchantment + "_enchantment", BOOLEAN));

            put("boot_enchantment_conflicts", BOOLEAN);
            put("bow_enchantment_conflicts", BOOLEAN);
            put("crossbow_enchantment_conflicts", BOOLEAN);
            put("damage_enchantment_conflicts", BOOLEAN);
            put("mining_enchantment_conflicts", BOOLEAN);
            put("protection_enchantment_conflicts", BOOLEAN);
            put("trident_enchantment_conflicts", BOOLEAN);

            registryAccess.registryOrThrow(Registries.POTION).keySet().forEach(potion ->
                    put(potion + "_effect", BOOLEAN));

            put("dispenser_interaction", BOOLEAN);
        }});

        BuiltInRegistries.ENTITY_TYPE.forEach((entityType) -> {
            ObjectList<String> col = new ObjectArrayList<>();
            ObjectList<String> val = new ObjectArrayList<>();

            if (entityType.equals(EntityType.PLAYER)) {
                col.add("can_be_on_fire");
                val.add("true");
                col.add("can_sprint");
                val.add("true");
                col.add("can_crouch");
                val.add("true");
                col.add("can_swim");
                val.add("true");
                col.add("can_jump");
                val.add("true");
                col.add("can_be_invisible");
                val.add("true");
                col.add("flying_speed");
                val.add("0.05");

                col.add("item_stats");
                val.add("true");
                col.add("entity_stats");
                val.add("true");
                col.add("time_stats");
                val.add("true");
                col.add("distance_stats");
                val.add("true");
                col.add("damage_stats");
                val.add("true");
                col.add("block_gui_interaction_stats");
                val.add("true");
                col.add("block_general_interaction_stats");
                val.add("true");
                col.add("general_stats");
                val.add("true");
            }

            if (!entityType.getCategory().equals(MobCategory.MISC) || entityType.equals(EntityType.VILLAGER) ||
                    entityType.equals(EntityType.IRON_GOLEM) || entityType.equals(EntityType.SNOW_GOLEM) ||
                    entityType.equals(EntityType.PLAYER)) {
                registryAccess.registryOrThrow(Registries.DAMAGE_TYPE).keySet().forEach(damageType -> {
                    col.add(damageType + "_damage");
                    val.add("true");
                    col.add(damageType + "_death");
                    val.add("true");
                });

                col.add("fireball_knockback");
                val.add("true");
                col.add("wither_skull_knockback");
                val.add("true");
                col.add("dragon_knockback");
                val.add("true");
                col.add("arrow_knockback");
                val.add("true");
                col.add("trident_knockback");
                val.add("true");
                col.add("llama_spit_knockback");
                val.add("true");
                col.add("shulker_bullet_knockback");
                val.add("true");
                col.add("mob_attack_knockback");
                val.add("true");
                col.add("player_attack_knockback");
                val.add("true");
                col.add("explosion_knockback");
                val.add("true");

                registryAccess.registryOrThrow(Registries.MOB_EFFECT).keySet().forEach(mobEffect -> {
                    col.add(mobEffect + "_effect");
                    val.add("true");
                });

                col.add("can_despawn");
                val.add("true");

                col.add("can_spawn");
                val.add("true");
                col.add("spawn_egg");
                val.add("true");
                col.add("spawner");
                val.add("true");
                col.add("can_drop_xp");
                val.add("true");
                col.add("ai");
                val.add("true");
            }

            if (entityType.equals(EntityType.PAINTING)) {
                BuiltInRegistries.PAINTING_VARIANT.keySet().forEach(painting -> {
                    col.add(painting + "_painting");
                    val.add("true");
                });
            }

            if (entityType.equals(EntityType.VILLAGER)) {
                BuiltInRegistries.VILLAGER_PROFESSION.keySet().forEach(profession -> {
                    col.add(profession + "_profession");
                    val.add("true");
                });
                BuiltInRegistries.VILLAGER_TYPE.keySet().forEach(type -> {
                    col.add(type + "_type");
                    val.add("true");
                });
            }

            if (VanillaHusbandryAdvancements.BREEDABLE_ANIMALS.contains(entityType) ||
                    VanillaHusbandryAdvancements.INDIRECTLY_BREEDABLE_ANIMALS.contains(entityType) ||
                    entityType.equals(EntityType.VILLAGER)) {
                col.add("can_breed");
                val.add("true");
            }

            if (entityType.equals(EntityType.ZOMBIE_VILLAGER)) {
                col.add("can_be_cured");
                val.add("true");
            }

            if (entityType.equals(EntityType.ZOMBIE_VILLAGER) || entityType.equals(EntityType.WITCH) ||
                    entityType.equals(EntityType.ZOMBIFIED_PIGLIN) || entityType.equals(EntityType.ZOGLIN) ||
                    entityType.equals(EntityType.DROWNED) || entityType.equals(EntityType.STRAY)) {
                col.add("can_be_converted_to");
                val.add("true");
            }

            if (entityType.equals(EntityType.SKELETON) || entityType.equals(EntityType.ZOMBIE) ||
                    entityType.equals(EntityType.PHANTOM) || entityType.equals(EntityType.STRAY) ||
                    entityType.equals(EntityType.DROWNED) || entityType.equals(EntityType.ZOMBIE_VILLAGER)) {
                col.add("burns_in_sunlight");
                val.add("true");
            }

            if (entityType.equals(EntityType.VILLAGER) || entityType.equals(EntityType.WANDERING_TRADER) ||
                    entityType.equals(EntityType.PIGLIN)) {
                col.add("can_trade");
                val.add("true");
            }

            if (entityType.equals(EntityType.VILLAGER) || entityType.equals(EntityType.WANDERING_TRADER)) {
                col.add("daily_restocks");
                val.add("true");
            }

            if (entityType.equals(EntityType.ITEM)) {
                col.add("despawn_time");
                val.add("6000");
            }

            col.add("can_exist");
            val.add("true");

            entities.put(BuiltInRegistries.ENTITY_TYPE.getKey(entityType).toString(), new ObjectObjectImmutablePair<>(col, val));
        });

        BuiltInRegistries.BLOCK.forEach((block) -> {
            ObjectList<String> col = new ObjectArrayList<>();
            ObjectList<String> val = new ObjectArrayList<>();

            col.add("can_place_in_overworld");
            val.add("true");
            col.add("can_place_in_nether");
            val.add("true");
            col.add("can_place_in_end");
            val.add("true");
            col.add("can_break");
            val.add("true");
            col.add("can_interact");
            val.add("true");
            col.add("works");
            val.add("true");

            if (block.toString().equals(Blocks.ICE.toString()) || block.toString().equals(Blocks.PACKED_ICE.toString()) ||
                    block.toString().equals(Blocks.BLUE_ICE.toString())) {
                col.add("friction_factor");
                val.add("0.98");
            } else if (block.toString().equals(Blocks.SLIME_BLOCK.toString())) {
                col.add("friction_factor");
                val.add("0.8");
            } else {
                col.add("friction_factor");
                val.add("0.6");
            }

            boolean isHoneyBlock = block.toString().equals(Blocks.HONEY_BLOCK.toString());
            col.add("speed_factor");
            if (block.toString().equals(Blocks.SOUL_SAND.toString()) || isHoneyBlock) {
                val.add("0.4");
            } else {
                val.add("1.0");
            }

            col.add("jump_factor");
            if (isHoneyBlock) {
                val.add("0.5");
            } else {
                val.add("1.0");
            }

            if (block.toString().equals(Blocks.CAULDRON.toString())) {
                col.add("can_be_filled_by_dripstone");
                val.add("true");
            }

            if (block.toString().equals(Blocks.WATER.toString())) {
                col.add("fluid_reaches_far");
                val.add("true");
                col.add("fluid_reaches_far_in_nether");
                val.add("true");
                col.add("fluid_speed");
                val.add("5");
                col.add("fluid_speed_in_nether");
                val.add("5");
            }

            if (block.toString().equals(Blocks.LAVA.toString())) {
                col.add("fluid_reaches_far");
                val.add("false");
                col.add("fluid_reaches_far_in_nether");
                val.add("true");
                col.add("fluid_speed");
                val.add("30");
                col.add("fluid_speed_in_nether");
                val.add("10");
            }

            boolean isObserver = block.toString().equals(Blocks.OBSERVER.toString());
            if (block.toString().equals(Blocks.REPEATER.toString()) || block.toString().equals(Blocks.COMPARATOR.toString()) || isObserver) {
                col.add("redstone_delay");
                val.add("2");
            }

            if (isObserver) {
                col.add("redstone_duration");
                val.add("2");
            }

            if (block.toString().endsWith("_ore") || block.toString().equals(Blocks.SPAWNER.toString()) ||
                    block.toString().equals(Blocks.SCULK_SENSOR.toString()) || block.toString().equals(Blocks.SCULK_CATALYST.toString()) ||
                    block.toString().equals(Blocks.SCULK_SHRIEKER.toString()) || block.toString().equals(Blocks.SCULK.toString())) {
                col.add("can_drop_xp");
                val.add("true");
            }

            if (DispenserBlock.DISPENSER_REGISTRY.containsKey(block.asItem())) {
                col.add("dispenser_interaction");
                val.add("true");
            }

            if (block instanceof FallingBlock) {
                col.add("can_fall");
                val.add("true");
            }

            blocks.put(BuiltInRegistries.BLOCK.getKey(block).toString(), new ObjectObjectImmutablePair<>(col, val));
        });

        BuiltInRegistries.ITEM.forEach((item) -> {
            if (BuiltInRegistries.BLOCK.stream().map(Block::asItem).toList().contains(item)) return;

            ObjectList<String> col = new ObjectArrayList<>();
            ObjectList<String> val = new ObjectArrayList<>();

            if (!BuiltInRegistries.BLOCK.stream().map(Block::toString).toList().contains(item.toString())) {
                col.add("can_use");
                val.add("true");

                if (item.toString().contains("netherite")) {
                    col.add("burns");
                    val.add("false");
                } else {
                    col.add("burns");
                    val.add("true");
                }

                if (item.equals(Items.BOW) || item.equals(Items.CROSSBOW)) {
                    col.add("can_spam");
                    val.add("true");
                }

                if (item.canBeDepleted()) {
                    col.add("durability");
                    val.add(String.valueOf(item.getMaxDamage()));
                    BuiltInRegistries.ENCHANTMENT.forEach((enchantment) -> {
                        if (enchantment.canEnchant(new ItemStack(item))) {
                            col.add(BuiltInRegistries.ENCHANTMENT.getKey(enchantment) + "_enchantment");
                            val.add("true");
                        }
                    });
                }

                FoodProperties foodProperties = item.getFoodProperties();
                if (foodProperties != null) {
                    col.add("nutrition");
                    val.add(String.valueOf(foodProperties.getNutrition()));
                    col.add("saturation");
                    val.add(String.valueOf(foodProperties.getSaturationModifier()));
                }

                if (Enchantments.FROST_WALKER.canEnchant(new ItemStack(item))) {
                    col.add("boot_enchantment_conflicts");
                    val.add("true");
                }

                if (Enchantments.INFINITY_ARROWS.canEnchant(new ItemStack(item))) {
                    col.add("bow_enchantment_conflicts");
                    val.add("true");
                }

                if (Enchantments.MULTISHOT.canEnchant(new ItemStack(item))) {
                    col.add("crossbow_enchantment_conflicts");
                    val.add("true");
                }

                if (Enchantments.SHARPNESS.canEnchant(new ItemStack(item))) {
                    col.add("damage_enchantment_conflicts");
                    val.add("true");
                }

                if (Enchantments.BLOCK_FORTUNE.canEnchant(new ItemStack(item))) {
                    col.add("mining_enchantment_conflicts");
                    val.add("true");
                }

                if (Enchantments.PROJECTILE_PROTECTION.canEnchant(new ItemStack(item))) {
                    col.add("protection_enchantment_conflicts");
                    val.add("true");
                }

                if (Enchantments.RIPTIDE.canEnchant(new ItemStack(item))) {
                    col.add("trident_enchantment_conflicts");
                    val.add("true");
                }

                if (item.equals(Items.POTION) || item.equals(Items.SPLASH_POTION) ||
                        item.equals(Items.LINGERING_POTION) || item.equals(Items.TIPPED_ARROW)) {
                    BuiltInRegistries.POTION.keySet().forEach((potion) -> {
                        col.add(potion + "_effect");
                        val.add("true");
                    });
                }
            }

            if (DispenserBlock.DISPENSER_REGISTRY.containsKey(item)) {
                col.add("dispenser_interaction");
                val.add("true");
            }

            items.put(BuiltInRegistries.ITEM.getKey(item).toString(), new ObjectObjectImmutablePair<>(col, val));
        });

        others.addAll(server.getAdvancements().getAllAdvancements()
                .stream().map(a -> a.getId().toString()).filter(a -> !a.contains("recipe")).toList());
        others.addAll(new Commands(Commands.CommandSelection.ALL, Commands.createValidationContext(VanillaRegistries.createLookup()))
                .getDispatcher().getRoot().getChildren().stream().map(commandNode -> "/" + commandNode.getName()).toList());
        others.addAll(BuiltInRegistries.STRUCTURE_TYPE.keySet()
                .stream().map(Object::toString).toList());
        others.addAll(registryAccess.registryOrThrow(Registries.STRUCTURE).keySet()
                .stream().map(Object::toString).toList());
        others.addAll(registryAccess.registryOrThrow(Registries.PLACED_FEATURE).keySet()
                .stream().map(Object::toString).toList());
        others.addAll(BuiltInRegistries.FEATURE.keySet()
                .stream().map(Object::toString).toList());
        others.addAll(registryAccess.registryOrThrow(Registries.BIOME).keySet()
                .stream().map(Object::toString).toList());

        entityData.put("stats", new ObjectArrayList<>() {{
            add(new ObjectObjectImmutablePair<>("item_stats", "Collect statistics related to items."));
            add(new ObjectObjectImmutablePair<>("entity_stats", "Collect statistics related to entities."));
            add(new ObjectObjectImmutablePair<>("time_stats", "Collect statistics related to time."));
            add(new ObjectObjectImmutablePair<>("distance_stats", "Collect statistics related to distance."));
            add(new ObjectObjectImmutablePair<>("damage_stats", "Collect statistics related to damage."));
            add(new ObjectObjectImmutablePair<>("block_gui_interaction_stats", "Collect statistics related to block GUIs."));
            add(new ObjectObjectImmutablePair<>("block_general_interaction_stats", "Collect statistics related to blocks."));
            add(new ObjectObjectImmutablePair<>("general_stats", "Collect general statistics."));
        }});
        entityData.put("damage", new ObjectArrayList<>() {{
            registryAccess.registryOrThrow(Registries.DAMAGE_TYPE).keySet().forEach(damageType ->
                    add(new ObjectObjectImmutablePair<>(damageType + "_damage", "Toggles " + cleanup(damageType) + " damage affecting the mob.")));
        }});
        entityData.put("knockback", new ObjectArrayList<>() {{
            add(new ObjectObjectImmutablePair<>("fireball_knockback", "Toggles fireballs knocking back the entity."));
            add(new ObjectObjectImmutablePair<>("wither_skull_knockback", "Toggles wither skulls knocking back the entity."));
            add(new ObjectObjectImmutablePair<>("dragon_knockback", "Toggles the ender dragon knocking back the entity."));
            add(new ObjectObjectImmutablePair<>("arrow_knockback", "Toggles arrows knocking back the entity."));
            add(new ObjectObjectImmutablePair<>("trident_knockback", "Toggles tridents knocking back the entity."));
            add(new ObjectObjectImmutablePair<>("llama_spit_knockback", "Toggles llama spit knocking back the entity."));
            add(new ObjectObjectImmutablePair<>("shulker_bullet_knockback", "Toggles shulker bullets knocking back the entity."));
            add(new ObjectObjectImmutablePair<>("mob_attack_knockback", "Toggles mob attacks knocking back the entity."));
            add(new ObjectObjectImmutablePair<>("player_attack_knockback", "Toggles player attacks knocking back the entity."));
            add(new ObjectObjectImmutablePair<>("explosion_knockback", "Toggles explosions knocking back the entity."));
        }});
        entityData.put("effects", new ObjectArrayList<>() {{
            registryAccess.registryOrThrow(Registries.MOB_EFFECT).keySet().forEach(mobEffect ->
                    add(new ObjectObjectImmutablePair<>(mobEffect.toString(), "Toggles " + cleanup(mobEffect) + " affecting the mob.")));
        }});
        entityData.put("death", new ObjectArrayList<>() {{
            registryAccess.registryOrThrow(Registries.DAMAGE_TYPE).keySet().forEach(damageType ->
                    add(new ObjectObjectImmutablePair<>(damageType + "_death", "Toggles " + cleanup(damageType) + " damage being able to kill the mob.")));
        }});
        entityData.put("painting", new ObjectArrayList<>() {{
            registryAccess.registryOrThrow(Registries.PAINTING_VARIANT).keySet().forEach(painting ->
                    add(new ObjectObjectImmutablePair<>(painting + "_painting", "Toggles the " + cleanup(painting) + " design being able to show on paintings.")));
        }});
        entityData.put("villager_type", new ObjectArrayList<>() {{
            registryAccess.registryOrThrow(Registries.VILLAGER_TYPE).keySet().forEach(villagerType ->
                    add(new ObjectObjectImmutablePair<>(villagerType + "_type", "Toggles villagers being able to be of the " + cleanup(villagerType) + " type.")));
        }});
        entityData.put("villager_profession", new ObjectArrayList<>() {{
            registryAccess.registryOrThrow(Registries.VILLAGER_PROFESSION).keySet().forEach(villagerProfession ->
                    add(new ObjectObjectImmutablePair<>(villagerProfession + "_profession", "Toggles villagers being able to have the " + cleanup(villagerProfession) + " profession.")));
        }});
        entityData.put("other", new ObjectArrayList<>() {{
            add(new ObjectObjectImmutablePair<>("can_be_on_fire", "Toggle the player being able to be on fire."));
            add(new ObjectObjectImmutablePair<>("can_sprint", "Toggle the player being able to sprint."));
            add(new ObjectObjectImmutablePair<>("can_crouch", "Toggle the player being able to crouch."));
            add(new ObjectObjectImmutablePair<>("can_swim", "Toggle the player being able to swim."));
            add(new ObjectObjectImmutablePair<>("can_jump", "Toggle the player being able to jump."));
            add(new ObjectObjectImmutablePair<>("can_be_invisible", "Toggle the player being able to be invisible."));
            add(new ObjectObjectImmutablePair<>("flying_speed", "Control the player's flying speed."));
            add(new ObjectObjectImmutablePair<>("can_despawn", "Toggle the mob being able to despawn."));
            add(new ObjectObjectImmutablePair<>("despawn_time", "Controls how long it takes for an entity to despawn."));
            add(new ObjectObjectImmutablePair<>("can_spawn", "Toggle the mob being able to spawn."));
            add(new ObjectObjectImmutablePair<>("spawn_egg", "Toggle the mob being able to be spawned by a spawn egg."));
            add(new ObjectObjectImmutablePair<>("spawner", "Toggle the mob being able to be spawned by a spawner."));
            add(new ObjectObjectImmutablePair<>("can_breed", "Toggle the mob being able to breed."));
            add(new ObjectObjectImmutablePair<>("can_exist", "Toggle the entity being able to exist."));
            add(new ObjectObjectImmutablePair<>("can_be_cured", "Toggle the mob being able to be cured."));
            add(new ObjectObjectImmutablePair<>("can_be_converted_to", "Toggle the mob being able to convert to another mob."));
            add(new ObjectObjectImmutablePair<>("burns_in_sunlight", "Toggle the mob being able to burn in sunlight."));
            add(new ObjectObjectImmutablePair<>("spawned_by_villagers", "Toggle the mob being able to be spawned by villagers."));
            add(new ObjectObjectImmutablePair<>("can_drop_xp", "Toggle the mob being able to drop XP."));
            add(new ObjectObjectImmutablePair<>("ai", "Toggle the mob's AI."));
            add(new ObjectObjectImmutablePair<>("can_trade", "Toggle the mob being able to trade."));
            add(new ObjectObjectImmutablePair<>("daily_restocks", "Control the number of times per day a villager restocks."));
        }});

        blockData.put("fluid", new ObjectArrayList<>() {{
            add(new ObjectObjectImmutablePair<>("fluid_reaches_far", "Toggle whether the fluid can travel 8 blocks or only 4 in the overworld or end."));
            add(new ObjectObjectImmutablePair<>("fluid_reaches_far_in_nether", "Toggle whether the fluid can travel 8 blocks or only 4 in the nether."));
            add(new ObjectObjectImmutablePair<>("fluid_speed", "Control how fast the fluid flows in the overworld or end."));
            add(new ObjectObjectImmutablePair<>("fluid_speed_in_nether", "Control how fast the fluid flows in the nether."));
        }});
        blockData.put("other", new ObjectArrayList<>() {{
            add(new ObjectObjectImmutablePair<>("can_place_in_overworld", "Toggle being able to place the block in the overworld."));
            add(new ObjectObjectImmutablePair<>("can_place_in_nether", "Toggle being able to place the block in the nether."));
            add(new ObjectObjectImmutablePair<>("can_place_in_end", "Toggle being able to place the block in the end."));
            add(new ObjectObjectImmutablePair<>("can_break", "Toggle being able to break the block."));
            add(new ObjectObjectImmutablePair<>("can_interact", "Toggle being able to interact with the block."));
            add(new ObjectObjectImmutablePair<>("works", "Toggle the block being able to carry out its function."));
            add(new ObjectObjectImmutablePair<>("friction_factor", "Control how much friction is applied to entities on the block."));
            add(new ObjectObjectImmutablePair<>("speed_factor", "Control how fast entities can travel on the block relative to others."));
            add(new ObjectObjectImmutablePair<>("jump_factor", "Control how high entities can jump on the block relative to others."));
            add(new ObjectObjectImmutablePair<>("can_be_filled_by_dripstone", "Toggle the block being able to be filled by dripstone."));
            add(new ObjectObjectImmutablePair<>("redstone_delay", "Control the redstone delay of the block."));
            add(new ObjectObjectImmutablePair<>("redstone_duration", "Control the redstone duration of the block."));
            add(new ObjectObjectImmutablePair<>("can_drop_xp", "Toggle the block being able to drop XP."));
            add(new ObjectObjectImmutablePair<>("dispenser_interaction", "Toggle the block having a special interaction with a dispenser."));
            add(new ObjectObjectImmutablePair<>("can_fall", "Toggle the block being able to fall."));
        }});

        itemData.put("enchantment", new ObjectArrayList<>() {{
            registryAccess.registryOrThrow(Registries.ENCHANTMENT).keySet().forEach(enchantment ->
                    add(new ObjectObjectImmutablePair<>(enchantment + "_enchantment", "Toggle the " + cleanup(enchantment) + " enchantment being able to be applied to the item.")));

            add(new ObjectObjectImmutablePair<>("boot_enchantment_conflicts", "Toggle the frost walker and depth strider enchantments being incompatible with each other."));
            add(new ObjectObjectImmutablePair<>("bow_enchantment_conflicts", "Toggle the infinity and mending enchantments being incompatible with each other."));
            add(new ObjectObjectImmutablePair<>("crossbow_enchantment_conflicts", "Toggle the multishot and piercing enchantments being incompatible with each other."));
            add(new ObjectObjectImmutablePair<>("damage_enchantment_conflicts", "Toggle the sharpness, smite, and bane of arthropods enchantments being incompatible with each other."));
            add(new ObjectObjectImmutablePair<>("mining_enchantment_conflicts", "Toggle the fortune and silk touch enchantments being incompatible with each other."));
            add(new ObjectObjectImmutablePair<>("protection_enchantment_conflicts", "Toggle the protection, fire protection, blast protection, and projectile protection enchantments being incompatible with each other."));
            add(new ObjectObjectImmutablePair<>("trident_enchantment_conflicts", "Toggle the loyalty and channeling enchantments being incompatible with the riptide enchantment."));
        }});
        itemData.put("potion", new ObjectArrayList<>() {{
            registryAccess.registryOrThrow(Registries.POTION).keySet().forEach(potion ->
                    add(new ObjectObjectImmutablePair<>(potion + "_effect", "Toggle the " + cleanup(potion) + " potion effect being able to be applied to the item.")));
        }});
        itemData.put("other", new ObjectArrayList<>() {{
            add(new ObjectObjectImmutablePair<>("can_use", "Toggle being able to use the item for its purpose."));
            add(new ObjectObjectImmutablePair<>("durability", "Control the durability of the item."));
            add(new ObjectObjectImmutablePair<>("burns", "Toggle the item being able to burn in fire or lava."));
            add(new ObjectObjectImmutablePair<>("can_spam", "Toggle being able to spam the bow/crossbow"));
            add(new ObjectObjectImmutablePair<>("nutrition", "Control the nutrition of the item."));
            add(new ObjectObjectImmutablePair<>("saturation", "Control the saturation of the item."));
            add(new ObjectObjectImmutablePair<>("dispenser_interaction", "Toggle the item having a special interaction with a dispenser."));
        }});

        server.getAdvancements().getAllAdvancements()
                .stream().map(a -> a.getId().toString()).filter(a -> !a.contains("recipe")).forEach(advancement ->
                        otherData.put(advancement, "Toggle the " + cleanup(advancement) + " advancement being obtainable."));
        new Commands(Commands.CommandSelection.ALL, Commands.createValidationContext(VanillaRegistries.createLookup()))
                .getDispatcher().getRoot().getChildren().stream().map(commandNode -> "/" + commandNode.getName()).forEach(command ->
                        otherData.put(command, "Toggle the /" + cleanup(command) + " command being usable."));
        registryAccess.registryOrThrow(Registries.STRUCTURE).keySet().forEach(structure ->
                otherData.put(structure.toString(), "Toggle the " + cleanup(structure) + " structure being able to be generated."));
        registryAccess.registryOrThrow(Registries.PLACED_FEATURE).keySet().forEach(placedFeature ->
                otherData.put(placedFeature.toString(), "Toggle the " + cleanup(placedFeature) + " feature being able to be generated."));
        BuiltInRegistries.FEATURE.keySet().forEach(feature ->
                otherData.put(feature.toString(), "Toggle the " + cleanup(feature) + " feature being able to be generated."));
        registryAccess.registryOrThrow(Registries.BIOME).keySet().forEach(biome ->
                otherData.put(biome.toString(), "Toggle the " + cleanup(biome) + " biome being able to be generated."));

        intRowMaximums.put("nutrition", 20);
        doubleRowMaximums.put("saturation", 9.9);
    }

    public static void handleDatabase() {
        String path = server.getWorldPath(LevelResource.ROOT) + "/vanilla_disable.db";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + path);
            statement = connection.createStatement();

            String MINECRAFT_VERSION = server.getServerVersion();
            Optional<ModContainer> MOD = FabricLoader.getInstance().getModContainer("vanilla_disable");
            String MOD_VERSION = MOD.orElseThrow().getMetadata().getVersion().toString();

            Path pPath = Paths.get(path);
            if (!Files.exists(pPath) || Files.size(pPath) == 0) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS meta (id STRING NOT NULL, version STRING);");
                statement.executeUpdate("INSERT INTO meta (id, version) VALUES ('minecraft_version', '" + MINECRAFT_VERSION + "');");
                statement.executeUpdate("INSERT INTO meta (id, version) VALUES ('mod_version', '" + MOD_VERSION + "');");

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS entities(id STRING NOT NULL, " +
                        cols.get("entities").entrySet().stream().map(entry -> "`" + entry.getKey() + "` " + entry.getValue())
                                .collect(Collectors.joining(", ")) + ");");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS blocks(id STRING NOT NULL, " +
                        cols.get("blocks").entrySet().stream().map(entry -> "`" + entry.getKey() + "` " + entry.getValue())
                                .collect(Collectors.joining(", ")) + ");");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS items(id STRING NOT NULL, " +
                        cols.get("items").entrySet().stream().map(entry -> "`" + entry.getKey() + "` " + entry.getValue())
                                .collect(Collectors.joining(", ")) + ");");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS others(id STRING NOT NULL, enabled BOOLEAN);");

                entities.forEach((key, value) -> {
                    try {
                        statement.executeUpdate("INSERT INTO entities (id, `" + String.join("`, `", value.left()) + "`) VALUES ('" + key + "', " + String.join(", ", value.right()) + ");");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                blocks.forEach((key, value) -> {
                    try {
                        statement.executeUpdate("INSERT INTO blocks (id, `" + String.join("`, `", value.left()) + "`) VALUES ('" + key + "', " + String.join(", ", value.right()) + ");");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                items.forEach((key, value) -> {
                    try {
                        statement.executeUpdate("INSERT INTO items (id, `" + String.join("`, `", value.left()) + "`) VALUES ('" + key + "', " + String.join(", ", value.right()) + ");");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                others.forEach((key) -> {
                    try {
                        statement.executeUpdate("INSERT INTO others (id, enabled) VALUES ('" + key + "', true);");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else {
                ObjectList<String> updated_entity_cols = new ObjectArrayList<>();
                ObjectList<String> updated_block_cols = new ObjectArrayList<>();
                ObjectList<String> updated_item_cols = new ObjectArrayList<>();

                ResultSet resultSet = statement.executeQuery("SELECT version FROM meta;");
                resultSet.next();
                String mc_ver = resultSet.getString("version");
                resultSet.next();
                String mod_ver = resultSet.getString("version");
                if (!mc_ver.equals(MINECRAFT_VERSION) || !mod_ver.equals(MOD_VERSION)) {
                    cols.get("entities").forEach((col, type) -> {
                        try {
                            statement.executeUpdate("ALTER TABLE entities ADD COLUMN `" + col + "` " + type + ";");
                            updated_entity_cols.add(col);
                        } catch (SQLException ignored) {
                        }
                    });
                    cols.get("blocks").forEach((col, type) -> {
                        try {
                            statement.executeUpdate("ALTER TABLE blocks ADD COLUMN `" + col + "` " + type + ";");
                            updated_block_cols.add(col);
                        } catch (SQLException ignored) {
                        }
                    });
                    cols.get("items").forEach((col, type) -> {
                        try {
                            statement.executeUpdate("ALTER TABLE items ADD COLUMN `" + col + "` " + type + ";");
                            updated_item_cols.add(col);
                        } catch (SQLException ignored) {
                        }
                    });

                    entities.forEach((key, value) -> {
                        try {
                            statement.executeUpdate("INSERT INTO entities (id, `" + String.join("`, `", value.left()) + "`) SELECT '" + key + "', " + String.join(", ", value.right()) + " WHERE NOT EXISTS(SELECT 1 FROM entities WHERE id = '" + key + "');");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    blocks.forEach((key, value) -> {
                        try {
                            statement.executeUpdate("INSERT INTO blocks (id, `" + String.join("`, `", value.left()) + "`) SELECT '" + key + "', " + String.join(", ", value.right()) + " WHERE NOT EXISTS(SELECT 1 FROM blocks WHERE id = '" + key + "');");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    items.forEach((key, value) -> {
                        try {
                            statement.executeUpdate("INSERT INTO items (id, `" + String.join("`, `", value.left()) + "`) SELECT '" + key + "', " + String.join(", ", value.right()) + " WHERE NOT EXISTS(SELECT 1 FROM items WHERE id = '" + key + "');");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    others.forEach((key) -> {
                        try {
                            statement.executeUpdate("INSERT INTO others (id, enabled) SELECT '" + key + "', true WHERE NOT EXISTS(SELECT 1 FROM others WHERE id = '" + key + "');");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    entities.forEach((key, value) -> updated_entity_cols.forEach((col) -> {
                        try {
                            statement.executeUpdate("UPDATE entities SET `" + col + "` = " + value.right().get(value.left().indexOf(col)) + " WHERE id = '" + key + "';");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (IndexOutOfBoundsException ignored) {
                        }
                    }));
                    blocks.forEach((key, value) -> updated_block_cols.forEach((col) -> {
                        try {
                            statement.executeUpdate("UPDATE blocks SET `" + col + "` = " + value.right().get(value.left().indexOf(col)) + " WHERE id = '" + key + "';");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (IndexOutOfBoundsException ignored) {
                        }
                    }));
                    items.forEach((key, value) -> updated_item_cols.forEach((col) -> {
                        try {
                            statement.executeUpdate("UPDATE items SET `" + col + "` = " + value.right().get(value.left().indexOf(col)) + " WHERE id = '" + key + "';");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (IndexOutOfBoundsException ignored) {
                        }
                    }));

                    statement.executeUpdate("UPDATE meta SET version = '" + MINECRAFT_VERSION + "' WHERE id = 'minecraft_version';");
                    statement.executeUpdate("UPDATE meta SET version = '" + MOD_VERSION + "' WHERE id = 'mod_version';");
                }
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setValue(String table, String row, String column, String value) {
        try {
            statement.executeUpdate("UPDATE " + table + " SET `" + column + "` = " + value + " WHERE id = '" + row + "';");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean getBoolean(String table, String row, String column) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT `" + column + "` FROM " + table + " WHERE id = '" + row + "';");
            resultSet.next();
            return resultSet.getBoolean(column);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getInt(String table, String row, String column) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT `" + column + "` FROM " + table + " WHERE id = '" + row + "';");
            resultSet.next();
            return resultSet.getInt(column);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static double getDouble(String table, String row, String column) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT `" + column + "` FROM " + table + " WHERE id = '" + row + "';");
            resultSet.next();
            return resultSet.getDouble(column);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void forceUpdateDB() {
        try {
            statement.executeUpdate("UPDATE meta SET version = 'FORCE_UPDATE'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void resetDBMeta() {
        try {
            String MINECRAFT_VERSION = server.getServerVersion();
            Optional<ModContainer> MOD = FabricLoader.getInstance().getModContainer("vanilla_disable");
            String MOD_VERSION = MOD.orElseThrow().getMetadata().getVersion().toString();
            statement.executeUpdate("UPDATE meta SET version = '" + MINECRAFT_VERSION + "' WHERE id = 'minecraft_version';");
            statement.executeUpdate("UPDATE meta SET version = '" + MOD_VERSION + "' WHERE id = 'mod_version';");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
