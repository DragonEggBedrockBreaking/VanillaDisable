package uk.debb.vanilla_disable.mixin.worldgen;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(StructureCheck.class)
public abstract class MixinStructureCheck implements Maps {
    @ModifyReturnValue(method = "canCreateStructure", at = @At("RETURN"))
    private boolean cancelStructureGeneration(boolean original, ChunkPos chunkPos, Structure structure) {
        GameRules.Key<GameRules.BooleanValue> gameRule = structureCheckClassMap.get(structure.getClass());
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            return false;
        }
        return original;
    }
}