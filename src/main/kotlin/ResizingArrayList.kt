package collections

import kotlin.math.max

private val DEFAULT_INITIAL_CAPACITY: Int = 16

class ResizingArrayList<T>(private val initialCapacity: Int) : ImperialMutableList<T> {
    init {
        if (initialCapacity < 0)
            throw IllegalArgumentException()
    }

    constructor() : this(DEFAULT_INITIAL_CAPACITY)

    override var size: Int = 0
        private set

    private var elements = clearedArray()

    private fun clearedArray(): Array<T?> =
        arrayOfNulls<Any?>(initialCapacity) as Array<T?>

    override fun add(index: Int, element: T) {
        if (size >= elements.size || index !in 0..size)
            throw IndexOutOfBoundsException()
        if (size + 1 > elements.size)
            elements = elements.copyOf(2 * elements.size)
        for (i in size downTo index + 1)
            elements[i] = elements[i - 1]
        elements[index] = element
        size++
    }

    override fun add(element: T): Unit = add(size, element)

    override fun get(index: Int): T =
        if (index !in 0..<size)
            throw IndexOutOfBoundsException()
        else
            elements[index]!!

    override fun set(index: Int, element: T) {
        if (index !in 0..size)
            throw IndexOutOfBoundsException()
        else
            elements[index] = element
    }

    override fun clear() {
        size = 0
        elements = clearedArray()
    }

    override fun contains(element: T): Boolean =
        elements.slice(0..<size).contains(element)

    override fun removeAt(index: Int): T {
        if (index !in 0..<size)
            throw IndexOutOfBoundsException()
        val indexCupboard: T = elements[index]!!
        for (i in index..<size - 1) {
            elements[i] = elements[i + 1]
        }
        size--
        return indexCupboard
    }

    override fun remove(element: T): Boolean {
        val position: Int = find(element)
        if (position == -1)
            return false
        else {
            removeAt(position)
            return true
        }
    }

    private fun find(element: T): Int =
        elements.slice(0..<size).indexOf(element)

    override fun toString(): String =
        elements.slice(0..<size).joinToString(", ", "[", "]")

    override fun iterator(): Iterator<T> = ArrayListIterator<T>(this)

    override fun addAll(index: Int, other: ImperialMutableList<T>) {
        if (index !in 0..size)
            throw IndexOutOfBoundsException()
        val newSize = size + other.size
        if (newSize > elements.size) {
            val newCapacity = max(newSize, 2 * elements.size)
            elements = elements.copyOf(newCapacity)
        }
        for (i in size - 1 downTo index) {
            elements[i + other.size] = elements[i]
        }
        var i: Int = 0
        for (element in other) {
            elements[index + i] = element
            i++
        }
        size = newSize
    }
}