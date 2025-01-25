package collections

data class Point(private var first: Int, private var second: Int) {
    operator fun times(other: Point): Point = Point(
        this.first * other.first,
        this.second * other.second,
    )
    operator fun times(scalar: Int): Point = Point(
        first * scalar,
        second * scalar,
    )
    operator fun get(index: Int): Int =
        when (index) {
            0    -> first
            1    -> second
            else -> throw IndexOutOfBoundsException(index)
        }
    operator fun set(index: Int, value: Int) =
        when (index) {
            0    -> first = value
            1    -> second = value
            else -> throw IndexOutOfBoundsException(index)
        }
}