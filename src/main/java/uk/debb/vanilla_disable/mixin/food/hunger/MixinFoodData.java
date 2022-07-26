package uk.debb.vanilla_disable.mixin.food.hunger;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.Difficulty;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(FoodData.class)
public abstract class MixinFoodData {
    /**
     * @param original the original value
     * @return peaceful if old hunger enabled else the difficulty
     * @author DragonEggBedrockBreaking
     */
    @ModifyExpressionValue(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;getDifficulty()Lnet/minecraft/world/Difficulty;"
            )
    )
    private Difficulty getWrongDifficulty(Difficulty original) {
        if (VDServer.getServer() == null) {
            return original;
        }
        if (GameruleHelper.getBool(Gamerules.OLD_HUNGER)) {
            GameruleHelper.setBool(GameRules.RULE_NATURAL_REGENERATION, false);
            return Difficulty.PEACEFUL;
        }
        return original;
    }
}