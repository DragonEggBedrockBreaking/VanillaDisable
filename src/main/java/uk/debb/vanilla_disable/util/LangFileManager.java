package uk.debb.vanilla_disable.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.quiltmc.loader.api.QuiltLoader;
import org.spongepowered.asm.mixin.Unique;

public class LangFileManager {
    /**
     * @author DragonEggBedrockBreaking
     * @reason if there is no resource loader, manually load the lang files
     * @throws IOException
     */
    @Unique
    public static void langFileFallback() throws IOException {
        File outerrpackdir = new File(QuiltLoader.getGameDir().toString() + "/resourcepacks/vdlangfile");
        outerrpackdir.delete();
        if (!QuiltLoader.isModLoaded("quilt_resource_loader")) {
            File rpackdir = new File(QuiltLoader.getGameDir().toString() + "/resourcepacks/vdlangfile/assets/vanilladisablelangfile/lang");
            rpackdir.mkdirs();
            InputStream inputUrl = LangFileManager.class.getResourceAsStream("/assets/vanilla_disable/lang/en_us.json");
            Path dest = new File(rpackdir.toString() + "/en_us.json").toPath();
            Files.copy(inputUrl, dest);
            InputStream otherInputUrl = LangFileManager.class.getResourceAsStream("/pack.mcmeta");
            Path otherDest = new File(outerrpackdir.toString() + "/pack.mcmeta").toPath();
            Files.copy(otherInputUrl, otherDest);
        }
    }
}
