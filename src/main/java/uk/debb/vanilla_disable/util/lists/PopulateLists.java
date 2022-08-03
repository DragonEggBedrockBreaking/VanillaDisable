package uk.debb.vanilla_disable.util.lists;

import net.minecraft.data.BuiltinRegistries;
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
    }
}