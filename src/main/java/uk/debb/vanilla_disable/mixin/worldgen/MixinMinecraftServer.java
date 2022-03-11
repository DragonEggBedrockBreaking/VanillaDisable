package uk.debb.vanilla_disable.mixin.worldgen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.DataPackConfig;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.world.level.storage.WorldData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

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
        String dataPackPath = RegisterGamerules.getServer().getWorldPath(LevelResource.DATAPACK_DIR).toString();
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
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.REMOVE_OVERWORLD_BIOMES)) {
            createDatapackDir("vanilla_disable_overworld_biomes", "dimension");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.REMOVE_NETHER_BIOMES)) {
            createDatapackDir("vanilla_disable_nether_biomes", "dimension");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.REMOVE_END_BIOMES)) {
            createDatapackDir("vanilla_disable_end_biomes", "dimension");
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason create the directories for datapacks for each structure
     */
    @Unique
    private void createStructureDatapackDirectories() {
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.BASTION_REMNANT_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_bastion_remnant", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.BURIED_TREASURE_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_buried_treasure", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.DESERT_PYRAMID_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_desert_pyramid", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.END_CITY_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_end_city", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.IGLOO_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_igloo", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.JUNGLE_PYRAMID_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_jungle_pyramid", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.MINESHAFT_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_mineshaft", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.FORTRESS_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_fortress", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.NETHER_FOSSIL_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_nether_fossil", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.MONUMENT_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_monument", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.OCEAN_RUIN_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_ocean_ruin", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.PILLAGER_OUTPOST_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_pillager_outpost", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.RUINED_PORTAL_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_ruined_portal", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.SHIPWRECK_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_shipwreck", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.STRONGHOLD_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_stronghold", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.SWAMP_HUT_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_swamp_hut", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.VILLAGE_GENERATION)) {
            createDatapackDir("vanilla_disable_structures_village", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.MANSION_GENERATION)) {
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
        String dataPackPath = RegisterGamerules.getServer().getWorldPath(LevelResource.DATAPACK_DIR).toString();
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
        String content = "{\"pack\":{\"pack_format\":9,\"description\":\"Vanilla Disable Biomes\"}}";
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.REMOVE_OVERWORLD_BIOMES)) {
            addMcmetaFile("vanilla_disable_overworld_biomes", content);
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.REMOVE_NETHER_BIOMES)) {
            addMcmetaFile("vanilla_disable_nether_biomes", content);
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.REMOVE_END_BIOMES)) {
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
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.BASTION_REMNANT_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_bastion_remnant", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.BURIED_TREASURE_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_buried_treasure", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.DESERT_PYRAMID_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_desert_pyramid", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.END_CITY_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_end_city", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.IGLOO_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_igloo", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.JUNGLE_PYRAMID_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_jungle_temple", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.MINESHAFT_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_mineshaft", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.FORTRESS_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_fortress", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.NETHER_FOSSIL_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_nether_fossil", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.MONUMENT_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_monument", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.OCEAN_RUIN_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_ocean_ruin", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.PILLAGER_OUTPOST_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_pillager_outpost", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.RUINED_PORTAL_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_ruined_portal", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.SHIPWRECK_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_shipwreck", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.STRONGHOLD_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_stronghold", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.SWAMP_HUT_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_swamp_hut", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.VILLAGE_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_village", content);
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.MANSION_GENERATION)) {
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
        String dataPackPath = RegisterGamerules.getServer().getWorldPath(LevelResource.DATAPACK_DIR).toString();
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
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.REMOVE_OVERWORLD_BIOMES)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/f4ba3e1f7e83948c66a5f383c199b338/raw/aa1c18898ccde6bb6cbba9c134d066b6c81bc1b6/overworld.json",
                "vanilla_disable_overworld_biomes", "overworld", "dimension");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.REMOVE_NETHER_BIOMES)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/f4ba3e1f7e83948c66a5f383c199b338/raw/aa1c18898ccde6bb6cbba9c134d066b6c81bc1b6/the_nether.json",
                "vanilla_disable_nether_biomes", "the_nether", "dimension");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.REMOVE_END_BIOMES)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/f4ba3e1f7e83948c66a5f383c199b338/raw/aa1c18898ccde6bb6cbba9c134d066b6c81bc1b6/the_end.json",
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
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.BASTION_REMNANT_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_bastion_remnants", "bastion_remnant", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.BURIED_TREASURE_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_buried_treasure", "buried_treasure", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.DESERT_PYRAMID_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_desert_pyramid", "desert_pyramid", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.END_CITY_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_end_city", "end_city", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.IGLOO_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_igloo", "igloo", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.JUNGLE_PYRAMID_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_jungle_pyramid", "jungle_pyramid", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.MINESHAFT_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_mineshaft", "mineshaft", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_mineshaft", "mineshaft_mesa", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.FORTRESS_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_fortress", "nether_fortress", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.NETHER_FOSSIL_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_nether_fossil", "nether_fossil", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.MONUMENT_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_monument", "ocean_monument", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.OCEAN_RUIN_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_ocean_ruin", "ocean_ruin_cold", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_ocean_ruin", "ocean_ruin_warm", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.PILLAGER_OUTPOST_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_pillager_outpost", "pillager_outpost", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.RUINED_PORTAL_GENERATION)) {
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
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.SHIPWRECK_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_shipwreck", "shipwreck", "tags/worldgen/biome/has_structure");
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_shipwreck", "shipwreck_beached", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.STRONGHOLD_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_stronghold", "stronghold", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.SWAMP_HUT_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_swamp_hut", "swamp_hut", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.VILLAGE_GENERATION)) {
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
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.MANSION_GENERATION)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                "vanilla_disable_structures_mansion", "woodland_mansion", "tags/worldgen/biome/has_structure");
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason patch the json files by using a random seed instead of seed 0
     * @param name the name of the datapack
     * @param shortname the name of the json file (without extension)
     * @throws IOException
     */
    @Unique
    private void patchBiomeJsonFile(String name, String shortname) throws IOException {
        String dataPackPath = RegisterGamerules.getServer().getWorldPath(LevelResource.DATAPACK_DIR).toString();
        File jsonFile = new File(dataPackPath + "/" + name + "/data/minecraft/dimension/" + shortname + ".json");
        String jsonFileToString = Files.readString(jsonFile.toPath());

        Pattern p = Pattern.compile("\"seed\": 0");
        Matcher m = p.matcher(jsonFileToString);
        Long seed = this.worldData.worldGenSettings().seed();
        String outputString = m.replaceAll("\"seed\": " + seed);

        FileWriter myWriter = new FileWriter(jsonFile.toString());
        myWriter.write(outputString);
        myWriter.close();
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason patch all of the datapacks by using a random seed instead of seed 0
     * @throws IOException
     */
    @Unique
    private void patchBiomeJsonFiles() throws IOException {
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.REMOVE_OVERWORLD_BIOMES)) {
            patchBiomeJsonFile("vanilla_disable_overworld_biomes", "overworld");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.REMOVE_NETHER_BIOMES)) {
            patchBiomeJsonFile("vanilla_disable_nether_biomes", "the_nether");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.REMOVE_END_BIOMES)) {
            patchBiomeJsonFile("vanilla_disable_end_biomes", "the_end");
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason create and patch all of the biome datapacks
     * @throws IOException
     */
    @Unique
    private void getAndPatchBiomeDataPacks() throws IOException {
        createBiomeDatapackDirectories();
        addBiomeMcmetaFiles();
        addBiomeJsonFiles();
        patchBiomeJsonFiles();
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
        PackRepository repo = RegisterGamerules.getServer().getPackRepository();
        DataPackConfig config = MixinMinecraftServer.getSelectedPacks(repo);
        List<String> enabledCopy = new ArrayList<String>();
        List<String> disabledCopy = new ArrayList<String>();
        enabledCopy.addAll(config.enabled); 
        disabledCopy.addAll(config.disabled);
        enabledCopy.add(name);
        disabledCopy.remove(name);
        config.enabled = enabledCopy;
        config.disabled = disabledCopy;
        RegisterGamerules.getLogger().debug(String.format(
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
        PackRepository repo = RegisterGamerules.getServer().getPackRepository();
        DataPackConfig config = MixinMinecraftServer.getSelectedPacks(repo);
        List<String> enabledCopy = new ArrayList<String>();
        List<String> disabledCopy = new ArrayList<String>();
        enabledCopy.addAll(config.enabled); 
        disabledCopy.addAll(config.disabled);
        enabledCopy.remove(name);
        disabledCopy.add(name);
        config.enabled = enabledCopy;
        config.disabled = disabledCopy;
        RegisterGamerules.getLogger().debug(String.format(
            "Datapack %s has been disabled due to your gamerule choices.",
            name.replaceAll("vanilla_disable_", "")));
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason toggle the biome datapacks based on the gamerules
     */
    @Unique
    private void toggleBiomeDataPacks() throws CommandSyntaxException {
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.REMOVE_OVERWORLD_BIOMES)) {
            disableDatapack("vanilla_disable_overworld_biomes");
        } else {
            enableDatapack("vanilla_disable_overworld_biomes");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.REMOVE_NETHER_BIOMES)) {
            disableDatapack("vanilla_disable_nether_biomes");
        } else {
            enableDatapack("vanilla_disable_nether_biomes");
        }
        if (!this.worldData.getGameRules().getBoolean(RegisterGamerules.REMOVE_END_BIOMES)) {
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
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.BASTION_REMNANT_GENERATION)) {
            disableDatapack("vanilla_disable_structures_bastion_remnant");
        } else {
            enableDatapack("vanilla_disable_structures_bastion_remnant");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.BURIED_TREASURE_GENERATION)) {
            disableDatapack("vanilla_disable_structures_buried_treasure");
        } else {
            enableDatapack("vanilla_disable_structures_buried_treasure");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.DESERT_PYRAMID_GENERATION)) {
            disableDatapack("vanilla_disable_structures_desert_pyramid");
        } else {
            enableDatapack("vanilla_disable_structures_desert_pyramid");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.END_CITY_GENERATION)) {
            disableDatapack("vanilla_disable_structures_end_city");
        } else {
            enableDatapack("vanilla_disable_structures_end_city");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.IGLOO_GENERATION)) {
            disableDatapack("vanilla_disable_structures_igloo");
        } else {
            enableDatapack("vanilla_disable_structures_igloo");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.JUNGLE_PYRAMID_GENERATION)) {
            disableDatapack("vanilla_disable_structures_jungle_pyramid");
        } else {
            enableDatapack("vanilla_disable_structures_jungle_pyramid");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.MINESHAFT_GENERATION)) {
            disableDatapack("vanilla_disable_structures_mineshaft");
        } else {
            enableDatapack("vanilla_disable_structures_mineshaft");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.FORTRESS_GENERATION)) {
            disableDatapack("vanilla_disable_structures_fortress");
        } else {
            enableDatapack("vanilla_disable_structures_fortress");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.NETHER_FOSSIL_GENERATION)) {
            disableDatapack("vanilla_disable_structures_nether_fossil");
        } else {
            enableDatapack("vanilla_disable_structures_nether_fossil");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.MONUMENT_GENERATION)) {
            disableDatapack("vanilla_disable_structures_monument");
        } else {
            enableDatapack("vanilla_disable_structures_monument");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.OCEAN_RUIN_GENERATION)) {
            disableDatapack("vanilla_disable_structures_ocean_ruin");
        } else {
            enableDatapack("vanilla_disable_structures_ocean_ruin");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.PILLAGER_OUTPOST_GENERATION)) {
            disableDatapack("vanilla_disable_structures_pillager_outpost");
        } else {
            enableDatapack("vanilla_disable_structures_pillager_outpost");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.RUINED_PORTAL_GENERATION)) {
            disableDatapack("vanilla_disable_structures_ruined_portal");
        } else {
            enableDatapack("vanilla_disable_structures_ruined_portal");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.SHIPWRECK_GENERATION)) {
            disableDatapack("vanilla_disable_structures_shipwreck");
        } else {
            enableDatapack("vanilla_disable_structures_shipwreck");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.STRONGHOLD_GENERATION)) {
            disableDatapack("vanilla_disable_structures_stronghold");
        } else {
            enableDatapack("vanilla_disable_structures_stronghold");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.SWAMP_HUT_GENERATION)) {
            disableDatapack("vanilla_disable_structures_swamp_hut");
        } else {
            enableDatapack("vanilla_disable_structures_swamp_hut");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.VILLAGE_GENERATION)) {
            disableDatapack("vanilla_disable_structures_village");
        } else {
            enableDatapack("vanilla_disable_structures_village");
        }
        if (this.worldData.getGameRules().getBoolean(RegisterGamerules.MANSION_GENERATION)) {
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
        if (RegisterGamerules.getServer() == null) return;
        getAndPatchBiomeDataPacks();
        getStructureDataPacks();
        toggleBiomeDataPacks();
        toggleStructureDataPacks();
    }
}
