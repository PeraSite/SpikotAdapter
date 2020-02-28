package kr.heartpattern.spikotadapter.s1088

import kr.heartpattern.spikot.SpikotPlugin
import org.bukkit.plugin.java.annotation.dependency.Dependency
import org.bukkit.plugin.java.annotation.plugin.Plugin

const val version = "[1.8.0 ~ 1.8.8]"

@Plugin(name = "SpikotAdapterS1088", version = "4.0.0")
@Dependency("Spikot")
class SpikotAdapterS1088Plugin : SpikotPlugin()