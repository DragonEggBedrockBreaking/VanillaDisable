package uk.debb.vanilla_disable.mixin.util.gamerule;

import net.minecraft.server.Bootstrap;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.data.gamerule.VDGamerules;

@Mixin(Bootstrap.class)
public abstract class MixinBootstrap {
    @Inject(method = "bootStrap", at = @At("RETURN"))
    private static void vanillaDisable$bootStrap(CallbackInfo ci) {
        VDGamerules.RAID_WAVES_EASY = GameRules.register("raidWavesEasy", GameRules.Category.valueOf("VANILLA_DISABLE"), GameRules.IntegerValue.create(4));
        VDGamerules.RAID_WAVES_NORMAL = GameRules.register("raidWavesNormal", GameRules.Category.valueOf("VANILLA_DISABLE"), GameRules.IntegerValue.create(6));
        VDGamerules.RAID_WAVES_HARD = GameRules.register("raidWavesHard", GameRules.Category.valueOf("VANILLA_DISABLE"), GameRules.IntegerValue.create(8));
        VDGamerules.RECIPE_BOOK_ENABLED = GameRules.register("recipeBookEnabled", GameRules.Category.valueOf("VANILLA_DISABLE"), GameRules.BooleanValue.create(true));
    }
}
