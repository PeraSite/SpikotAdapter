package kr.heartpattern.spikotadapter.s1144

import kr.heartpattern.spikot.SpikotPlugin
import org.bukkit.plugin.java.annotation.dependency.Dependency
import org.bukkit.plugin.java.annotation.plugin.Plugin

const val version = "[1.14.0 ~ 1.14.4]"

@Plugin(name = "SpikotAdapterS1144", version = "4.0.0")
@Dependency("Spikot")
class SpikotAdapterS1144Plugin : SpikotPlugin()