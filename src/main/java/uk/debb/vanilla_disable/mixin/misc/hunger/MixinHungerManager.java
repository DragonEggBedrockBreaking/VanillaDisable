package uk.debb.vanilla_disable.mixin.misc.hunger;

import net.minecraft.entity.player.HungerManager;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HungerManager.class)
public abstract class MixinHungerManager {
    /**
     * @author DragonEggBedrockBreaking
     * @reason stop hunger from reducing
     * @param world the world
     * @return peaceful if old hunger enabled else the difficulty
     */
    @Redirect(
        method = "update",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/World;getDifficulty()Lnet/minecraft/world/Difficulty;"
        )
    )
    private Difficulty getWrongDifficulty(World world) {
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.OLD_HUNGER)) {
            RegisterGamerules.getServer().getGameRules().get(GameRules.NATURAL_REGENERATION).set(false, RegisterGamerules.getServer());
            return Difficulty.PEACEFUL;
        }
        return world.getDifficulty();
    }
}
