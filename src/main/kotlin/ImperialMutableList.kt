package collections

interface ImperialMutableList<T> {
    val size: Int
    fun get(index: Int): T
    fun add(element: T)
    fun add(index: Int, element: T)
    fun clear()
    fun contains(element: T): Boolean
    fun removeAt(index: Int): T
    fun remove(element: T): Boolean
    fun set(index: Int, element: T)
}
