package uk.debb.vanilla_disable.mixin.command.entity.trading;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

import java.io.IOException;

@Mixin(Villager.class)
public abstract class MixinVillager {
    @Shadow
    private int numberOfRestocksToday;
    @Shadow
    private long lastRestockGameTime;

    @ModifyReturnValue(method = "allowedToRestock", at = @At("RETURN"))
    private boolean vanillaDisable$allowedToRestock(boolean original) {
        int villagerDailyRestocks = CommandDataHandler.getCachedInt("entities", "minecraft:villager", "daily_restocks");
        long restockTimeLimit = 4800L / villagerDailyRestocks;
        try (Level level = ((Entity) (Object) this).level()){
            return this.numberOfRestocksToday == 0 || this.numberOfRestocksToday < 2 && level.getGameTime() > this.lastRestockGameTime + restockTimeLimit;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
