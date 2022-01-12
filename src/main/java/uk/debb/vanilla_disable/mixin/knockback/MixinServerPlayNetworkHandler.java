package uk.debb.vanilla_disable.mixin.knockback;

import net.minecraft.network.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class MixinServerPlayNetworkHandler {
    @Shadow public ServerPlayerEntity player;

    /**
     * @author DragonEggBedrockBreaking
     * @reason Disable all explosion knockback if explosion knockback is disabled
     * @param packet the packet
     * @param ci the callback info
     */
    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    public void sendPacket(Packet<?> packet, CallbackInfo ci) {
        if (packet instanceof ExplosionS2CPacket &&
            !(this.player.getWorld().getGameRules().getBoolean(RegisterGamerules.KNOCKBACK_ENABLED) &&
              this.player.getWorld().getGameRules().getBoolean(RegisterGamerules.EXPLOSION_KNOCKBACK))) {
            ci.cancel();
        }
    }
}