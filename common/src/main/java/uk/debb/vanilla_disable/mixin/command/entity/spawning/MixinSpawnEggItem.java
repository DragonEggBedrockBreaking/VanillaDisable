package uk.debb.vanilla_disable.mixin.command.entity.spawning;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import java.util.Optional;

@Mixin(SpawnEggItem.class)
public abstract class MixinSpawnEggItem {
    @Shadow
    public abstract EntityType<?> getType(@Nullable CompoundTag compoundTag);

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemStack = context.getItemInHand();
        String entity = CommandDataHandler.getKeyFromEntityTypeRegistry(this.getType(itemStack.getTag()));
        if (!CommandDataHandler.getCachedBoolean("entities", entity, "spawn_egg")) {
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }

    @Inject(method = "spawnOffspringFromSpawnEgg", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$spawnOffspringFromSpawnEgg(Player player, Mob mob, EntityType<? extends Mob> entityType, ServerLevel serverLevel, Vec3 pos, ItemStack stack, CallbackInfoReturnable<Optional<Mob>> cir) {
        String entity = CommandDataHandler.getKeyFromEntityTypeRegistry(entityType);
        if (!CommandDataHandler.getCachedBoolean("entities", entity, "spawn_egg")) {
            cir.setReturnValue(Optional.empty());
        }
    }
}
