package uk.debb.vanilla_disable.command.mixin.rule.entity.trading;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.command.data.CommandDataHandler;

@Mixin(Villager.class)
public abstract class MixinVillager {
    @Shadow
    private int numberOfRestocksToday;
    @Shadow
    private long lastRestockGameTime;

    @ModifyReturnValue(method = "allowedToRestock", at = @At("RETURN"))
    private boolean editRestockFrequency(boolean original) {
        int villagerDailyRestocks = CommandDataHandler.getCachedInt("entities", "minecraft:villager", "daily_restocks");
        long restockTimeLimit = 4800L / villagerDailyRestocks;
        return this.numberOfRestocksToday == 0 || this.numberOfRestocksToday < 2 && ((Entity) (Object) this).level().getGameTime() > this.lastRestockGameTime + restockTimeLimit;
    }
}
