package uk.debb.vanilla_disable.mixin.food;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(FoodData.class)
public abstract class MixinFoodData {
    @Shadow public abstract void eat(int i, float f);

    @Shadow private int foodLevel;
    @Unique
    private static final Object2ObjectMap<FoodProperties, GameRules.Key<GameRules.IntegerValue>> foodMap = new Object2ObjectOpenHashMap<>();

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addOptionsToMap() {
        foodMap.put(Foods.APPLE, Gamerules.APPLE_NUTRITION);
        foodMap.put(Foods.BAKED_POTATO, Gamerules.BAKED_POTATO_NUTRITION);
        foodMap.put(Foods.BEEF, Gamerules.BEEF_NUTRITION);
        foodMap.put(Foods.BEETROOT, Gamerules.BEETROOT_NUTRITION);
        foodMap.put(Foods.BEETROOT_SOUP, Gamerules.BEETROOT_SOUP_NUTRITION);
        foodMap.put(Foods.BREAD, Gamerules.BREAD_NUTRITION);
        foodMap.put(Foods.CARROT, Gamerules.CARROT_NUTRITION);
        foodMap.put(Foods.CHICKEN, Gamerules.CHICKEN_NUTRITION);
        foodMap.put(Foods.CHORUS_FRUIT, Gamerules.CHORUS_FRUIT_NUTRITION);
        foodMap.put(Foods.COD, Gamerules.COD_NUTRITION);
        foodMap.put(Foods.COOKED_BEEF, Gamerules.COOKED_BEEF_NUTRITION);
        foodMap.put(Foods.COOKED_CHICKEN, Gamerules.COOKED_CHICKEN_NUTRITION);
        foodMap.put(Foods.COOKED_COD, Gamerules.COOKED_COD_NUTRITION);
        foodMap.put(Foods.COOKED_MUTTON, Gamerules.COOKED_MUTTON_NUTRITION);
        foodMap.put(Foods.COOKED_PORKCHOP, Gamerules.COOKED_PORKCHOP_NUTRITION);
        foodMap.put(Foods.COOKED_RABBIT, Gamerules.COOKED_RABBIT_NUTRITION);
        foodMap.put(Foods.COOKED_SALMON, Gamerules.COOKED_SALMON_NUTRITION);
        foodMap.put(Foods.COOKIE, Gamerules.COOKIE_NUTRITION);
        foodMap.put(Foods.ENCHANTED_GOLDEN_APPLE, Gamerules.ENCHANTED_GOLDEN_APPLE_NUTRITION);
        foodMap.put(Foods.GOLDEN_APPLE, Gamerules.GOLDEN_APPLE_NUTRITION);
        foodMap.put(Foods.GOLDEN_CARROT, Gamerules.GOLDEN_CARROT_NUTRITION);
        foodMap.put(Foods.HONEY_BOTTLE, Gamerules.HONEY_BOTTLE_NUTRITION);
        foodMap.put(Foods.MELON_SLICE, Gamerules.MELON_SLICE_NUTRITION);
        foodMap.put(Foods.MUSHROOM_STEW, Gamerules.MUSHROOM_STEW_NUTRITION);
        foodMap.put(Foods.MUTTON, Gamerules.MUTTON_NUTRITION);
        foodMap.put(Foods.POISONOUS_POTATO, Gamerules.POISONOUS_POTATO_NUTRITION);
        foodMap.put(Foods.PORKCHOP, Gamerules.PORKCHOP_NUTRITION);
        foodMap.put(Foods.POTATO, Gamerules.POTATO_NUTRITION);
        foodMap.put(Foods.PUFFERFISH, Gamerules.PUFFERFISH_NUTRITION);
        foodMap.put(Foods.PUMPKIN_PIE, Gamerules.PUMPKIN_PIE_NUTRITION);
        foodMap.put(Foods.RABBIT, Gamerules.RABBIT_NUTRITION);
        foodMap.put(Foods.RABBIT_STEW, Gamerules.RABBIT_STEW_NUTRITION);
        foodMap.put(Foods.ROTTEN_FLESH, Gamerules.ROTTEN_FLESH_NUTRITION);
        foodMap.put(Foods.SALMON, Gamerules.SALMON_NUTRITION);
        foodMap.put(Foods.SPIDER_EYE, Gamerules.SPIDER_EYE_NUTRITION);
        foodMap.put(Foods.SUSPICIOUS_STEW, Gamerules.SUSPICIOUS_STEW_NUTRITION);
        foodMap.put(Foods.SWEET_BERRIES, Gamerules.SWEET_BERRIES_NUTRITION);
        foodMap.put(Foods.GLOW_BERRIES, Gamerules.GLOW_BERRIES_NUTRITION);
        foodMap.put(Foods.TROPICAL_FISH, Gamerules.TROPICAL_FISH_NUTRITION);
    }

    /**
     * @author DragonEggBEdrockBreaking
     * @reason change the nutrition level of food
     * @param item the food item
     * @param itemStack the stack of the food item
     * @param ci the callback info
     */
    @Inject(method = "eat(Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/ItemStack;)V", at = @At("HEAD"), cancellable = true)
    private void modifyNutrition(Item item, ItemStack itemStack, CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (foodMap.isEmpty()) {
            addOptionsToMap();
        }
        if (item.isEdible()) {
            FoodProperties lv = item.getFoodProperties();
            GameRules.Key<GameRules.IntegerValue> gameRule = foodMap.get(item.getFoodProperties());
            this.eat(GameruleHelper.getInt(gameRule), lv.getSaturationModifier());
        }
        ci.cancel();
    }
}
