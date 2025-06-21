/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package uk.debb.vanilla_disable.mixin.feature.entity.breeding;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.config.data.DataUtils;
import uk.debb.vanilla_disable.config.data.SqlManager;

@Mixin({AbstractCow.class, AbstractHorse.class, Armadillo.class, Axolotl.class, Bee.class, Camel.class, Cat.class, Chicken.class,
        Fox.class, Frog.class, Goat.class, Hoglin.class, Llama.class, Ocelot.class, Panda.class, Parrot.class, Pig.class,
        PolarBear.class, Rabbit.class, Sheep.class, Sniffer.class, Strider.class, Turtle.class, Wolf.class})
public abstract class MultipleMixinFood {
    @ModifyReturnValue(method = "isFood", at = @At("RETURN"))
    private boolean vanillaDisable$isFood(boolean original, ItemStack stack) {
        if (SqlManager.isConnectionNull()) return original;
        String entity = DataUtils.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        String item = "can_breed_with_" + DataUtils.lightCleanup(DataUtils.getKeyFromItemRegistry(stack.getItem()));
        return SqlManager.getBoolean("entities", entity, item);
    }
}
