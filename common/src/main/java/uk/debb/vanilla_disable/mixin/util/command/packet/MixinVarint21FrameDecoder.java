package uk.debb.vanilla_disable.mixin.util.command.packet;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.BandwidthDebugMonitor;
import net.minecraft.network.VarInt;
import net.minecraft.network.Varint21FrameDecoder;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(value = Varint21FrameDecoder.class, priority = 1001)
public abstract class MixinVarint21FrameDecoder {
    @Shadow
    private static boolean copyVarint(ByteBuf $$0, ByteBuf $$1) {
        return false;
    }

    @Shadow @Final @Nullable private BandwidthDebugMonitor monitor;

    @Unique
    private final ByteBuf vanillaDisable$helperBuf = Unpooled.directBuffer(8);

    @ModifyReceiver(
            method = "handlerRemoved0",
            at = @At(
                    value = "INVOKE",
                    target = "Lio/netty/buffer/ByteBuf;release()Z"
            )
    )
    private ByteBuf vanillaDisable$release(ByteBuf instance) {
        return this.vanillaDisable$helperBuf;
    }

    @ModifyConstant(method = "copyVarint", constant = @Constant(intValue = 3))
    private static int vanillaDisable$copyVarint1(int value) {
        return 8;
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason Increase the buffer size to 8 bytes
     */
    @Overwrite
    public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> objects) {
        byteBuf.markReaderIndex();
        this.vanillaDisable$helperBuf.clear();
        if (!copyVarint(byteBuf, this.vanillaDisable$helperBuf)) {
            byteBuf.resetReaderIndex();
        } else {
            int i = VarInt.read(this.vanillaDisable$helperBuf);
            if (byteBuf.readableBytes() < i) {
                byteBuf.resetReaderIndex();
            } else {
                if (this.monitor != null) {
                    this.monitor.onReceive(i + VarInt.getByteSize(i));
                }
                objects.add(byteBuf.readBytes(i));
            }
        }
    }
}
