/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.entity.breeding;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.config.data.DataUtils;
import uk.debb.vanilla_disable.config.data.SqlManager;

@Mixin(Villager.class)
public abstract class MixinVillager {
    @Shadow
    public abstract VillagerData getVillagerData();

    @ModifyReturnValue(method = "wantsToPickUp", at = @At("RETURN"))
    private boolean vanillaDisable$wantsToPickUp(boolean original, ServerLevel serverLevel, ItemStack itemStack) {
        String name = "can_breed_with_" + DataUtils.lightCleanup(DataUtils.getKeyFromItemRegistry(itemStack.getItem()));
        return (SqlManager.getBoolean("entities", "minecraft:villager", name) ||
                this.getVillagerData().profession().value().requestedItems().contains(itemStack.getItem())) &&
                ((Villager) (Object) this).getInventory().canAddItem(itemStack);
    }
}
