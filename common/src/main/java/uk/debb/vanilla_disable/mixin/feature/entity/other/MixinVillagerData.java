/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.entity.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.config.data.DataDefinitions;
import uk.debb.vanilla_disable.config.data.DataUtils;
import uk.debb.vanilla_disable.config.data.SqlManager;

import java.util.Objects;

@Mixin(VillagerData.class)
public abstract class MixinVillagerData {
    @ModifyReturnValue(method = "type", at = @At("RETURN"))
    private Holder<VillagerType> vanillaDisable$type(Holder<VillagerType> original) {
        if (SqlManager.isConnectionNull()) return original;
        if (DataDefinitions.villagerTypeRegistry.getKey(original.value()) == null) return original;
        if (!SqlManager.getBoolean("entities", "minecraft:villager",
                DataUtils.lightCleanup(Objects.requireNonNull(DataDefinitions.villagerTypeRegistry.getKey(original.value()))) + "_type")) {
            return DataDefinitions.villagerTypeRegistry.getOrThrow(VillagerType.PLAINS);
        }
        return original;
    }

    @ModifyReturnValue(method = "profession", at = @At("RETURN"))
    private Holder<VillagerProfession> vanillaDisable$profession(Holder<VillagerProfession> original) {
        if (SqlManager.isConnectionNull()) return original;
        if (DataDefinitions.villagerProfessionRegistry.getKey(original.value()) == null) return original;
        if (!SqlManager.getBoolean("entities", "minecraft:villager",
                DataUtils.lightCleanup(Objects.requireNonNull(DataDefinitions.villagerProfessionRegistry.getKey(original.value()))) + "_profession")) {
            return DataDefinitions.villagerProfessionRegistry.getOrThrow(VillagerProfession.NONE);
        }
        return original;
    }
}
