package uk.debb.vanilla_disable.mixin.command.entity.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import java.util.Objects;

@Mixin(Painting.class)
public abstract class MixinPainting {
    @Shadow
    @Final
    private static ResourceKey<PaintingVariant> DEFAULT_VARIANT;

    @ModifyReturnValue(method = "getVariant", at = @At("RETURN"))
    private Holder<PaintingVariant> getVariant(Holder<PaintingVariant> original) {
        if (!CommandDataHandler.getCachedBoolean("entities", "minecraft:painting",
                CommandDataHandler.lightCleanup(Objects.requireNonNull(CommandDataHandler.paintingVariantRegistry.getKey(original.value()))) + "_painting")) {
            return BuiltInRegistries.PAINTING_VARIANT.getHolderOrThrow(DEFAULT_VARIANT);
        }
        return original;
    }
}
