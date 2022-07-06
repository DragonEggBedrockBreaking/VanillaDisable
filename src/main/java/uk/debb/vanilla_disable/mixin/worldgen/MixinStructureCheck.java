package uk.debb.vanilla_disable.mixin.worldgen;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureCheck;
import net.minecraft.world.level.levelgen.structure.structures.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;
import uk.debb.vanilla_disable.util.VDServer;

@Mixin(StructureCheck.class)
public abstract class MixinStructureCheck {
    @Unique
    private static final Object2ObjectMap<Class<?>, GameRules.Key<GameRules.BooleanValue>> structureMap = new Object2ObjectOpenHashMap<>();

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addOptionsToMap() {
        structureMap.put(BuriedTreasureStructure.class, Gamerules.BURIED_TREASURE_GENERATION);
        structureMap.put(DesertPyramidStructure.class, Gamerules.DESERT_PYRAMID_GENERATION);
        structureMap.put(EndCityStructure.class, Gamerules.END_CITY_GENERATION);
        structureMap.put(IglooStructure.class, Gamerules.IGLOO_GENERATION);
        structureMap.put(JungleTempleStructure.class, Gamerules.JUNGLE_PYRAMID_GENERATION);
        structureMap.put(MineshaftStructure.class, Gamerules.MINESHAFT_GENERATION);
        structureMap.put(NetherFortressStructure.class, Gamerules.FORTRESS_GENERATION);
        structureMap.put(NetherFossilStructure.class, Gamerules.NETHER_FOSSIL_GENERATION);
        structureMap.put(OceanMonumentStructure.class, Gamerules.MONUMENT_GENERATION);
        structureMap.put(OceanRuinStructure.class, Gamerules.OCEAN_RUIN_GENERATION);
        structureMap.put(RuinedPortalStructure.class, Gamerules.RUINED_PORTAL_GENERATION);
        structureMap.put(ShipwreckStructure.class, Gamerules.SHIPWRECK_GENERATION);
        structureMap.put(StrongholdStructure.class, Gamerules.STRONGHOLD_GENERATION);
        structureMap.put(SwampHutStructure.class, Gamerules.SWAMP_HUT_GENERATION);
        structureMap.put(WoodlandMansionStructure.class, Gamerules.MANSION_GENERATION);
    }

    /**
     * @param chunkPos  the position of the structure
     * @param structure the structure that is generating
     * @param cir       the returnable callback info
     * @author DragonEggBedrockBreaking
     * @reason stop structures from generating
     */
    @Inject(method = "canCreateStructure", at = @At("HEAD"), cancellable = true)
    private void cancelStructureGeneration(ChunkPos chunkPos, Structure structure, CallbackInfoReturnable<Boolean> cir) {
        if (VDServer.getServer() == null) return;
        if (structureMap.isEmpty()) {
            addOptionsToMap();
        }
        GameRules.Key<GameRules.BooleanValue> gameRule = structureMap.get(structure.getClass());
        if (gameRule != null && !GameruleHelper.getBool(gameRule)) {
            cir.setReturnValue(false);
        }
    }
}