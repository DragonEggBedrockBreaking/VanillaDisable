package uk.debb.vanilla_disable.command.data;

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
    public static final Object2ObjectMap<String, Object2ObjectMap<String, String>> entities = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, String>> blocks = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, String>> items = new Object2ObjectOpenHashMap<>();
    public static final ObjectSet<String> others = new ObjectArraySet<>();

    public static final Object2ObjectMap<String, Object2ObjectMap<String, String>> entityData = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, String>> blockData = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, Object2ObjectMap<String, String>> itemData = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectMap<String, String> otherData = new Object2ObjectOpenHashMap<>();

    public static final Object2IntMap<String> intRowMaximums = new Object2IntArrayMap<>();
    public static final Object2DoubleMap<String> doubleRowMaximums = new Object2DoubleArrayMap<>();

    public static MinecraftServer server;
    private static Connection connection;
    private static Statement statement;
    public static boolean populationDone = false;

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

        BuiltInRegistries.ENTITY_TYPE.forEach((entityType) ->
                entities.put(BuiltInRegistries.ENTITY_TYPE.getKey(entityType).toString(), new Object2ObjectOpenHashMap<>() {{
            if (entityType.equals(EntityType.PLAYER)) {
                put("can_be_on_fire", "true");
                put("can_sprint", "true");
                put("can_crouch", "true");
                put("can_swim", "true");
                put("can_jump", "true");
                put("can_be_invisible", "true");
                put("flying_speed", "0.05");

                put("item_stats", "true");
                put("entity_stats", "true");
                put("time_stats", "true");
                put("distance_stats", "true");
                put("damage_stats", "true");
                put("block_gui_interaction_stats", "true");
                put("block_general_interaction_stats", "true");
                put("general_stats", "true");
            }

            if (!entityType.getCategory().equals(MobCategory.MISC) || entityType.equals(EntityType.VILLAGER) ||
                    entityType.equals(EntityType.IRON_GOLEM) || entityType.equals(EntityType.SNOW_GOLEM) ||
                    entityType.equals(EntityType.PLAYER)) {
                registryAccess.registryOrThrow(Registries.DAMAGE_TYPE).keySet().forEach(damageType -> {
                    put(damageType + "_damage", "true");
                    put(damageType + "_death", "true");
                });

                put("fireball_knockback", "true");
                put("wither_skull_knockback", "true");
                put("dragon_knockback", "true");
                put("arrow_knockback", "true");
                put("trident_knockback", "true");
                put("llama_spit_knockback", "true");
                put("shulker_bullet_knockback", "true");
                put("mob_attack_knockback", "true");
                put("player_attack_knockback", "true");
                put("explosion_knockback", "true");

                registryAccess.registryOrThrow(Registries.MOB_EFFECT).keySet().forEach(mobEffect ->
                        put(mobEffect + "_effect", "true"));

                put("can_despawn", "true");

                put("can_spawn", "true");
                put("spawn_egg", "true");
                put("spawner", "true");
                put("can_drop_xp", "true");
                put("ai", "true");
            }

            if (entityType.equals(EntityType.PAINTING)) {
                BuiltInRegistries.PAINTING_VARIANT.keySet().forEach(painting ->
                        put(painting + "_painting", "true"));
            }

            if (entityType.equals(EntityType.VILLAGER)) {
                BuiltInRegistries.VILLAGER_PROFESSION.keySet().forEach(profession ->
                        put(profession + "_profession", "true"));
                BuiltInRegistries.VILLAGER_TYPE.keySet().forEach(type -> put(type + "_type", "true"));
            }

            if (VanillaHusbandryAdvancements.BREEDABLE_ANIMALS.contains(entityType) ||
                    VanillaHusbandryAdvancements.INDIRECTLY_BREEDABLE_ANIMALS.contains(entityType) ||
                    entityType.equals(EntityType.VILLAGER)) {
                put("can_breed", "true");
            }

            if (entityType.equals(EntityType.ZOMBIE_VILLAGER)) {
                put("can_be_cured", "true");
            }

            if (entityType.equals(EntityType.ZOMBIE_VILLAGER) || entityType.equals(EntityType.WITCH) ||
                    entityType.equals(EntityType.ZOMBIFIED_PIGLIN) || entityType.equals(EntityType.ZOGLIN) ||
                    entityType.equals(EntityType.DROWNED) || entityType.equals(EntityType.STRAY)) {
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
                put("daily_restocks", "true");
            }

            if (entityType.equals(EntityType.ITEM)) {
                put("despawn_time", "6000");
            }

            put("can_exist", "true");
        }}));

        BuiltInRegistries.BLOCK.forEach((block) ->
                blocks.put(BuiltInRegistries.BLOCK.getKey(block).toString(), new Object2ObjectOpenHashMap<>() {{
            put("can_place_in_overworld", "true");
            put("can_place_in_nether", "true");
            put("can_place_in_end", "true");
            put("can_break", "true");
            put("can_interact", "true");
            put("works", "true");

            if (block.toString().equals(Blocks.ICE.toString()) || block.toString().equals(Blocks.PACKED_ICE.toString()) ||
                    block.toString().equals(Blocks.BLUE_ICE.toString())) {
                put("friction_factor", "0.98");
            } else if (block.toString().equals(Blocks.SLIME_BLOCK.toString())) {
                put("friction_factor", "0.8");
            } else {
                put("friction_factor", "0.6");
            }

            boolean isHoneyBlock = block.toString().equals(Blocks.HONEY_BLOCK.toString());
            if (block.toString().equals(Blocks.SOUL_SAND.toString()) || isHoneyBlock) {
                put("speed_factor", "0.4");
            } else {
                put("speed_factor", "1.0");
            }

            if (isHoneyBlock) {
                put("jump_factor", "0.5");
            } else {
                put("jump_factor", "1.0");
            }

            if (block.toString().equals(Blocks.CAULDRON.toString())) {
                put("can_be_filled_by_dripstone", "true");
            }

            if (block.toString().equals(Blocks.WATER.toString())) {
                put("fluid_reaches_far", "true");
                put("fluid_reaches_far_in_nether", "true");
                put("fluid_speed", "5");
                put("fluid_speed_in_nether", "5");
            }

            if (block.toString().equals(Blocks.LAVA.toString())) {
                put("fluid_reaches_far", "false");
                put("fluid_reaches_far_in_nether", "true");
                put("fluid_speed", "30");
                put("fluid_speed_in_nether", "10");
            }

            boolean isObserver = block.toString().equals(Blocks.OBSERVER.toString());
            if (block.toString().equals(Blocks.REPEATER.toString()) || block.toString().equals(Blocks.COMPARATOR.toString()) || isObserver) {
                put("redstone_delay", "2");
            }

            if (isObserver) {
                put("redstone_duration", "2");
            }

            if (block.toString().endsWith("_ore") || block.toString().equals(Blocks.SPAWNER.toString()) ||
                    block.toString().equals(Blocks.SCULK_SENSOR.toString()) || block.toString().equals(Blocks.SCULK_CATALYST.toString()) ||
                    block.toString().equals(Blocks.SCULK_SHRIEKER.toString()) || block.toString().equals(Blocks.SCULK.toString())) {
                put("can_drop_xp", "true");
            }

            if (DispenserBlock.DISPENSER_REGISTRY.containsKey(block.asItem())) {
                put("dispenser_interaction", "true");
            }

            if (block instanceof FallingBlock) {
                put("can_fall", "true");
            }
        }}));

        BuiltInRegistries.ITEM.forEach((item) -> {
            if (BuiltInRegistries.BLOCK.stream().map(Block::asItem).toList().contains(item)) return;

            items.put(BuiltInRegistries.ITEM.getKey(item).toString(), new Object2ObjectOpenHashMap<>() {{
                if (!BuiltInRegistries.BLOCK.stream().map(Block::toString).toList().contains(item.toString())) {
                    put("can_use", "true");

                    if (item.toString().contains("netherite")) {
                        put("burns", "false");
                    } else {
                        put("burns", "true");
                    }

                    if (item.equals(Items.BOW) || item.equals(Items.CROSSBOW)) {
                        put("can_spam", "true");
                    }

                    if (item.canBeDepleted()) {
                        put("durability", String.valueOf(item.getMaxDamage()));
                        BuiltInRegistries.ENCHANTMENT.forEach((enchantment) -> {
                            if (enchantment.canEnchant(new ItemStack(item))) {
                                put(BuiltInRegistries.ENCHANTMENT.getKey(enchantment) + "_enchantment", "true");
                            }
                        });
                    }

                    FoodProperties foodProperties = item.getFoodProperties();
                    if (foodProperties != null) {
                        put("nutrition", String.valueOf(foodProperties.getNutrition()));
                        put("saturation", String.valueOf(foodProperties.getSaturationModifier()));
                    }

                    if (Enchantments.FROST_WALKER.canEnchant(new ItemStack(item))) {
                        put("boot_enchantment_conflicts", "true");
                    }

                    if (Enchantments.INFINITY_ARROWS.canEnchant(new ItemStack(item))) {
                        put("bow_enchantment_conflicts", "true");
                    }

                    if (Enchantments.MULTISHOT.canEnchant(new ItemStack(item))) {
                        put("crossbow_enchantment_conflicts", "true");
                    }

                    if (Enchantments.SHARPNESS.canEnchant(new ItemStack(item))) {
                        put("damage_enchantment_conflicts", "true");
                    }

                    if (Enchantments.BLOCK_FORTUNE.canEnchant(new ItemStack(item))) {
                        put("mining_enchantment_conflicts", "true");
                    }

                    if (Enchantments.PROJECTILE_PROTECTION.canEnchant(new ItemStack(item))) {
                        put("protection_enchantment_conflicts", "true");
                    }

                    if (Enchantments.RIPTIDE.canEnchant(new ItemStack(item))) {
                        put("trident_enchantment_conflicts", "true");
                    }

                    if (item.equals(Items.POTION) || item.equals(Items.SPLASH_POTION) ||
                            item.equals(Items.LINGERING_POTION) || item.equals(Items.TIPPED_ARROW)) {
                        BuiltInRegistries.POTION.keySet().forEach((potion) -> put(potion + "_effect", "true"));
                    }
                }

                if (DispenserBlock.DISPENSER_REGISTRY.containsKey(item)) {
                    put("dispenser_interaction", "true");
                }
            }});
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

        entityData.put("stats", new Object2ObjectOpenHashMap<>() {{
            put("item_stats", "Collect statistics related to items.");
            put("entity_stats", "Collect statistics related to entities.");
            put("time_stats", "Collect statistics related to time.");
            put("distance_stats", "Collect statistics related to distance.");
            put("damage_stats", "Collect statistics related to damage.");
            put("block_gui_interaction_stats", "Collect statistics related to block GUIs.");
            put("block_general_interaction_stats", "Collect statistics related to blocks.");
            put("general_stats", "Collect general statistics.");
        }});
        entityData.put("damage", new Object2ObjectOpenHashMap<>() {{
            registryAccess.registryOrThrow(Registries.DAMAGE_TYPE).keySet().forEach(damageType ->
                    put(damageType + "_damage", "Toggles " + cleanup(damageType) + " damage affecting the mob."));
        }});
        entityData.put("knockback", new Object2ObjectOpenHashMap<>() {{
            put("fireball_knockback", "Toggles fireballs knocking back the entity.");
            put("wither_skull_knockback", "Toggles wither skulls knocking back the entity.");
            put("dragon_knockback", "Toggles the ender dragon knocking back the entity.");
            put("arrow_knockback", "Toggles arrows knocking back the entity.");
            put("trident_knockback", "Toggles tridents knocking back the entity.");
            put("llama_spit_knockback", "Toggles llama spit knocking back the entity.");
            put("shulker_bullet_knockback", "Toggles shulker bullets knocking back the entity.");
            put("mob_attack_knockback", "Toggles mob attacks knocking back the entity.");
            put("player_attack_knockback", "Toggles player attacks knocking back the entity.");
            put("explosion_knockback", "Toggles explosions knocking back the entity.");
        }});
        entityData.put("effects", new Object2ObjectOpenHashMap<>() {{
            registryAccess.registryOrThrow(Registries.MOB_EFFECT).keySet().forEach(mobEffect ->
                    put(mobEffect.toString(), "Toggles " + cleanup(mobEffect) + " affecting the mob."));
        }});
        entityData.put("death", new Object2ObjectOpenHashMap<>() {{
            registryAccess.registryOrThrow(Registries.DAMAGE_TYPE).keySet().forEach(damageType ->
                    put(damageType + "_death", "Toggles " + cleanup(damageType) + " damage being able to kill the mob."));
        }});
        entityData.put("painting", new Object2ObjectOpenHashMap<>() {{
            registryAccess.registryOrThrow(Registries.PAINTING_VARIANT).keySet().forEach(painting ->
                    put(painting + "_painting", "Toggles the " + cleanup(painting) + " design being able to show on paintings."));
        }});
        entityData.put("villager_type", new Object2ObjectOpenHashMap<>() {{
            registryAccess.registryOrThrow(Registries.VILLAGER_TYPE).keySet().forEach(villagerType ->
                    put(villagerType + "_type", "Toggles villagers being able to be of the " + cleanup(villagerType) + " type."));
        }});
        entityData.put("villager_profession", new Object2ObjectOpenHashMap<>() {{
            registryAccess.registryOrThrow(Registries.VILLAGER_PROFESSION).keySet().forEach(villagerProfession ->
                    put(villagerProfession + "_profession", "Toggles villagers being able to have the " + cleanup(villagerProfession) + " profession."));
        }});
        entityData.put("other", new Object2ObjectOpenHashMap<>() {{
            put("can_be_on_fire", "Toggle the player being able to be on fire.");
            put("can_sprint", "Toggle the player being able to sprint.");
            put("can_crouch", "Toggle the player being able to crouch.");
            put("can_swim", "Toggle the player being able to swim.");
            put("can_jump", "Toggle the player being able to jump.");
            put("can_be_invisible", "Toggle the player being able to be invisible.");
            put("flying_speed", "Control the player's flying speed.");
            put("can_despawn", "Toggle the mob being able to despawn.");
            put("despawn_time", "Controls how long it takes for an entity to despawn.");
            put("can_spawn", "Toggle the mob being able to spawn.");
            put("spawn_egg", "Toggle the mob being able to be spawned by a spawn egg.");
            put("spawner", "Toggle the mob being able to be spawned by a spawner.");
            put("can_breed", "Toggle the mob being able to breed.");
            put("can_exist", "Toggle the entity being able to exist.");
            put("can_be_cured", "Toggle the mob being able to be cured.");
            put("can_be_converted_to", "Toggle the mob being able to convert to another mob.");
            put("burns_in_sunlight", "Toggle the mob being able to burn in sunlight.");
            put("spawned_by_villagers", "Toggle the mob being able to be spawned by villagers.");
            put("can_drop_xp", "Toggle the mob being able to drop XP.");
            put("ai", "Toggle the mob's AI.");
            put("can_trade", "Toggle the mob being able to trade.");
            put("daily_restocks", "Control the number of times per day a villager restocks.");
        }});

        blockData.put("fluid", new Object2ObjectOpenHashMap<>() {{
            put("fluid_reaches_far", "Toggle whether the fluid can travel 8 blocks or only 4 in the overworld or end.");
            put("fluid_reaches_far_in_nether", "Toggle whether the fluid can travel 8 blocks or only 4 in the nether.");
            put("fluid_speed", "Control how fast the fluid flows in the overworld or end.");
            put("fluid_speed_in_nether", "Control how fast the fluid flows in the nether.");
        }});
        blockData.put("other", new Object2ObjectOpenHashMap<>() {{
            put("can_place_in_overworld", "Toggle being able to place the block in the overworld.");
            put("can_place_in_nether", "Toggle being able to place the block in the nether.");
            put("can_place_in_end", "Toggle being able to place the block in the end.");
            put("can_break", "Toggle being able to break the block.");
            put("can_interact", "Toggle being able to interact with the block.");
            put("works", "Toggle the block being able to carry out its function.");
            put("friction_factor", "Control how much friction is applied to entities on the block.");
            put("speed_factor", "Control how fast entities can travel on the block relative to others.");
            put("jump_factor", "Control how high entities can jump on the block relative to others.");
            put("can_be_filled_by_dripstone", "Toggle the block being able to be filled by dripstone.");
            put("redstone_delay", "Control the redstone delay of the block.");
            put("redstone_duration", "Control the redstone duration of the block.");
            put("can_drop_xp", "Toggle the block being able to drop XP.");
            put("dispenser_interaction", "Toggle the block having a special interaction with a dispenser.");
            put("can_fall", "Toggle the block being able to fall.");
        }});

        itemData.put("enchantment", new Object2ObjectOpenHashMap<>() {{
            registryAccess.registryOrThrow(Registries.ENCHANTMENT).keySet().forEach(enchantment ->
                    put(enchantment + "_enchantment", "Toggle the " + cleanup(enchantment) + " enchantment being able to be applied to the item."));

            put("boot_enchantment_conflicts", "Toggle the frost walker and depth strider enchantments being incompatible with each other.");
            put("bow_enchantment_conflicts", "Toggle the infinity and mending enchantments being incompatible with each other.");
            put("crossbow_enchantment_conflicts", "Toggle the multishot and piercing enchantments being incompatible with each other.");
            put("damage_enchantment_conflicts", "Toggle the sharpness, smite, and bane of arthropods enchantments being incompatible with each other.");
            put("mining_enchantment_conflicts", "Toggle the fortune and silk touch enchantments being incompatible with each other.");
            put("protection_enchantment_conflicts", "Toggle the protection, fire protection, blast protection, and projectile protection enchantments being incompatible with each other.");
            put("trident_enchantment_conflicts", "Toggle the loyalty and channeling enchantments being incompatible with the riptide enchantment.");
        }});
        itemData.put("potion", new Object2ObjectOpenHashMap<>() {{
            registryAccess.registryOrThrow(Registries.POTION).keySet().forEach(potion ->
                    put(potion + "_effect", "Toggle the " + cleanup(potion) + " potion effect being able to be applied to the item."));
        }});
        itemData.put("other", new Object2ObjectOpenHashMap<>() {{
            put("can_use", "Toggle being able to use the item for its purpose.");
            put("durability", "Control the durability of the item.");
            put("burns", "Toggle the item being able to burn in fire or lava.");
            put("can_spam", "Toggle being able to spam the bow/crossbow");
            put("nutrition", "Control the nutrition of the item.");
            put("saturation", "Control the saturation of the item.");
            put("dispenser_interaction", "Toggle the item having a special interaction with a dispenser.");
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

        populationDone = true;
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
                        statement.executeUpdate("INSERT INTO entities (id, `" + String.join("`, `", value.keySet()) + "`) VALUES ('" + key + "', " + String.join(", ", value.values()) + ");");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                blocks.forEach((key, value) -> {
                    try {
                        statement.executeUpdate("INSERT INTO blocks (id, `" + String.join("`, `", value.keySet()) + "`) VALUES ('" + key + "', " + String.join(", ", value.values()) + ");");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                items.forEach((key, value) -> {
                    try {
                        statement.executeUpdate("INSERT INTO items (id, `" + String.join("`, `", value.keySet()) + "`) VALUES ('" + key + "', " + String.join(", ", value.values()) + ");");
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
                            statement.executeUpdate("INSERT INTO entities (id, `" + String.join("`, `", value.keySet()) + "`) SELECT '" + key + "', " + String.join(", ", value.values()) + " WHERE NOT EXISTS(SELECT 1 FROM entities WHERE id = '" + key + "');");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    blocks.forEach((key, value) -> {
                        try {
                            statement.executeUpdate("INSERT INTO blocks (id, `" + String.join("`, `", value.keySet()) + "`) SELECT '" + key + "', " + String.join(", ", value.values()) + " WHERE NOT EXISTS(SELECT 1 FROM blocks WHERE id = '" + key + "');");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    items.forEach((key, value) -> {
                        try {
                            statement.executeUpdate("INSERT INTO items (id, `" + String.join("`, `", value.keySet()) + "`) SELECT '" + key + "', " + String.join(", ", value.values()) + " WHERE NOT EXISTS(SELECT 1 FROM items WHERE id = '" + key + "');");
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
                            statement.executeUpdate("UPDATE entities SET `" + col + "` = " + value.get(col) + " WHERE id = '" + key + "';");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (IndexOutOfBoundsException ignored) {
                        }
                    }));
                    blocks.forEach((key, value) -> updated_block_cols.forEach((col) -> {
                        try {
                            statement.executeUpdate("UPDATE blocks SET `" + col + "` = " + value.get(col) + " WHERE id = '" + key + "';");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (IndexOutOfBoundsException ignored) {
                        }
                    }));
                    items.forEach((key, value) -> updated_item_cols.forEach((col) -> {
                        try {
                            statement.executeUpdate("UPDATE items SET `" + col + "` = " + value.get(col) + " WHERE id = '" + key + "';");
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
