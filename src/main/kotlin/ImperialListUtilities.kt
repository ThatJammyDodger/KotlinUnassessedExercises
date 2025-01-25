package collections

import kotlin.math.min

fun <T, U> ImperialMutableList<T>.map(
    f: (T) -> U
) : ImperialMutableList<U> {
    val newList = ResizingArrayList<U>(this.size)
    for (element in this)
        newList.add(f(element))
    return newList
}

fun <T> ImperialMutableList<T>.filter(
    predicate: (T) -> Boolean
) : ImperialMutableList<T> {
    val newList = ResizingArrayList<T>(this.size)
    for (element in this)
        if (predicate(element))
            newList.add(element)
    return newList
}

fun <T, U> ImperialMutableList<T>.zip(
    other: ImperialMutableList<U>
) : ImperialMutableList<Pair<T, U>> {
    val finalLength = min(this.size, other.size)
    val firstList = ResizingArrayList<T>(finalLength)
    val finalList = ResizingArrayList<Pair<T, U>>(finalLength)
    var i = 0
    for (element in this) {
        if (i == finalLength) break
        firstList.add(element)
        i++
    }
    i = 0
    // access for firstList is resizing, hence O(1)
    for (element in other) {
        if (i == finalLength) break
        finalList.add(firstList[i] to element)
        i++
    }
    return finalList
}

fun <T> ImperialMutableList<T>.reduce(
    f: (T, T) -> T
) : T {
    if (this.size == 0)
        throw NoSuchElementException()
    val iterator: Iterator<T> = this.iterator()
    var accumulator: T = iterator.next()
    while (iterator.hasNext())
        accumulator = f(accumulator, iterator.next())
    return accumulator
}

fun <T, U> ImperialMutableList<T>.reduce(
    f: (U, T) -> U,
    starter: U,
) : U {
    if (this.size == 0)
        throw NoSuchElementException()
    var accumulator: U = starter
    for (element in this) {
        accumulator = f(accumulator, element)
    }
    return accumulator
}
