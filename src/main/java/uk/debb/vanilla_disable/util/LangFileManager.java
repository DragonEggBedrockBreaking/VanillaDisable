package uk.debb.vanilla_disable.util;

import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class LangFileManager {
    public static void langFileFallback() throws IOException {
        if (!FabricLoader.getInstance().isModLoaded("fabric-resource-loader-v0")) {
            File outerrpackdir = new File(FabricLoader.getInstance().getGameDir().toString() + "/resourcepacks/vdlangfile");
            FileUtils.deleteDirectory(outerrpackdir);
            File rpackdir = new File(FabricLoader.getInstance().getGameDir().toString() + "/resourcepacks/vdlangfile/assets/vanilladisablelangfile/lang");
            if (!rpackdir.mkdirs()) return;
            InputStream inputUrl = LangFileManager.class.getResourceAsStream("/assets/vanilla_disable/lang/en_us.json");
            Path dest = new File(rpackdir + "/en_us.json").toPath();
            if (inputUrl != null) {
                Files.copy(inputUrl, dest, StandardCopyOption.REPLACE_EXISTING);
            }
            String content = "{\"pack\":{\"pack_format\":12,\"description\":\"Vanilla Disable Language File\"}}";
            String mcmetaPath = new File(outerrpackdir + "/pack.mcmeta").toString();
            FileWriter mcmetaWriter = new FileWriter(mcmetaPath);
            mcmetaWriter.write(content);
            mcmetaWriter.close();
        }
    }
}