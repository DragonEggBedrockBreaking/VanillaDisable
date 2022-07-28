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
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(FoodData.class)
public abstract class MixinFoodData {
    @Unique
    private static final Object2ObjectMap<FoodProperties, GameRules.Key<GameRules.IntegerValue>> nutritionMap = new Object2ObjectOpenHashMap<>();
    @Unique
    private static final Object2ObjectMap<FoodProperties, GameRules.Key<GameRules.IntegerValue>> saturationMap = new Object2ObjectOpenHashMap<>();

    @Shadow
    public abstract void eat(int i, float f);

    @Unique
    private void addOptionsToMap() {
        nutritionMap.put(Foods.APPLE, Gamerules.APPLE_NUTRITION);
        nutritionMap.put(Foods.BAKED_POTATO, Gamerules.BAKED_POTATO_NUTRITION);
        nutritionMap.put(Foods.BEEF, Gamerules.BEEF_NUTRITION);
        nutritionMap.put(Foods.BEETROOT, Gamerules.BEETROOT_NUTRITION);
        nutritionMap.put(Foods.BEETROOT_SOUP, Gamerules.BEETROOT_SOUP_NUTRITION);
        nutritionMap.put(Foods.BREAD, Gamerules.BREAD_NUTRITION);
        nutritionMap.put(Foods.CARROT, Gamerules.CARROT_NUTRITION);
        nutritionMap.put(Foods.CHICKEN, Gamerules.CHICKEN_NUTRITION);
        nutritionMap.put(Foods.CHORUS_FRUIT, Gamerules.CHORUS_FRUIT_NUTRITION);
        nutritionMap.put(Foods.COD, Gamerules.COD_NUTRITION);
        nutritionMap.put(Foods.COOKED_BEEF, Gamerules.COOKED_BEEF_NUTRITION);
        nutritionMap.put(Foods.COOKED_CHICKEN, Gamerules.COOKED_CHICKEN_NUTRITION);
        nutritionMap.put(Foods.COOKED_COD, Gamerules.COOKED_COD_NUTRITION);
        nutritionMap.put(Foods.COOKED_MUTTON, Gamerules.COOKED_MUTTON_NUTRITION);
        nutritionMap.put(Foods.COOKED_PORKCHOP, Gamerules.COOKED_PORKCHOP_NUTRITION);
        nutritionMap.put(Foods.COOKED_RABBIT, Gamerules.COOKED_RABBIT_NUTRITION);
        nutritionMap.put(Foods.COOKED_SALMON, Gamerules.COOKED_SALMON_NUTRITION);
        nutritionMap.put(Foods.COOKIE, Gamerules.COOKIE_NUTRITION);
        nutritionMap.put(Foods.DRIED_KELP, Gamerules.DRIED_KELP_NUTRITION);
        nutritionMap.put(Foods.ENCHANTED_GOLDEN_APPLE, Gamerules.ENCHANTED_GOLDEN_APPLE_NUTRITION);
        nutritionMap.put(Foods.GOLDEN_APPLE, Gamerules.GOLDEN_APPLE_NUTRITION);
        nutritionMap.put(Foods.GOLDEN_CARROT, Gamerules.GOLDEN_CARROT_NUTRITION);
        nutritionMap.put(Foods.HONEY_BOTTLE, Gamerules.HONEY_BOTTLE_NUTRITION);
        nutritionMap.put(Foods.MELON_SLICE, Gamerules.MELON_SLICE_NUTRITION);
        nutritionMap.put(Foods.MUSHROOM_STEW, Gamerules.MUSHROOM_STEW_NUTRITION);
        nutritionMap.put(Foods.MUTTON, Gamerules.MUTTON_NUTRITION);
        nutritionMap.put(Foods.POISONOUS_POTATO, Gamerules.POISONOUS_POTATO_NUTRITION);
        nutritionMap.put(Foods.PORKCHOP, Gamerules.PORKCHOP_NUTRITION);
        nutritionMap.put(Foods.POTATO, Gamerules.POTATO_NUTRITION);
        nutritionMap.put(Foods.PUFFERFISH, Gamerules.PUFFERFISH_NUTRITION);
        nutritionMap.put(Foods.PUMPKIN_PIE, Gamerules.PUMPKIN_PIE_NUTRITION);
        nutritionMap.put(Foods.RABBIT, Gamerules.RABBIT_NUTRITION);
        nutritionMap.put(Foods.RABBIT_STEW, Gamerules.RABBIT_STEW_NUTRITION);
        nutritionMap.put(Foods.ROTTEN_FLESH, Gamerules.ROTTEN_FLESH_NUTRITION);
        nutritionMap.put(Foods.SALMON, Gamerules.SALMON_NUTRITION);
        nutritionMap.put(Foods.SPIDER_EYE, Gamerules.SPIDER_EYE_NUTRITION);
        nutritionMap.put(Foods.SUSPICIOUS_STEW, Gamerules.SUSPICIOUS_STEW_NUTRITION);
        nutritionMap.put(Foods.SWEET_BERRIES, Gamerules.SWEET_BERRIES_NUTRITION);
        nutritionMap.put(Foods.GLOW_BERRIES, Gamerules.GLOW_BERRIES_NUTRITION);
        nutritionMap.put(Foods.TROPICAL_FISH, Gamerules.TROPICAL_FISH_NUTRITION);

        saturationMap.put(Foods.APPLE, Gamerules.APPLE_SATURATION);
        saturationMap.put(Foods.BAKED_POTATO, Gamerules.BAKED_POTATO_SATURATION);
        saturationMap.put(Foods.BEEF, Gamerules.BEEF_SATURATION);
        saturationMap.put(Foods.BEETROOT, Gamerules.BEETROOT_SATURATION);
        saturationMap.put(Foods.BEETROOT_SOUP, Gamerules.BEETROOT_SOUP_SATURATION);
        saturationMap.put(Foods.BREAD, Gamerules.BREAD_SATURATION);
        saturationMap.put(Foods.CARROT, Gamerules.CARROT_SATURATION);
        saturationMap.put(Foods.CHICKEN, Gamerules.CHICKEN_SATURATION);
        saturationMap.put(Foods.CHORUS_FRUIT, Gamerules.CHORUS_FRUIT_SATURATION);
        saturationMap.put(Foods.COD, Gamerules.COD_SATURATION);
        saturationMap.put(Foods.COOKED_BEEF, Gamerules.COOKED_BEEF_SATURATION);
        saturationMap.put(Foods.COOKED_CHICKEN, Gamerules.COOKED_CHICKEN_SATURATION);
        saturationMap.put(Foods.COOKED_COD, Gamerules.COOKED_COD_SATURATION);
        saturationMap.put(Foods.COOKED_MUTTON, Gamerules.COOKED_MUTTON_SATURATION);
        saturationMap.put(Foods.COOKED_PORKCHOP, Gamerules.COOKED_PORKCHOP_SATURATION);
        saturationMap.put(Foods.COOKED_RABBIT, Gamerules.COOKED_RABBIT_SATURATION);
        saturationMap.put(Foods.COOKED_SALMON, Gamerules.COOKED_SALMON_SATURATION);
        saturationMap.put(Foods.COOKIE, Gamerules.COOKIE_SATURATION);
        saturationMap.put(Foods.DRIED_KELP, Gamerules.DRIED_KELP_SATURATION);
        saturationMap.put(Foods.ENCHANTED_GOLDEN_APPLE, Gamerules.ENCHANTED_GOLDEN_APPLE_SATURATION);
        saturationMap.put(Foods.GOLDEN_APPLE, Gamerules.GOLDEN_APPLE_SATURATION);
        saturationMap.put(Foods.GOLDEN_CARROT, Gamerules.GOLDEN_CARROT_SATURATION);
        saturationMap.put(Foods.HONEY_BOTTLE, Gamerules.HONEY_BOTTLE_SATURATION);
        saturationMap.put(Foods.MELON_SLICE, Gamerules.MELON_SLICE_SATURATION);
        saturationMap.put(Foods.MUSHROOM_STEW, Gamerules.MUSHROOM_STEW_SATURATION);
        saturationMap.put(Foods.MUTTON, Gamerules.MUTTON_SATURATION);
        saturationMap.put(Foods.POISONOUS_POTATO, Gamerules.POISONOUS_POTATO_SATURATION);
        saturationMap.put(Foods.PORKCHOP, Gamerules.PORKCHOP_SATURATION);
        saturationMap.put(Foods.POTATO, Gamerules.POTATO_SATURATION);
        saturationMap.put(Foods.PUFFERFISH, Gamerules.PUFFERFISH_SATURATION);
        saturationMap.put(Foods.PUMPKIN_PIE, Gamerules.PUMPKIN_PIE_SATURATION);
        saturationMap.put(Foods.RABBIT, Gamerules.RABBIT_SATURATION);
        saturationMap.put(Foods.RABBIT_STEW, Gamerules.RABBIT_STEW_SATURATION);
        saturationMap.put(Foods.ROTTEN_FLESH, Gamerules.ROTTEN_FLESH_SATURATION);
        saturationMap.put(Foods.SALMON, Gamerules.SALMON_SATURATION);
        saturationMap.put(Foods.SPIDER_EYE, Gamerules.SPIDER_EYE_SATURATION);
        saturationMap.put(Foods.SUSPICIOUS_STEW, Gamerules.SUSPICIOUS_STEW_SATURATION);
        saturationMap.put(Foods.SWEET_BERRIES, Gamerules.SWEET_BERRIES_SATURATION);
        saturationMap.put(Foods.GLOW_BERRIES, Gamerules.GLOW_BERRIES_SATURATION);
        saturationMap.put(Foods.TROPICAL_FISH, Gamerules.TROPICAL_FISH_SATURATION);
    }

    @Inject(method = "eat(Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/ItemStack;)V", at = @At("HEAD"), cancellable = true)
    private void modifyNutrition(Item item, ItemStack itemStack, CallbackInfo ci) {
        if (nutritionMap.isEmpty() || saturationMap.isEmpty()) {
            addOptionsToMap();
        }
        if (item.isEdible()) {
            GameRules.Key<GameRules.IntegerValue> nutritionGamerule = nutritionMap.get(item.getFoodProperties());
            GameRules.Key<GameRules.IntegerValue> saturationGamerule = saturationMap.get(item.getFoodProperties());
            this.eat(GameruleHelper.getInt(nutritionGamerule), (float) GameruleHelper.getInt(saturationGamerule) / 10);
        }
        ci.cancel();
    }
}