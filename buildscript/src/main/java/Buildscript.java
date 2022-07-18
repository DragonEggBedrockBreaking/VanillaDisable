import io.github.coolcrabs.brachyura.decompiler.BrachyuraDecompiler;
import io.github.coolcrabs.brachyura.decompiler.fernflower.FernflowerDecompiler;
import io.github.coolcrabs.brachyura.fabric.FabricContext.ModDependencyCollector;
import io.github.coolcrabs.brachyura.fabric.FabricContext.ModDependencyFlag;
import io.github.coolcrabs.brachyura.fabric.FabricContext;
import io.github.coolcrabs.brachyura.fabric.FabricLoader;
import io.github.coolcrabs.brachyura.maven.Maven;
import io.github.coolcrabs.brachyura.maven.MavenId;
import io.github.coolcrabs.brachyura.minecraft.Minecraft;
import io.github.coolcrabs.brachyura.minecraft.VersionMeta;
import io.github.coolcrabs.brachyura.quilt.QuiltMaven;
import io.github.coolcrabs.brachyura.quilt.SimpleQuiltProject;
import net.fabricmc.mappingio.tree.MappingTree;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Buildscript extends SimpleQuiltProject {
    @Override
    public VersionMeta createMcVersion() {
        return Minecraft.getVersion(Versions.MINECRAFT_VERSION);
    }

    @Override
    public MappingTree createMappings() {
        return createMojmap();
    }

    @Override
    public FabricLoader getLoader() {
        return new FabricLoader(QuiltMaven.URL, QuiltMaven.loader(Versions.QUILT_LOADER_VERSION));
    }

    @Override
    public void getModDependencies(ModDependencyCollector d) {
        // Quilted Fabric API
        d.addMaven(QuiltMaven.URL, new MavenId(QuiltMaven.GROUP_ID + ".quilted-fabric-api", "fabric-game-rule-api-v1", Versions.QFAPI_VERSION), ModDependencyFlag.RUNTIME, ModDependencyFlag.COMPILE);
        // CaffeineConfig
        jij(d.addMaven("https://maven.flashyreese.me/", new MavenId("releases.net.caffeinemc", "mixin-config", Versions.CAFFEINE_CONFIG_VERSION), ModDependencyFlag.COMPILE, ModDependencyFlag.RUNTIME));
        // MixinExtras
        jij(d.addMaven("https://jitpack.io/", new MavenId("com.github.LlamaLad7", "MixinExtras", Versions.MIXIN_EXTRAS_VERSION), ModDependencyFlag.COMPILE, ModDependencyFlag.RUNTIME));
        // Conditional Mixin
        jij(d.addMaven("https://jitpack.io/", new MavenId("com.github.Fallen-Breath", "conditional-mixin", Versions.CONDITIONAL_MIXIN_VERSION), ModDependencyFlag.COMPILE, ModDependencyFlag.RUNTIME));
        // LazyDFU
        d.addMaven("https://api.modrinth.com/maven/", new MavenId("maven.modrinth", "lazydfu", Versions.LAZYDFU_VERSION), ModDependencyFlag.RUNTIME);
    }

    @Override
    protected FabricContext createContext() {
        return new SimpleQuiltContext(){
            @Override
            public List<Path> getCompileDependencies() {
                List<Path> paths = super.getCompileDependencies();
                ArrayList<Path> a = new ArrayList<>();
                ArrayList<Path> b = new ArrayList<>();
                for (Path p : paths) {
                    if (p.getFileName().toString().contains("MixinExtras")) {
                        a.add(p);
                    } else {
                        b.add(p);
                    }
                }
                a.addAll(b);
                return a;
            }
        };
    }

    @Override
    public int getJavaVersion() {
        return Versions.JAVA_VERSION;
    }

    @Override
    public BrachyuraDecompiler decompiler() {
        return new FernflowerDecompiler(Maven.getMavenJarDep(QuiltMaven.URL, new MavenId("org.quiltmc", "quiltflower", Versions.QUILTFLOWER_VERSION)));
    }

    @Override
    public Path getBuildJarPath() {
        return getBuildLibsDir().resolve(getModId() + "-" + "mc" + createMcVersion().version + "-" + getVersion() + "-quilt" + ".jar");
    }
}
