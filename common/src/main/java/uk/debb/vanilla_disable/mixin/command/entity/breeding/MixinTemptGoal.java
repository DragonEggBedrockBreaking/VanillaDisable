package uk.debb.vanilla_disable.mixin.command.entity.breeding;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(TemptGoal.class)
public abstract class MixinTemptGoal {
    @Shadow
    @Final
    protected PathfinderMob mob;

    @ModifyReturnValue(method = "shouldFollow", at = @At("RETURN"))
    private boolean shouldFollow(boolean original, LivingEntity livingEntity) {
        if (CommandDataHandler.isConnectionNull()) return original;
        String entity = CommandDataHandler.getKeyFromEntityTypeRegistry(this.mob.getType());
        Item mainHandItem = livingEntity.getMainHandItem().getItem();
        Item offHandItem = livingEntity.getOffhandItem().getItem();
        return CommandDataHandler.getCachedBreedingItems(entity).stream().anyMatch(itemStack -> itemStack.is(mainHandItem) || itemStack.is(offHandItem));
    }
}
