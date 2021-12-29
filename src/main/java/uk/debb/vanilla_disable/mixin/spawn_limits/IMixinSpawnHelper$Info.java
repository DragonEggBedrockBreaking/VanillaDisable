package uk.debb.vanilla_disable.mixin.spawn_limits;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.SpawnDensityCapper;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SpawnHelper.Info.class)
public interface IMixinSpawnHelper$Info {
    @Accessor("spawningChunkCount") 
    int getSpawningChunkCount();
    @Accessor("groupToCount")
    Object2IntOpenHashMap<SpawnGroup> getGroupToCount();
    @Accessor("densityCapper")
    SpawnDensityCapper getDensityCapper();
}
