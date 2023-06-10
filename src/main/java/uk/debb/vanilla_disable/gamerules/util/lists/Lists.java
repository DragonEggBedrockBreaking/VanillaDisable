package uk.debb.vanilla_disable.gamerules.util.lists;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.core.Holder;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.biome.Biome;

public interface Lists {
    ObjectList<String> serverPlayerStringList = new ObjectArrayList<>();
    ObjectList<Holder<Biome>> netherBiomes = new ObjectArrayList<>();
    ObjectList<Holder<Biome>> theEndBiomes = new ObjectArrayList<>();
    ObjectList<GameRules.Key<?>> vanillaGamerules = new ObjectArrayList<>();
}