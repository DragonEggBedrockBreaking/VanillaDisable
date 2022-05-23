package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Player.class)
public abstract class MixinPlayer {
    /**
     * @author DragonEggBedrockBreaking
     * @reason creepers ignite when clicked with flint and steel, fire aspect should be the same
     * @param target the entity the player is targetting
     * @param ci the callback info
     */
    @Inject(method = "attack", at = @At("RETURN"), cancellable = true)
    private void igniteCreeper(Entity target, CallbackInfo ci) {
        if (VDServer.getServer() == null) return;
        if (VDServer.getServer().getGameRules().getBoolean(Gamerules.FIRE_ASPECT_IGNITES_CREEPERS) &&
            target instanceof Creeper && EnchantmentHelper.getFireAspect((Player)(Object)this) > 0) {
            ((Creeper)target).ignite();
        }
    }
}
