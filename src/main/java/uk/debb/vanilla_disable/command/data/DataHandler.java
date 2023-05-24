package uk.debb.vanilla_disable.command.data;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.*;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.advancements.AdvancementList;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.packs.VanillaHusbandryAdvancements;
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

public class DataHandler {
    private static Connection connection;
    private static Statement statement;

    private static final Object2ObjectMap<String, ObjectList<String>> cols = new Object2ObjectOpenHashMap<>();
    private static final Object2ObjectMap<String, Pair<ObjectList<String>, ObjectList<String>>> entities = new Object2ObjectOpenHashMap<>();
    private static final Object2ObjectMap<String, Pair<ObjectList<String>, ObjectList<String>>> blocks = new Object2ObjectOpenHashMap<>();
    private static final Object2ObjectMap<String, Pair<ObjectList<String>, ObjectList<String>>> items = new Object2ObjectOpenHashMap<>();
    private static final ObjectList<String> others = new ObjectArrayList<>();

    private static String processData(Object data) {
        String truncated = data.toString().replace("minecraft:", "").replace("{", "").replace("}", "").replace("Block", "");
        if (truncated.contains(".")) {
            return truncated.split("\\.")[2];
        }
        return truncated;
    }

    private static void populate(MinecraftServer server) {
        RegistryAccess registryAccess = server.registryAccess();
        cols.put("entities", new ObjectArrayList<>() {{
            add("can_be_on_fire BOOLEAN");
            add("can_sprint BOOLEAN");
            add("can_crouch BOOLEAN");
            add("can_swim BOOLEAN");
            add("can_jump BOOLEAN");
            add("can_be_invisible BOOLEAN");
            add("flying_speed REAL");

            add("item_stats BOOLEAN");
            add("entity_stats BOOLEAN");
            add("time_stats BOOLEAN");
            add("distance_stats BOOLEAN");
            add("damage_stats BOOLEAN");
            add("block_gui_interaction_stats BOOLEAN");
            add("block_general_interaction_stats BOOLEAN");
            add("general_stats BOOLEAN");

            registryAccess.registryOrThrow(Registries.DAMAGE_TYPE).keySet().forEach(damageType ->
                    add(processData(damageType) + "_damage BOOLEAN"));

            add("fireball_knockback BOOLEAN");
            add("wither_skull_knockback BOOLEAN");
            add("dragon_knockback BOOLEAN");
            add("arrow_knockback BOOLEAN");
            add("trident_knockback BOOLEAN");
            add("llama_spit_knockback BOOLEAN");
            add("shulker_bullet_knockback BOOLEAN");
            add("mob_attack_knockback BOOLEAN");
            add("player_attack_knockback BOOLEAN");
            add("explosion_knockback BOOLEAN");

            registryAccess.registryOrThrow(Registries.MOB_EFFECT).keySet().forEach(mobEffect ->
                    add(processData(mobEffect) + "_effect BOOLEAN"));

            add("anvil_death BOOLEAN");
            add("cactus_death BOOLEAN");
            add("cramming_death BOOLEAN");
            add("dragon_breath_death BOOLEAN");
            add("drowning_death BOOLEAN");
            add("explosion_death BOOLEAN");
            add("falling_block_death BOOLEAN");
            add("falling_death BOOLEAN");
            add("falling_stalactite_death BOOLEAN");
            add("fly_into_wall_death BOOLEAN");
            add("freezing_death BOOLEAN");
            add("hot_floor_death BOOLEAN");
            add("in_fire_death BOOLEAN");
            add("in_wall_death BOOLEAN");
            add("lava_death BOOLEAN");
            add("lightning_bolt_death BOOLEAN");
            add("magic_death BOOLEAN");
            add("mob_death BOOLEAN");
            add("on_fire_death BOOLEAN");
            add("out_of_world_death BOOLEAN");
            add("player_death BOOLEAN");
            add("sonic_boom_death BOOLEAN");
            add("stalagmite_death BOOLEAN");
            add("starvation_death BOOLEAN");
            add("stinging_death BOOLEAN");
            add("sweet_berry_bush_death BOOLEAN");
            add("thorns_death BOOLEAN");
            add("wither_death BOOLEAN");

            registryAccess.registryOrThrow(Registries.PAINTING_VARIANT).keySet().forEach(painting ->
                    add(processData(painting) + "_painting BOOLEAN"));

            registryAccess.registryOrThrow(Registries.VILLAGER_PROFESSION).keySet().forEach(profession ->
                    add(processData(profession) + "_profession BOOLEAN"));
            registryAccess.registryOrThrow(Registries.VILLAGER_TYPE).keySet().forEach(type ->
                    add(processData(type) + "_type BOOLEAN"));

            add("can_despawn BOOLEAN");
            add("despawn_time INTEGER");
            add("min_despawn_distance INTEGER");
            add("max_despawn_distance INTEGER");
            add("mobcap INTEGER");

            add("can_spawn BOOLEAN");
            add("spawn_egg BOOLEAN");
            add("spawner BOOLEAN");
            add("can_breed BOOLEAN");
            add("can_exist BOOLEAN");
            add("can_be_cured BOOLEAN");
            add("can_be_converted_to BOOLEAN");
            add("burns_in_sunlight BOOLEAN");
            add("spawned_by_villagers BOOLEAN");
            add("can_drop_xp BOOLEAN");
            add("ai BOOLEAN");
            add("can_trade BOOLEAN");
            add("daily_restocks INTEGER");
        }});
        cols.put("blocks", new ObjectArrayList<>() {{
            add("can_place_in_overworld BOOLEAN");
            add("can_place_in_nether BOOLEAN");
            add("can_place_in_end BOOLEAN");
            add("can_break BOOLEAN");
            add("can_interact BOOLEAN");
            add("works BOOLEAN");
            add("friction_factor REAL");
            add("speed_factor REAL");
            add("jump_factor REAL");
            add("can_be_filled_by_dripstone BOOLEAN");
            add("fluid_reaches_far BOOLEAN");
            add("fluid_reaches_far_in_nether BOOLEAN");
            add("fluid_speed INTEGER");
            add("fluid_speed_in_nether INTEGER");
            add("redstone_delay INTEGER");
            add("redstone_duration INTEGER");
            add("can_drop_xp INTEGER");
            add("dispenser_interaction BOOLEAN");
            add("can_fall BOOLEAN");
        }});
        cols.put("items", new ObjectArrayList<>() {{
            add("can_use BOOLEAN");
            add("durability INTEGER");
            add("burns BOOLEAN");
            add("can_spam BOOLEAN");
            add("nutrition REAL");
            add("saturation REAL");

            registryAccess.registryOrThrow(Registries.ENCHANTMENT).keySet().forEach(enchantment ->
                    add(processData(enchantment) + "_enchantment BOOLEAN"));

            add("boot_enchantment_conflicts BOOLEAN");
            add("bow_enchantment_conflicts BOOLEAN");
            add("crossbow_enchantment_conflicts BOOLEAN");
            add("damage_enchantment_conflicts BOOLEAN");
            add("mining_enchantment_conflicts BOOLEAN");
            add("protection_enchantment_conflicts BOOLEAN");
            add("trident_enchantment_conflicts BOOLEAN");

            registryAccess.registryOrThrow(Registries.POTION).keySet().forEach(potion ->
                    add(processData(potion) + "_effect BOOLEAN"));

            add("dispenser_interaction BOOLEAN");
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
                    entityType.equals(EntityType.IRON_GOLEM) || entityType.equals(EntityType.SNOW_GOLEM)) {
                registryAccess.registryOrThrow(Registries.DAMAGE_TYPE).keySet().forEach(damageType -> {
                    col.add(processData(damageType) + "_damage");
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
                    col.add(processData(mobEffect) + "_effect");
                    val.add("true");
                });

                col.add("anvil_death");
                val.add("true");
                col.add("cactus_death");
                val.add("true");
                col.add("cramming_death");
                val.add("true");
                col.add("dragon_breath_death");
                val.add("true");
                col.add("drowning_death");
                val.add("true");
                col.add("explosion_death");
                val.add("true");
                col.add("falling_block_death");
                val.add("true");
                col.add("falling_death");
                val.add("true");
                col.add("falling_stalactite_death");
                val.add("true");
                col.add("fly_into_wall_death");
                val.add("true");
                col.add("freezing_death");
                val.add("true");
                col.add("hot_floor_death");
                val.add("true");
                col.add("in_fire_death");
                val.add("true");
                col.add("in_wall_death");
                val.add("true");
                col.add("lava_death");
                val.add("true");
                col.add("lightning_bolt_death");
                val.add("true");
                col.add("magic_death");
                val.add("true");
                col.add("mob_death");
                val.add("true");
                col.add("on_fire_death");
                val.add("true");
                col.add("out_of_world_death");
                val.add("true");
                col.add("player_death");
                val.add("true");
                col.add("sonic_boom_death");
                val.add("true");
                col.add("stalagmite_death");
                val.add("true");
                col.add("starvation_death");
                val.add("true");
                col.add("stinging_death");
                val.add("true");
                col.add("sweet_berry_bush_death");
                val.add("true");
                col.add("thorns_death");
                val.add("true");
                col.add("wither_death");
                val.add("true");

                col.add("can_despawn");
                val.add("true");
                col.add("min_despawn_distance");
                val.add("32");

                switch (entityType.getCategory()) {
                    case MONSTER -> {
                        col.add("max_despawn_distance");
                        val.add("128");
                        col.add("mobcap");
                        val.add("70");
                    }
                    case CREATURE -> {
                        col.add("max_despawn_distance");
                        val.add("128");
                        col.add("mobcap");
                        val.add("10");
                    }
                    case AMBIENT -> {
                        col.add("max_despawn_distance");
                        val.add("128");
                        col.add("mobcap");
                        val.add("15");
                    }
                    case AXOLOTLS, UNDERGROUND_WATER_CREATURE, WATER_CREATURE -> {
                        col.add("max_despawn_distance");
                        val.add("128");
                        col.add("mobcap");
                        val.add("5");
                    }
                    case WATER_AMBIENT -> {
                        col.add("max_despawn_distance");
                        val.add("64");
                        col.add("mobcap");
                        val.add("20");
                    }
                }

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
                    col.add(processData(painting) + "_painting");
                    val.add("true");
                });
            }

            if (entityType.equals(EntityType.VILLAGER)) {
                BuiltInRegistries.VILLAGER_PROFESSION.keySet().forEach(profession -> {
                    col.add(processData(profession) + "_profession");
                    val.add("true");
                });
                BuiltInRegistries.VILLAGER_TYPE.keySet().forEach(type -> {
                    col.add(processData(type) + "_type");
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

            entities.put(processData(entityType), new ObjectObjectImmutablePair<>(col, val));
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

            if (block.toString().equals(Blocks.SOUL_SAND.toString()) || block.toString().equals(Blocks.HONEY_BLOCK.toString())) {
                col.add("speed_factor");
                val.add("0.4");
            } else {
                col.add("speed_factor ");
                val.add("1.0");
            }

            if (block.toString().equals(Blocks.HONEY_BLOCK.toString())) {
                col.add("jump_factor");
                val.add("0.5");
            } else {
                col.add("jump_factor");
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

            if (block.toString().equals(Blocks.REPEATER.toString()) || block.toString().equals(Blocks.COMPARATOR.toString()) || block.toString().equals(Blocks.OBSERVER.toString())) {
                col.add("redstone_delay");
                val.add("2");
            }

            if (block.toString().equals(Blocks.OBSERVER.toString())) {
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

            blocks.put(processData(block), new ObjectObjectImmutablePair<>(col, val));
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
                            col.add(processData(enchantment.getDescriptionId()) + "_enchantment");
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
                    BuiltInRegistries.POTION.forEach((potion) -> {
                        col.add(processData(BuiltInRegistries.POTION.getKey(potion)) + "_effect");
                        val.add("true");
                    });
                }
            }

            if (DispenserBlock.DISPENSER_REGISTRY.containsKey(item)) {
                col.add("dispenser_interaction");
                val.add("true");
            }

            items.put(processData(item), new ObjectObjectImmutablePair<>(col, val));
        });

        others.addAll(new AdvancementList().getAllAdvancements()
                .stream().map(a -> a.getId().toString()).toList());
        others.addAll(new CommandDispatcher<CommandSourceStack>().getRoot().getChildren()
                .stream().map(CommandNode::getName).toList());
        others.addAll(registryAccess.registryOrThrow(Registries.STRUCTURE_TYPE).keySet()
                .stream().map(DataHandler::processData).toList());
        others.addAll(registryAccess.registryOrThrow(Registries.PLACED_FEATURE).keySet()
                .stream().map(DataHandler::processData).toList());
        others.addAll(registryAccess.registryOrThrow(Registries.FEATURE).keySet()
                .stream().map(DataHandler::processData).toList());
        others.addAll(registryAccess.registryOrThrow(Registries.BIOME).keySet()
                .stream().map(DataHandler::processData).toList());
    }

    public static void handleDatabase(MinecraftServer server) {
        String path = server.getWorldPath(LevelResource.ROOT) + "/vanilla_disable.db";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + path);
            statement = connection.createStatement();

            String MINECRAFT_VERSION = server.getServerVersion();
            Optional<ModContainer> MOD = FabricLoader.getInstance().getModContainer("vanilla_disable");
            String MOD_VERSION = MOD.orElseThrow().getMetadata().getVersion().toString();

            Path pPath = Paths.get(path);
            if (!Files.exists(pPath) || Files.size(pPath) == 0) {
                populate(server);

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS meta (id STRING NOT NULL, version STRING);");
                statement.executeUpdate("INSERT INTO meta (id, version) VALUES ('minecraft_version', '" + MINECRAFT_VERSION + "');");
                statement.executeUpdate("INSERT INTO meta (id, version) VALUES ('mod_version', '" + MOD_VERSION + "');");

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS entities(id STRING NOT NULL, " + String.join(", ", cols.get("entities")) + ");");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS blocks(id STRING NOT NULL, " + String.join(", ", cols.get("blocks")) + ");");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS items(id STRING NOT NULL, " + String.join(", ", cols.get("items")) + ");");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS others(id STRING NOT NULL, enabled BOOLEAN);");

                entities.forEach((key, value) -> {
                    try {
                        statement.executeUpdate("INSERT INTO entities (id, " + String.join(", ", value.left()) + ") VALUES ('" + key + "', " + String.join(", ", value.right()) + ");");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                blocks.forEach((key, value) -> {
                    try {
                        statement.executeUpdate("INSERT INTO blocks (id, " + String.join(", ", value.left()) + ") VALUES ('" + key + "', " + String.join(", ", value.right()) + ");");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                items.forEach((key, value) -> {
                    try {
                        statement.executeUpdate("INSERT INTO items (id, " + String.join(", ", value.left()) + ") VALUES ('" + key + "', " + String.join(", ", value.right()) + ");");
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
                populate(server);
                ObjectList<String> updated_entity_cols = new ObjectArrayList<>();
                ObjectList<String> updated_block_cols = new ObjectArrayList<>();
                ObjectList<String> updated_item_cols = new ObjectArrayList<>();

                ResultSet resultSet = statement.executeQuery("SELECT version FROM meta;");
                resultSet.next();
                String mc_ver = resultSet.getString("version");
                resultSet.next();
                String mod_ver = resultSet.getString("version");
                if (!mc_ver.equals(MINECRAFT_VERSION) || !mod_ver.equals(MOD_VERSION)) {
                    cols.get("entities").forEach((col) -> {
                        try {
                            statement.executeUpdate("ALTER TABLE entities ADD COLUMN " + col + " BOOLEAN;");
                            updated_entity_cols.add(col);
                        } catch (SQLException ignored) {
                        }
                    });
                    cols.get("blocks").forEach((col) -> {
                        try {
                            statement.executeUpdate("ALTER TABLE blocks ADD COLUMN " + col + " BOOLEAN;");
                            updated_block_cols.add(col);
                        } catch (SQLException ignored) {
                        }
                    });
                    cols.get("items").forEach((col) -> {
                        try {
                            statement.executeUpdate("ALTER TABLE items ADD COLUMN " + col + " BOOLEAN;");
                            updated_item_cols.add(col);
                        } catch (SQLException ignored) {
                        }
                    });

                    entities.forEach((key, value) -> {
                        try {
                            statement.executeUpdate("INSERT INTO entities (id, " + String.join(", ", value.left()) + ") SELECT '" + key + "', " + String.join(", ", value.right()) + " WHERE NOT EXISTS(SELECT 1 FROM entities WHERE id = '" + key + "');");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    blocks.forEach((key, value) -> {
                        try {
                            statement.executeUpdate("INSERT INTO blocks (id, " + String.join(", ", value.left()) + ") SELECT '" + key + "', " + String.join(", ", value.right()) + " WHERE NOT EXISTS(SELECT 1 FROM blocks WHERE id = '" + key + "');");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    items.forEach((key, value) -> {
                        try {
                            statement.executeUpdate("INSERT INTO items (id, " + String.join(", ", value.left()) + ") SELECT '" + key + "', " + String.join(", ", value.right()) + " WHERE NOT EXISTS(SELECT 1 FROM items WHERE id = '" + key + "');");
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
                            statement.executeUpdate("UPDATE entities SET " + col.split(" ")[0] + " = " + value.right().get(value.left().indexOf(col.split(" ")[0])) + " WHERE id = '" + key + "';");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (IndexOutOfBoundsException ignored) {
                        }
                    }));
                    blocks.forEach((key, value) -> updated_block_cols.forEach((col) -> {
                        try {
                            statement.executeUpdate("UPDATE blocks SET " + col + " = " + value.right().get(value.left().indexOf(col)) + " WHERE id = '" + key + "';");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (IndexOutOfBoundsException ignored) {
                        }
                    }));
                    items.forEach((key, value) -> updated_item_cols.forEach((col) -> {
                        try {
                            statement.executeUpdate("UPDATE items SET " + col + " = " + value.right().get(value.left().indexOf(col)) + " WHERE id = '" + key + "';");
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

    public static void setValue(String table, String row, String columns, String value) {
        try {
            statement.executeUpdate("UPDATE " + table + " SET " + columns + " = " + value + " WHERE id = '" + row + "';");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean getBoolean(String table, String row, String columns) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT " + columns + " FROM " + table + " WHERE id = '" + row + "';");
            resultSet.next();
            return resultSet.getBoolean(columns);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static int getInt(String table, String row, String columns) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT " + columns + " FROM " + table + " WHERE id = '" + row + "';");
            resultSet.next();
            return resultSet.getInt(columns);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static double getDouble(String table, String row, String columns) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT " + columns + " FROM " + table + " WHERE id = '" + row + "';");
            resultSet.next();
            return resultSet.getDouble(columns);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
