package kr.heartpattern.spikotadapter.s1152

import kr.heartpattern.spikot.SpikotPlugin
import org.bukkit.plugin.java.annotation.dependency.Dependency
import org.bukkit.plugin.java.annotation.plugin.Plugin

const val version = "[1.15.0 ~ 1.15.2]"

@Plugin(name = "SpikotAdapterS1152", version = "4.0.0")
@Dependency("Spikot")
class SpikotAdapterS1152Plugin : SpikotPlugin()