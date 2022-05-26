import java.nio.file.Path;
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

public class Buildscript extends SimpleQuiltProject {
    @Override
    public VersionMeta createMcVersion() {
        // Minecraft Version
        return Minecraft.getVersion("1.18.2");
    }

    @Override
    public MappingTree createMappings() {
        // Uses Mojang Official Mappings
        return createMojmap();
    }

    @Override
    public FabricLoader getLoader() {
        // Quilt Loader Version
        return new FabricLoader(QuiltMaven.URL, QuiltMaven.loader("0.16.1"));
    }

    @Override
    public String getModId() {
        // Mod Name
        return "vanilla_disable";
    }

    @Override
    public String getVersion() {
        // Mod Version
        return "1.7.0";
    }

    @Override
    public void getModDependencies(ModDependencyCollector d) {
        // Quilt Standard Libraries
        jij(d.addMaven(QuiltMaven.URL, new MavenId(QuiltMaven.GROUP_ID + ".quilted-fabric-api", "fabric-game-rule-api-v1", "1.0.0-beta.14+0.51.1-1.18.2"), ModDependencyFlag.RUNTIME, ModDependencyFlag.COMPILE));
        // CaffeineConfig
        jij(d.addMaven("https://jitpack.io", new MavenId("com.github.FlashyReese:CaffeineConfig:afbaa01"), ModDependencyFlag.COMPILE, ModDependencyFlag.RUNTIME));
        // LazyDFU
        d.addMaven("https://api.modrinth.com/maven/", new MavenId("maven.modrinth:lazydfu:0.1.2"), ModDependencyFlag.RUNTIME);
    }

    @Override
    public int getJavaVersion() {
        // Default is Java 8
        return 17;
    }

    @Override
    public BrachyuraDecompiler decompiler() {
        // Uses QuiltFlower instead of CFR
        return new FernflowerDecompiler(Maven.getMavenJarDep(QuiltMaven.URL, new MavenId("org.quiltmc:quiltflower:1.8.1")));
    };

    @Override
    public Path getBuildJarPath() {
        // Changes the jar file name
        return getBuildLibsDir().resolve(getModId() + "-" + "mc" + createMcVersion().version + "-" + getVersion() + "-quilt" + ".jar");
    }

    @Override
    public ProcessorChain resourcesProcessingChain() {
        // Patches version in quilt.mod.json
        return new ProcessorChain(super.resourcesProcessingChain(), new QmjVersionPatcher(this));
    }
}
