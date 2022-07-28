package uk.debb.vanilla_disable.mixin.ai;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LlamaFollowCaravanGoal;
import net.minecraft.world.entity.ai.goal.OcelotAttackGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

@Mixin(Goal.class)
public abstract class MixinGoal {
    @Unique
    private static final Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> goalMap = new Object2ObjectOpenHashMap<>();

    @Unique
    private void addOptionsToMap() {
        goalMap.put(Bee.BaseBeeGoal.class, Gamerules.BEE_AI);
        goalMap.put(Bee.BeeWanderGoal.class, Gamerules.BEE_AI);
        goalMap.put(Blaze.BlazeAttackGoal.class, Gamerules.BLAZE_AI);
        goalMap.put(Cat.CatRelaxOnOwnerGoal.class, Gamerules.CAT_AI);
        goalMap.put(Dolphin.DolphinSwimToTreasureGoal.class, Gamerules.DOLPHIN_AI);
        goalMap.put(Dolphin.DolphinSwimWithPlayerGoal.class, Gamerules.DOLPHIN_AI);
        goalMap.put(Dolphin.PlayWithItemsGoal.class, Gamerules.DOLPHIN_AI);
        goalMap.put(Drowned.DrownedSwimUpGoal.class, Gamerules.DROWNED_AI);
        goalMap.put(Drowned.DrownedGoToWaterGoal.class, Gamerules.DROWNED_AI);
        goalMap.put(EnderMan.EndermanFreezeWhenLookedAt.class, Gamerules.ENDERMAN_AI);
        goalMap.put(EnderMan.EndermanLeaveBlockGoal.class, Gamerules.ENDERMAN_AI);
        goalMap.put(EnderMan.EndermanTakeBlockGoal.class, Gamerules.ENDERMAN_AI);
        goalMap.put(Fox.FaceplantGoal.class, Gamerules.FOX_AI);
        goalMap.put(Fox.FoxBehaviorGoal.class, Gamerules.FOX_AI);
        goalMap.put(Fox.FoxSearchForItemsGoal.class, Gamerules.FOX_AI);
        goalMap.put(Fox.StalkPreyGoal.class, Gamerules.FOX_AI);
        goalMap.put(Ghast.GhastLookGoal.class, Gamerules.GHAST_AI);
        goalMap.put(Ghast.GhastShootFireballGoal.class, Gamerules.GHAST_AI);
        goalMap.put(Ghast.RandomFloatAroundGoal.class, Gamerules.GHAST_AI);
        goalMap.put(Guardian.GuardianAttackGoal.class, Gamerules.GUARDIAN_AI);
        goalMap.put(LlamaFollowCaravanGoal.class, Gamerules.LLAMA_AI);
        goalMap.put(OcelotAttackGoal.class, Gamerules.OCELOT_AI);
        goalMap.put(Panda.PandaLieOnBackGoal.class, Gamerules.PANDA_AI);
        goalMap.put(Panda.PandaRollGoal.class, Gamerules.PANDA_AI);
        goalMap.put(Panda.PandaSitGoal.class, Gamerules.PANDA_AI);
        goalMap.put(Panda.PandaSneezeGoal.class, Gamerules.PANDA_AI);
        goalMap.put(Phantom.PhantomAttackPlayerTargetGoal.class, Gamerules.PHANTOM_AI);
        goalMap.put(Phantom.PhantomAttackStrategyGoal.class, Gamerules.PHANTOM_AI);
        goalMap.put(Phantom.PhantomMoveTargetGoal.class, Gamerules.PHANTOM_AI);
        goalMap.put(Pufferfish.PufferfishPuffGoal.class, Gamerules.PUFFERFISH_AI);
        goalMap.put(Raider.HoldGroundAttackGoal.class, Gamerules.RAIDER_AI);
        goalMap.put(Raider.ObtainRaidLeaderBannerGoal.class, Gamerules.RAIDER_AI);
        goalMap.put(Raider.RaiderCelebration.class, Gamerules.RAIDER_AI);
        goalMap.put(Raider.RaiderMoveThroughVillageGoal.class, Gamerules.RAIDER_AI);
        goalMap.put(Shulker.ShulkerAttackGoal.class, Gamerules.SHULKER_AI);
        goalMap.put(Shulker.ShulkerPeekGoal.class, Gamerules.SHULKER_AI);
        goalMap.put(Silverfish.SilverfishWakeUpFriendsGoal.class, Gamerules.SILVERFISH_AI);
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

    @ModifyReturnValue(method = "canContinueToUse", at = @At("RETURN"))
    private boolean blockContinuance(boolean original) {
        if (goalMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = goalMap.get(this.getClass());
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            return false;
        }
        return original;
    }
}