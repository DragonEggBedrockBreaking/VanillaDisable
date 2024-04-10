package uk.debb.vanilla_disable.mixin.command.item.potion;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import java.util.Objects;
import java.util.Optional;

@Mixin(Arrow.class)
public abstract class MixinArrow {
    @ModifyReturnValue(method = "getPotionContents", at = @At("RETURN"))
    private PotionContents vanillaDisable$getPotionContents(PotionContents original) {
        if (((Arrow) (Object) this).getPickupItemStackOrigin().is(Items.TIPPED_ARROW)) {
            Optional<Holder<Potion>> optionalPotionHolder = original.potion();
            if (optionalPotionHolder.isPresent()) {
                String potion = Objects.requireNonNull(CommandDataHandler.potionRegistry.getKey(optionalPotionHolder.get().value())) + "_effect";
                if (!CommandDataHandler.getCachedBoolean("items", "minecraft:tipped_arrow", CommandDataHandler.lightCleanup(potion))) {
                    return PotionContents.EMPTY;
                }
            }
        }
        return original;
    }
}
