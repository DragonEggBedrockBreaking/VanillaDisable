/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.item.other;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.config.data.DataUtils;
import uk.debb.vanilla_disable.config.data.SqlManager;

@Mixin(Item.class)
public abstract class MixinItem {
    @Shadow
    public abstract Item asItem();

    @ModifyReturnValue(method = "canDestroyBlock", at = @At("RETURN"))
    private boolean vanillaDisable$canDestroyBlock(boolean original) {
        String name = DataUtils.getKeyFromItemRegistry(this.asItem());
        return SqlManager.getBoolean("items", name, "can_break_blocks_in_creative");
    }
}
