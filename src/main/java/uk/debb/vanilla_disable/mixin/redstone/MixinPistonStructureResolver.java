package uk.debb.vanilla_disable.mixin.redstone;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;

@Restriction(
        conflict = {
                @Condition("carpet")
        }
)
@Mixin(PistonStructureResolver.class)
public abstract class MixinPistonStructureResolver {
    /**
     * @param oldPushLimit 12; the vanilla push limit
     * @return the push limit in our gamerules
     * @author DragonEggBedrockBreaking
     * @reason edit the piston push limit
     */
    @ModifyConstant(method = "addBlockLine", constant = @Constant(intValue = 12))
    private int pushLimit(int oldPushLimit) {
        return GameruleHelper.getInt(Gamerules.PISTON_PUSH_LIMIT);
    }
}