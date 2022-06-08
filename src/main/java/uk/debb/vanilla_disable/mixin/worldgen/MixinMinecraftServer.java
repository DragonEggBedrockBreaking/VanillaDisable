package uk.debb.vanilla_disable.mixin.worldgen;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.DataPackConfig;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.world.level.storage.WorldData;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.util.VDServer;
import uk.debb.vanilla_disable.util.GameruleHelper;
import uk.debb.vanilla_disable.util.Gamerules;


@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
    @Shadow @Final protected WorldData worldData;
    @Shadow private static DataPackConfig getSelectedPacks(PackRepository repo) {
        return null;
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason create the directories for a datapacks
     * @param name the name of the datapack
     */
    private void createDatapackDir(String name, String dirname) {
        String dataPackPath = VDServer.getServer().getWorldPath(LevelResource.DATAPACK_DIR).toString();
        File dataPackDir = new File(dataPackPath + "/" + name + "/data/minecraft/" + dirname + "/");
        dataPackDir.getParentFile().mkdirs();
        dataPackDir.mkdir();
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason create the directories for datapacks for each dimension
     */
    @Unique
    private void createBiomeDatapackDirectories() {
        if (GameruleHelper.getBool(Gamerules.REMOVE_OVERWORLD_BIOMES, this.worldData)) {
            createDatapackDir("vanilla_disable_overworld_biomes", "dimension");
        }
        if (GameruleHelper.getBool(Gamerules.REMOVE_NETHER_BIOMES, this.worldData)) {
            createDatapackDir("vanilla_disable_nether_biomes", "dimension");
        }
        if (GameruleHelper.getBool(Gamerules.REMOVE_END_BIOMES, this.worldData)) {
            createDatapackDir("vanilla_disable_end_biomes", "dimension");
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason create the directories for datapacks for each structure
     */
    @Unique
    private void createStructureDatapackDirectories() {
        if (!GameruleHelper.getBool(Gamerules.ANCIENT_CITY_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_ancient_city", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.BASTION_REMNANT_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_bastion_remnant", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.BURIED_TREASURE_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_buried_treasure", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.DESERT_PYRAMID_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_desert_pyramid", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.END_CITY_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_end_city", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.IGLOO_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_igloo", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.JUNGLE_PYRAMID_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_jungle_pyramid", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.MINESHAFT_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_mineshaft", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.FORTRESS_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_fortress", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.NETHER_FOSSIL_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_nether_fossil", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.MONUMENT_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_monument", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.OCEAN_RUIN_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_ocean_ruin", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.PILLAGER_OUTPOST_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_pillager_outpost", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.RUINED_PORTAL_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_ruined_portal", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.SHIPWRECK_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_shipwreck", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.STRONGHOLD_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_stronghold", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.SWAMP_HUT_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_swamp_hut", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.VILLAGE_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_village", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.MANSION_GENERATION, this.worldData)) {
            createDatapackDir("vanilla_disable_structures_mansion", "tags/worldgen/biome/has_structure");
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason create and write the mcmeta file for a datapack
     * @param name the name of the datapack
     * @throws IOException
     */
    @Unique
    private void addMcmetaFile(String name, String content) throws IOException {
        String dataPackPath = VDServer.getServer().getWorldPath(LevelResource.DATAPACK_DIR).toString();
        File mcmetaFile = new File(dataPackPath + "/" + name + "/pack.mcmeta");
        FileWriter myWriter = new FileWriter(mcmetaFile.toString());
        myWriter.write(content);
        myWriter.close();
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason create and write the mcmeta file for datapacks for each dimension
     * @throws IOException
     */
    @Unique
    private void addBiomeMcmetaFiles() throws IOException {
        String content = "{\"pack\":{\"pack_format\":10,\"description\":\"Vanilla Disable Biomes\"}}";
        if (GameruleHelper.getBool(Gamerules.REMOVE_OVERWORLD_BIOMES, this.worldData)) {
            addMcmetaFile("vanilla_disable_overworld_biomes", content);
        }
        if (GameruleHelper.getBool(Gamerules.REMOVE_NETHER_BIOMES, this.worldData)) {
            addMcmetaFile("vanilla_disable_nether_biomes", content);
        }
        if (GameruleHelper.getBool(Gamerules.REMOVE_END_BIOMES, this.worldData)) {
            addMcmetaFile("vanilla_disable_end_biomes", content);
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason create and write the mcmeta file for datapacks for each structure
     * @throws IOException
     */
    @Unique
    private void addStructureMcmetaFiles() throws IOException {
        String content = "{\"pack\":{\"pack_format\":9,\"description\":\"Vanilla Disable Structures\"}}";
        if (!GameruleHelper.getBool(Gamerules.ANCIENT_CITY_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_ancient_city", content);
        }
        if (!GameruleHelper.getBool(Gamerules.BASTION_REMNANT_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_bastion_remnant", content);
        }
        if (!GameruleHelper.getBool(Gamerules.BURIED_TREASURE_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_buried_treasure", content);
        }
        if (!GameruleHelper.getBool(Gamerules.DESERT_PYRAMID_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_desert_pyramid", content);
        }
        if (!GameruleHelper.getBool(Gamerules.END_CITY_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_end_city", content);
        }
        if (!GameruleHelper.getBool(Gamerules.IGLOO_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_igloo", content);
        }
        if (!GameruleHelper.getBool(Gamerules.JUNGLE_PYRAMID_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_jungle_temple", content);
        }
        if (!GameruleHelper.getBool(Gamerules.MINESHAFT_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_mineshaft", content);
        }
        if (!GameruleHelper.getBool(Gamerules.FORTRESS_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_fortress", content);
        }
        if (!GameruleHelper.getBool(Gamerules.NETHER_FOSSIL_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_nether_fossil", content);
        }
        if (!GameruleHelper.getBool(Gamerules.MONUMENT_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_monument", content);
        }
        if (!GameruleHelper.getBool(Gamerules.OCEAN_RUIN_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_ocean_ruin", content);
        }
        if (!GameruleHelper.getBool(Gamerules.PILLAGER_OUTPOST_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_pillager_outpost", content);
        }
        if (!GameruleHelper.getBool(Gamerules.RUINED_PORTAL_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_ruined_portal", content);
        }
        if (!GameruleHelper.getBool(Gamerules.SHIPWRECK_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_shipwreck", content);
        }
        if (!GameruleHelper.getBool(Gamerules.STRONGHOLD_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_stronghold", content);
        }
        if (!GameruleHelper.getBool(Gamerules.SWAMP_HUT_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_swamp_hut", content);
        }
        if (!GameruleHelper.getBool(Gamerules.VILLAGE_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_village", content);
        }
        if (!GameruleHelper.getBool(Gamerules.MANSION_GENERATION, this.worldData)) {
            addMcmetaFile("vanilla_disable_structures_mansion", content);
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason download and add the json files
     * @param url the url of the json files to download
     * @param name the name of the datapack
     * @param shortname the name of the json file (without extension)
     * @throws IOException
     */
    @Unique
    private void addJsonFile(String url, String name, String shortname, String dirname) throws IOException {
        String dataPackPath = VDServer.getServer().getWorldPath(LevelResource.DATAPACK_DIR).toString();
        FileOutputStream jsonFileDir = new FileOutputStream(dataPackPath + "/" + name + "/data/minecraft/" + dirname + "/" + shortname + ".json");
        URL URLToDownload = new URL(url);
        ReadableByteChannel readableByteChannel = Channels.newChannel(URLToDownload.openStream());
        jsonFileDir.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        jsonFileDir.close();
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason download and add the json files for each dimension
     * @throws IOException
     */
    @Unique
    private void addBiomeJsonFiles() throws IOException {
        if (GameruleHelper.getBool(Gamerules.REMOVE_OVERWORLD_BIOMES, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/f79a7fd2405c5eeddb838e0cf91cd655/raw/655c530e27a1eee389ae088ef99eddea7b768169/overworld.json",
                "vanilla_disable_overworld_biomes", "overworld", "dimension");
        }
        if (GameruleHelper.getBool(Gamerules.REMOVE_NETHER_BIOMES, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/f79a7fd2405c5eeddb838e0cf91cd655/raw/655c530e27a1eee389ae088ef99eddea7b768169/the_nether.json",
                "vanilla_disable_nether_biomes", "the_nether", "dimension");
        }
        if (GameruleHelper.getBool(Gamerules.REMOVE_END_BIOMES, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/f79a7fd2405c5eeddb838e0cf91cd655/raw/655c530e27a1eee389ae088ef99eddea7b768169/the_end.json",
                "vanilla_disable_end_biomes", "the_end", "dimension");
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason download and add the json files for each structure/biome-specific structure variant
     * @throws IOException
     */
    @Unique
    private void addStructureJsonFiles() throws IOException {
        if (!GameruleHelper.getBool(Gamerules.ANCIENT_CITY_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_ancient_city", "ancient_city", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.BASTION_REMNANT_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_bastion_remnants", "bastion_remnant", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.BURIED_TREASURE_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_buried_treasure", "buried_treasure", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.DESERT_PYRAMID_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_desert_pyramid", "desert_pyramid", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.END_CITY_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_end_city", "end_city", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.IGLOO_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_igloo", "igloo", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.JUNGLE_PYRAMID_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_jungle_pyramid", "jungle_pyramid", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.MINESHAFT_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_mineshaft", "mineshaft", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_mineshaft", "mineshaft_mesa", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.FORTRESS_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_fortress", "nether_fortress", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.NETHER_FOSSIL_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_nether_fossil", "nether_fossil", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.MONUMENT_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_monument", "ocean_monument", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.OCEAN_RUIN_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_ocean_ruin", "ocean_ruin_cold", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_ocean_ruin", "ocean_ruin_warm", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.PILLAGER_OUTPOST_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_pillager_outpost", "pillager_outpost", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.RUINED_PORTAL_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_ruined_portal", "ruined_portal_desert", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_ruined_portal", "ruined_portal_jungle", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_ruined_portal", "ruined_portal_mountain", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_ruined_portal", "ruined_portal_nether", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_ruined_portal", "ruined_portal_ocean", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_ruined_portal", "ruined_portal_standard", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_ruined_portal", "ruined_portal_swamp", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.SHIPWRECK_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_shipwreck", "shipwreck", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_shipwreck", "shipwreck_beached", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.STRONGHOLD_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_stronghold", "stronghold", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.SWAMP_HUT_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_swamp_hut", "swamp_hut", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.VILLAGE_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_village", "village_desert", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_village", "village_plains", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_village", "village_savanna", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_village", "village_snowy", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_village", "village_taiga", "tags/worldgen/biome/has_structure");
        }
        if (!GameruleHelper.getBool(Gamerules.MANSION_GENERATION, this.worldData)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_mansion", "woodland_mansion", "tags/worldgen/biome/has_structure");
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason create all of the biome datapacks
     * @throws IOException
     */
    @Unique
    private void getBiomeDataPacks() throws IOException {
        createBiomeDatapackDirectories();
        addBiomeMcmetaFiles();
        addBiomeJsonFiles();
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason create all of the structure datapacks
     * @throws IOException
     */
    @Unique
    private void getStructureDataPacks() throws IOException {
        createStructureDatapackDirectories();
        addStructureMcmetaFiles();
        addStructureJsonFiles();
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason easily enables the relevant datapack for worldgen
     * @param name the name of the datapack to enable
     */
    @Unique
    private void enableDatapack(String name) {
        PackRepository repo = VDServer.getServer().getPackRepository();
        DataPackConfig config = MixinMinecraftServer.getSelectedPacks(repo);
        ObjectList<String> enabledCopy = new ObjectArrayList<String>();
        ObjectList<String> disabledCopy = new ObjectArrayList<String>();
        enabledCopy.addAll(config.enabled); 
        disabledCopy.addAll(config.disabled);
        enabledCopy.add(name);
        disabledCopy.remove(name);
        config.enabled = enabledCopy;
        config.disabled = disabledCopy;
        LoggerFactory.getLogger("Vanilla Disable").debug(String.format(
            "Datapack %s has been enabled due to your gamerule choices.",
            name.replaceAll("vanilla_disable_", "")));
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason easily disables the relevant datapack for worldgen
     * @param name the name of the datapack to disable
     */
    @Unique
    private void disableDatapack(String name) {
        PackRepository repo = VDServer.getServer().getPackRepository();
        DataPackConfig config = MixinMinecraftServer.getSelectedPacks(repo);
        ObjectList<String> enabledCopy = new ObjectArrayList<String>();
        ObjectList<String> disabledCopy = new ObjectArrayList<String>();
        enabledCopy.addAll(config.enabled); 
        disabledCopy.addAll(config.disabled);
        enabledCopy.remove(name);
        disabledCopy.add(name);
        config.enabled = enabledCopy;
        config.disabled = disabledCopy;
        LoggerFactory.getLogger("Vanilla Disable").debug(String.format(
            "Datapack %s has been disabled due to your gamerule choices.",
            name.replaceAll("vanilla_disable_", "")));
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason toggle the biome datapacks based on the gamerules
     */
    @Unique
    private void toggleBiomeDataPacks() throws CommandSyntaxException {
        if (!GameruleHelper.getBool(Gamerules.REMOVE_OVERWORLD_BIOMES, this.worldData)) {
            disableDatapack("vanilla_disable_overworld_biomes");
        } else {
            enableDatapack("vanilla_disable_overworld_biomes");
        }
        if (!GameruleHelper.getBool(Gamerules.REMOVE_NETHER_BIOMES, this.worldData)) {
            disableDatapack("vanilla_disable_nether_biomes");
        } else {
            enableDatapack("vanilla_disable_nether_biomes");
        }
        if (!GameruleHelper.getBool(Gamerules.REMOVE_END_BIOMES, this.worldData)) {
            disableDatapack("vanilla_disable_end_biomes");
        } else {
            enableDatapack("vanilla_disable_end_biomes");
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @throws CommandSyntaxException
     * @reason toggle the structure datapacks based on the gamerules
     */
    @Unique
    private void toggleStructureDataPacks() throws CommandSyntaxException {
        if (GameruleHelper.getBool(Gamerules.ANCIENT_CITY_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_ancient_city");
        } else {
            enableDatapack("vanilla_disable_structures_ancient_city");
        }
        if (GameruleHelper.getBool(Gamerules.BASTION_REMNANT_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_bastion_remnant");
        } else {
            enableDatapack("vanilla_disable_structures_bastion_remnant");
        }
        if (GameruleHelper.getBool(Gamerules.BURIED_TREASURE_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_buried_treasure");
        } else {
            enableDatapack("vanilla_disable_structures_buried_treasure");
        }
        if (GameruleHelper.getBool(Gamerules.DESERT_PYRAMID_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_desert_pyramid");
        } else {
            enableDatapack("vanilla_disable_structures_desert_pyramid");
        }
        if (GameruleHelper.getBool(Gamerules.END_CITY_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_end_city");
        } else {
            enableDatapack("vanilla_disable_structures_end_city");
        }
        if (GameruleHelper.getBool(Gamerules.IGLOO_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_igloo");
        } else {
            enableDatapack("vanilla_disable_structures_igloo");
        }
        if (GameruleHelper.getBool(Gamerules.JUNGLE_PYRAMID_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_jungle_pyramid");
        } else {
            enableDatapack("vanilla_disable_structures_jungle_pyramid");
        }
        if (GameruleHelper.getBool(Gamerules.MINESHAFT_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_mineshaft");
        } else {
            enableDatapack("vanilla_disable_structures_mineshaft");
        }
        if (GameruleHelper.getBool(Gamerules.FORTRESS_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_fortress");
        } else {
            enableDatapack("vanilla_disable_structures_fortress");
        }
        if (GameruleHelper.getBool(Gamerules.NETHER_FOSSIL_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_nether_fossil");
        } else {
            enableDatapack("vanilla_disable_structures_nether_fossil");
        }
        if (GameruleHelper.getBool(Gamerules.MONUMENT_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_monument");
        } else {
            enableDatapack("vanilla_disable_structures_monument");
        }
        if (GameruleHelper.getBool(Gamerules.OCEAN_RUIN_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_ocean_ruin");
        } else {
            enableDatapack("vanilla_disable_structures_ocean_ruin");
        }
        if (GameruleHelper.getBool(Gamerules.PILLAGER_OUTPOST_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_pillager_outpost");
        } else {
            enableDatapack("vanilla_disable_structures_pillager_outpost");
        }
        if (GameruleHelper.getBool(Gamerules.RUINED_PORTAL_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_ruined_portal");
        } else {
            enableDatapack("vanilla_disable_structures_ruined_portal");
        }
        if (GameruleHelper.getBool(Gamerules.SHIPWRECK_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_shipwreck");
        } else {
            enableDatapack("vanilla_disable_structures_shipwreck");
        }
        if (GameruleHelper.getBool(Gamerules.STRONGHOLD_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_stronghold");
        } else {
            enableDatapack("vanilla_disable_structures_stronghold");
        }
        if (GameruleHelper.getBool(Gamerules.SWAMP_HUT_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_swamp_hut");
        } else {
            enableDatapack("vanilla_disable_structures_swamp_hut");
        }
        if (GameruleHelper.getBool(Gamerules.VILLAGE_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_village");
        } else {
            enableDatapack("vanilla_disable_structures_village");
        }
        if (GameruleHelper.getBool(Gamerules.MANSION_GENERATION, this.worldData)) {
            disableDatapack("vanilla_disable_structures_mansion");
        } else {
            enableDatapack("vanilla_disable_structures_mansion");
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason after the world is loaded, if first load, create datapacks and patch them
     * @reason and always toggle them, on all loads
     * @param ci the callback info
     * @throws IOException
     */
    @Inject(method = "createLevels", at = @At("HEAD"), cancellable = true)
    private void onLevelLoad(CallbackInfo ci) throws IOException, CommandSyntaxException {
        if (VDServer.getServer() == null) return;
        getBiomeDataPacks();
        getStructureDataPacks();
        toggleBiomeDataPacks();
        toggleStructureDataPacks();
    }
}
