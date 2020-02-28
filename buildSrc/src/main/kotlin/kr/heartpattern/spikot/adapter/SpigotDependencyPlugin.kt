package kr.heartpattern.spikot.adapter

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels

private val DOWNLOAD_LINK = "https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar"

class SpigotDependencyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create("spigotDependency", SpigotDependencyExtension::class.java)

        project.afterEvaluate{
            project.tasks.create("installSpigotDependency", Exec::class.java) { task ->
                val buildSpigotDirectory = File(project.buildDir,"buildtool")
                task.doFirst{
                    buildSpigotDirectory.mkdirs()
                    val buildTool = File(buildSpigotDirectory, "buildtools.jar")

                    // Download latest build tool
                    val readChannel = Channels.newChannel(URL(DOWNLOAD_LINK).openStream())
                    val writeChannel = FileOutputStream(buildTool).channel
                    writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE)
                }
                task.workingDir = buildSpigotDirectory
                task.commandLine = listOf(
                    "java", "-jar", "buildtools.jar", "--rev", extension.version, "--compile", "NONE"
                )
            }

            project.dependencies.add("compileOnly", extension.dependency)
        }
    }
}