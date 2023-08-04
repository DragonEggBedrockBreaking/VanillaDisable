package uk.debb.vanilla_disable;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.PackType;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LangFileManager implements ClientModInitializer {
    @SuppressWarnings("ConstantConditions StatementWithEmptyBody")
    @Override
    public void onInitializeClient() {
        try {
            File outerrpackdir = new File(FabricLoader.getInstance().getGameDir().toString() + "/resourcepacks/vdlangfile");
            boolean exists = outerrpackdir.exists();
            FileUtils.deleteDirectory(outerrpackdir);

            if (!FabricLoader.getInstance().isModLoaded("fabric-resource-loader-v0") && !FabricLoader.getInstance().isModLoaded("quilt_resource_loader")) {
                File rpackdir = new File(FabricLoader.getInstance().getGameDir().toString() + "/resourcepacks/vdlangfile/assets/vanilladisablelangfile/lang");
                if (!rpackdir.mkdirs()) return;

                int version = SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES);
                String content = "{\"pack\":{\"pack_format\":" + version + ",\"description\":\"Vanilla Disable Language File\"}}";
                String mcmetaPath = new File(outerrpackdir + "/pack.mcmeta").toString();
                FileWriter mcmetaWriter = new FileWriter(mcmetaPath);
                mcmetaWriter.write(content);
                mcmetaWriter.close();

                File optionsTxt = new File(FabricLoader.getInstance().getGameDir().toString() + "/options.txt");
                String optionsTxtContent = FileUtils.readFileToString(optionsTxt, StandardCharsets.UTF_8);
                ObjectList<String> lines = new ObjectArrayList<>();
                Arrays.stream(optionsTxtContent.split("\n")).forEach(line -> {
                    if (line.startsWith("resourcePacks") && !line.contains("vdlangfile") && !exists) {
                        lines.add(line.replace("]", ",\"file/vdlangfile\"]"));
                    } else {
                        lines.add(line);
                    }
                });
                FileUtils.write(optionsTxt, String.join("\n", lines), StandardCharsets.UTF_8);

                Thread thread = new Thread(() -> {
                    while (Minecraft.getInstance().getLanguageManager() == null) {}
                    while (Minecraft.getInstance().getLanguageManager().getLanguages().size() == 1) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException ignored) {}
                    }
                    Set<String> languages = Minecraft.getInstance().getLanguageManager().getLanguages().keySet();

                    languages.forEach(language -> {
                        InputStream inputUrl = LangFileManager.class.getResourceAsStream("/assets/vanilla_disable/lang/" + language + ".json");
                        Path dest = new File(rpackdir + "/" + language + ".json").toPath();
                        if (inputUrl != null) {
                            try {
                                Files.copy(inputUrl, dest, StandardCopyOption.REPLACE_EXISTING);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                });
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
