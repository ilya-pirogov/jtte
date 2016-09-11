package info.devels.jtte.utils

import java.util.*
import javax.print.attribute.IntegerSyntax


class FastBitSet(val size: Int) {
    var data: IntArray
        private set

    constructor(data: IntArray) : this(data.size) {
        this.data = data
    }

    constructor(size: Int, vararg array: Int) : this(size) {
        if (array.size > size) {
            throw IllegalArgumentException("Too big array")
        }

        for (i in 0..array.size - 1) {
            this.data[i] = array[i]
        }
    }

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

    fun inv(): FastBitSet {
        val data = IntArray(this.data.size)

        for (i in 0..this.data.size - 1) {
            data[i] = this.data[i].inv()
        }

        return FastBitSet(data)
    }

    infix fun and(other: FastBitSet): FastBitSet {
        val data = IntArray(this.data.size)

        for (i in 0..this.data.size - 1) {
            data[i] = data[i] and this.data[i]
        }

        return FastBitSet(data)
    }

    infix fun xand(other: FastBitSet): FastBitSet {
        val data = IntArray(this.data.size)

        for (i in 0..this.data.size - 1) {
            data[i] = data[i].inv() and this.data[i]
        }

        return FastBitSet(data)
    }

    operator fun compareTo(other: Int): Int {
        return this.compareTo(FastBitSet(this.data.size, other))
    }

    operator fun compareTo(other: FastBitSet): Int {
        var result = 0
        for (i in this.data.size - 1 downTo 0) {
            val otherBit =  if (other.data.size > i) other.data[i] else 0

            result = this.data[i].compareTo(otherBit)
            if (result != 0) {
                return result
            }
        }
        return result
    }

    operator fun get(i: Int) = (data[i / 32] and (1 shl (i % 32))) != 0

    operator fun set(i: Int, value: Boolean) {
        if (value) {

            data[i / 32] = (data[i / 32] or (1 shl (i % 32)))
        } else {
            data[i / 32] = (data[i / 32] and (1 shl (i % 32)).inv())
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (other !is FastBitSet) {
            return false
        }

        if (this === other) {
            return true
        }

        if (this.data.size != other.data.size) {
            return false
        }

        for (i in 0..this.data.size - 1) {
            if (this.data[i] != other.data[i]) {
                return false
            }
        }

        return true
    }

    override fun hashCode(): Int{
        return Arrays.hashCode(data)
    }


    fun getEnabledBits(): List<Int> {
        val bitList = arrayListOf<Int>()

        for (i in 0..data.size - 1) {
            val int = data[i]
            var offset = i * 32
            var leftBits = int

            while (leftBits != 0) {
                if ((leftBits and 1) == 1) {
                    bitList.add(offset)
                }
                offset++
                leftBits = leftBits ushr 1
            }
        }

        return bitList
    }

    override fun toString(): String{
        return this.data.map { Integer.toBinaryString(it) }.joinToString(" ")
    }


}