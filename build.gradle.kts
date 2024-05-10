import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
    java
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("dev.architectury.loom") version "1.6-SNAPSHOT" apply false
}

architectury {
    minecraft = rootProject.property("minecraft_version").toString()
}

@Suppress("UnstableApiUsage")
subprojects {
    apply(plugin = "dev.architectury.loom")
    val loom = project.extensions.getByName<LoomGradleExtensionAPI>("loom")
    loom.silentMojangMappingsLicense()

    dependencies {
        "minecraft"("com.mojang:minecraft:${rootProject.property("minecraft_version")}")
        "mappings"(loom.layered {
            officialMojangMappings()
            parchment("org.parchmentmc.data:parchment-${rootProject.property("parchment_mc_version")}:${rootProject.property("parchment_version")}@zip")
        })
    }

    tasks.processResources {
        val expandProps = mapOf(
            "mod_version" to rootProject.property("mod_version"),
            "minecraft_version" to rootProject.property("minecraft_version"),
            "minecraft_version_range" to rootProject.property("minecraft_version_range"),
            "mod_name" to rootProject.property("mod_name"),
            "mod_author" to rootProject.property("mod_author"),
            "mod_id" to rootProject.property("mod_id"),
            "license" to rootProject.property("license"),
            "mod_description" to rootProject.property("mod_description"),
            "neoforge_version" to rootProject.property("neoforge_version"),
            "neoforge_loader_version_range" to rootProject.property("neoforge_loader_version_range"),
            "credits" to rootProject.property("credits")
        )
        inputs.properties(expandProps)
        filesMatching(listOf("pack.mcmeta", "fabric.mod.json", "META-INF/neoforge.mods.toml", "*.mixins.json")) {
            expand(expandProps)
        }
    }

    tasks.create("clientIdeBeforeRun") {}
    tasks.create("idePostSync") {}
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "architectury-plugin")

    repositories {
        maven("https://jitpack.io") {
            name = "JitPack"
        }
        maven("https://maven.terraformersmc.com/") {
            name = "TerraformersMC"
        }
        maven("https://maven.parchmentmc.org") {
            name = "ParchmentMC"
        }
        maven("https://maven.neoforged.net/releases/") {
            name = "NeoForge"
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release = 21
    }
    tasks.create("prepareWorkspace") {}
}