@file:Suppress("PropertyName")

plugins {
    id("idea")
    id("net.neoforged.gradle.userdev") version "7.0.+"
    id("java-library")
}

val MINECRAFT_VERSION: String by rootProject.extra
val H2_VERSION: String by rootProject.extra
val PARCHMENT_MC_VERSION: String by rootProject.extra
val PARCHMENT_VERSION: String by rootProject.extra
val NEOFORGE_VERSION: String by rootProject.extra

base {
    archivesName.set("vanilla_disable-neoforge-mc${MINECRAFT_VERSION}")
}

jarJar.enable()

dependencies {
    implementation("net.neoforged:neoforge:${NEOFORGE_VERSION}")
    implementation("com.h2database:h2:${H2_VERSION}")
    jarJar("com.h2database:h2:${H2_VERSION}")
    compileOnly(project(":common"))
}

minecraft {
    accessTransformers {
        file("src/main/resources/META-INF/accesstransformer.cfg")
    }
}

runs {
    configureEach {
        dependencies {
            runtime("com.h2database:h2:${H2_VERSION}")
        }
    }
}

tasks.jar {
    from(rootDir.resolve("LICENSE"))
}
tasks.named("test") {
    enabled = false
}

// NeoGradle compiles the game, but we don't want to add our common code to the game's code
val notNeoTask: (Task) -> Boolean = { it: Task -> !it.name.startsWith("neo") && !it.name.startsWith("compileService") }

tasks.withType<JavaCompile>().matching(notNeoTask).configureEach {
    source(project(":common").sourceSets.main.get().allSource)
}
tasks.withType<ProcessResources>().matching(notNeoTask).configureEach {
    from(project(":common").sourceSets.main.get().resources)
}

subsystems.parchment {
    minecraftVersion = PARCHMENT_MC_VERSION
    mappingsVersion = PARCHMENT_VERSION
}
