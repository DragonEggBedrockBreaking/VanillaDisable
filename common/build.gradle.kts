@Suppress("LocalVariableName")
architectury {
    val enabled_platforms: String by rootProject
    common(enabled_platforms.split(","))
}

@Suppress("UnstableApiUsage")
loom {
    mixin.useLegacyMixinAp.set(false)
    accessWidenerPath.set(file("src/main/resources/${rootProject.property("mod_id")}.aw"))
}

dependencies {
    compileOnly("io.github.llamalad7:mixinextras-common:${rootProject.property("mixin_extras_version")}")
    annotationProcessor("io.github.llamalad7:mixinextras-common:${rootProject.property("mixin_extras_version")}")
    compileOnly("net.fabricmc:sponge-mixin:${rootProject.property("fabric_mixin_version")}")
    compileOnly("com.h2database:h2:${rootProject.property("h2_version")}")
    compileOnly("net.fabricmc:fabric-loader:${rootProject.property("fabric_loader_version")}")
}