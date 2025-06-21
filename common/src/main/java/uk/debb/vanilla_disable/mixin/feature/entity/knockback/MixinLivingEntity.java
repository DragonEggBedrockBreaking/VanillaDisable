/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.entity.knockback;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import uk.debb.vanilla_disable.config.data.DataUtils;
import uk.debb.vanilla_disable.config.data.SqlManager;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Shadow
    public abstract @Nullable LivingEntity getLastHurtByMob();

    @WrapMethod(method = "knockback")
    public void vanillaDisable$knockback(double strength, double x, double z, Operation<Void> original) {
        String target = DataUtils.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        LivingEntity lastHurtByMob = this.getLastHurtByMob();
        if (lastHurtByMob != null) {
            String source = DataUtils.getKeyFromEntityTypeRegistry(lastHurtByMob.getType());
            if (!SqlManager.getBoolean("entities", target, DataUtils.lightCleanup(source) + "_knockback")) {
                return;
            }
        }
        original.call(strength, x, z);
    }
}
