package uk.debb.vanilla_disable.mixin.mob;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(Villager.class)
public abstract class MixinVillager extends Entity {
    @Shadow
    private int numberOfRestocksToday;
    @Shadow
    private long lastRestockGameTime;

    public MixinVillager(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    /**
     * @param original the original difficulty
     * @return the difficulty
     * @author DragonEggBedrockBreaking
     */
    @ModifyExpressionValue(
            method = "thunderHit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;getDifficulty()Lnet/minecraft/world/Difficulty;"
            )
    )
    private Difficulty getWrongDifficulty(Difficulty original) {
        if (!GameruleHelper.getBool(Gamerules.VILLAGERS_CONVERT_TO_WITCHES)) {
            return Difficulty.PEACEFUL;
        }
        return original;
    }

    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     */
    @ModifyReturnValue(method = "allowedToRestock", at = @At("RETURN"))
    private boolean editRestockFrequency(boolean original) {
        int villagerDailyRestocks = GameruleHelper.getInt(Gamerules.VILLAGER_DAILY_RESTOCKS);
        if (villagerDailyRestocks == 0) return original;
        long restockTimeLimit = 4800L / villagerDailyRestocks;
        return this.numberOfRestocksToday == 0 | this.numberOfRestocksToday < 2 && this.level.getGameTime() > this.lastRestockGameTime + restockTimeLimit;
    }
}