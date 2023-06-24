package uk.debb.vanilla_disable.mixin.command.mob_category;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.MobCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.data.command.CommandDataHandler;

@Mixin(MobCategory.class)
public abstract class MixinMobCategory {
    @Shadow
    public abstract String getName();

    @ModifyReturnValue(method = "getMaxInstancesPerChunk", at = @At("RETURN"))
    private int getMaxInstancesPerChunk(int original) {
        if (CommandDataHandler.isConnectionNull()) return original;
        return CommandDataHandler.getCachedInt("mob_categories", this.getName(), "mobcap");
    }
}
