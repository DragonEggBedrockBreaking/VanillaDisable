package uk.debb.vanilla_disable.mixin.advancement;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(PlayerAdvancements.class)
public abstract class MixinPlayerAdvancements {
    @Unique
    private static final Object2ObjectMap<String, GameRules.Key<GameRules.BooleanValue>> advancementToGameruleMap = new Object2ObjectOpenHashMap<>();

    /**
     * @param string the advancement id
     * @param gameRule the gamerule for the advancement
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private static void putInMap(String string, GameRules.Key<GameRules.BooleanValue> gameRule) {
        advancementToGameruleMap.put("advancements." + string + ".title", gameRule);
    }

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private static void addOptionsToMap() {
        putInMap("adventure.root", Gamerules.ADVENTURE_ADVANCEMENT_ADVENTURE);
        putInMap("adventure.sleep_in_bed", Gamerules.ADVENTURE_ADVANCEMENT_SWEET_DREAMS);
        putInMap("adventure.adventuring_time", Gamerules.ADVENTURE_ADVANCEMENT_ADVENTURING_TIME);
        putInMap("adventure.trade", Gamerules.ADVENTURE_ADVANCEMENT_WHAT_A_DEAL);
        putInMap("adventure.trade_at_world_height", Gamerules.ADVENTURE_ADVANCEMENT_STAR_TRADER);
        putInMap("adventure.kill_a_mob", Gamerules.ADVENTURE_ADVANCEMENT_MONSTER_HUNTER);
        putInMap("adventure.kill_all_mobs", Gamerules.ADVENTURE_ADVANCEMENT_MONSTERS_HUNTED);
        putInMap("adventure.shoot_arrow", Gamerules.ADVENTURE_ADVANCEMENT_TAKE_AIM);
        putInMap("adventure.throw_trident", Gamerules.ADVENTURE_ADVANCEMENT_THROWAWAY_JOKE);
        putInMap("adventure.very_very_frightening", Gamerules.ADVENTURE_ADVANCEMENT_VERY_FRIGHTENING);
        putInMap("adventure.summon_iron_golem", Gamerules.ADVENTURE_ADVANCEMENT_HIRED_HELP);
        putInMap("adventure.sniper_duel", Gamerules.ADVENTURE_ADVANCEMENT_SNIPER_DUEL);
        putInMap("adventure.totem_of_undying", Gamerules.ADVENTURE_ADVANCEMENT_POSTMORTAL);
        putInMap("adventure.ol_betsy", Gamerules.ADVENTURE_ADVANCEMENT_OLD_BETSY);
        putInMap("adventure.whos_the_pillager_now", Gamerules.ADVENTURE_ADVANCEMENT_WHOS_THE_PILLAGER_NOW);
        putInMap("adventure.two_birds_one_arrow", Gamerules.ADVENTURE_ADVANCEMENT_TWO_BIRDS_ONE_ARROW);
        putInMap("adventure.arbalistic", Gamerules.ADVENTURE_ADVANCEMENT_ARBALISTIC);
        putInMap("adventure.voluntary_exile", Gamerules.ADVENTURE_ADVANCEMENT_VOLUNTARY_EXILE);
        putInMap("adventure.hero_of_the_village", Gamerules.ADVENTURE_ADVANCEMENT_HERO_OF_VILLAGE);
        putInMap("adventure.honey_block_slide", Gamerules.ADVENTURE_ADVANCEMENT_STICKY_SITUATION);
        putInMap("adventure.bullseye", Gamerules.ADVENTURE_ADVANCEMENT_BULLSEYE);
        putInMap("adventure.walk_on_powder_snow_with_leather_boots", Gamerules.ADVENTURE_ADVANCEMENT_LIGHT_AS_RABBIT);
        putInMap("adventure.lightning_rod_with_villager_no_fire", Gamerules.ADVENTURE_ADVANCEMENT_SURGE_PROTECTOR);
        putInMap("adventure.spyglass_at_parrot", Gamerules.ADVENTURE_ADVANCEMENT_IS_IT_A_BIRD);
        putInMap("adventure.spyglass_at_ghast", Gamerules.ADVENTURE_ADVANCEMENT_IS_IT_A_BALLOON);
        putInMap("adventure.play_jukebox_in_meadows", Gamerules.ADVENTURE_ADVANCEMENT_SOUND_OF_MUSIC);
        putInMap("adventure.spyglass_at_dragon", Gamerules.ADVENTURE_ADVANCEMENT_IS_IT_A_PLANE);
        putInMap("adventure.fall_from_world_height", Gamerules.ADVENTURE_ADVANCEMENT_CAVES_AND_CLIFFS);
        putInMap("adventure.kill_mob_near_sculk_catalyst", Gamerules.ADVENTURE_ADVANCEMENT_IT_SPREADS);
        putInMap("adventure.avoid_vibration", Gamerules.ADVENTURE_ADVANCEMENT_SNEAK_100);

        putInMap("husbandry.root", Gamerules.HUSBANDRY_ADVANCEMENT_HUSBANDRY);
        putInMap("husbandry.plant_seed", Gamerules.HUSBANDRY_ADVANCEMENT_SEEDY_PLACE);
        putInMap("husbandry.breed_an_animal", Gamerules.HUSBANDRY_ADVANCEMENT_PARROTS_AND_BATS);
        putInMap("husbandry.balanced_diet", Gamerules.HUSBANDRY_ADVANCEMENT_BALANCED_DIET);
        putInMap("husbandry.netherite_hoe", Gamerules.HUSBANDRY_ADVANCEMENT_SERIOUS_DEDICATION);
        putInMap("husbandry.tame_an_animal", Gamerules.HUSBANDRY_ADVANCEMENT_BEST_FRIENDS);
        putInMap("husbandry.breed_all_animals", Gamerules.HUSBANDRY_ADVANCEMENT_TWO_BY_TWO);
        putInMap("husbandry.fishy_business", Gamerules.HUSBANDRY_ADVANCEMENT_FISHY_BUSINESS);
        putInMap("husbandry.tactical_fishing", Gamerules.HUSBANDRY_ADVANCEMENT_TACTICAL_FISHING);
        putInMap("husbandry.axolotl_in_a_bucket", Gamerules.HUSBANDRY_ADVANCEMENT_CUTEST_PREDATOR);
        putInMap("husbandry.kill_axolotl_target", Gamerules.HUSBANDRY_ADVANCEMENT_HEALING_FRIENDSHIP);
        putInMap("husbandry.complete_catalogue", Gamerules.HUSBANDRY_ADVANCEMENT_COMPLETE_CATALOGUE);
        putInMap("husbandry.safely_harvest_honey", Gamerules.HUSBANDRY_ADVANCEMENT_BEE_OUR_GUEST);
        putInMap("husbandry.wax_on", Gamerules.HUSBANDRY_ADVANCEMENT_WAX_ON);
        putInMap("husbandry.wax_off", Gamerules.HUSBANDRY_ADVANCEMENT_WAX_OFF);
        putInMap("husbandry.tadpole_in_a_bucket", Gamerules.HUSBANDRY_ADVANCEMENT_BUKKIT);
        putInMap("husbandry.leash_all_frog_variants", Gamerules.HUSBANDRY_ADVANCEMENT_SQUAD_HOPS_INTO_TOWN);
        putInMap("husbandry.froglights", Gamerules.HUSBANDRY_ADVANCEMENT_POWERS_COMBINED);
        putInMap("husbandry.silk_touch_nest", Gamerules.HUSBANDRY_ADVANCEMENT_BEELOCATION);
        putInMap("husbandry.ride_a_boat_with_a_goat", Gamerules.HUSBANDRY_ADVANCEMENT_FLOATS_YOUR_GOAT);
        putInMap("husbandry.make_a_sign_glow", Gamerules.HUSBANDRY_ADVANCEMENT_GLOW_AND_BEHOLD);
        putInMap("husbandry.allay_deliver_item_to_player", Gamerules.HUSBANDRY_ADVANCEMENT_FRIEND_IN_ME);
        putInMap("husbandry.allay_deliver_cake_to_note_block", Gamerules.HUSBANDRY_ADVANCEMENT_BIRTHDAY_SONG);

        putInMap("nether.root", Gamerules.NETHER_ADVANCEMENT_NETHER);
        putInMap("nether.return_to_sender", Gamerules.NETHER_ADVANCEMENT_RETURN_TO_SENDER);
        putInMap("nether.find_fortress", Gamerules.NETHER_ADVANCEMENT_TERRIBLE_FORTRESS);
        putInMap("nether.fast_travel", Gamerules.NETHER_ADVANCEMENT_SUBSPACE_BUBBLE);
        putInMap("nether.uneasy_alliance", Gamerules.NETHER_ADVANCEMENT_UNEASY_ALLIANCE);
        putInMap("nether.get_wither_skull", Gamerules.NETHER_ADVANCEMENT_SPOOKY_SCARY_SKELETON);
        putInMap("nether.summon_wither", Gamerules.NETHER_ADVANCEMENT_WITHERING_HEIGHTS);
        putInMap("nether.obtain_blaze_rod", Gamerules.NETHER_ADVANCEMENT_INTO_FIRE);
        putInMap("nether.create_beacon", Gamerules.NETHER_ADVANCEMENT_BRING_HOME_BEACON);
        putInMap("nether.create_full_beacon", Gamerules.NETHER_ADVANCEMENT_BEACONATOR);
        putInMap("nether.brew_potion", Gamerules.NETHER_ADVANCEMENT_LOCAL_BREWERY);
        putInMap("nether.all_potions", Gamerules.NETHER_ADVANCEMENT_FURIOUS_COCKTAIL);
        putInMap("nether.all_effects", Gamerules.NETHER_ADVANCEMENT_HOW_DID_WE_GET_HERE);
        putInMap("nether.obtain_ancient_debris", Gamerules.NETHER_ADVANCEMENT_HIDDEN_IN_DEPTHS);
        putInMap("nether.netherite_armor", Gamerules.NETHER_ADVANCEMENT_COVER_IN_DEBRIS);
        putInMap("nether.use_lodestone", Gamerules.NETHER_ADVANCEMENT_COUNTRY_LODE);
        putInMap("nether.obtain_crying_obsidian", Gamerules.NETHER_ADVANCEMENT_CUTTING_ONIONS);
        putInMap("nether.charge_respawn_anchor", Gamerules.NETHER_ADVANCEMENT_NOT_QUITE_NINE_LIVES);
        putInMap("nether.ride_strider", Gamerules.NETHER_ADVANCEMENT_BOAT_HAS_LEGS);
        putInMap("nether.ride_strider_in_overworld_lava", Gamerules.NETHER_ADVANCEMENT_FEELS_LIKE_HOME);
        putInMap("nether.explore_nether", Gamerules.NETHER_ADVANCEMENT_HOT_TOURIST_DESTINATIONS);
        putInMap("nether.find_bastion", Gamerules.NETHER_ADVANCEMENT_THOSE_WERE_THE_DAYS);
        putInMap("nether.loot_bastion", Gamerules.NETHER_ADVANCEMENT_WAR_PIGS);
        putInMap("nether.distract_piglin", Gamerules.NETHER_ADVANCEMENT_OH_SHINY);

        putInMap("story.root", Gamerules.STORY_ADVANCEMENT_MINECRAFT);
        putInMap("story.mine_stone", Gamerules.STORY_ADVANCEMENT_STONE_AGE);
        putInMap("story.upgrade_tools", Gamerules.STORY_ADVANCEMENT_GETTING_UPGRADE);
        putInMap("story.smelt_iron", Gamerules.STORY_ADVANCEMENT_ACQUIRE_HARDWARE);
        putInMap("story.iron_tools", Gamerules.STORY_ADVANCEMENT_IRON_PICK);
        putInMap("story.mine_diamond", Gamerules.STORY_ADVANCEMENT_DIAMONDS);
        putInMap("story.lava_bucket", Gamerules.STORY_ADVANCEMENT_HOT_STUFF);
        putInMap("story.obtain_armor", Gamerules.STORY_ADVANCEMENT_SUIT_UP);
        putInMap("story.enchant_item", Gamerules.STORY_ADVANCEMENT_ENCHANTER);
        putInMap("story.form_obsidian", Gamerules.STORY_ADVANCEMENT_ICE_BUCKET_CHALLENGE);
        putInMap("story.deflect_arrow", Gamerules.STORY_ADVANCEMENT_NOT_TODAY_THANKS);
        putInMap("story.shiny_gear", Gamerules.STORY_ADVANCEMENT_COVER_WITH_DIAMONDS);
        putInMap("story.enter_the_nether", Gamerules.STORY_ADVANCEMENT_GO_DEEPER);
        putInMap("story.cure_zombie_villager", Gamerules.STORY_ADVANCEMENT_ZOMBIE_DOCTOR);
        putInMap("story.follow_ender_eye", Gamerules.STORY_ADVANCEMENT_EYE_SPY);
        putInMap("story.enter_the_end", Gamerules.THE_END_ADVANCEMENT_THE_END);

        putInMap("end.root", Gamerules.THE_END_ADVANCEMENT_THE_END);
        putInMap("end.kill_dragon", Gamerules.THE_END_ADVANCEMENT_FREE_THE_END);
        putInMap("end.enter_end_gateway", Gamerules.THE_END_ADVANCEMENT_REMOTE_GETAWAY);
        putInMap("end.respawn_dragon", Gamerules.THE_END_ADVANCEMENT_END_AGAIN);
        putInMap("end.find_end_city", Gamerules.THE_END_ADVANCEMENT_CITY_AT_END);
        putInMap("end.dragon_breath", Gamerules.THE_END_ADVANCEMENT_YOU_NEED_MINT);
        putInMap("end.levitate", Gamerules.THE_END_ADVANCEMENT_GREAT_VIEW);
        putInMap("end.elytra", Gamerules.THE_END_ADVANCEMENT_SKY_IS_LIMIT);
        putInMap("end.dragon_egg", Gamerules.THE_END_ADVANCEMENT_NEXT_GENERATION);
    }

    /**
     * @param original the original value
     * @param advancement the advancement
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "getOrStartProgress", at = @At("RETURN"))
    private AdvancementProgress cancelPerformingCriterion(AdvancementProgress original, Advancement advancement) {
        if (advancementToGameruleMap.isEmpty()) {
            addOptionsToMap();
        }
        if (advancement.getDisplay() != null) {
            GameRules.Key<GameRules.BooleanValue> gameRule = advancementToGameruleMap.get(advancement.getDisplay().getTitle().toString().split("'")[1]);
            if ((!GameruleHelper.getBool(Gamerules.ADVANCEMENTS_ENABLED) || (gameRule != null && !GameruleHelper.getBool(gameRule)))) {
                return new AdvancementProgress();
            }
        }
        return original;
    }
}
