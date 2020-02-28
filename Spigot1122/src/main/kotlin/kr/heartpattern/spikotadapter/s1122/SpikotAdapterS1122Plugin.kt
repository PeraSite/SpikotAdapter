package kr.heartpattern.spikotadapter.s1122

import kr.heartpattern.spikot.SpikotPlugin
import org.bukkit.plugin.java.annotation.dependency.Dependency
import org.bukkit.plugin.java.annotation.plugin.Plugin

const val version = "[1.12.0 ~ 1.12.2]"

@Plugin(name = "SpikotAdapterS1122", version = "4.0.0")
@Dependency("Spikot")
class SpikotAdapterS1122Plugin : SpikotPlugin()