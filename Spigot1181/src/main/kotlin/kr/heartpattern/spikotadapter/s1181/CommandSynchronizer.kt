package kr.heartpattern.spikotadapter.s1181

import kr.heartpattern.spikot.command.internal.CommandManager
import kr.heartpattern.spikot.module.AbstractModule
import kr.heartpattern.spikot.module.ModulePriority
import kr.heartpattern.spikot.module.ServerModule
import kr.heartpattern.spikot.utils.withAccessible
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_18_R1.CraftServer

@ServerModule(priority = ModulePriority.API, dependOn = [CommandManager::class])
object CommandSynchronizer : AbstractModule() {
    override fun onEnable() {
        val method = CraftServer::class.java.getDeclaredMethod("syncCommands")
        method.withAccessible {
            method(Bukkit.getServer())
        }
    }
}
