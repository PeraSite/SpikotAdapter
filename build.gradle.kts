import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.6.10"
    
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("io.papermc.paperweight.userdev") version "1.3.3" apply false
    id("kr.entree.spigradle") version "2.3.3" apply false
}


subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "io.papermc.paperweight.userdev")
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "kr.entree.spigradle")
    
    
    tasks {
        named<ShadowJar>("shadowJar") {
            archiveFileName.set("SpikotAdapter-${project.name}")
        }
    }
}

allprojects {
    group = "kr.heartpattern.spikot"
    version = "1.0-SNAPSHOT"
    
    repositories {
        mavenCentral()
        mavenLocal()
    }
    
    dependencies {
        compileOnly(kotlin("stdlib-jdk8"))
    }
}
