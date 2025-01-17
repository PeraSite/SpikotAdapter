package kr.heartpattern.spikotadapter.s1181

import kr.heartpattern.spikot.adapter.Adapter
import kr.heartpattern.spikot.adapter.SupportedVersion
import kr.heartpattern.spikot.adapters.ItemStackAdapter
import kr.heartpattern.spikot.adapters.NBTAdapter
import kr.heartpattern.spikot.module.AbstractModule
import kr.heartpattern.spikot.nbt.WrapperNBTCompound
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.ItemStack
import org.bukkit.craftbukkit.v1_18_R1.inventory.CraftItemStack

@Adapter
@SupportedVersion(version)
class ItemStackAdapterImpl : AbstractModule(), ItemStackAdapter {
    private companion object {
        val handleField = CraftItemStack::class.java.getDeclaredField("handle")

        init {
            handleField.isAccessible = true
        }
    }

    private val org.bukkit.inventory.ItemStack.tag: CompoundTag?
        get() = if (this is CraftItemStack) (handleField.get(this) as ItemStack?)?.tag else null

    override fun getWrappedTag(itemStack: org.bukkit.inventory.ItemStack): WrapperNBTCompound? {
        return itemStack.tag?.let { NBTAdapter.wrapNBTCompound(it) }
    }

    override fun hasTag(itemStack: org.bukkit.inventory.ItemStack): Boolean {
        return itemStack.tag != null
    }

    override fun isCraftItemStack(itemStack: org.bukkit.inventory.ItemStack): Boolean {
        return itemStack is CraftItemStack
    }

    override fun toCraftItemStack(itemStack: org.bukkit.inventory.ItemStack): org.bukkit.inventory.ItemStack {
        return if (itemStack is CraftItemStack) itemStack
        else CraftItemStack.asCraftMirror(CraftItemStack.asNMSCopy(itemStack))
    }

    override fun fromNBTCompound(nbt: WrapperNBTCompound): org.bukkit.inventory.ItemStack {
        val itemStack = ItemStack.of(nbt.tag as CompoundTag)
        return CraftItemStack.asCraftMirror(itemStack)
    }

    override fun toNBTCompound(itemStack: org.bukkit.inventory.ItemStack): WrapperNBTCompound {
        return if (itemStack is CraftItemStack) {
            NBTAdapter.wrapNBTCompound((handleField.get(itemStack) as ItemStack).save(CompoundTag()))
        } else {
            val converted = CraftItemStack.asCraftCopy(itemStack)
            toNBTCompound(converted)
        }
    }
}
