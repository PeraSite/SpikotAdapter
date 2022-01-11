package kr.heartpattern.spikotadapter.s1181

import kr.heartpattern.spikot.adapters.NBTAdapter
import kr.heartpattern.spikot.nbt.*
import kr.heartpattern.spikot.nbt.TagType
import net.minecraft.nbt.*
import java.util.*

class WrapperNBTEndImpl : WrapperNBTEnd {
    override val tag: Any
        get() = EndTag.INSTANCE
    
    override val value: Unit
        get() = Unit
}

class WrapperNBTByteImpl(override val tag: ByteTag) : WrapperNBTByte {
    constructor(value: Byte) : this(ByteTag.valueOf(value))
    
    override val value: Byte
        get() = tag.asByte
}

class WrapperNBTLongImpl(override val tag: LongTag) : WrapperNBTLong {
    constructor(value: Long) : this(LongTag.valueOf(value))
    
    override val value: Long
        get() = tag.asLong
}

class WrapperNBTIntImpl(override val tag: IntTag) : WrapperNBTInt {
    constructor(value: Int) : this(IntTag.valueOf(value))
    
    override val value: Int
        get() = tag.asInt
}

class WrapperNBTShortImpl(override val tag: ShortTag) : WrapperNBTShort {
    constructor(value: Short) : this(ShortTag.valueOf(value))
    
    override val value: Short
        get() = tag.asShort
}

class WrapperNBTDoubleImpl(override val tag: DoubleTag) : WrapperNBTDouble {
    constructor(value: Double) : this(DoubleTag.valueOf(value))
    
    override val value: Double
        get() = tag.asDouble
}

class WrapperNBTFloatImpl(override val tag: FloatTag) : WrapperNBTFloat {
    constructor(value: Float) : this(FloatTag.valueOf(value))
    
    override val value: Float
        get() = tag.asFloat
}

class WrapperNBTByteArrayImpl(override val tag: ByteArrayTag) : WrapperNBTByteArray {
    constructor(value: ByteArray) : this(ByteArrayTag(value))
    
    override val value: ByteArray
        get() = tag.asByteArray
}

class WrapperNBTIntArrayImpl(override val tag: IntArrayTag) : WrapperNBTIntArray {
    constructor(value: IntArray) : this(IntArrayTag(value))
    
    override val value: IntArray
        get() = tag.asIntArray
}

class WrapperNBTLongArrayImpl(override val tag: LongArrayTag) : WrapperNBTLongArray {
    constructor(value: LongArray) : this(LongArrayTag(value))
    
    override val value: LongArray
        get() = tag.asLongArray
}

class WrapperNBTStringImpl(override val tag: StringTag) : WrapperNBTString {
    constructor(value: String) : this(StringTag.valueOf(value))
    
    override val value: String
        get() = tag.asString
}

class WrapperNBTListImpl<W : WrapperNBTBase<*>>(override val tag: ListTag) : WrapperNBTList<W>,
    AbstractMutableList<W>() {
    constructor(value: List<W>) : this(ListTag()) {
        for (element in value) {
            tag.add(element.tag as Tag)
        }
    }
    
    override val enclosing: TagType<*>
        get() = TagType.ofId(tag.id.toInt())
    
    override val value: MutableList<W>
        get() = this
    
    override val size: Int
        get() = tag.size
    
    override fun add(index: Int, element: W) {
        tag.add(index, element.tag as Tag)
    }
    
    @Suppress("UNCHECKED_CAST")
    override fun get(index: Int): W {
        return NBTAdapter.wrapNBT(tag[index]) as W
    }
    
    @Suppress("UNCHECKED_CAST")
    override fun removeAt(index: Int): W {
        return NBTAdapter.wrapNBT(tag.removeAt(index)) as W
    }
    
    @Suppress("UNCHECKED_CAST")
    override fun set(index: Int, element: W): W {
        val old = NBTAdapter.wrapNBT(tag[index]) as W
        tag.set(index, element.tag as Tag)
        return old
    }
}

class WrapperNBTCompoundImpl(override val tag: CompoundTag) : WrapperNBTCompound,
    AbstractMutableMap<String, WrapperNBTBase<*>>() {
   
    constructor(map: Map<String, WrapperNBTBase<*>>) : this(CompoundTag()) {
        for ((key, value) in map) {
            tag.put(key, value.tag as Tag)
        }
    }
    
    @Suppress("UNCHECKED_CAST")
    private val map = tag.tags as MutableMap<String, Tag>
    
    override val entries: MutableSet<MutableMap.MutableEntry<String, WrapperNBTBase<*>>>
        get() = object : AbstractMutableSet<MutableMap.MutableEntry<String, WrapperNBTBase<*>>>() {
            override val size: Int
                get() = map.size
            
            override fun add(element: MutableMap.MutableEntry<String, WrapperNBTBase<*>>): Boolean {
                if (element.key in map && map[element.key] == element.value.tag) return false
                map[element.key] = element.value.tag as Tag
                return true
            }
            
            override fun iterator(): MutableIterator<MutableMap.MutableEntry<String, WrapperNBTBase<*>>> {
                return object : MutableIterator<MutableMap.MutableEntry<String, WrapperNBTBase<*>>> {
                    private val original = map.iterator()
                    override fun hasNext(): Boolean {
                        return original.hasNext()
                    }
                    
                    override fun next(): MutableMap.MutableEntry<String, WrapperNBTBase<*>> {
                        return original.next().let { SimpleEntry(it.key, NBTAdapter.wrapNBT(it.value)) }
                    }
                    
                    override fun remove() {
                        original.remove()
                    }
                }
            }
        }
    
    override fun clear() {
        map.clear()
    }
    
    override fun containsKey(key: String): Boolean {
        return tag.contains(key)
    }
    
    override fun containsValue(value: WrapperNBTBase<*>): Boolean {
        return map.containsValue(value.tag as Tag)
    }
    
    override fun get(key: String): WrapperNBTBase<*> {
        return NBTAdapter.wrapNBT(tag[key]!!)
    }
    
    override fun isEmpty(): Boolean {
        return tag.isEmpty
    }
    
    override fun remove(key: String): WrapperNBTBase<*>? {
        return map.remove(key)?.let { NBTAdapter.wrapNBT(it) }
    }
    
    override fun remove(key: String, value: WrapperNBTBase<*>): Boolean {
        return map.remove(key, value.tag as Tag)
    }
    
    override val value: MutableMap<String, WrapperNBTBase<*>>
        get() = this
    
    override fun getBoolean(key: String): Boolean {
        return tag.getBoolean(key)
    }
    
    override fun getByte(key: String): Byte {
        return tag.getByte(key)
    }
    
    override fun getByteArray(key: String): ByteArray {
        return tag.getByteArray(key)
    }
    
    override fun getCompound(key: String): WrapperNBTCompound {
        return NBTAdapter.wrapNBTCompound(tag.getCompound(key))
    }
    
    override fun getDouble(key: String): Double {
        return tag.getDouble(key)
    }
    
    override fun getEnd(key: String) {
        // Nothing
    }
    
    override fun getFloat(key: String): Float {
        return tag.getFloat(key)
    }
    
    override fun getInt(key: String): Int {
        return tag.getInt(key)
    }
    
    override fun getIntArray(key: String): IntArray {
        return tag.getIntArray(key)
    }
    
    override fun <W : WrapperNBTBase<*>> getList(key: String, type: TagType<*>): WrapperNBTList<W> {
        return NBTAdapter.wrapNBTList(tag.getList(key, type.id))
    }
    
    override fun getLong(key: String): Long {
        return tag.getLong(key)
    }
    
    override fun getLongArray(key: String): LongArray {
        return NBTAdapter.wrapNBTLongArray(tag[key]!!).value
    }
    
    override fun getShort(key: String): Short {
        return tag.getShort(key)
    }
    
    override fun getString(key: String): String {
        return tag.getString(key)
    }
    
    override fun getUUID(key: String): UUID {
        return tag.getUUID(key)!!
    }
    
    override fun hasKeyOfType(key: String, type: TagType<*>): Boolean {
        return tag.contains(key, type.id)
    }
    
    override fun hasUUID(key: String): Boolean {
        return tag.hasUUID(key)
    }
    
    override fun put(key: String, value: WrapperNBTBase<*>): WrapperNBTBase<*>? {
        val old = tag.get(key)?.let { NBTAdapter.wrapNBT(it) }
        tag.put(key, value.tag as Tag)
        return old
    }
    
    override fun setBoolean(key: String, value: Boolean) {
        tag.putBoolean(key, value)
    }
    
    override fun setByte(key: String, value: Byte) {
        tag.putByte(key, value)
    }
    
    override fun setByteArray(key: String, value: ByteArray) {
        tag.putByteArray(key, value)
    }
    
    override fun setCompound(key: String, value: WrapperNBTCompound) {
        tag.put(key, value.tag as Tag)
    }
    
    override fun setDouble(key: String, value: Double) {
        tag.putDouble(key, value)
    }
    
    override fun setEnd(key: String, value: Unit) {
        tag.put(key, WrapperNBTEndImpl().tag as Tag)
    }
    
    override fun setFloat(key: String, value: Float) {
        tag.putFloat(key, value)
    }
    
    override fun setInt(key: String, value: Int) {
        tag.putInt(key, value)
    }
    
    override fun setIntArray(key: String, value: IntArray) {
        tag.putIntArray(key, value)
    }
    
    override fun <W : WrapperNBTBase<*>> setList(key: String, value: WrapperNBTList<W>) {
        tag.put(key, value.tag as Tag)
    }
    
    override fun setLong(key: String, value: Long) {
        tag.putLong(key, value)
    }
    
    override fun setLongArray(key: String, value: LongArray) {
        tag.put(key, NBTAdapter.createNBTLongArray(value).tag as Tag)
    }
    
    override fun setShort(key: String, value: Short) {
        tag.putShort(key, value)
    }
    
    override fun setString(key: String, value: String) {
        tag.putString(key, value)
    }
    
    override fun setUUID(key: String, value: UUID) {
        tag.putUUID(key, value)
    }
}
