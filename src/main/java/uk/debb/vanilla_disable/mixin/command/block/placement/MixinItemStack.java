package uk.debb.vanilla_disable.mixin.command.block.placement;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import static net.minecraft.world.level.dimension.BuiltinDimensionTypes.*;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {
    @Shadow
    public abstract Item getItem();

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        ResourceLocation dimensionType = context.getLevel().dimensionType().effectsLocation();
        String name = "";
        if (this.getItem() instanceof BlockItem blockItem) {
            name = CommandDataHandler.getKeyFromBlockRegistry(blockItem.getBlock());
        } else if (this.getItem() instanceof BucketItem bucketItem) {
            if ((bucketItem.content.equals(Fluids.WATER) || bucketItem.content.equals(Fluids.FLOWING_WATER)) &&
                    !dimensionType.equals(NETHER_EFFECTS)) {
                name = "minecraft:water";
            } else if (bucketItem.content.equals(Fluids.LAVA) || bucketItem.content.equals(Fluids.FLOWING_LAVA)) {
                name = "minecraft:lava";
            }
        }
        if (name.equals("minecraft:powder_snow_bucket")) {
            name = "minecraft:powder_snow";
        }
        if (!name.equals("")) {
            String col = "invalid dimension";
            if (dimensionType.equals(OVERWORLD_EFFECTS)) {
                col = "can_place_in_overworld";
            } else if (dimensionType.equals(NETHER_EFFECTS)) {
                col = "can_place_in_nether";
            } else if (dimensionType.equals(END_EFFECTS)) {
                col = "can_place_in_end";
            }
            if (!CommandDataHandler.getCachedBoolean("blocks", name, col)) {
                cir.setReturnValue(InteractionResult.FAIL);
            }
        }
    }
}
