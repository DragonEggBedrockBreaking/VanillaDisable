package uk.debb.vanilla_disable.util.lists;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.biome.Biomes;

public class PopulateLists implements Lists {
    public static void populateLists() {
        serverPlayerStringList.add("jump");
        serverPlayerStringList.add("drop");
        serverPlayerStringList.add("bred");
        serverPlayerStringList.add("fish_caught");
        serverPlayerStringList.add("target_hit");
        serverPlayerStringList.add("trade");

        netherBiomes.add(BuiltinRegistries.BIOME.getHolderOrThrow(Biomes.BASALT_DELTAS));
        netherBiomes.add(BuiltinRegistries.BIOME.getHolderOrThrow(Biomes.CRIMSON_FOREST));
        netherBiomes.add(BuiltinRegistries.BIOME.getHolderOrThrow(Biomes.SOUL_SAND_VALLEY));
        netherBiomes.add(BuiltinRegistries.BIOME.getHolderOrThrow(Biomes.WARPED_FOREST));

        theEndBiomes.add(BuiltinRegistries.BIOME.getHolderOrThrow(Biomes.END_BARRENS));
        theEndBiomes.add(BuiltinRegistries.BIOME.getHolderOrThrow(Biomes.END_HIGHLANDS));
        theEndBiomes.add(BuiltinRegistries.BIOME.getHolderOrThrow(Biomes.END_MIDLANDS));
        theEndBiomes.add(BuiltinRegistries.BIOME.getHolderOrThrow(Biomes.SMALL_END_ISLANDS));

        vanillaGamerules.add(GameRules.RULE_DOFIRETICK);
        vanillaGamerules.add(GameRules.RULE_MOBGRIEFING);
        vanillaGamerules.add(GameRules.RULE_KEEPINVENTORY);
        vanillaGamerules.add(GameRules.RULE_DOMOBSPAWNING);
        vanillaGamerules.add(GameRules.RULE_DOMOBLOOT);
        vanillaGamerules.add(GameRules.RULE_DOBLOCKDROPS);
        vanillaGamerules.add(GameRules.RULE_DOENTITYDROPS);
        vanillaGamerules.add(GameRules.RULE_COMMANDBLOCKOUTPUT);
        vanillaGamerules.add(GameRules.RULE_NATURAL_REGENERATION);
        vanillaGamerules.add(GameRules.RULE_DAYLIGHT);
        vanillaGamerules.add(GameRules.RULE_LOGADMINCOMMANDS);
        vanillaGamerules.add(GameRules.RULE_SHOWDEATHMESSAGES);
        vanillaGamerules.add(GameRules.RULE_SENDCOMMANDFEEDBACK);
        vanillaGamerules.add(GameRules.RULE_REDUCEDDEBUGINFO);
        vanillaGamerules.add(GameRules.RULE_SPECTATORSGENERATECHUNKS);
        vanillaGamerules.add(GameRules.RULE_DISABLE_ELYTRA_MOVEMENT_CHECK);
        vanillaGamerules.add(GameRules.RULE_WEATHER_CYCLE);
        vanillaGamerules.add(GameRules.RULE_LIMITED_CRAFTING);
        vanillaGamerules.add(GameRules.RULE_ANNOUNCE_ADVANCEMENTS);
        vanillaGamerules.add(GameRules.RULE_DISABLE_RAIDS);
        vanillaGamerules.add(GameRules.RULE_DOINSOMNIA);
        vanillaGamerules.add(GameRules.RULE_DO_IMMEDIATE_RESPAWN);
        vanillaGamerules.add(GameRules.RULE_DROWNING_DAMAGE);
        vanillaGamerules.add(GameRules.RULE_FALL_DAMAGE);
        vanillaGamerules.add(GameRules.RULE_FIRE_DAMAGE);
        vanillaGamerules.add(GameRules.RULE_FREEZE_DAMAGE);
        vanillaGamerules.add(GameRules.RULE_DO_PATROL_SPAWNING);
        vanillaGamerules.add(GameRules.RULE_DO_TRADER_SPAWNING);
        vanillaGamerules.add(GameRules.RULE_DO_WARDEN_SPAWNING);
        vanillaGamerules.add(GameRules.RULE_FORGIVE_DEAD_PLAYERS);
        vanillaGamerules.add(GameRules.RULE_UNIVERSAL_ANGER);
        vanillaGamerules.add(GameRules.RULE_RANDOMTICKING);
        vanillaGamerules.add(GameRules.RULE_SPAWN_RADIUS);
        vanillaGamerules.add(GameRules.RULE_MAX_ENTITY_CRAMMING);
        vanillaGamerules.add(GameRules.RULE_MAX_COMMAND_CHAIN_LENGTH);
        vanillaGamerules.add(GameRules.RULE_PLAYERS_SLEEPING_PERCENTAGE);
    }
}