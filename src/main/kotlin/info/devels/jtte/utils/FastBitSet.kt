package info.devels.jtte.utils


class FastBitSet(size: Int) {
    var data: IntArray
        private set

    init {
        data = IntArray((size + 31) / 32) { 0 }
    }

    fun clear() {
        data.fill(0)
    }

    val isFull: Boolean
        get() = data.all { it == -1 }

    val isEmpty: Boolean
        get() = data.all { it == 0 }

    fun setArray(array: IntArray) {
        if (array.size == 0) {
            data = IntArray((data.size + 31) / 32) { 0 }
            return
        }

        if (array.size < data.size) {
            throw IllegalArgumentException("Size mismatch %d %d".format(array.size, data.size))
        }

        data = array
    }

    operator fun get(i: Int) = (data[i / 32] and (1 shl (i % 32))) != 0

    operator fun set(i: Int, value: Boolean) {
        if (value) {

            data[i / 32] = (data[i / 32] or (1 shl (i % 32)))
        } else {
            data[i / 32] = (data[i / 32] and (1 shl (i % 32)).inv())
        }
    }
}