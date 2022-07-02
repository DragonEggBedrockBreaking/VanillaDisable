package uk.debb.vanilla_disable.mixin.ai;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LlamaFollowCaravanGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(Goal.class)
public abstract class MixinGoal {
    @Unique
    private static final Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> goalMap = new Object2ObjectOpenHashMap<>();

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addOptionsToMap() {
        goalMap.put(Bee.BaseBeeGoal.class, Gamerules.BEE_AI);
        goalMap.put(Bee.BeeWanderGoal.class, Gamerules.BEE_AI);
        goalMap.put(Blaze.BlazeAttackGoal.class, Gamerules.BLAZE_AI);
        goalMap.put(Dolphin.DolphinSwimToTreasureGoal.class, Gamerules.DOLPHIN_AI);
        goalMap.put(Dolphin.DolphinSwimWithPlayerGoal.class, Gamerules.DOLPHIN_AI);
        goalMap.put(Dolphin.PlayWithItemsGoal.class, Gamerules.DOLPHIN_AI);
        goalMap.put(Drowned.DrownedSwimUpGoal.class, Gamerules.DROWNED_AI);
        goalMap.put(Drowned.DrownedGoToWaterGoal.class, Gamerules.DROWNED_AI);
        goalMap.put(EnderMan.EndermanFreezeWhenLookedAt.class, Gamerules.ENDERMAN_AI);
        goalMap.put(EnderMan.EndermanLeaveBlockGoal.class, Gamerules.ENDERMAN_AI);
        goalMap.put(EnderMan.EndermanTakeBlockGoal.class, Gamerules.ENDERMAN_AI);
        goalMap.put(Fox.FoxBehaviorGoal.class, Gamerules.FOX_AI);
        goalMap.put(Fox.FoxSearchForItemsGoal.class, Gamerules.FOX_AI);
        goalMap.put(Ghast.GhastLookGoal.class, Gamerules.GHAST_AI);
        goalMap.put(Ghast.GhastShootFireballGoal.class, Gamerules.GHAST_AI);
        goalMap.put(Ghast.RandomFloatAroundGoal.class, Gamerules.GHAST_AI);
        goalMap.put(Guardian.GuardianAttackGoal.class, Gamerules.GUARDIAN_AI);
        goalMap.put(LlamaFollowCaravanGoal.class, Gamerules.LLAMA_AI);
        goalMap.put(Panda.PandaLieOnBackGoal.class, Gamerules.PANDA_AI);
        goalMap.put(Panda.PandaRollGoal.class, Gamerules.PANDA_AI);
        goalMap.put(Panda.PandaSitGoal.class, Gamerules.PANDA_AI);
        goalMap.put(Panda.PandaSneezeGoal.class, Gamerules.PANDA_AI);
        goalMap.put(Phantom.PhantomAttackPlayerTargetGoal.class, Gamerules.PHANTOM_AI);
        goalMap.put(Phantom.PhantomAttackStrategyGoal.class, Gamerules.PHANTOM_AI);
        goalMap.put(Phantom.PhantomMoveTargetGoal.class, Gamerules.PHANTOM_AI);
        goalMap.put(Pufferfish.PufferfishPuffGoal.class, Gamerules.PUFFERFISH_AI);
        goalMap.put(Shulker.ShulkerAttackGoal.class, Gamerules.SHULKER_AI);
        goalMap.put(Shulker.ShulkerPeekGoal.class, Gamerules.SHULKER_AI);
        goalMap.put(Silverfish.SilverfishWakeUpFriendsGoal.class, Gamerules.SHULKER_AI);
        goalMap.put(Slime.SlimeAttackGoal.class, Gamerules.SLIME_AI);
        goalMap.put(Slime.SlimeFloatGoal.class, Gamerules.SLIME_AI);
        goalMap.put(Slime.SlimeKeepOnJumpingGoal.class, Gamerules.SLIME_AI);
        goalMap.put(Slime.SlimeRandomDirectionGoal.class, Gamerules.SLIME_AI);
        goalMap.put(Squid.SquidRandomMovementGoal.class, Gamerules.SQUID_AI);
        goalMap.put(Squid.SquidFleeGoal.class, Gamerules.SQUID_AI);
        goalMap.put(Turtle.TurtleGoHomeGoal.class, Gamerules.TURTLE_AI);
        goalMap.put(Turtle.TurtleTravelGoal.class, Gamerules.TURTLE_AI);
        goalMap.put(Vex.VexChargeAttackGoal.class, Gamerules.VEX_AI);
        goalMap.put(Vex.VexRandomMoveGoal.class, Gamerules.VEX_AI);
    }

    /**
     * @param cir the returnable callback info (Boolean)
     * @author DragonEggBedrockNreaking
     * @reason block some AIs based on gamerules
     */
    @Inject(method = "canContinueToUse", at = @At("HEAD"), cancellable = true)
    private void blockContinuance(CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (goalMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = goalMap.get(this.getClass());
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            cir.setReturnValue(false);
        }
    }
}