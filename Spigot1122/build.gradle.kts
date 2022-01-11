plugins {
    kotlin("jvm") version "1.6.10"
    id("spigotdependency")
}

dependencies {
//    implementation(project(":Common"))
}

spigotDependency {
    version = "1.12.2"
    dependency = "org.spigotmc:spigot:1.12.2-R0.1-SNAPSHOT"
}
