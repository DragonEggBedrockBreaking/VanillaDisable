package uk.debb.vanilla_disable.mixin.worldgen;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureCheck;
import net.minecraft.world.level.levelgen.structure.StructureCheckResult;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(StructureCheck.class)
public abstract class MixinStructureCheck implements Maps {
    @ModifyReturnValue(method = "checkStart", at = @At("RETURN"))
    private StructureCheckResult cancelCheckingStart(StructureCheckResult original, ChunkPos chunkPos, Structure structure, boolean bl) {
        Gamerules gameRule;
        if (structure instanceof JigsawStructure jigsawStructure) {
            gameRule = structureCheckStringMap.get(jigsawStructure.startPool.unwrap().orThrow().location().toString());
        } else {
            gameRule = structureCheckStructureTypeMap.get(structure.type());
        }
        if (gameRule != null && !gameRule.getValue(Boolean::parseBoolean)) {
            return StructureCheckResult.START_NOT_PRESENT;
        }
        return original;
    }
}