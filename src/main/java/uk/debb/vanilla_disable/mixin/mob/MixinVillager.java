package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(Villager.class)
public abstract class MixinVillager {
    /**
     * @author DragonEggBedrockBreaking
     * @reason stop villagers from turning into witches
     * @param world the world
     * @param serverWorld the world
     * @param entity the lightning
     * @return the difficulty
     */
    @Redirect(
        method = "thunderHit",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/level/ServerLevel;getDifficulty()Lnet/minecraft/world/Difficulty;"
        )
    )
    public Difficulty getWrongDifficulty(ServerLevel world, ServerLevel serverWorld, LightningBolt entity) {
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.VILLAGERS_CONVERT_TO_WITCHES)) {
            return world.getDifficulty();
        } else {
            return Difficulty.PEACEFUL;
        }
    }
}
