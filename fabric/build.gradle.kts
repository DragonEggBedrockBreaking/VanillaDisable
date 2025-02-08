@file:Suppress("PropertyName")

plugins {
    java
    idea
    id("fabric-loom") version("1.9.+")
}

val MINECRAFT_VERSION: String by rootProject.extra
val H2_VERSION: String by rootProject.extra
val PARCHMENT_MC_VERSION: String by rootProject.extra
val PARCHMENT_VERSION: String by rootProject.extra
val FABRIC_LOADER_VERSION: String by rootProject.extra

base {
    archivesName.set("vanilla_disable-fabric-mc${MINECRAFT_VERSION}")
}

repositories {
    maven { url = uri("https://maven.terraformersmc.com/") }
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
    modImplementation("com.h2database:h2:${H2_VERSION}")
    include("com.h2database:h2:${H2_VERSION}")

    implementation("com.google.code.findbugs:jsr305:3.0.1")
    compileOnly(project(":common"))
}

loom {
    accessWidenerPath.set(project(":common").file("src/main/resources/vanilla_disable.aw"))

    @Suppress("UnstableApiUsage")
    mixin { defaultRefmapName.set("vanilla_disable.refmap.json") }

    runs {
        named("client") {
            client()
            configName = "Fabric Client"
            ideConfigGenerated(true)
            runDir("run")
        }
        named("server") {
            server()
            configName = "Fabric Server"
            ideConfigGenerated(true)
            runDir("run")
        }
    }
}

tasks {
    withType<JavaCompile> {
        source(project(":common").sourceSets.main.get().allSource)
    }

    processResources {
        from(project(":common").sourceSets.main.get().resources)
    }

    jar {
        from(rootDir.resolve("LICENSE"))
    }
}
