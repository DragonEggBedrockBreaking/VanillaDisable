package uk.debb.vanilla_disable.util.lists;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;

public interface Lists {
    ObjectList<String> serverPlayerStringList = new ObjectArrayList<>();
    ObjectList<Holder<Biome>> netherBiomes = new ObjectArrayList<>();
    ObjectList<Holder<Biome>> theEndBiomes = new ObjectArrayList<>();
}