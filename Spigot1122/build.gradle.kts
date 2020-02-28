plugins{
    id("kr.heartpattern.spikot") version "4.0.3"
    id("spigotdependency")
}

dependencies{
    compile(project(":Common"))
}

spigotDependency{
    version = "1.12.2"
    dependency = "org.spigotmc:spigot:1.12.2-R0.1-SNAPSHOT"
}