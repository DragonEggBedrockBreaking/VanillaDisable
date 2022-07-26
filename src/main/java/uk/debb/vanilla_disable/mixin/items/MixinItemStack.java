package uk.debb.vanilla_disable.mixin.items;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.VDServer;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {
    @Unique
    private static final Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> itemToGameruleMap = new Object2ObjectOpenHashMap<>();

    @Shadow
    public abstract Item getItem();

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addOptionsToMap() {
        itemToGameruleMap.put(BoatItem.class, Gamerules.BOATS_ENABLED);
        itemToGameruleMap.put(BottleItem.class, Gamerules.BOTTLES_ENABLED);
        itemToGameruleMap.put(BowItem.class, Gamerules.BOWS_ENABLED);
        itemToGameruleMap.put(BucketItem.class, Gamerules.BUCKETS_ENABLED);
        itemToGameruleMap.put(BundleItem.class, Gamerules.BUNDLES_ENABLED);
        itemToGameruleMap.put(CrossbowItem.class, Gamerules.CROSSBOWS_ENABLED);
        itemToGameruleMap.put(EggItem.class, Gamerules.EGGS_ENABLED);
        itemToGameruleMap.put(EmptyMapItem.class, Gamerules.MAPS_ENABLED);
        itemToGameruleMap.put(EnderEyeItem.class, Gamerules.ENDER_EYES_ENABLED);
        itemToGameruleMap.put(EnderpearlItem.class, Gamerules.ENDER_PEARLS_ENABLED);
        itemToGameruleMap.put(ExperienceBottleItem.class, Gamerules.EXPERIENCE_BOTTLES_ENABLED);
        itemToGameruleMap.put(FireworkRocketItem.class, Gamerules.FIREWORKS_ENABLED);
        itemToGameruleMap.put(FishingRodItem.class, Gamerules.FISHING_ENABLED);
        itemToGameruleMap.put(FoodOnAStickItem.class, Gamerules.FOOD_ON_STICKS_ENABLED);
        itemToGameruleMap.put(InstrumentItem.class, Gamerules.GOAT_HORNS_ENABLED);
        itemToGameruleMap.put(KnowledgeBookItem.class, Gamerules.BOOKS_ENABLED);
        itemToGameruleMap.put(ShieldItem.class, Gamerules.SHIELDS_ENABLED);
        itemToGameruleMap.put(SnowballItem.class, Gamerules.SNOWBALLS_ENABLED);
        itemToGameruleMap.put(SpyglassItem.class, Gamerules.SPYGLASSES_ENABLED);
        itemToGameruleMap.put(TridentItem.class, Gamerules.TRIDENTS_ENABLED);
        itemToGameruleMap.put(WritableBookItem.class, Gamerules.BOOKS_ENABLED);
        itemToGameruleMap.put(WrittenBookItem.class, Gamerules.BOOKS_ENABLED);
        itemToGameruleMap.put(AxeItem.class, Gamerules.AXES_ENABLED);
        itemToGameruleMap.put(BoneMealItem.class, Gamerules.BONE_MEAL_ENABLED);
        itemToGameruleMap.put(CompassItem.class, Gamerules.COMPASSES_ENABLED);
        itemToGameruleMap.put(EndCrystalItem.class, Gamerules.END_CRYSTALS_ENABLED);
        itemToGameruleMap.put(FireChargeItem.class, Gamerules.FIRE_CHARGES_ENABLED);
        itemToGameruleMap.put(FlintAndSteelItem.class, Gamerules.FLINT_AND_STEEL_ENABLED);
        itemToGameruleMap.put(HangingEntityItem.class, Gamerules.HANGING_ENTITIES_ENABLED);
        itemToGameruleMap.put(HoeItem.class, Gamerules.HOES_ENABLED);
        itemToGameruleMap.put(HoneycombItem.class, Gamerules.HONEYCOMBS_ENABLED);
        itemToGameruleMap.put(LeadItem.class, Gamerules.LEADS_ENABLED);
        itemToGameruleMap.put(MapItem.class, Gamerules.MAPS_ENABLED);
        itemToGameruleMap.put(MinecartItem.class, Gamerules.MINECARTS_ENABLED);
        itemToGameruleMap.put(RecordItem.class, Gamerules.RECORDS_ENABLED);
        itemToGameruleMap.put(ShearsItem.class, Gamerules.SHEARS_ENABLED);
        itemToGameruleMap.put(ShovelItem.class, Gamerules.SHOVELS_ENABLED);
        itemToGameruleMap.put(SolidBucketItem.class, Gamerules.BUCKETS_ENABLED);
        itemToGameruleMap.put(DyeItem.class, Gamerules.DYES_ENABLED);
        itemToGameruleMap.put(NameTagItem.class, Gamerules.NAMETAGS_ENABLED);
        itemToGameruleMap.put(SaddleItem.class, Gamerules.SADDLES_ENABLED);
        itemToGameruleMap.put(ChorusFruitItem.class, Gamerules.CHORUS_FRUIT_EFFECTS_ENABLED);
        itemToGameruleMap.put(HoneyBottleItem.class, Gamerules.HONEY_BOTTLE_EFFECTS_ENABLED);
        itemToGameruleMap.put(MilkBucketItem.class, Gamerules.MILK_BUCKET_EFFECTS_ENABLED);
        itemToGameruleMap.put(SuspiciousStewItem.class, Gamerules.SUSPICIOUS_STEW_EFFECTS_ENABLED);
    }

    /**
     * @param level           the level the usage was in
     * @param player          the player that used the item
     * @param interactionHand the hand that used it
     * @param cir             the returnable callback info (net.minecraft.world.InteractionResultHolder<net.minecraft.world.item.ItemStack>>
     * @author DragonEggBedrockBreaking
     */
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void cancelUsage(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (VDServer.getServer() == null) return;
        if (itemToGameruleMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = itemToGameruleMap.get(this.getItem().getClass());
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            cir.setReturnValue(InteractionResultHolder.fail(player.getItemInHand(interactionHand)));
            cir.cancel();
        }
    }

    /**
     * @param useOnContext the context that the item is used on
     * @param cir          returnable callback info (net.minecraft.world.InteractionResult)
     * @author DragonEggBedrockBreaking
     */
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void cancelUsageOn(UseOnContext useOnContext, CallbackInfoReturnable<InteractionResult> cir) {
        if (VDServer.getServer() == null) return;
        if (itemToGameruleMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = itemToGameruleMap.get(this.getItem().getClass());
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            cir.setReturnValue(InteractionResult.FAIL);
            cir.cancel();
        }
    }

    /**
     * @param player          the player who right-clicked the item
     * @param livingEntity    the animal that was right-clicked on
     * @param interactionHand the hand that right-clicks on the entity
     * @param cir             returnable callback info (net.minecraft.world.InteractionResult)
     * @author DragonEggBedrockBreaking
     */
    @Inject(method = "interactLivingEntity", at = @At("HEAD"), cancellable = true)
    private void cancelLivingEntityInteraction(Player player, LivingEntity livingEntity, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
        if (VDServer.getServer() == null) return;
        if (itemToGameruleMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = itemToGameruleMap.get(this.getItem().getClass());
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            cir.setReturnValue(InteractionResult.FAIL);
            cir.cancel();
        }
    }

    /**
     * @param level        the level the item is being used in
     * @param livingEntity the entity that is using the item
     * @param cir          the returnable callback info (net.minecraft.world.item.ItemStack)
     * @author DragonEggBedrockBreaking
     */
    @Inject(method = "finishUsingItem", at = @At("HEAD"), cancellable = true)
    private void cancelItemUseFinishing(Level level, LivingEntity livingEntity, CallbackInfoReturnable<ItemStack> cir) {
        if (VDServer.getServer() == null) return;
        if (itemToGameruleMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = itemToGameruleMap.get(this.getItem().getClass());
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            cir.setReturnValue(ItemStack.EMPTY);
            cir.cancel();
        }
    }
}