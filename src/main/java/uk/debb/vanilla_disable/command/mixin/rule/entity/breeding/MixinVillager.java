package uk.debb.vanilla_disable.command.mixin.rule.entity.breeding;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(Villager.class)
public abstract class MixinVillager {
    @ModifyExpressionValue(
            method = "wantsToPickUp",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Set;contains(Ljava/lang/Object;)Z"
            )
    )
    private boolean contains(boolean original, ItemStack itemStack) {
        String name = "can_breed_with_" + CommandDataHandler.getKeyFromItemRegistry(itemStack.getItem());
        return CommandDataHandler.getCachedBoolean("entities", "minecraft:villager", name);
    }
}
