package uk.debb.vanilla_disable.mixin.misc.hunger;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {
    /**
     * @author DragonEggBedrockBreaking
     * @reason increases your health when you eat food
     */
    @Inject(method = "eatFood", at = @At("HEAD"), cancellable = true)
    private void changeEatingFood(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.OLD_HUNGER) && stack.getItem().isFood()) {
            ((LivingEntity)(Object)this).setHealth(((LivingEntity)(Object)this).getHealth() + stack.getItem().getFoodComponent().getHunger());
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason allows you to eat food with a full hunger bar
     * @param hungerManager the hunger manager
     * @param ignoreHunger whether or not to ignore hunger
     * @return whether one can eat food
     */
    @Redirect(
        method = "canConsume",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/player/HungerManager;isNotFull()Z"
        )
    )
    public boolean isNeverFull(HungerManager hungerManager, boolean ignoreHunger) {
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.OLD_HUNGER)) {
            return ((LivingEntity)(Object)this).getHealth() < ((LivingEntity)(Object)this).getMaxHealth();
        }
        return hungerManager.isNotFull();
    }
}
