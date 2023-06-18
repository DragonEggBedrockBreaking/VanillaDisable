package uk.debb.vanilla_disable.command.mixin.rule.entity.other;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.command.data.DataHandler;

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer {
    @Inject(method = "awardStat", at = @At("HEAD"), cancellable = true)
    private void awardStat(Stat<?> stat, int i, CallbackInfo ci) {
        if (stat.getType().equals(Stats.CUSTOM)) {
            if (!DataHandler.getBoolean("entities", "minecraft:player",
                    stat.getName().split(":")[1].replace(".", ":") + "_custom_stat")) {
                ci.cancel();
            }
        } else {
            if (!DataHandler.getBoolean("entities", "minecraft:player", DataHandler.statTypeRegistry.getKey(stat.getType()) + "_stat_type")) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "die", at = @At("HEAD"), cancellable = true)
    private void die(DamageSource damageSource, CallbackInfo ci) {
        if (!DataHandler.getBoolean("entities", "minecraft:player",
                DataHandler.damageTypeRegistry.getKey(damageSource.type()) + "_death")) {
            ((Player) (Object) this).setHealth(1);
            ci.cancel();
        }
    }
}
