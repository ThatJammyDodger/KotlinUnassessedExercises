package collections

class FixedCapacityList<T>(capacity: Int) : ImperialMutableList<T> {
    override var size: Int = 0
        private set

    private val elements: Array<T?> =
        if (capacity < 0)
            throw IllegalArgumentException()
        else arrayOfNulls<Any?>(capacity) as Array<T?>

    override fun add(index: Int, element: T) {
        if (size >= elements.size || index !in 0..size)
            throw IndexOutOfBoundsException()
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

    override fun set(index: Int, element: T): Unit =
        if (index !in 0..<size)
            throw IndexOutOfBoundsException()
        else
            elements[index] = element

    override fun clear() {
        size = 0
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
}

