package uk.debb.vanilla_disable.command.mixin.rule.block.placement;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(BucketItem.class)
public abstract class MixinBucketItem {
    @Shadow
    @Final
    public Fluid content;

    @ModifyExpressionValue(
            method = "emptyContents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/dimension/DimensionType;ultraWarm()Z"
            )
    )
    private boolean isNotUltraWarm(boolean original) {
        if ((this.content.equals(Fluids.WATER) || this.content.equals(Fluids.FLOWING_WATER)) && original) {
            return !CommandDataHandler.getCachedBoolean("blocks", "minecraft:water", "can_place_in_nether");
        }
        return original;
    }
}
