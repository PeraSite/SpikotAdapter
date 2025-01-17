package kr.heartpattern.spikotadapter.s1181

import kr.heartpattern.spikot.adapter.Adapter
import kr.heartpattern.spikot.adapter.SupportedVersion
import kr.heartpattern.spikot.adapters.NBTAdapter
import kr.heartpattern.spikot.module.AbstractModule
import kr.heartpattern.spikot.nbt.*
import kr.heartpattern.spikot.nbt.TagType
import kr.heartpattern.spikot.utils.withAccessible
import net.minecraft.nbt.*
import java.io.ByteArrayOutputStream

@Adapter
@SupportedVersion(version)
class NBTAdapterImpl : AbstractModule(), NBTAdapter {
    private val nbtTagEnd: EndTag

    init {
        val constructor = EndTag::class.java.getDeclaredConstructor()
        constructor.withAccessible {
            nbtTagEnd = constructor.newInstance()
        }
    }

    override fun compressNBT(nbt: WrapperNBTCompound): ByteArray {
        val outputStream = ByteArrayOutputStream()
        NbtIo.writeCompressed(nbt.tag as CompoundTag, outputStream)
        return outputStream.toByteArray()
    }

    override fun decompressNBT(array: ByteArray): WrapperNBTCompound {
        val inputStream = array.inputStream()
        val nbt = NbtIo.readCompressed(inputStream)
        return wrapNBTCompound(nbt)
    }

    override fun createNBTByte(value: Byte): WrapperNBTByte = WrapperNBTByteImpl(value)

    override fun createNBTByteArray(value: ByteArray): WrapperNBTByteArray = WrapperNBTByteArrayImpl(value)

    override fun createNBTDouble(value: Double): WrapperNBTDouble = WrapperNBTDoubleImpl(value)

    override fun createNBTEnd(value: Unit): WrapperNBTEnd = WrapperNBTEndImpl()

    override fun createNBTFloat(value: Float): WrapperNBTFloat = WrapperNBTFloatImpl(value)

    override fun createNBTInt(value: Int): WrapperNBTInt = WrapperNBTIntImpl(value)

    override fun createNBTIntArray(value: IntArray): WrapperNBTIntArray = WrapperNBTIntArrayImpl(value)

    override fun createNBTLong(value: Long): WrapperNBTLong = WrapperNBTLongImpl(value)

    override fun createNBTLongArray(value: LongArray): WrapperNBTLongArray = WrapperNBTLongArrayImpl(value)

    override fun createNBTShort(value: Short): WrapperNBTShort = WrapperNBTShortImpl(value)

    override fun createNBTString(value: String): WrapperNBTString = WrapperNBTStringImpl(value)

    override fun <W : WrapperNBTBase<*>> createNBTList(value: List<W>): WrapperNBTList<W> = WrapperNBTListImpl(value)

    override fun createNBTCompound(value: Map<String, WrapperNBTBase<*>>): WrapperNBTCompound =
        WrapperNBTCompoundImpl(value)

    override fun getType(tag: Any): TagType<*> {
        return TagType.ofId((tag as Tag).id.toInt())
    }

    override fun wrapNBTByte(tag: Any): WrapperNBTByte = WrapperNBTByteImpl(tag as ByteTag)

    override fun wrapNBTByteArray(tag: Any): WrapperNBTByteArray = WrapperNBTByteArrayImpl(tag as ByteArrayTag)

    override fun wrapNBTCompound(tag: Any): WrapperNBTCompound = WrapperNBTCompoundImpl(tag as CompoundTag)

    override fun wrapNBTDouble(tag: Any): WrapperNBTDouble = WrapperNBTDoubleImpl(tag as DoubleTag)

    override fun wrapNBTEnd(tag: Any): WrapperNBTEnd = WrapperNBTEndImpl()

    override fun wrapNBTFloat(tag: Any): WrapperNBTFloat = WrapperNBTFloatImpl(tag as FloatTag)

    override fun wrapNBTInt(tag: Any): WrapperNBTInt = WrapperNBTIntImpl(tag as IntTag)

    override fun wrapNBTIntArray(tag: Any): WrapperNBTIntArray = WrapperNBTIntArrayImpl(tag as IntArrayTag)

    override fun <W : WrapperNBTBase<*>> wrapNBTList(tag: Any): WrapperNBTList<W> =
        WrapperNBTListImpl(tag as ListTag)

    override fun wrapNBTLong(tag: Any): WrapperNBTLong = WrapperNBTLongImpl(tag as LongTag)

    override fun wrapNBTLongArray(tag: Any): WrapperNBTLongArray = WrapperNBTLongArrayImpl(tag as LongArrayTag)

    override fun wrapNBTShort(tag: Any): WrapperNBTShort = WrapperNBTShortImpl(tag as ShortTag)

    override fun wrapNBTString(tag: Any): WrapperNBTString = WrapperNBTStringImpl(tag as StringTag)
}
