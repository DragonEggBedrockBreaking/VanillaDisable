package uk.debb.vanilla_disable.mixin.spawning;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.SummonCommand;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.system.CallbackI.I;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(SummonCommand.class)
public abstract class MixinSummonCommand {
    @Shadow
    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslatableText("commands.summon.failed"));
    /**
     * @author DragonEggBedrockBreaking
     * @reason disable the /summon command
     * @param source the source of the command
     * @param entity2 the entity to be summonned
     * @param pos the position to summon the entity at
     * @param nbt the nbt data of the entity
     * @param initialise whether or not to initialise it
     * @param cir the returnable callback info
     */
    @Inject(method = "execute", at = @At(value = "HEAD"))
    private static void execute(ServerCommandSource source, Identifier entity2, Vec3d pos, NbtCompound nbt, boolean initialise, CallbackInfoReturnable<I> cir) throws CommandSyntaxException {
        if (!source.getWorld().getGameRules().getBoolean(RegisterGamerules.SUMMON_COMMAND)) {
            throw FAILED_EXCEPTION.create();
        }
    }
}
