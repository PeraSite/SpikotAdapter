package kr.heartpattern.spikotadapter.s1102

import kr.heartpattern.spikot.SpikotPlugin
import org.bukkit.plugin.java.annotation.dependency.Dependency
import org.bukkit.plugin.java.annotation.plugin.Plugin

const val version = "[1.10.0 ~ 1.10.2]"

@Plugin(name = "SpikotAdapterS1102", version = "4.0.0")
@Dependency("Spikot")
class SpikotAdapterS1102Plugin : SpikotPlugin()