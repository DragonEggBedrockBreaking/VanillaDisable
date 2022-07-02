package uk.debb.vanilla_disable.mixin.potions;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.LingeringPotionItem;
import net.minecraft.world.item.SplashPotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

import java.util.ArrayList;
import java.util.List;

@Mixin(PotionUtils.class)
public abstract class MixinPotionUtils {
    private static final Object2ObjectMap<Potion, GameRules.Key<GameRules.BooleanValue>> potionMap = new Object2ObjectOpenHashMap<>();

    /**
     * @author DragonEggBedrockBreaking
     */
    private static void addOptionsToMap() {
        potionMap.put(Potions.FIRE_RESISTANCE, Gamerules.FIRE_RESISTANCE_POTION);
        potionMap.put(Potions.LONG_FIRE_RESISTANCE, Gamerules.FIRE_RESISTANCE_POTION);
        potionMap.put(Potions.HARMING, Gamerules.HARMING_POTION);
        potionMap.put(Potions.STRONG_HARMING, Gamerules.HARMING_POTION);
        potionMap.put(Potions.HEALING, Gamerules.HEALING_POTION);
        potionMap.put(Potions.STRONG_HEALING, Gamerules.HEALING_POTION);
        potionMap.put(Potions.INVISIBILITY, Gamerules.INVISIBILITY_POTION);
        potionMap.put(Potions.LONG_INVISIBILITY, Gamerules.INVISIBILITY_POTION);
        potionMap.put(Potions.LEAPING, Gamerules.LEAPING_POTION);
        potionMap.put(Potions.LONG_LEAPING, Gamerules.LEAPING_POTION);
        potionMap.put(Potions.STRONG_LEAPING, Gamerules.LEAPING_POTION);
        potionMap.put(Potions.LUCK, Gamerules.LUCK_POTION);
        potionMap.put(Potions.NIGHT_VISION, Gamerules.NIGHT_VISION_POTION);
        potionMap.put(Potions.LONG_NIGHT_VISION, Gamerules.NIGHT_VISION_POTION);
        potionMap.put(Potions.POISON, Gamerules.POISON_POTION);
        potionMap.put(Potions.LONG_POISON, Gamerules.POISON_POTION);
        potionMap.put(Potions.STRONG_POISON, Gamerules.POISON_POTION);
        potionMap.put(Potions.REGENERATION, Gamerules.REGENERATION_POTION);
        potionMap.put(Potions.LONG_REGENERATION, Gamerules.REGENERATION_POTION);
        potionMap.put(Potions.STRONG_REGENERATION, Gamerules.REGENERATION_POTION);
        potionMap.put(Potions.SLOW_FALLING, Gamerules.SLOW_FALLING_POTION);
        potionMap.put(Potions.LONG_SLOW_FALLING, Gamerules.SLOW_FALLING_POTION);
        potionMap.put(Potions.SLOWNESS, Gamerules.SLOWNESS_POTION);
        potionMap.put(Potions.LONG_SLOWNESS, Gamerules.SLOWNESS_POTION);
        potionMap.put(Potions.STRONG_SLOWNESS, Gamerules.SLOWNESS_POTION);
        potionMap.put(Potions.STRENGTH, Gamerules.STRENGTH_POTION);
        potionMap.put(Potions.LONG_STRENGTH, Gamerules.STRENGTH_POTION);
        potionMap.put(Potions.STRONG_STRENGTH, Gamerules.STRENGTH_POTION);
        potionMap.put(Potions.SWIFTNESS, Gamerules.SWIFTNESS_POTION);
        potionMap.put(Potions.LONG_SWIFTNESS, Gamerules.SWIFTNESS_POTION);
        potionMap.put(Potions.STRONG_SWIFTNESS, Gamerules.SWIFTNESS_POTION);
        potionMap.put(Potions.TURTLE_MASTER, Gamerules.TURTLE_MASTER_POTION);
        potionMap.put(Potions.LONG_TURTLE_MASTER, Gamerules.TURTLE_MASTER_POTION);
        potionMap.put(Potions.STRONG_TURTLE_MASTER, Gamerules.TURTLE_MASTER_POTION);
        potionMap.put(Potions.WATER_BREATHING, Gamerules.WATER_BREATHING_POTION);
        potionMap.put(Potions.LONG_WATER_BREATHING, Gamerules.WATER_BREATHING_POTION);
        potionMap.put(Potions.WEAKNESS, Gamerules.WEAKNESS_POTION);
        potionMap.put(Potions.LONG_WEAKNESS, Gamerules.WEAKNESS_POTION);
    }

    /**
     * @param itemStack the potion
     * @param cir       the returnable callback info (java.util.List<net.minecraft.world.effect.MobEffectInstance>)
     * @author DragonEggBedrockBreaking
     * @reason removes a potion's effects if gamerule disabled
     */
    @Inject(method = "getMobEffects", at = @At("HEAD"), cancellable = true)
    private static void removeMobEffects(ItemStack itemStack, CallbackInfoReturnable<List<MobEffectInstance>> cir) {
        if (VDServer.getServer() == null) return;
        if (potionMap.isEmpty()) {
            addOptionsToMap();
        }
        Potion potion = PotionUtils.getPotion(itemStack);
        if (itemStack.getItem() instanceof SplashPotionItem) {
            if (!GameruleHelper.getBool(Gamerules.SPLASH_POTIONS_ENABLED)) {
                cir.setReturnValue(new ArrayList<>());
            }
        } else if (itemStack.getItem() instanceof LingeringPotionItem) {
            if (!GameruleHelper.getBool(Gamerules.LINGERING_POTIONS_ENABLED)) {
                cir.setReturnValue(new ArrayList<>());
            }
        } else if (!GameruleHelper.getBool(Gamerules.NORMAL_POTIONS_ENABLED)) {
            cir.setReturnValue(new ArrayList<>());
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = potionMap.get(potion);
        if (!GameruleHelper.getBool(Gamerules.POTIONS_ENABLED) || (gameRule != null && !GameruleHelper.getBool(gameRule))) {
            cir.setReturnValue(new ArrayList<>());
        }
    }
}