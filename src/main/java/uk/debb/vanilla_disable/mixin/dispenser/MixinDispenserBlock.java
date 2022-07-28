package uk.debb.vanilla_disable.mixin.dispenser;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.*;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(DispenserBlock.class)
public abstract class MixinDispenserBlock {
    @Unique
    private static final Object2ObjectMap<Item, GameRules.Key<GameRules.BooleanValue>> itemToGameruleMap = new Object2ObjectOpenHashMap<>();

    @Unique
    private void addOptionsToMap() {
        itemToGameruleMap.put(Items.ARROW, Gamerules.DISPENSER_FIRES_PROJECTILES);
        itemToGameruleMap.put(Items.TIPPED_ARROW, Gamerules.DISPENSER_FIRES_PROJECTILES);
        itemToGameruleMap.put(Items.SPECTRAL_ARROW, Gamerules.DISPENSER_FIRES_PROJECTILES);
        itemToGameruleMap.put(Items.EGG, Gamerules.DISPENSER_FIRES_PROJECTILES);
        itemToGameruleMap.put(Items.SNOWBALL, Gamerules.DISPENSER_FIRES_PROJECTILES);
        itemToGameruleMap.put(Items.EXPERIENCE_BOTTLE, Gamerules.DISPENSER_FIRES_PROJECTILES);
        itemToGameruleMap.put(Items.SPLASH_POTION, Gamerules.DISPENSER_FIRES_PROJECTILES);
        itemToGameruleMap.put(Items.LINGERING_POTION, Gamerules.DISPENSER_FIRES_PROJECTILES);

        for (SpawnEggItem item : SpawnEggItem.eggs()) {
            itemToGameruleMap.put(item, Gamerules.DISPENSER_SPAWNS_MOBS);
        }
        itemToGameruleMap.put(Items.ARMOR_STAND, Gamerules.DISPENSER_PLACES_ARMOUR_STANDS);
        itemToGameruleMap.put(Items.FIREWORK_ROCKET, Gamerules.DISPENSER_LAUNCHES_FIREWORKS);
        itemToGameruleMap.put(Items.FIRE_CHARGE, Gamerules.DISPENSER_LAUNCHES_FIRE_CHARGES);
        itemToGameruleMap.put(Items.FLINT_AND_STEEL, Gamerules.DISPENSER_FLINTS_AND_STEELS);
        itemToGameruleMap.put(Items.BONE_MEAL, Gamerules.DISPENSER_BONEMEALS_PLANTS);
        itemToGameruleMap.put(Items.TNT, Gamerules.DISPENSER_LIGHTS_TNT);
        itemToGameruleMap.put(Items.SHULKER_BOX, Gamerules.DISPENSER_PLACES_SHULKER_BOXES);
        for (DyeColor colour : DyeColor.values()) {
            itemToGameruleMap.put(ShulkerBoxBlock.getBlockByColor(colour).asItem(), Gamerules.DISPENSER_PLACES_SHULKER_BOXES);
        }
        itemToGameruleMap.put(Items.GLASS_BOTTLE, Gamerules.DISPENSER_FILLS_BOTTLES);
        itemToGameruleMap.put(Items.GLOWSTONE, Gamerules.DISPENSER_FILLS_RESPAWN_ANCHOR);
        itemToGameruleMap.put(Items.SHEARS, Gamerules.DISPENSER_SHEARS_SHEEP);
        itemToGameruleMap.put(Items.HONEYCOMB, Gamerules.DISPENSER_WAXES_COPPER);
        itemToGameruleMap.put(Items.POTION, Gamerules.DISPENSER_WATERS_MUD);

        itemToGameruleMap.put(Items.SADDLE, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.LEATHER_HORSE_ARMOR, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.IRON_HORSE_ARMOR, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.GOLDEN_HORSE_ARMOR, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.DIAMOND_HORSE_ARMOR, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.WHITE_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.ORANGE_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.CYAN_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.BLUE_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.BROWN_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.BLACK_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.GRAY_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.GREEN_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.LIGHT_BLUE_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.LIGHT_GRAY_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.LIME_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.MAGENTA_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.PINK_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.PURPLE_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.RED_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.YELLOW_CARPET, Gamerules.DISPENSER_EQUIPS_ARMOUR);
        itemToGameruleMap.put(Items.CHEST, Gamerules.DISPENSER_EQUIPS_ARMOUR);

        itemToGameruleMap.put(Items.OAK_BOAT, Gamerules.DISPENSER_PLACES_BOATS);
        itemToGameruleMap.put(Items.SPRUCE_BOAT, Gamerules.DISPENSER_PLACES_BOATS);
        itemToGameruleMap.put(Items.BIRCH_BOAT, Gamerules.DISPENSER_PLACES_BOATS);
        itemToGameruleMap.put(Items.JUNGLE_BOAT, Gamerules.DISPENSER_PLACES_BOATS);
        itemToGameruleMap.put(Items.DARK_OAK_BOAT, Gamerules.DISPENSER_PLACES_BOATS);
        itemToGameruleMap.put(Items.ACACIA_BOAT, Gamerules.DISPENSER_PLACES_BOATS);
        itemToGameruleMap.put(Items.MANGROVE_BOAT, Gamerules.DISPENSER_PLACES_BOATS);
        itemToGameruleMap.put(Items.OAK_CHEST_BOAT, Gamerules.DISPENSER_PLACES_BOATS);
        itemToGameruleMap.put(Items.SPRUCE_CHEST_BOAT, Gamerules.DISPENSER_PLACES_BOATS);
        itemToGameruleMap.put(Items.BIRCH_CHEST_BOAT, Gamerules.DISPENSER_PLACES_BOATS);
        itemToGameruleMap.put(Items.JUNGLE_CHEST_BOAT, Gamerules.DISPENSER_PLACES_BOATS);
        itemToGameruleMap.put(Items.DARK_OAK_CHEST_BOAT, Gamerules.DISPENSER_PLACES_BOATS);
        itemToGameruleMap.put(Items.ACACIA_CHEST_BOAT, Gamerules.DISPENSER_PLACES_BOATS);
        itemToGameruleMap.put(Items.MANGROVE_CHEST_BOAT, Gamerules.DISPENSER_PLACES_BOATS);

        itemToGameruleMap.put(Items.LAVA_BUCKET, Gamerules.DISPENSER_BUCKETS_ITEMS);
        itemToGameruleMap.put(Items.WATER_BUCKET, Gamerules.DISPENSER_BUCKETS_ITEMS);
        itemToGameruleMap.put(Items.POWDER_SNOW_BUCKET, Gamerules.DISPENSER_BUCKETS_ITEMS);
        itemToGameruleMap.put(Items.SALMON_BUCKET, Gamerules.DISPENSER_BUCKETS_ITEMS);
        itemToGameruleMap.put(Items.COD_BUCKET, Gamerules.DISPENSER_BUCKETS_ITEMS);
        itemToGameruleMap.put(Items.PUFFERFISH_BUCKET, Gamerules.DISPENSER_BUCKETS_ITEMS);
        itemToGameruleMap.put(Items.TROPICAL_FISH_BUCKET, Gamerules.DISPENSER_BUCKETS_ITEMS);
        itemToGameruleMap.put(Items.AXOLOTL_BUCKET, Gamerules.DISPENSER_BUCKETS_ITEMS);
        itemToGameruleMap.put(Items.TADPOLE_BUCKET, Gamerules.DISPENSER_BUCKETS_ITEMS);
        itemToGameruleMap.put(Items.BUCKET, Gamerules.DISPENSER_BUCKETS_ITEMS);

        itemToGameruleMap.put(Items.CREEPER_HEAD, Gamerules.DISPENSER_PLACES_HEADS);
        itemToGameruleMap.put(Items.ZOMBIE_HEAD, Gamerules.DISPENSER_PLACES_HEADS);
        itemToGameruleMap.put(Items.DRAGON_HEAD, Gamerules.DISPENSER_PLACES_HEADS);
        itemToGameruleMap.put(Items.SKELETON_SKULL, Gamerules.DISPENSER_PLACES_HEADS);
        itemToGameruleMap.put(Items.PLAYER_HEAD, Gamerules.DISPENSER_PLACES_HEADS);
        itemToGameruleMap.put(Items.WITHER_SKELETON_SKULL, Gamerules.DISPENSER_PLACES_HEADS);
        itemToGameruleMap.put(Items.CARVED_PUMPKIN, Gamerules.DISPENSER_PLACES_HEADS);
    }

    @ModifyReturnValue(method = "getDispenseMethod", at = @At("RETURN"))
    private DispenseItemBehavior modifyDispenseMethod(DispenseItemBehavior original, ItemStack itemStack) {
        if (itemToGameruleMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = itemToGameruleMap.get(itemStack.getItem());
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            return new DefaultDispenseItemBehavior();
        }
        return original;
    }
}
