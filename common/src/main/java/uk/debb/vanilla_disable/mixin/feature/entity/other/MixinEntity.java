/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.entity.other;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.config.data.DataUtils;
import uk.debb.vanilla_disable.config.data.SqlManager;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow
    private BlockPos blockPosition;
    @Shadow
    private Level level;

    @Shadow
    public abstract void discard();

    @Shadow
    public abstract EntityType<?> getType();

    @SuppressWarnings("deprecation")
    @Inject(method = "onInsideBlock", at = @At("HEAD"))
    private void vanillaDisable$onInsideBlock(CallbackInfo ci) {
        String name = DataUtils.getKeyFromEntityTypeRegistry(this.getType());
        if (((Entity) (Object) this) instanceof Boat boat && SqlManager.getBoolean("entities", name, "alpha_behaviour")) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockState otherBlockState = this.level.getBlockState(this.blockPosition.relative(direction));
                if (otherBlockState.isSolid()) {
                    boat.hurt(this.level.damageSources().generic(), Float.MAX_VALUE);
                }
            }
        }
    }

    @Inject(method = "checkDespawn", at = @At("HEAD"), cancellable = true)
    private void vanillaDisable$checkDespawn(CallbackInfo ci) {
        String entityName = DataUtils.getKeyFromEntityTypeRegistry(this.getType());
        if (!SqlManager.getBoolean("entities", entityName, "can_exist")) {
            this.discard();
            ci.cancel();
        }
    }
}
