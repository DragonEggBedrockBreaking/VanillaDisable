package uk.debb.vanilla_disable.mixin.food.hunger;

import net.minecraft.world.Difficulty;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(FoodData.class)
public abstract class MixinFoodData {
    /**
     * @param level the level
     * @return peaceful if old hunger enabled else the difficulty
     * @author DragonEggBedrockBreaking
     * @reason stop hunger from reducing
     */
    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;getDifficulty()Lnet/minecraft/world/Difficulty;"
            )
    )
    private Difficulty getWrongDifficulty(Level level) {
        if (VDServer.getServer() == null) {
            return level.getDifficulty();
        }
        if (GameruleHelper.getBool(Gamerules.OLD_HUNGER)) {
            GameruleHelper.setBool(GameRules.RULE_NATURAL_REGENERATION, false);
            return Difficulty.PEACEFUL;
        }
        return level.getDifficulty();
    }
}