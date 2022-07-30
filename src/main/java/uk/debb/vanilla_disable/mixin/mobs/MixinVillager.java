package uk.debb.vanilla_disable.mixin.mobs;

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
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.IntegerGamerules;

@Mixin(Villager.class)
public abstract class MixinVillager extends Entity {
    @Shadow
    private int numberOfRestocksToday;
    @Shadow
    private long lastRestockGameTime;

    public MixinVillager(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyExpressionValue(
            method = "thunderHit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;getDifficulty()Lnet/minecraft/world/Difficulty;"
            )
    )
    private Difficulty getWrongDifficulty(Difficulty original) {
        if (!GameruleHelper.getBool(BooleanGamerules.VILLAGERS_CONVERT_TO_WITCHES)) {
            return Difficulty.PEACEFUL;
        }
        return original;
    }

    @ModifyReturnValue(method = "allowedToRestock", at = @At("RETURN"))
    private boolean editRestockFrequency(boolean original) {
        int villagerDailyRestocks = GameruleHelper.getInt(IntegerGamerules.VILLAGER_DAILY_RESTOCKS);
        if (villagerDailyRestocks == 0) return original;
        long restockTimeLimit = 4800L / villagerDailyRestocks;
        return this.numberOfRestocksToday == 0 | this.numberOfRestocksToday < 2 && this.level.getGameTime() > this.lastRestockGameTime + restockTimeLimit;
    }
}