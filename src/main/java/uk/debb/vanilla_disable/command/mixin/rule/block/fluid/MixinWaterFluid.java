package uk.debb.vanilla_disable.command.mixin.rule.block.fluid;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.material.WaterFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(WaterFluid.class)
public abstract class MixinWaterFluid {
    @ModifyReturnValue(method = "getDropOff", at = @At("RETURN"))
    private int getDropOff(int original, LevelReader world) {
        if (world instanceof Level) {
            if (world.dimensionType().ultraWarm()) {
                return CommandDataHandler.getCachedBoolean("blocks", "minecraft:water", "fluid_reaches_far_in_nether") ? 1 : 2;
            } else {
                return CommandDataHandler.getCachedBoolean("blocks", "minecraft:water", "fluid_reaches_far") ? 1 : 2;
            }
        }
        return original;
    }

    @ModifyReturnValue(method = "getTickDelay", at = @At("RETURN"))
    private int getTickDelay(int original, LevelReader world) {
        if (world instanceof Level) {
            if (world.dimensionType().ultraWarm()) {
                return CommandDataHandler.getCachedInt("blocks", "minecraft:water", "fluid_speed_in_nether");
            } else {
                return CommandDataHandler.getCachedInt("blocks", "minecraft:water", "fluid_speed");
            }
        }
        return original;
    }
}