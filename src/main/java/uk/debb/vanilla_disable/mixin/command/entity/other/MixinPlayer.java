package uk.debb.vanilla_disable.mixin.command.entity.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import java.util.Objects;

@Mixin(Player.class)
public abstract class MixinPlayer {
    @ModifyReturnValue(method = "isInvulnerableTo", at = @At(value = "RETURN"))
    private boolean isInvulnerableTo(boolean original, DamageSource damageSource) {
        return original || !CommandDataHandler.getCachedBoolean("entities", "minecraft:player",
                CommandDataHandler.lightCleanup(Objects.requireNonNull(CommandDataHandler.damageTypeRegistry.getKey(damageSource.type()))) + "_damage");
    }

    @Inject(method = "attack", at = @At("RETURN"))
    private void attack(Entity target, CallbackInfo ci) {
        if (target instanceof Creeper creeper && EnchantmentHelper.getFireAspect((Player) (Object) this) > 0 &&
                CommandDataHandler.getCachedBoolean("entities", "minecraft:creeper", "can_be_lit_by_fire_aspect")) {
            creeper.ignite();
        }
    }

    @Inject(method = "interactOn", at = @At("HEAD"), cancellable = true)
    private void interactOn(Entity entity, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
        String entityType = CommandDataHandler.getKeyFromEntityTypeRegistry(entity.getType());
        if (!CommandDataHandler.getCachedBoolean("entities", entityType, "can_player_interact")) {
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }
}
