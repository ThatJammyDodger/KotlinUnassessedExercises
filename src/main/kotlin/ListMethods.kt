package collections

fun <T> doesEitherContain(
    first: ImperialMutableList<T>,
    second: ImperialMutableList<T>,
    element: T,
) : Boolean = first.contains(element) || second.contains(element)

class ArrayListIterator<T>(
    private val targetList: ImperialMutableList<T>
) : Iterator<T> {
    private var nextIndex: Int = 0
    override fun next(): T =
        if (!hasNext())
            throw NoSuchElementException()
        else
            targetList[nextIndex++]
    override fun hasNext(): Boolean = nextIndex < targetList.size
}

fun <T> combine(
    first: ImperialMutableList<T>,
    second: ImperialMutableList<T>,
) : ImperialMutableList<T> {
    val result = SinglyLinkedList<T>()
    for (element in first) {
        result.add(element)
    }
    for (element in second) {
        result.add(element)
    }
    return result
}

