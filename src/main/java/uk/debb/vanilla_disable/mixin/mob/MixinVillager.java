package uk.debb.vanilla_disable.mixin.mob;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

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
     * @param level       the level
     * @param serverWorld the world
     * @param entity      the lightning
     * @return the difficulty
     * @author DragonEggBedrockBreaking
     * @reason stop villagers from turning into witches
     */
    @Redirect(
            method = "thunderHit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;getDifficulty()Lnet/minecraft/world/Difficulty;"
            )
    )
    public Difficulty getWrongDifficulty(ServerLevel level, ServerLevel serverWorld, LightningBolt entity) {
        if (VDServer.getServer() == null) {
            return level.getDifficulty();
        }
        if (GameruleHelper.getBool(Gamerules.VILLAGERS_CONVERT_TO_WITCHES)) {
            return level.getDifficulty();
        } else {
            return Difficulty.PEACEFUL;
        }
    }

    /**
     * @param cir the returnable callback info
     * @author DragonEggBedrockBreaking
     * @reason change how frequently villagers can restock
     */
    @Inject(method = "allowedToRestock", at = @At("HEAD"), cancellable = true)
    private void editRestockFrequency(CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        int villagerDailyRestocks = GameruleHelper.getInt(Gamerules.VILLAGER_DAILY_RESTOCKS);
        if (villagerDailyRestocks == 0) return;
        long restockTimeLimit = 4800L / villagerDailyRestocks;
        cir.setReturnValue(this.numberOfRestocksToday == 0 | this.numberOfRestocksToday < 2 && this.level.getGameTime() > this.lastRestockGameTime + restockTimeLimit);
    }
}