package kr.heartpattern.spikotadapter.s1112

import kr.heartpattern.spikot.SpikotPlugin
import org.bukkit.plugin.java.annotation.dependency.Dependency
import org.bukkit.plugin.java.annotation.plugin.Plugin

const val version = "[1.11.0 ~ 1.11.2]"

@Plugin(name = "SpikotAdapterS1112", version = "4.0.0")
@Dependency("Spikot")
class SpikotAdapterS1112Plugin : SpikotPlugin()