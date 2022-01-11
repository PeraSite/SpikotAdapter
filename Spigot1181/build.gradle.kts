dependencies {
    paperDevBundle("1.18.1-R0.1-SNAPSHOT")
    compileOnly("kr.heartpattern:Spikot:5.0-SNAPSHOT-1.18.1")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-XXLanguage:+InlineClasses",
                "-Xuse-experimental=kotlin.Experimental"
            )
        }
        sourceCompatibility = "17"
    }
    assemble {
        dependsOn(reobfJar)
    }
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
        
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
}
