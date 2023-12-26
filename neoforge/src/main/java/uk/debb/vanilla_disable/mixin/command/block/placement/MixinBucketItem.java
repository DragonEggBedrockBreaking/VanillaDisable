package uk.debb.vanilla_disable.mixin.command.block.placement;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(BucketItem.class)
public abstract class MixinBucketItem {
    @Shadow
    @Final
    private Fluid content;

    @ModifyExpressionValue(
            method = "emptyContents(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/BlockHitResult;Lnet/minecraft/world/item/ItemStack;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/neoforged/neoforge/fluids/FluidType;isVaporizedOnPlacement(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/neoforged/neoforge/fluids/FluidStack;)Z"
            )
    )
    private boolean vanillaDisable$isVaporisedOnPlacement(boolean original) {
        if (original && this.content.equals(Fluids.WATER)) {
            return !CommandDataHandler.getCachedBoolean("blocks", "minecraft:water", "can_place_in_nether");
        }
        return original;
    }

    @ModifyExpressionValue(
            method = "emptyContents(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/BlockHitResult;Lnet/minecraft/world/item/ItemStack;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/dimension/DimensionType;ultraWarm()Z"
            )
    )
    private boolean vanillaDisable$ultraWarm(boolean original) {
        if (original && this.content.equals(Fluids.WATER)) {
            return !CommandDataHandler.getCachedBoolean("blocks", "minecraft:water", "can_place_in_nether");
        }
        return original;
    }
}
