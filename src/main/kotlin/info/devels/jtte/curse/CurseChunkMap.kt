package info.devels.jtte.curse

import info.devels.jtte.utils.FastBitSet
import net.minecraft.client.Minecraft

class CurseChunkMap() {
    val bitSet = FastBitSet(256)

    constructor(init: IntArray) : this() {
        bitSet.setArray(init)
    }

    fun markBits(dx1: Int, dz1: Int, dx2: Int, dz2: Int): Boolean {
        var changed = false

        if (bitSet.isFull) {
            return false
        }

        for (dx in dx1..dx2) {
            for (dz in dz1..dz2) {
                changed = !bitSet[dx + dz * 16]
                bitSet[dx + dz * 16] = true
            }
        }

        return changed;
    }

    fun test(dx: Int, dz: Int): Boolean {
        return bitSet[dx + dz * 16]
    }

    fun copy(): CurseChunkMap {
        val bs = IntArray(bitSet.data.size)
        for (i in 0..bitSet.data.size - 1) {
            bs[i] = bitSet.data[i]
        }
        return CurseChunkMap(bs)
    }

    inline fun xzBit(dx: Int, dz: Int, cb: (bit: Int) -> Boolean) {
        cb(dx + dz * 8)
    }

    companion object {
        val Empty = CurseChunkMap();
    }
}