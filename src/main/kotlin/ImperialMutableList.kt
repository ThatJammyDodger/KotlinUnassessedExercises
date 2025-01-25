package collections

interface ImperialMutableList<T> {
    val size: Int
    operator fun get(index: Int): T
    fun add(element: T)
    fun add(index: Int, element: T)
    fun clear()
    fun contains(element: T): Boolean
    fun removeAt(index: Int): T
    fun remove(element: T): Boolean
    operator fun set(index: Int, element: T)
    operator fun iterator() : Iterator<T>

    fun addAll(other: ImperialMutableList<T>) = addAll(size, other)

    fun addAll(index: Int, other: ImperialMutableList<T>) {
        if (index !in 0..size)
            throw IndexOutOfBoundsException()
        var i = 0
        for (element in other) {
            add(index + i, element)
            i++
        }
    }
}
