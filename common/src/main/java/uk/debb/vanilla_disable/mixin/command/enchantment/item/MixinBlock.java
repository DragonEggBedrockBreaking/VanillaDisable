package uk.debb.vanilla_disable.mixin.command.enchantment.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Inject(method = "getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)Ljava/util/List;", at = @At("HEAD"), cancellable = true)
    private static void vanillaDisable$getDrops(BlockState state, ServerLevel level, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack tool, CallbackInfoReturnable<List<ItemStack>> cir) {
        String item = "can_enchant_" + CommandDataHandler.lightCleanup(CommandDataHandler.getKeyFromItemRegistry(tool.getItem()));
        EnchantmentHelper.setEnchantments(
                EnchantmentHelper.getEnchantments(tool)
                        .entrySet()
                        .stream()
                        .filter(entry -> {
                            String enchantment = CommandDataHandler.getKeyFromEnchantmentRegistry(entry.getKey());
                            return CommandDataHandler.getCachedBoolean("enchantments", enchantment, item);
                        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), tool);
        LootParams.Builder builder = new LootParams.Builder(level)
                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                .withParameter(LootContextParams.TOOL, tool)
                .withOptionalParameter(LootContextParams.THIS_ENTITY, entity)
                .withOptionalParameter(LootContextParams.BLOCK_ENTITY, blockEntity);
        cir.setReturnValue(state.getDrops(builder));
    }
}
