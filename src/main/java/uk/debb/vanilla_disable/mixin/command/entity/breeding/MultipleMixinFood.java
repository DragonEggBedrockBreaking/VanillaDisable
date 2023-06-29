package uk.debb.vanilla_disable.mixin.command.entity.breeding;

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
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin({Animal.class, AbstractHorse.class, Axolotl.class, Bee.class, Camel.class, Cat.class, Chicken.class, Fox.class,
        Frog.class, Hoglin.class, Llama.class, Ocelot.class, Panda.class, Parrot.class, Pig.class, PolarBear.class,
        Rabbit.class, Sniffer.class, Strider.class, Turtle.class, Wolf.class})
public abstract class MultipleMixinFood {
    @ModifyReturnValue(method = "isFood", at = @At("RETURN"))
    private boolean isFood(boolean original, ItemStack stack) {
        if (CommandDataHandler.isConnectionNull()) return original;
        String entity = CommandDataHandler.getKeyFromEntityTypeRegistry(((Entity) (Object) this).getType());
        String item = "can_breed_with_" + CommandDataHandler.lightCleanup(CommandDataHandler.getKeyFromItemRegistry(stack.getItem()));
        return CommandDataHandler.getCachedBoolean("entities", entity, item);
    }
}
