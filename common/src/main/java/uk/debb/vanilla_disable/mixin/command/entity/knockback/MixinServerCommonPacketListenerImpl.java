package uk.debb.vanilla_disable.mixin.command.entity.knockback;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundExplodePacket;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(ServerCommonPacketListenerImpl.class)
public abstract class MixinServerCommonPacketListenerImpl {
    @Inject(method = "send(Lnet/minecraft/network/protocol/Packet;)V", at = @At("HEAD"), cancellable = true)
    public void vanillaDisable$send(Packet<?> packet, CallbackInfo ci) {
        if (packet instanceof ClientboundExplodePacket &&
                !(CommandDataHandler.getCachedBoolean("entities", "minecraft:player", "explosion_knockback"))) {
            ci.cancel();
        }
    }
}