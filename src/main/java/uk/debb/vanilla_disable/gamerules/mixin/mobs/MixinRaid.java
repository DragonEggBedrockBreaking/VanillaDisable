package uk.debb.vanilla_disable.gamerules.mixin.mobs;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.raid.Raid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.gamerules.gamerules.Gamerules;

@Mixin(Raid.class)
public abstract class MixinRaid {
    @ModifyReturnValue(method = "getNumGroups", at = @At("RETURN"))
    private int modifyNumGroups(int original, Difficulty difficulty) {
        switch (difficulty) {
            case EASY -> {
                return Gamerules.RAID_WAVES_EASY.getInt() - 1;
            }
            case NORMAL -> {
                return Gamerules.RAID_WAVES_NORMAL.getInt() - 1;
            }
            case HARD -> {
                return Gamerules.RAID_WAVES_HARD.getInt() - 1;
            }
            default -> {
                return 0;
            }
        }
    }
}
