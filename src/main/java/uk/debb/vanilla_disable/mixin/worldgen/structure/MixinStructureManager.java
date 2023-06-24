package uk.debb.vanilla_disable.mixin.worldgen.structure;

import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.worldgen.WorldgenDataHandler;

import java.util.Objects;
import java.util.function.Consumer;

@Mixin(StructureManager.class)
public abstract class MixinStructureManager {
    @Inject(method = "fillStartsForStructure", at = @At("HEAD"), cancellable = true)
    private void fillStartsForStructure(Structure structure, LongSet longSet, Consumer<StructureStart> consumer, CallbackInfo ci) {
        String rule = WorldgenDataHandler.cleanup(Objects.requireNonNull(WorldgenDataHandler.structureRegistry.getKey(structure)));
        if (!WorldgenDataHandler.get("structures", rule)) {
            ci.cancel();
        }
    }
}
