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
import uk.debb.vanilla_disable.util.gamerules.BooleanGamerules;

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

    @Shadow
    public abstract Path getWorldPath(LevelResource arg);

    @Shadow
    public abstract PackRepository getPackRepository();

    private boolean createDatapackDir(String name, String dirname) {
        String dataPackPath = this.getWorldPath(LevelResource.DATAPACK_DIR).toString();
        File dataPackDir = new File(dataPackPath + "/" + name + "/data/minecraft/" + dirname + "/");
        return dataPackDir.getParentFile().mkdirs() && dataPackDir.mkdir();
    }

    @Unique
    private boolean createBiomeDatapackDirectories() {
        if (this.worldData.getGameRules().getBoolean(BooleanGamerules.REMOVE_OVERWORLD_BIOMES.getGameRule())) {
            if (!createDatapackDir("vanilla_disable_overworld_biomes", "dimension")) return false;
        }
        if (this.worldData.getGameRules().getBoolean(BooleanGamerules.REMOVE_NETHER_BIOMES.getGameRule())) {
            if (!createDatapackDir("vanilla_disable_nether_biomes", "dimension")) return false;
        }
        if (this.worldData.getGameRules().getBoolean(BooleanGamerules.REMOVE_END_BIOMES.getGameRule())) {
            return createDatapackDir("vanilla_disable_end_biomes", "dimension");
        }
        return true;
    }

    @Unique
    private boolean createStructureDatapackDirectories() {
        if (!this.worldData.getGameRules().getBoolean(BooleanGamerules.ANCIENT_CITY_GENERATION.getGameRule())) {
            if (!createDatapackDir("vanilla_disable_structures_ancient_city", "tags/worldgen/biome/has_structure"))
                return false;
        }
        if (!this.worldData.getGameRules().getBoolean(BooleanGamerules.BASTION_REMNANT_GENERATION.getGameRule())) {
            if (!createDatapackDir("vanilla_disable_structures_bastion_remnant", "tags/worldgen/biome/has_structure"))
                return false;
        }
        if (!this.worldData.getGameRules().getBoolean(BooleanGamerules.PILLAGER_OUTPOST_GENERATION.getGameRule())) {
            if (!createDatapackDir("vanilla_disable_structures_pillager_outpost", "tags/worldgen/biome/has_structure"))
                return false;
        }
        if (!this.worldData.getGameRules().getBoolean(BooleanGamerules.VILLAGE_GENERATION.getGameRule())) {
            return createDatapackDir("vanilla_disable_structures_village", "tags/worldgen/biome/has_structure");
        }
        return true;
    }

    @Unique
    private void addMcmetaFile(String name, String content) throws IOException {
        String dataPackPath = this.getWorldPath(LevelResource.DATAPACK_DIR).toString();
        File mcmetaFile = new File(dataPackPath + "/" + name + "/pack.mcmeta");
        FileWriter myWriter = new FileWriter(mcmetaFile.toString());
        myWriter.write(content);
        myWriter.close();
    }

    @Unique
    private void addBiomeMcmetaFiles() throws IOException {
        String content = "{\"pack\":{\"pack_format\":10,\"description\":\"Vanilla Disable Biomes\"}}";
        if (this.worldData.getGameRules().getBoolean(BooleanGamerules.REMOVE_OVERWORLD_BIOMES.getGameRule())) {
            addMcmetaFile("vanilla_disable_overworld_biomes", content);
        }
        if (this.worldData.getGameRules().getBoolean(BooleanGamerules.REMOVE_NETHER_BIOMES.getGameRule())) {
            addMcmetaFile("vanilla_disable_nether_biomes", content);
        }
        if (this.worldData.getGameRules().getBoolean(BooleanGamerules.REMOVE_END_BIOMES.getGameRule())) {
            addMcmetaFile("vanilla_disable_end_biomes", content);
        }
    }

    @Unique
    private void addStructureMcmetaFiles() throws IOException {
        String content = "{\"pack\":{\"pack_format\":9,\"description\":\"Vanilla Disable Structures\"}}";
        if (!this.worldData.getGameRules().getBoolean(BooleanGamerules.ANCIENT_CITY_GENERATION.getGameRule())) {
            addMcmetaFile("vanilla_disable_structures_ancient_city", content);
        }
        if (!this.worldData.getGameRules().getBoolean(BooleanGamerules.BASTION_REMNANT_GENERATION.getGameRule())) {
            addMcmetaFile("vanilla_disable_structures_bastion_remnant", content);
        }
        if (!this.worldData.getGameRules().getBoolean(BooleanGamerules.PILLAGER_OUTPOST_GENERATION.getGameRule())) {
            addMcmetaFile("vanilla_disable_structures_pillager_outpost", content);
        }
        if (!this.worldData.getGameRules().getBoolean(BooleanGamerules.VILLAGE_GENERATION.getGameRule())) {
            addMcmetaFile("vanilla_disable_structures_village", content);
        }
    }

    @Unique
    private void addJsonFile(String url, String name, String shortname, String dirname) throws IOException {
        String dataPackPath = this.getWorldPath(LevelResource.DATAPACK_DIR).toString();
        FileOutputStream jsonFileDir = new FileOutputStream(dataPackPath + "/" + name + "/data/minecraft/" + dirname + "/" + shortname + ".json");
        URL URLToDownload = new URL(url);
        ReadableByteChannel readableByteChannel = Channels.newChannel(URLToDownload.openStream());
        jsonFileDir.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        jsonFileDir.close();
    }

    @Unique
    private void addBiomeJsonFiles() throws IOException {
        if (this.worldData.getGameRules().getBoolean(BooleanGamerules.REMOVE_OVERWORLD_BIOMES.getGameRule())) {
            addJsonFile(
                    "https://gist.githubusercontent.com/DragonEggBedrockBreaking/f79a7fd2405c5eeddb838e0cf91cd655/raw/655c530e27a1eee389ae088ef99eddea7b768169/overworld.json",
                    "vanilla_disable_overworld_biomes", "overworld", "dimension");
        }
        if (this.worldData.getGameRules().getBoolean(BooleanGamerules.REMOVE_NETHER_BIOMES.getGameRule())) {
            addJsonFile(
                    "https://gist.githubusercontent.com/DragonEggBedrockBreaking/f79a7fd2405c5eeddb838e0cf91cd655/raw/655c530e27a1eee389ae088ef99eddea7b768169/the_nether.json",
                    "vanilla_disable_nether_biomes", "the_nether", "dimension");
        }
        if (this.worldData.getGameRules().getBoolean(BooleanGamerules.REMOVE_END_BIOMES.getGameRule())) {
            addJsonFile(
                    "https://gist.githubusercontent.com/DragonEggBedrockBreaking/f79a7fd2405c5eeddb838e0cf91cd655/raw/655c530e27a1eee389ae088ef99eddea7b768169/the_end.json",
                    "vanilla_disable_end_biomes", "the_end", "dimension");
        }
    }

    @Unique
    private void addStructureJsonFiles() throws IOException {
        if (!this.worldData.getGameRules().getBoolean(BooleanGamerules.ANCIENT_CITY_GENERATION.getGameRule())) {
            addJsonFile(
                    "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                    "vanilla_disable_structures_ancient_city", "ancient_city", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(BooleanGamerules.BASTION_REMNANT_GENERATION.getGameRule())) {
            addJsonFile(
                    "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                    "vanilla_disable_structures_bastion_remnants", "bastion_remnant", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(BooleanGamerules.PILLAGER_OUTPOST_GENERATION.getGameRule())) {
            addJsonFile(
                    "https://gist.githubusercontent.com/DragonEggBedrockBreaking/315016e5e1691f36a425bea70cbc209d/raw/30655b175d6e2122826ba34a4e38dc79f3e300d9/structure_template.json",
                    "vanilla_disable_structures_pillager_outpost", "pillager_outpost", "tags/worldgen/biome/has_structure");
        }
        if (!this.worldData.getGameRules().getBoolean(BooleanGamerules.VILLAGE_GENERATION.getGameRule())) {
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

    @Unique
    private void getBiomeDataPacks() throws IOException {
        if (createBiomeDatapackDirectories()) {
            addBiomeMcmetaFiles();
            addBiomeJsonFiles();
        }
    }

    @Unique
    private void getStructureDataPacks() throws IOException {
        if (createStructureDatapackDirectories()) {
            addStructureMcmetaFiles();
            addStructureJsonFiles();
        }
    }

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

    @Unique
    private void toggleBiomeDataPacks() {
        if (!this.worldData.getGameRules().getBoolean(BooleanGamerules.REMOVE_OVERWORLD_BIOMES.getGameRule())) {
            disableDatapack("vanilla_disable_overworld_biomes");
        } else {
            enableDatapack("vanilla_disable_overworld_biomes");
        }
        if (!this.worldData.getGameRules().getBoolean(BooleanGamerules.REMOVE_NETHER_BIOMES.getGameRule())) {
            disableDatapack("vanilla_disable_nether_biomes");
        } else {
            enableDatapack("vanilla_disable_nether_biomes");
        }
        if (!this.worldData.getGameRules().getBoolean(BooleanGamerules.REMOVE_END_BIOMES.getGameRule())) {
            disableDatapack("vanilla_disable_end_biomes");
        } else {
            enableDatapack("vanilla_disable_end_biomes");
        }
    }

    @Unique
    private void toggleStructureDataPacks() {
        if (this.worldData.getGameRules().getBoolean(BooleanGamerules.ANCIENT_CITY_GENERATION.getGameRule())) {
            disableDatapack("vanilla_disable_structures_ancient_city");
        } else {
            enableDatapack("vanilla_disable_structures_ancient_city");
        }
        if (this.worldData.getGameRules().getBoolean(BooleanGamerules.BASTION_REMNANT_GENERATION.getGameRule())) {
            disableDatapack("vanilla_disable_structures_bastion_remnant");
        } else {
            enableDatapack("vanilla_disable_structures_bastion_remnant");
        }
        if (this.worldData.getGameRules().getBoolean(BooleanGamerules.PILLAGER_OUTPOST_GENERATION.getGameRule())) {
            disableDatapack("vanilla_disable_structures_pillager_outpost");
        } else {
            enableDatapack("vanilla_disable_structures_pillager_outpost");
        }
        if (this.worldData.getGameRules().getBoolean(BooleanGamerules.VILLAGE_GENERATION.getGameRule())) {
            disableDatapack("vanilla_disable_structures_village");
        } else {
            enableDatapack("vanilla_disable_structures_village");
        }
    }

    @Inject(method = "createLevels", at = @At("HEAD"))
    private void onLevelLoad(CallbackInfo ci) throws IOException {
        getBiomeDataPacks();
        getStructureDataPacks();
        toggleBiomeDataPacks();
        toggleStructureDataPacks();
    }
}