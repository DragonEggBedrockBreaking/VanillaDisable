package uk.debb.vanilla_disable.mixin.redstone;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Restriction(
        conflict = {
                @Condition("carpet")
        }
)
@Mixin(PistonStructureResolver.class)
public abstract class MixinPistonStructureResolver {
    @ModifyConstant(method = "addBlockLine", constant = @Constant(intValue = 12))
    private int pushLimit(int oldPushLimit) {
        return Gamerules.PISTON_PUSH_LIMIT.getValue(Integer::parseInt);
    }
}