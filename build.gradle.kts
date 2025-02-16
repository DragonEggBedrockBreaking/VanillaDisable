@file:Suppress("PropertyName")

plugins {
    id("java")
    id("fabric-loom") version("1.9.+") apply(false)
}

val MOD_NAME by extra { "VanillaDisable" }
val MOD_DESCRIPTION by extra { "Allows you to disable a lot of vanilla features." }
val MOD_ID by extra { "vanilla_disable" }
val MOD_AUTHOR by extra { "DragonEggBedrockBreaking" }
val CREDITS by extra { "Wesley1808, FallenBreath, NoComment, LittleLily, ThatTrollzer, Kichura, ellieisjelly" }
val MAVEN_GROUP by extra { "uk.debb" }
val LICENSE by extra { "MPL-2.0" }
val MOD_VERSION by extra { "4.0.4" }

val MINECRAFT_VERSION by extra { "1.21.4" }
val MINECRAFT_VERSION_RANGE_FABRIC by extra { ">=1.21.4" }
val MINECRAFT_VERSION_RANGE_NEOFORGE by extra { "[1.21.4,1.22)" }
val H2_VERSION by extra { "2.3.232" }
val ASM_VERSION by extra { "9.7.1" }
val PARCHMENT_MC_VERSION by extra { "1.21.4" }
val PARCHMENT_VERSION by extra { "2025.01.19" }
val MIXIN_EXTRAS_VERSION by extra { "0.4.1" }
val FABRIC_MIXIN_VERSION by extra { "0.15.5+mixin.0.8.7" }
val FABRIC_LOADER_VERSION by extra { "0.16.10" }
val FABRIC_LOADER_VERSION_RANGE by extra { ">=0.16.9" }
val NEOFORGE_VERSION by extra { "21.4.89-beta"}
val NEOFORGE_LOADER_VERSION_RANGE by extra { ">=21.4.0-beta" }

allprojects {
    apply(plugin = "java")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

subprojects {
    java.toolchain.languageVersion = JavaLanguageVersion.of(21)

    version = MOD_VERSION
    group = MAVEN_GROUP

    tasks.processResources {
        val expandProps = mapOf(
            "mod_name" to MOD_NAME,
            "mod_description" to MOD_DESCRIPTION,
            "mod_id" to MOD_ID,
            "mod_author" to MOD_AUTHOR,
            "credits" to CREDITS,
            "license" to LICENSE,
            "mod_version" to MOD_VERSION,
            "minecraft_version" to MINECRAFT_VERSION,
            "minecraft_version_range_fabric" to MINECRAFT_VERSION_RANGE_FABRIC,
            "minecraft_version_range_neoforge" to MINECRAFT_VERSION_RANGE_NEOFORGE,
            "fabric_loader_version_range" to FABRIC_LOADER_VERSION_RANGE,
            "neoforge_loader_version_range" to NEOFORGE_LOADER_VERSION_RANGE
        )
        inputs.properties(expandProps)
        filesMatching(listOf("pack.mcmeta", "fabric.mod.json", "META-INF/neoforge.mods.toml", "*.mixins.json")) {
            expand(expandProps)
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(21)
    }

    // Disables Gradle's custom module metadata from being published to maven. The
    // metadata includes mapped dependencies which are not reasonably consumable by
    // other mod developers.
    tasks.withType<GenerateModuleMetadata>().configureEach {
        enabled = false
    }
}
