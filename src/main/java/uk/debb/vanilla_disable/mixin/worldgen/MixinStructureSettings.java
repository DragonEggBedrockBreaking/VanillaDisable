package uk.debb.vanilla_disable.mixin.worldgen;

import com.google.common.collect.ImmutableMultimap;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(StructureSettings.class)
public abstract class MixinStructureSettings {
    /**
     * @author DragonEggBedrockBreaking
     * @reason map of all structures to their gamerules
     */
    @Unique
    private static final Map<StructureFeature<?>, GameRules.Key<GameRules.BooleanValue>> structureToGameruleMap = new HashMap<StructureFeature<?>, GameRules.Key<GameRules.BooleanValue>>();

    /**
     * @author DragonEggBedrockBreaking
     * @reason the map otherwise initialises before the gamerules are created and always returns null
     */
    @Unique
    private void addOptionsToMap() {
        structureToGameruleMap.put(StructureFeature.BASTION_REMNANT, RegisterGamerules.BASTION_REMNANT_GENERATION);
        structureToGameruleMap.put(StructureFeature.BURIED_TREASURE, RegisterGamerules.BURIED_TREASURE_GENERATION);
        structureToGameruleMap.put(StructureFeature.DESERT_PYRAMID, RegisterGamerules.DESERT_PYRAMID_GENERATION);
        structureToGameruleMap.put(StructureFeature.END_CITY, RegisterGamerules.END_CITY_GENERATION);
        structureToGameruleMap.put(StructureFeature.NETHER_BRIDGE, RegisterGamerules.FORTRESS_GENERATION);
        structureToGameruleMap.put(StructureFeature.IGLOO, RegisterGamerules.IGLOO_GENERATION);
        structureToGameruleMap.put(StructureFeature.JUNGLE_TEMPLE, RegisterGamerules.JUNGLE_PYRAMID_GENERATION);
        structureToGameruleMap.put(StructureFeature.WOODLAND_MANSION, RegisterGamerules.MANSION_GENERATION);
        structureToGameruleMap.put(StructureFeature.MINESHAFT, RegisterGamerules.MINESHAFT_GENERATION);
        structureToGameruleMap.put(StructureFeature.OCEAN_MONUMENT, RegisterGamerules.MONUMENT_GENERATION);
        structureToGameruleMap.put(StructureFeature.NETHER_FOSSIL, RegisterGamerules.NETHER_FOSSIL_GENERATION);
        structureToGameruleMap.put(StructureFeature.OCEAN_RUIN, RegisterGamerules.OCEAN_RUIN_GENERATION);
        structureToGameruleMap.put(StructureFeature.PILLAGER_OUTPOST, RegisterGamerules.PILLAGER_OUTPOST_GENERATION);
        structureToGameruleMap.put(StructureFeature.RUINED_PORTAL, RegisterGamerules.RUINED_PORTAL_GENERATION);
        structureToGameruleMap.put(StructureFeature.SHIPWRECK, RegisterGamerules.SHIPWRECK_GENERATION);
        structureToGameruleMap.put(StructureFeature.STRONGHOLD, RegisterGamerules.STRONGHOLD_GENERATION);
        structureToGameruleMap.put(StructureFeature.SWAMP_HUT, RegisterGamerules.SWAMP_HUT_GENERATION);
        structureToGameruleMap.put(StructureFeature.VILLAGE, RegisterGamerules.VILLAGE_GENERATION);
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason removes structures from the multimap if the gamerule is false
     * @param feature the structure that is being searched for
     * @param cir the returnable callback info
     */
    @Inject(method = "structures", at = @At("HEAD"), cancellable = true)
    private void cancelGettingStructures(StructureFeature<?> feature, CallbackInfoReturnable<ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> cir) {
        if (RegisterGamerules.getServer() == null) return;
        if (structureToGameruleMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = structureToGameruleMap.get(feature);
        if (gameRule != null && !RegisterGamerules.getServer().getGameRules().getBoolean(gameRule)) {
            cir.setReturnValue(ImmutableMultimap.of());
        }
    }
}
