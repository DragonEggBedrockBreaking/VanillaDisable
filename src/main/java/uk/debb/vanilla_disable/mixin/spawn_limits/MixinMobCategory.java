package uk.debb.vanilla_disable.mixin.spawn_limits;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(MobCategory.class)
public abstract class MixinMobCategory {
    /**
     * @author DragonEggBedrockBreaking
     * @reason map of all spawn groups to their gamerules
     */
    @Unique
    private static final Map<MobCategory, GameRules.Key<GameRules.IntegerValue>> spawnGroupMap = new HashMap<MobCategory, GameRules.Key<GameRules.IntegerValue>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addOptionsToMap() {
        spawnGroupMap.put(MobCategory.MONSTER, RegisterGamerules.MONSTER_MOBCAP);
        spawnGroupMap.put(MobCategory.CREATURE, RegisterGamerules.CREATURE_MOBCAP);
        spawnGroupMap.put(MobCategory.AMBIENT, RegisterGamerules.AMBIENT_MOBCAP);
        spawnGroupMap.put(MobCategory.AXOLOTLS, RegisterGamerules.AXOLOTL_MOBCAP);
        spawnGroupMap.put(MobCategory.UNDERGROUND_WATER_CREATURE, RegisterGamerules.GLOWSQUID_MOBCAP);
        spawnGroupMap.put(MobCategory.WATER_AMBIENT, RegisterGamerules.WATER_AMBIENT_MOBCAP);
        spawnGroupMap.put(MobCategory.WATER_CREATURE, RegisterGamerules.WATER_CREATURE_MOBCAP);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason modify vanilla spawn caps
     * @param cir the returnable callback info
     */
    @Inject(method = "getMaxInstancesPerChunk", at = @At("HEAD"), cancellable = true)
    public void getMaxInstancesPerChunk(CallbackInfoReturnable<Integer> cir) {
        if (spawnGroupMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.IntegerValue> gameRule = spawnGroupMap.get((MobCategory) (Object) this);
        if (gameRule != null) {
            cir.setReturnValue(RegisterGamerules.getServer().getGameRules().getInt(gameRule));
        }
    }
}
