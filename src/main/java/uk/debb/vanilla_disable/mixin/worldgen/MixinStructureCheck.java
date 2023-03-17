package uk.debb.vanilla_disable.mixin.worldgen;

import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureCheck;
import net.minecraft.world.level.levelgen.structure.StructureCheckResult;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(StructureCheck.class)
public abstract class MixinStructureCheck implements Maps {
    @Inject(method = "checkStart", at = @At("HEAD"), cancellable = true)
    private void cancelCheckingStart(ChunkPos chunkPos, Structure structure, boolean bl, CallbackInfoReturnable<StructureCheckResult> cir) {
        Gamerules gameRule;
        if (structure instanceof JigsawStructure jigsawStructure) {
            gameRule = structureCheckStringMap.get(jigsawStructure.startPool.unwrap().orThrow().location().toString());
        } else {
            gameRule = structureCheckStructureTypeMap.get(structure.type());
        }
        if (gameRule != null && !gameRule.getBool()) {
            cir.setReturnValue(StructureCheckResult.START_NOT_PRESENT);
        }
    }
}