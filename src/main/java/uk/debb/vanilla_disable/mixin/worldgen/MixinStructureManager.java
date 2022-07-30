package uk.debb.vanilla_disable.mixin.worldgen;

import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.maps.Maps;

import java.util.function.Consumer;

@Mixin(StructureManager.class)
public abstract class MixinStructureManager implements Maps {
    @Inject(method = "fillStartsForStructure", at = @At("HEAD"), cancellable = true)
    private void cancelFillingStartsForStructure(Structure structure, LongSet longSet, Consumer<StructureStart> consumer, CallbackInfo ci) {
        BooleanGamerules gameRule;
        if (structure instanceof JigsawStructure jigsawStructure) {
            gameRule = structureCheckStringMap.get(jigsawStructure.startPool.unwrap().orThrow().location().toString());
        } else {
            gameRule = structureCheckStructureTypeMap.get(structure.type());
        }
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            ci.cancel();
        }
    }
}
