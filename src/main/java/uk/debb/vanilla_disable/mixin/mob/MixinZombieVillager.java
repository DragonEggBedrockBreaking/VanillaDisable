package uk.debb.vanilla_disable.mixin.mob;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.ZombieVillager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(ZombieVillager.class)
public abstract class MixinZombieVillager {
    /**
     * @param original the original value
     * @author DragonEggBedrockBreaking
     * @reason stop curing of zombie villagers
     */
    @ModifyReturnValue(method = "mobInteract", at = @At("RETURN"))
    private InteractionResult cureMob(InteractionResult original) {
        if (VDServer.getServer() == null) return original;
        if (!GameruleHelper.getBool(Gamerules.CURABLE_ZILLAGERS)) {
            return InteractionResult.CONSUME;
        }
        return original;
    }
}