package uk.debb.vanilla_disable.mixin.arrow;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

import java.util.Set;

@Mixin(Arrow.class)
public abstract class MixinArrow extends AbstractArrow {
    public MixinArrow(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow private Potion potion;
    @Shadow @Final private Set<MobEffectInstance> effects;
    @Shadow @Final private static EntityDataAccessor<Integer> ID_EFFECT_COLOR;

    @Unique
    private static final Object2ObjectMap<Potion, GameRules.Key<GameRules.BooleanValue>> tippedArrowToGameruleMap = new Object2ObjectOpenHashMap<>();

    @Unique
    private void addOptionsToMap() {
        tippedArrowToGameruleMap.put(Potions.FIRE_RESISTANCE, Gamerules.FIRE_RESISTANCE_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.LONG_FIRE_RESISTANCE, Gamerules.FIRE_RESISTANCE_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.HARMING, Gamerules.HARMING_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.STRONG_HARMING, Gamerules.HARMING_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.HEALING, Gamerules.HEALING_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.STRONG_HEALING, Gamerules.HEALING_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.INVISIBILITY, Gamerules.INVISIBILITY_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.LONG_INVISIBILITY, Gamerules.INVISIBILITY_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.LEAPING, Gamerules.LEAPING_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.LONG_LEAPING, Gamerules.LEAPING_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.STRONG_LEAPING, Gamerules.LEAPING_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.LUCK, Gamerules.LUCK_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.NIGHT_VISION, Gamerules.NIGHT_VISION_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.LONG_NIGHT_VISION, Gamerules.NIGHT_VISION_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.POISON, Gamerules.POISON_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.LONG_POISON, Gamerules.POISON_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.STRONG_POISON, Gamerules.POISON_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.REGENERATION, Gamerules.REGENERATION_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.LONG_REGENERATION, Gamerules.REGENERATION_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.STRONG_REGENERATION, Gamerules.REGENERATION_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.SLOW_FALLING, Gamerules.SLOW_FALLING_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.LONG_SLOW_FALLING, Gamerules.SLOW_FALLING_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.SLOWNESS, Gamerules.SLOWNESS_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.LONG_SLOWNESS, Gamerules.SLOWNESS_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.STRONG_SLOWNESS, Gamerules.SLOWNESS_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.STRENGTH, Gamerules.STRENGTH_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.LONG_STRENGTH, Gamerules.STRENGTH_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.STRONG_STRENGTH, Gamerules.STRENGTH_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.SWIFTNESS, Gamerules.SWIFTNESS_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.LONG_SWIFTNESS, Gamerules.SWIFTNESS_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.STRONG_SWIFTNESS, Gamerules.SWIFTNESS_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.TURTLE_MASTER, Gamerules.TURTLE_MASTER_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.LONG_TURTLE_MASTER, Gamerules.TURTLE_MASTER_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.STRONG_TURTLE_MASTER, Gamerules.TURTLE_MASTER_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.WATER_BREATHING, Gamerules.WATER_BREATHING_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.LONG_WATER_BREATHING, Gamerules.WATER_BREATHING_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.WEAKNESS, Gamerules.WEAKNESS_TIPPED_ARROW);
        tippedArrowToGameruleMap.put(Potions.LONG_WEAKNESS, Gamerules.WEAKNESS_TIPPED_ARROW);
    }

    @Inject(method = "setEffectsFromItem", at = @At("HEAD"), cancellable = true)
    private void clearEffectsFromItem(ItemStack itemStack, CallbackInfo ci) {
        if (itemStack.is(Items.TIPPED_ARROW)) {
            if (tippedArrowToGameruleMap.isEmpty()) {
                addOptionsToMap();
            }
            GameRules.Key<GameRules.BooleanValue> gameRule = tippedArrowToGameruleMap.get(PotionUtils.getPotion(itemStack));
            if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
                this.potion = Potions.EMPTY;
                this.effects.clear();
                this.entityData.set(ID_EFFECT_COLOR, -1);
                ci.cancel();
            }
        }
    }
}
