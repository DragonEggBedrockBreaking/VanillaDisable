/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.entity.spawning;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import uk.debb.vanilla_disable.config.data.DataUtils;
import uk.debb.vanilla_disable.config.data.SqlManager;

import java.util.Optional;

@Mixin(SpawnEggItem.class)
public abstract class MixinSpawnEggItem {
    @WrapMethod(method = "useOn")
    private InteractionResult vanillaDisable$useOn(UseOnContext context, Operation<InteractionResult> original) {
        ItemStack itemStack = context.getItemInHand();
        String entity = DataUtils.getKeyFromEntityTypeRegistry(((SpawnEggItem) (Object) this).getType(context.getLevel().registryAccess(), itemStack));
        if (SqlManager.getBoolean("entities", entity, "spawn_egg")) {
            return original.call(context);
        }
        return InteractionResult.FAIL;
    }

    @WrapMethod(method = "spawnOffspringFromSpawnEgg")
    private Optional<Mob> vanillaDisable$spawnOffspringFromSpawnEgg(Player player, Mob mob, EntityType<? extends Mob> entityType, ServerLevel serverLevel, Vec3 pos, ItemStack stack, Operation<Optional<Mob>> original) {
        String entity = DataUtils.getKeyFromEntityTypeRegistry(entityType);
        if (SqlManager.getBoolean("entities", entity, "spawn_egg")) {
            return original.call(player, mob, entityType, serverLevel, pos, stack);
        }
        return Optional.empty();
    }
}
