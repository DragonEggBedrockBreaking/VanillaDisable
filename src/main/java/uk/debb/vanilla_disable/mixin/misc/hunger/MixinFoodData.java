package uk.debb.vanilla_disable.mixin.misc.hunger;

import net.minecraft.world.Difficulty;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FoodData.class)
public abstract class MixinFoodData {
    /**
     * @author DragonEggBedrockBreaking
     * @reason stop hunger from reducing
     * @param world the world
     * @return peaceful if old hunger enabled else the difficulty
     */
    @Redirect(
        method = "tick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;getDifficulty()Lnet/minecraft/world/Difficulty;"
        )
    )
    private Difficulty getWrongDifficulty(Level level) {
        if (RegisterGamerules.getServer() == null) {
            return level.getDifficulty();
        }
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.OLD_HUNGER)) {
            RegisterGamerules.getServer().getGameRules().getRule(GameRules.RULE_NATURAL_REGENERATION).set(false, RegisterGamerules.getServer());
            return Difficulty.PEACEFUL;
        }
        return level.getDifficulty();
    }
}
