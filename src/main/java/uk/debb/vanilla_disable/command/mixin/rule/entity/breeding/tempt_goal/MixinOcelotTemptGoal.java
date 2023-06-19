package uk.debb.vanilla_disable.command.mixin.rule.entity.breeding.tempt_goal;

import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(Ocelot.OcelotTemptGoal.class)
public abstract class MixinOcelotTemptGoal {
    @ModifyArg(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ai/goal/TemptGoal;<init>(Lnet/minecraft/world/entity/PathfinderMob;DLnet/minecraft/world/item/crafting/Ingredient;Z)V"
            ),
            index = 2
    )
    private static Ingredient getIngredient(Ingredient ingredient) {
        if (CommandDataHandler.isConnectionNull()) return ingredient;
        return CommandDataHandler.getCachedBreedingItems("minecraft:ocelot");
    }
}
