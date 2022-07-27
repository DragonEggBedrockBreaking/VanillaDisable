package uk.debb.vanilla_disable.mixin.worldgen;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
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
import uk.debb.vanilla_disable.util.gamerules.GameruleHelper;
import uk.debb.vanilla_disable.util.gamerules.Gamerules;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
    @Shadow
    @Final
    protected WorldData worldData;

    @Shadow
    private static DataPackConfig getSelectedPacks(PackRepository repo) {
        return null;
    }

    @Shadow public abstract Path getWorldPath(LevelResource arg);

    @Shadow public abstract PackRepository getPackRepository();

    /**
     * @param name the name of the datapack
     * @author DragonEggBedrockBreaking
     */
    private boolean createDatapackDir(String name, String dirname) {
        String dataPackPath = this.getWorldPath(LevelResource.DATAPACK_DIR).toString();
        File dataPackDir = new File(dataPackPath + "/" + name + "/data/minecraft/" + dirname + "/");
        return dataPackDir.getParentFile().mkdirs() && dataPackDir.mkdir();
    }

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private boolean createBiomeDatapackDirectories() {
        if (this.worldData.getGameRules().getBoolean(Gamerules.REMOVE_OVERWORLD_BIOMES)) {
            if (!createDatapackDir("vanilla_disable_overworld_biomes", "dimension")) return false;
        }
        if (this.worldData.getGameRules().getBoolean(Gamerules.REMOVE_NETHER_BIOMES)) {
            if (!createDatapackDir("vanilla_disable_nether_biomes", "dimension")) return false;
        }
        if (this.worldData.getGameRules().getBoolean(Gamerules.REMOVE_END_BIOMES)) {
            return createDatapackDir("vanilla_disable_end_biomes", "dimension");
        }
        return true;
    }

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private boolean createStructureDatapackDirectories() {
        if (!this.worldData.getGameRules().getBoolean(Gamerules.ANCIENT_CITY_GENERATION)) {
            if (!createDatapackDir("vanilla_disable_structures_ancient_city", "tags/worldgen/biome/has_structure"))
                return false;
        }
        if (!this.worldData.getGameRules().getBoolean(Gamerules.BASTION_REMNANT_GENERATION)) {
            if (!createDatapackDir("vanilla_disable_structures_bastion_remnant", "tags/worldgen/biome/has_structure"))
                return false;
        }
        if (!this.worldData.getGameRules().getBoolean(Gamerules.PILLAGER_OUTPOST_GENERATION)) {
            if (!createDatapackDir("vanilla_disable_structures_pillager_outpost", "tags/worldgen/biome/has_structure"))
                return false;
        }
        if (!this.worldData.getGameRules().getBoolean(Gamerules.VILLAGE_GENERATION)) {
            return createDatapackDir("vanilla_disable_structures_village", "tags/worldgen/biome/has_structure");
        }
        return true;
    }

    /**
     * @param name the name of the datapack
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addMcmetaFile(String name, String content) throws IOException {
        String dataPackPath = this.getWorldPath(LevelResource.DATAPACK_DIR).toString();
        File mcmetaFile = new File(dataPackPath + "/" + name + "/pack.mcmeta");
        FileWriter myWriter = new FileWriter(mcmetaFile.toString());
        myWriter.write(content);
        myWriter.close();
    }

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addBiomeMcmetaFiles() throws IOException {
        String content = "{\"pack\":{\"pack_format\":10,\"description\":\"Vanilla Disable Biomes\"}}";
        if (this.worldData.getGameRules().getBoolean(Gamerules.REMOVE_OVERWORLD_BIOMES)) {
            addMcmetaFile("vanilla_disable_overworld_biomes", content);
        }
        if (this.worldData.getGameRules().getBoolean(Gamerules.REMOVE_NETHER_BIOMES)) {
            addMcmetaFile("vanilla_disable_nether_biomes", content);
        }
        if (this.worldData.getGameRules().getBoolean(Gamerules.REMOVE_END_BIOMES)) {
            addMcmetaFile("vanilla_disable_end_biomes", content);
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addStructureMcmetaFiles() throws IOException {
        String content = "{\"pack\":{\"pack_format\":9,\"description\":\"Vanilla Disable Structures\"}}";
        if (!this.worldData.getGameRules().getBoolean(Gamerules.ANCIENT_CITY_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_ancient_city", content);
        }
        if (!this.worldData.getGameRules().getBoolean(Gamerules.BASTION_REMNANT_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_bastion_remnant", content);
        }
        if (!this.worldData.getGameRules().getBoolean(Gamerules.PILLAGER_OUTPOST_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_pillager_outpost", content);
        }
        if (!this.worldData.getGameRules().getBoolean(Gamerules.VILLAGE_GENERATION)) {
            addMcmetaFile("vanilla_disable_structures_village", content);
        }
    }

    /**
     * @param url       the url of the json files to download
     * @param name      the name of the datapack
     * @param shortname the name of the json file (without extension)
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addJsonFile(String url, String name, String shortname, String dirname) throws IOException {
        String dataPackPath = this.getWorldPath(LevelResource.DATAPACK_DIR).toString();
        FileOutputStream jsonFileDir = new FileOutputStream(dataPackPath + "/" + name + "/data/minecraft/" + dirname + "/" + shortname + ".json");
        URL URLToDownload = new URL(url);
        ReadableByteChannel readableByteChannel = Channels.newChannel(URLToDownload.openStream());
        jsonFileDir.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        jsonFileDir.close();
    }

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addBiomeJsonFiles() throws IOException {
        if (this.worldData.getGameRules().getBoolean(Gamerules.REMOVE_OVERWORLD_BIOMES)) {
            addJsonFile(
                    "https://gist.githubusercontent.com/DragonEggBedrockBreaking/f79a7fd2405c5eeddb838e0cf91cd655/raw/655c530e27a1eee389ae088ef99eddea7b768169/overworld.json",
                    "vanilla_disable_overworld_biomes", "overworld", "dimension");
        }
        if (this.worldData.getGameRules().getBoolean(Gamerules.REMOVE_NETHER_BIOMES)) {
            addJsonFile(
                    "https://gist.githubusercontent.com/DragonEggBedrockBreaking/f79a7fd2405c5eeddb838e0cf91cd655/raw/655c530e27a1eee389ae088ef99eddea7b768169/the_nether.json",
                    "vanilla_disable_nether_biomes", "the_nether", "dimension");
        }
        if (this.worldData.getGameRules().getBoolean(Gamerules.REMOVE_END_BIOMES)) {
            addJsonFile(
                    "https://gist.githubusercontent.com/DragonEggBedrockBreaking/f79a7fd2405c5eeddb838e0cf91cd655/raw/655c530e27a1eee389ae088ef99eddea7b768169/the_end.json",
                    "vanilla_disable_end_biomes", "the_end", "dimension");
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void addStructureJsonFiles() throws IOException {
        if (!this.worldData.getGameRules().getBoolean(Gamerules.ANCIENT_CITY_GENERATION)) {
            addJsonFile(
                    "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                    "vanilla_disable_structures_ancient_city", "ancient_city", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(Gamerules.BASTION_REMNANT_GENERATION)) {
            addJsonFile(
                    "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                    "vanilla_disable_structures_bastion_remnants", "bastion_remnant", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(Gamerules.PILLAGER_OUTPOST_GENERATION)) {
            addJsonFile(
                    "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                    "vanilla_disable_structures_pillager_outpost", "pillager_outpost", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(Gamerules.VILLAGE_GENERATION)) {
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
    }

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void getBiomeDataPacks() throws IOException {
        if (createBiomeDatapackDirectories()) {
            addBiomeMcmetaFiles();
            addBiomeJsonFiles();
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void getStructureDataPacks() throws IOException {
        if (createStructureDatapackDirectories()) {
            addStructureMcmetaFiles();
            addStructureJsonFiles();
        }
    }

    /**
     * @param name the name of the datapack to enable
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void enableDatapack(String name) {
        PackRepository repo = this.getPackRepository();
        DataPackConfig config = MixinMinecraftServer.getSelectedPacks(repo);
        ObjectList<String> enabledCopy = new ObjectArrayList<>();
        ObjectList<String> disabledCopy = new ObjectArrayList<>();
        assert config != null;
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
     * @param name the name of the datapack to disable
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void disableDatapack(String name) {
        PackRepository repo = this.getPackRepository();
        DataPackConfig config = MixinMinecraftServer.getSelectedPacks(repo);
        ObjectList<String> enabledCopy = new ObjectArrayList<>();
        ObjectList<String> disabledCopy = new ObjectArrayList<>();
        assert config != null;
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
     */
    @Unique
    private void toggleBiomeDataPacks() {
        if (!this.worldData.getGameRules().getBoolean(Gamerules.REMOVE_OVERWORLD_BIOMES)) {
            disableDatapack("vanilla_disable_overworld_biomes");
        } else {
            enableDatapack("vanilla_disable_overworld_biomes");
        }
        if (!this.worldData.getGameRules().getBoolean(Gamerules.REMOVE_NETHER_BIOMES)) {
            disableDatapack("vanilla_disable_nether_biomes");
        } else {
            enableDatapack("vanilla_disable_nether_biomes");
        }
        if (!this.worldData.getGameRules().getBoolean(Gamerules.REMOVE_END_BIOMES)) {
            disableDatapack("vanilla_disable_end_biomes");
        } else {
            enableDatapack("vanilla_disable_end_biomes");
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     */
    @Unique
    private void toggleStructureDataPacks() {
        if (this.worldData.getGameRules().getBoolean(Gamerules.ANCIENT_CITY_GENERATION)) {
            disableDatapack("vanilla_disable_structures_ancient_city");
        } else {
            enableDatapack("vanilla_disable_structures_ancient_city");
        }
        if (this.worldData.getGameRules().getBoolean(Gamerules.BASTION_REMNANT_GENERATION)) {
            disableDatapack("vanilla_disable_structures_bastion_remnant");
        } else {
            enableDatapack("vanilla_disable_structures_bastion_remnant");
        }
        if (this.worldData.getGameRules().getBoolean(Gamerules.PILLAGER_OUTPOST_GENERATION)) {
            disableDatapack("vanilla_disable_structures_pillager_outpost");
        } else {
            enableDatapack("vanilla_disable_structures_pillager_outpost");
        }
        if (this.worldData.getGameRules().getBoolean(Gamerules.VILLAGE_GENERATION)) {
            disableDatapack("vanilla_disable_structures_village");
        } else {
            enableDatapack("vanilla_disable_structures_village");
        }
    }

    /**
     * @param ci the callback info
     * @author DragonEggBedrockBreaking
     * @reason after the world is loaded, if first load, create datapacks and patch them
     * @reason and always toggle them, on all loads
     */
    @Inject(method = "createLevels", at = @At("HEAD"))
    private void onLevelLoad(CallbackInfo ci) throws IOException {
        getBiomeDataPacks();
        getStructureDataPacks();
        toggleBiomeDataPacks();
        toggleStructureDataPacks();
    }
}