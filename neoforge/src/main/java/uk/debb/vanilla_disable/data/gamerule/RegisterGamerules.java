package uk.debb.vanilla_disable.data.gamerule;

import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import uk.debb.vanilla_disable.Constants;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterGamerules {
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        VDGamerules.RAID_WAVES_EASY = GameRules.register("raidWavesEasy", GameRules.Category.SPAWNING, GameRules.IntegerValue.create(4));
        VDGamerules.RAID_WAVES_NORMAL = GameRules.register("raidWavesNormal", GameRules.Category.SPAWNING, GameRules.IntegerValue.create(6));
        VDGamerules.RAID_WAVES_HARD = GameRules.register("raidWavesHard", GameRules.Category.SPAWNING, GameRules.IntegerValue.create(8));
        VDGamerules.RECIPE_BOOK_ENABLED = GameRules.register("recipeBookEnabled", GameRules.Category.MISC, GameRules.BooleanValue.create(true));
    }
}
