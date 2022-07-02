import io.github.coolcrabs.brachyura.decompiler.BrachyuraDecompiler;
import io.github.coolcrabs.brachyura.decompiler.fernflower.FernflowerDecompiler;
import io.github.coolcrabs.brachyura.fabric.FabricContext.ModDependencyCollector;
import io.github.coolcrabs.brachyura.fabric.FabricContext.ModDependencyFlag;
import io.github.coolcrabs.brachyura.fabric.FabricLoader;
import io.github.coolcrabs.brachyura.maven.Maven;
import io.github.coolcrabs.brachyura.maven.MavenId;
import io.github.coolcrabs.brachyura.minecraft.Minecraft;
import io.github.coolcrabs.brachyura.minecraft.VersionMeta;
import io.github.coolcrabs.brachyura.processing.ProcessorChain;
import io.github.coolcrabs.brachyura.quilt.QuiltMaven;
import io.github.coolcrabs.brachyura.quilt.SimpleQuiltProject;
import net.fabricmc.mappingio.tree.MappingTree;

import java.nio.file.Path;

public class Buildscript extends SimpleQuiltProject {
    @Override
    public VersionMeta createMcVersion() {
        // Minecraft Version
        return Minecraft.getVersion(Versions.MINECRAFT_VERSION);
    }

    @Override
    public MappingTree createMappings() {
        // Uses Mojang Official Mappings
        return createMojmap();
    }

    @Override
    public FabricLoader getLoader() {
        // Quilt Loader Version
        return new FabricLoader(QuiltMaven.URL, QuiltMaven.loader(Versions.QUILT_LOADER_VERSION));
    }

    @Override
    public String getModId() {
        // Mod Name
        return "vanilla_disable";
    }

    @Override
    public String getVersion() {
        // Mod Version
        return Versions.MOD_VERSION;
    }

    @Override
    public void getModDependencies(ModDependencyCollector d) {
        // Quilt Standard Libraries
        if (Versions.JIJ_QFAPI)
            jij(d.addMaven(QuiltMaven.URL, new MavenId(QuiltMaven.GROUP_ID + ".quilted-fabric-api", "fabric-game-rule-api-v1", Versions.QFAPI_VERSION), ModDependencyFlag.RUNTIME, ModDependencyFlag.COMPILE));
        else
            d.addMaven(QuiltMaven.URL, new MavenId(QuiltMaven.GROUP_ID + ".quilted-fabric-api", "fabric-game-rule-api-v1", Versions.QFAPI_VERSION), ModDependencyFlag.RUNTIME, ModDependencyFlag.COMPILE);
        // CaffeineConfig
        jij(d.addMaven("https://maven.flashyreese.me/", new MavenId("releases.net.caffeinemc", "mixin-config", Versions.CAFFEINE_CONFIG_VERSION), ModDependencyFlag.COMPILE, ModDependencyFlag.RUNTIME));
        // LazyDFU
        if (Versions.LAZYDFU_ENABLED)
            d.addMaven("https://api.modrinth.com/maven/", new MavenId("maven.modrinth", "lazydfu", Versions.LAZYDFU_VERSION), ModDependencyFlag.RUNTIME);
    }

    @Override
    public int getJavaVersion() {
        // Default is Java 8
        return Versions.JAVA_VERSION;
    }

    @Override
    public BrachyuraDecompiler decompiler() {
        // Uses QuiltFlower instead of CFR
        return new FernflowerDecompiler(Maven.getMavenJarDep(QuiltMaven.URL, new MavenId("org.quiltmc", "quiltflower", Versions.QUILTFLOWER_VERSION)));
    }

    @Override
    public Path getBuildJarPath() {
        // Changes the jar file name
        return getBuildLibsDir().resolve(getModId() + "-" + "mc" + createMcVersion().version + "-" + getVersion() + "-quilt" + ".jar");
    }

    @Override
    public ProcessorChain resourcesProcessingChain() {
        // Patches version in quilt.mod.json
        return new ProcessorChain(super.resourcesProcessingChain(), new QmjVersionPatcher());
    }
}