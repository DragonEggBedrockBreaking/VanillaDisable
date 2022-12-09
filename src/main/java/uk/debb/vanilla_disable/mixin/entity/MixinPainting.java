package uk.debb.vanilla_disable.mixin.entity;

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
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Painting.class)
public abstract class MixinPainting implements Maps {
    @Shadow
    @Final
    private static ResourceKey<PaintingVariant> DEFAULT_VARIANT;

    @ModifyReturnValue(method = "getVariant", at = @At("RETURN"))
    private Holder<PaintingVariant> modifyVariant(Holder<PaintingVariant> original) {
        Gamerules gameRule = paintingHolderPaintingVariantMap.get(original);
        if (!Gamerules.ALTERNATE_PAINTINGS_ENABLED.getBool() || (gameRule != null && !gameRule.getBool())) {
            return BuiltInRegistries.PAINTING_VARIANT.getHolderOrThrow(DEFAULT_VARIANT);
        }
        return original;
    }
}
