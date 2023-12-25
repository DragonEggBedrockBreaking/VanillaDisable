package uk.debb.vanilla_disable.mixin.command.entity.breeding;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(Villager.class)
public abstract class MixinVillager {
    @ModifyExpressionValue(
            method = "wantsToPickUp",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Set;contains(Ljava/lang/Object;)Z"
            )
    )
    private boolean vanillaDisable$contains(boolean original, ItemStack stack) {
        String name = "can_breed_with_" + CommandDataHandler.getKeyFromItemRegistry(stack.getItem());
        return CommandDataHandler.getCachedBoolean("entities", "minecraft:villager", name);
    }
}
