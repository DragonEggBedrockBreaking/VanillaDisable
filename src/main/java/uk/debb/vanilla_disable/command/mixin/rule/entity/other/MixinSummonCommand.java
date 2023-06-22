package uk.debb.vanilla_disable.command.mixin.rule.entity.other;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.SummonCommand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(SummonCommand.class)
public abstract class MixinSummonCommand {
    @Inject(method = "createEntity", at = @At("HEAD"))
    private static void createEntity(CommandSourceStack commandSourceStack, Holder.Reference<EntityType<?>> reference, Vec3 vec3, CompoundTag compoundTag, boolean bl, CallbackInfoReturnable<Entity> cir) throws CommandSyntaxException {
        String entity = CommandDataHandler.getKeyFromEntityTypeRegistry(reference.value());
        if (!CommandDataHandler.getCachedBoolean("entities", entity, "can_be_summoned")) {
            throw new SimpleCommandExceptionType(Component.translatable("vd.commands.summon.disabled")).create();
        }
    }
}