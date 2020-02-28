package kr.heartpattern.spikotadapter.s1094

import kr.heartpattern.spikot.SpikotPlugin
import org.bukkit.plugin.java.annotation.dependency.Dependency
import org.bukkit.plugin.java.annotation.plugin.Plugin

const val version = "[1.9.0 ~ 1.9.4]"

@Plugin(name = "SpikotAdapterS1094", version = "4.0.0")
@Dependency("Spikot")
class SpikotAdapterS1094Plugin : SpikotPlugin()