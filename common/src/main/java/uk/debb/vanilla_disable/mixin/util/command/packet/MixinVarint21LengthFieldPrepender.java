package uk.debb.vanilla_disable.mixin.util.command.packet;

import net.minecraft.network.Varint21LengthFieldPrepender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Varint21LengthFieldPrepender.class)
public abstract class MixinVarint21LengthFieldPrepender {
    @ModifyConstant(method = "encode(Lio/netty/channel/ChannelHandlerContext;Lio/netty/buffer/ByteBuf;Lio/netty/buffer/ByteBuf;)V", constant = @Constant(intValue = 3))
    private int vanillaDisable$encode(int value) {
        return 8;
    }
}
