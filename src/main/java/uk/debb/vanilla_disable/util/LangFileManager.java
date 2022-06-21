package uk.debb.vanilla_disable.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.quiltmc.loader.api.QuiltLoader;
import org.spongepowered.asm.mixin.Unique;

public class LangFileManager {
    /**
     * @author DragonEggBedrockBreaking
     * @reason if there is no resource loader, manually load the lang files
     */
    @Unique
    public static void langFileFallback() throws IOException {
        if (!QuiltLoader.isModLoaded("quilt_resource_loader")) {
            File outerrpackdir = new File(QuiltLoader.getGameDir().toString() + "/resourcepacks/vdlangfile");
            File rpackdir = new File(QuiltLoader.getGameDir().toString() + "/resourcepacks/vdlangfile/assets/vanilladisablelangfile/lang");
            if (!rpackdir.mkdirs()) return;
            InputStream inputUrl = LangFileManager.class.getResourceAsStream("/assets/vanilla_disable/lang/en_us.json");
            Path dest = new File(rpackdir + "/en_us.json").toPath();
            if (inputUrl != null) {
                Files.copy(inputUrl, dest, StandardCopyOption.REPLACE_EXISTING);
            }
            String content = "{\"pack\":{\"pack_format\":9,\"description\":\"Vanilla Disable Language File\"}}";
            String mcmetaPath = new File(outerrpackdir + "/pack.mcmeta").toString();
            FileWriter mcmetaWriter = new FileWriter(mcmetaPath);
            mcmetaWriter.write(content);
            mcmetaWriter.close();
        }
    }
}
