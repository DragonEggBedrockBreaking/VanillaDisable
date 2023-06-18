package uk.debb.vanilla_disable.command.mixin.rule.entity.breeding;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin({Animal.class, AbstractHorse.class, Axolotl.class, Bee.class, Camel.class, Cat.class, Chicken.class, Fox.class,
        Frog.class, Hoglin.class, Llama.class, Ocelot.class, Panda.class, Parrot.class, Pig.class, PolarBear.class,
        Rabbit.class, Sniffer.class, Strider.class, Turtle.class, Wolf.class})
public abstract class MultipleMixinFood {
    @ModifyReturnValue(method = "isFood", at = @At("RETURN"))
    private boolean isFood(boolean original, ItemStack itemStack) {
        if (DataHandler.isConnectionNull()) return original;
        String entity = DataHandler.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        String item = "can_breed_with_" + DataHandler.lightCleanup(DataHandler.getKeyFromItemRegistry(itemStack.getItem()));
        return DataHandler.getBoolean("entities", entity, item);
    }
}
