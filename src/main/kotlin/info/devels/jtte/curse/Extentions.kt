package info.devels.jtte.curse

import cofh.lib.util.position.ChunkCoord


fun MutableMap<ChunkCoord, CurseChunkMap>.getRaw(key: ChunkCoord): IntArray {
    val map = this[key] ?: CurseChunkMap()

    return map.bitSet.data
}

fun MutableMap<ChunkCoord, CurseChunkMap>.fromRaw(key: ChunkCoord, value: IntArray) {
    this[key] = CurseChunkMap(value);
}

fun IntArray.dump(): String {
    val sb = StringBuilder("[")
    for (i in this) {
        sb.append(i)
        sb.append(", ")
    }
    if (this.size > 0) {
        sb.delete(sb.length - 2, sb.length)
    }
    sb.append("]")
    return sb.toString()
}

infix fun Int.absMod(int: Int): Int {
    val res = this % int
    return if (res < 0) res + int else res
}

fun Int.toBinaryString(): String {
    return Integer.toBinaryString(this)
}