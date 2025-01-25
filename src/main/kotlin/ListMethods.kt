package collections

fun <T> doesEitherContain(
    first: ImperialMutableList<T>,
    second: ImperialMutableList<T>,
    element: T,
) : Boolean = first.contains(element) || second.contains(element)

