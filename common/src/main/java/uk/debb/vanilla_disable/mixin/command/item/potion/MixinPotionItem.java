package uk.debb.vanilla_disable.mixin.command.item.potion;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.Holder;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import java.util.Objects;
import java.util.Optional;

@Mixin(PotionItem.class)
public abstract class MixinPotionItem {
    @ModifyExpressionValue(
            method = "finishUsingItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;getOrDefault(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    private Object vanillaDisable$getOrDefault(Object original) {
        String item = CommandDataHandler.getKeyFromItemRegistry((PotionItem) (Object) this);
        Optional<Holder<Potion>> potion = ((PotionContents) original).potion();
        if (potion.isEmpty()) return original;
        String pot = CommandDataHandler.lightCleanup(Objects.requireNonNull(CommandDataHandler.potionRegistry.getKey(potion.get().value())));
        if (!CommandDataHandler.getCachedBoolean("items", item, pot + "_effect")) {
            return PotionContents.EMPTY;
        }
        return original;
    }
}
