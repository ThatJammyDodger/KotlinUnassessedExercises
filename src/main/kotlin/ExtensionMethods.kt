package collections

fun Int.isPowerOfTwo(): Boolean =
    this > 0 && (this and (this - 1)) == 0

fun String.isPalindrome(caseSensitive: Boolean = true): Boolean {
    val recased: String = if (caseSensitive) this else this.lowercase()
    return recased == recased.reversed()
}

fun Double.sameAsFloat(): Boolean =
    (this.toFloat()).toDouble() == this

operator fun Int.times(point: Point) =
    point.times(this)

fun <A,B> Pair<A,B>.equalComponents() =
    first == second
fun <A,B> Pair<A,B>.swap(): Pair<B, A> =
    Pair(second, first)

fun List<Boolean>.someTrue() =
    this.contains(true)
fun List<Boolean>.someFalse() =
    this.contains(false)
fun List<Boolean>.allTrue() =
    !this.someFalse()
fun List<Boolean>.allFalse() =
    !this.someTrue()

// example of defining a custom infix function
// as one of the exercises wanted to know about
infix fun <T> T.doNothing(other: Any): T = this
