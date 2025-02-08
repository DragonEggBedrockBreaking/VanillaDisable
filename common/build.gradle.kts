@file:Suppress("PropertyName")
import net.fabricmc.loom.task.AbstractRemapJarTask

plugins {
    id("java")
    id("idea")
    id("fabric-loom") version "1.9.+"
}

val MINECRAFT_VERSION: String by rootProject.extra
val H2_VERSION: String by rootProject.extra
val ASM_VERSION: String by rootProject.extra
val PARCHMENT_MC_VERSION: String by rootProject.extra
val PARCHMENT_VERSION: String by rootProject.extra
val MIXIN_EXTRAS_VERSION: String by rootProject.extra
val FABRIC_MIXIN_VERSION: String by rootProject.extra
val FABRIC_LOADER_VERSION: String by rootProject.extra

base {
    archivesName.set("vanilla_disable-common-mc${MINECRAFT_VERSION}")
}

repositories {
    maven { url = uri("https://maven.parchmentmc.org/") }
}

dependencies {
    minecraft(group = "com.mojang", name = "minecraft", version = MINECRAFT_VERSION)
    @Suppress("UnstableApiUsage")
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${PARCHMENT_MC_VERSION}:${PARCHMENT_VERSION}@zip")
    })
    modImplementation("net.fabricmc:fabric-loader:${FABRIC_LOADER_VERSION}")
    compileOnly("io.github.llamalad7:mixinextras-common:${MIXIN_EXTRAS_VERSION}")
    annotationProcessor("io.github.llamalad7:mixinextras-common:${MIXIN_EXTRAS_VERSION}")
    compileOnly("net.fabricmc:sponge-mixin:${FABRIC_MIXIN_VERSION}")
    compileOnly("com.h2database:h2:${H2_VERSION}")
    compileOnly("org.ow2.asm:asm-tree:${ASM_VERSION}")
}

tasks.withType<AbstractRemapJarTask>().forEach {
    it.targetNamespace = "named"
}

loom {
    @Suppress("UnstableApiUsage")
    mixin {
        defaultRefmapName = "vanilla_disable.refmap.json"
    }
    accessWidenerPath = file("src/main/resources/vanilla_disable.aw")
}

tasks {
    jar {
        from(rootDir.resolve("LICENSE"))
    }
    register("prepareWorkspace") {
        dependsOn("genSourcesWithVineflower")
    }
}
