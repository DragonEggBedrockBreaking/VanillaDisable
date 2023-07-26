package uk.debb.vanilla_disable;

import it.unimi.dsi.fastutil.objects.ObjectList;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;
import net.minecraft.server.packs.PackType;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class LangFileManager implements ClientModInitializer {
    private final ObjectList<String> languages = ObjectList.of("en_us");

    @Override
    public void onInitializeClient() {
        try {
            File outerrpackdir = new File(FabricLoader.getInstance().getGameDir().toString() + "/resourcepacks/vdlangfile");
            FileUtils.deleteDirectory(outerrpackdir);

            if (!FabricLoader.getInstance().isModLoaded("fabric-resource-loader-v0") && !FabricLoader.getInstance().isModLoaded("quilt_resource_loader")) {
                File rpackdir = new File(FabricLoader.getInstance().getGameDir().toString() + "/resourcepacks/vdlangfile/assets/vanilladisablelangfile/lang");
                if (!rpackdir.mkdirs()) return;

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

                int version = SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES);
                String content = "{\"pack\":{\"pack_format\":" + version + ",\"description\":\"Vanilla Disable Language File\"}}";
                String mcmetaPath = new File(outerrpackdir + "/pack.mcmeta").toString();
                FileWriter mcmetaWriter = new FileWriter(mcmetaPath);
                mcmetaWriter.write(content);
                mcmetaWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
