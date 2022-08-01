package uk.debb.vanilla_disable.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.maps.Maps;

@Mixin(Painting.class)
public abstract class MixinPainting implements Maps {
    @Shadow
    private static Holder<PaintingVariant> getDefaultVariant() {
        return null;
    }

    @ModifyReturnValue(method = "getVariant", at = @At("RETURN"))
    private Holder<PaintingVariant> modifyVariant(Holder<PaintingVariant> original) {
        Gamerules gameRule = paintingHolderPaintingVariantMap.get(original);
        if (!Gamerules.ALTERNATE_PAINTINGS_ENABLED.getValue(Boolean::parseBoolean) || (gameRule != null && !gameRule.getValue(Boolean::parseBoolean))) {
            return getDefaultVariant();
        }
        return original;
    }
}
