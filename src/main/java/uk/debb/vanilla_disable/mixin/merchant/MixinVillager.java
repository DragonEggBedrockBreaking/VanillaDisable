package uk.debb.vanilla_disable.mixin.merchant;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
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

    @ModifyReturnValue(method = "allowedToRestock", at = @At("RETURN"))
    private boolean editRestockFrequency(boolean original) {
        int villagerDailyRestocks = Gamerules.VILLAGER_DAILY_RESTOCKS.getValue(Integer::parseInt);
        if (villagerDailyRestocks == 0) return original;
        long restockTimeLimit = 4800L / villagerDailyRestocks;
        return this.numberOfRestocksToday == 0 | this.numberOfRestocksToday < 2 && this.level.getGameTime() > this.lastRestockGameTime + restockTimeLimit;
    }
}
