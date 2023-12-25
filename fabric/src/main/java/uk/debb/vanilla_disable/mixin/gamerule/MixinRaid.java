package uk.debb.vanilla_disable.mixin.gamerule;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.raid.Raid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.gamerule.RegisterGamerules;

@Mixin(Raid.class)
public abstract class MixinRaid {
    @ModifyReturnValue(method = "getNumGroups", at = @At("RETURN"))
    private int vanillaDisable$getNumGroups(int original, Difficulty difficulty) {
        if (RegisterGamerules.server == null) return original;
        return switch (difficulty) {
            case PEACEFUL -> 0;
            case EASY -> RegisterGamerules.server.getGameRules().getInt(RegisterGamerules.RAID_WAVES_EASY) - 1;
            case NORMAL -> RegisterGamerules.server.getGameRules().getInt(RegisterGamerules.RAID_WAVES_NORMAL) - 1;
            case HARD -> RegisterGamerules.server.getGameRules().getInt(RegisterGamerules.RAID_WAVES_HARD) - 1;
        };
    }
}
