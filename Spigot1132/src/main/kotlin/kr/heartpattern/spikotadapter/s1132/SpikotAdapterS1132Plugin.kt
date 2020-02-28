package kr.heartpattern.spikotadapter.s1132

import kr.heartpattern.spikot.SpikotPlugin
import org.bukkit.plugin.java.annotation.dependency.Dependency
import org.bukkit.plugin.java.annotation.plugin.Plugin

const val version = "[1.13.0 ~ 1.13.2]"

@Plugin(name = "SpikotAdapterS1132", version = "4.0.0")
@Dependency("Spikot")
class SpikotAdapterS1132Plugin : SpikotPlugin()