package uk.debb.vanilla_disable.mixin.worldgen.biomes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.DebugLevelSource;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.storage.LevelResource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.debb.vanilla_disable.gamerules.RegisterGamerules;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
    /**
     * @author DragonEggBedrockBreaking
     * @reason create the directories for a datapacks
     * @param name the name of the datapack
     */
    private void createDatapackDir(String name) {
        String dataPackPath = RegisterGamerules.getServer().getWorldPath(LevelResource.DATAPACK_DIR).toString();
        File dataPackDir = new File(dataPackPath + "/" + name + "/data/minecraft/dimension/");
        dataPackDir.getParentFile().mkdirs();
        dataPackDir.mkdir();
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason create the directories for datapacks for each dimension
     */
    @Unique
    private void createDatapackDirectories() {
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.REMOVE_OVERWORLD_BIOMES)) {
            createDatapackDir("vanilla_disable_overworld_biomes");
        }
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.REMOVE_NETHER_BIOMES)) {
            createDatapackDir("vanilla_disable_nether_biomes");
        }
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.REMOVE_END_BIOMES)) {
            createDatapackDir("vanilla_disable_end_biomes");
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason create and write the mcmeta file for a datapack
     * @param name the name of the datapack
     * @throws IOException
     */
    @Unique
    private void addMcmetaFile(String name) throws IOException {
        String dataPackPath = RegisterGamerules.getServer().getWorldPath(LevelResource.DATAPACK_DIR).toString();
        File mcmetaFile = new File(dataPackPath + "/" + name + "/pack.mcmeta");
        FileWriter myWriter = new FileWriter(mcmetaFile.toString());
        myWriter.write("{\"pack\":{\"pack_format\":8,\"description\":\"Vanilla Disable Biomes\"}}");
        myWriter.close();
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason create and write the mcmeta file for datapacks for each dimension
     * @throws IOException
     */
    @Unique
    private void addMcmetaFiles() throws IOException {
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.REMOVE_OVERWORLD_BIOMES)) {
            addMcmetaFile("vanilla_disable_overworld_biomes");
        }
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.REMOVE_NETHER_BIOMES)) {
            addMcmetaFile("vanilla_disable_nether_biomes");
        }
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.REMOVE_END_BIOMES)) {
            addMcmetaFile("vanilla_disable_end_biomes");
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason download and add the json files for each dimension
     * @param url the url of the json files to download
     * @param name the name of the datapack
     * @param shortname the name of the json file (without extension)
     * @throws IOException
     */
    @Unique
    private void addJsonFile(String url, String name, String shortname) throws IOException {
        String dataPackPath = RegisterGamerules.getServer().getWorldPath(LevelResource.DATAPACK_DIR).toString();
        FileOutputStream jsonFileDir = new FileOutputStream(dataPackPath + "/" + name + "/data/minecraft/dimension/" + shortname + ".json");
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
    private void addJsonFiles() throws IOException {
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.REMOVE_OVERWORLD_BIOMES)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/f4ba3e1f7e83948c66a5f383c199b338/raw/aa1c18898ccde6bb6cbba9c134d066b6c81bc1b6/overworld.json",
                "vanilla_disable_overworld_biomes", "overworld");
        }
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.REMOVE_NETHER_BIOMES)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/f4ba3e1f7e83948c66a5f383c199b338/raw/aa1c18898ccde6bb6cbba9c134d066b6c81bc1b6/the_nether.json",
                "vanilla_disable_nether_biomes", "the_nether");
        }
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.REMOVE_END_BIOMES)) {
            addJsonFile(
                "https://gist.githubusercontent.com/DragonEggBedrockBreaking/f4ba3e1f7e83948c66a5f383c199b338/raw/aa1c18898ccde6bb6cbba9c134d066b6c81bc1b6/the_end.json",
                "vanilla_disable_end_biomes", "the_end");
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
    private void patchJsonFile(String name, String shortname) throws IOException {
        String dataPackPath = RegisterGamerules.getServer().getWorldPath(LevelResource.DATAPACK_DIR).toString();
        File jsonFile = new File(dataPackPath + "/" + name + "/data/minecraft/dimension/" + shortname + ".json");
        String jsonFileToString = Files.readString(jsonFile.toPath());

        Pattern p = Pattern.compile("\"seed\": 0");
        Matcher m = p.matcher(jsonFileToString);
        Long seed = RegisterGamerules.getServer().overworld().getSeed();
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
    private void patchJsonFiles() throws IOException {
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.REMOVE_OVERWORLD_BIOMES)) {
            patchJsonFile("vanilla_disable_overworld_biomes", "overworld");
        }
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.REMOVE_NETHER_BIOMES)) {
            patchJsonFile("vanilla_disable_nether_biomes", "the_nether");
        }
        if (RegisterGamerules.getServer().getGameRules().getBoolean(RegisterGamerules.REMOVE_END_BIOMES)) {
            patchJsonFile("vanilla_disable_end_biomes", "the_end");
        }
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason create and patch all of the datapacks
     * @throws IOException
     */
    @Unique
    private void getAndPatchDataPacks() throws IOException {
        createDatapackDirectories();
        addMcmetaFiles();
        addJsonFiles();
        patchJsonFiles();
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason toggle the datapacks based on the gamerules
     */
    @Unique
    private void toggleDataPacks() {
        MinecraftServer server = RegisterGamerules.getServer();
        if (!server.getGameRules().getBoolean(RegisterGamerules.REMOVE_OVERWORLD_BIOMES)) {
            server.getCommands().performCommand(
                server.createCommandSourceStack(), "/datapack disable \"file/vanilla_disable_overworld_biomes\"");
        }
        if (!server.getGameRules().getBoolean(RegisterGamerules.REMOVE_NETHER_BIOMES)) {
            server.getCommands().performCommand(
                server.createCommandSourceStack(), "/datapack disable \"file/vanilla_disable_nether_biomes\"");
        }
        if (!server.getGameRules().getBoolean(RegisterGamerules.REMOVE_END_BIOMES)) {
            server.getCommands().performCommand(
                server.createCommandSourceStack(), "/datapack disable \"file/vanilla_disable_end_biomes\"");
        }
        server.getCommands().performCommand(
            server.createCommandSourceStack(), "/reload");
    }

    /**
     * @author DragonEggBedrockBreaking
     * @reason after the world is loaded, if first load, create datapacks and patch them
     * @reason and always toggle them, on all loads
     * @param ci the callback info
     * @throws IOException
     */
    @Inject(method = "loadLevel", at = @At("RETURN"), cancellable = true)
    private void onLevelLoad(CallbackInfo ci) throws IOException {
        if (!(RegisterGamerules.getServer().overworld().getChunkSource().getGenerator() instanceof FlatLevelSource) &&
            !(RegisterGamerules.getServer().overworld().getChunkSource().getGenerator() instanceof DebugLevelSource)) {
            if (RegisterGamerules.getServer().overworld().getDayTime() < 100) {
                getAndPatchDataPacks();
            }
            toggleDataPacks();
        }
    }
}
