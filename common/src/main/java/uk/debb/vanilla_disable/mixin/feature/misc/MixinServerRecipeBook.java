/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.misc;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerRecipeBook;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import uk.debb.vanilla_disable.config.data.SqlManager;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Mixin(ServerRecipeBook.class)
public class MixinServerRecipeBook {
    @WrapMethod(method = "loadRecipes")
    private void vanillaDisable$loadRecipes(List<ResourceKey<Recipe<?>>> list, Consumer<ResourceKey<Recipe<?>>> consumer, Predicate<ResourceKey<Recipe<?>>> predicate, Operation<Void> original) {
        if (SqlManager.getBoolean("misc", "recipe_book", "enabled")) {
            original.call(list, consumer, predicate);
        }
    }

    @WrapMethod(method = "sendInitialRecipeBook")
    private void vanillaDisable$sendInitialRecipeBook(ServerPlayer player, Operation<Void> original) {
        if (SqlManager.getBoolean("misc", "recipe_book", "enabled")) {
            original.call(player);
        }
    }
}
