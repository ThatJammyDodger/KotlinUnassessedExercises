package collections

fun Int.isPowerOfTwo(): Boolean =
    this > 0 && (this and (this - 1)) == 0

fun String.isPalindrome(caseSensitive: Boolean = true): Boolean {
    val recased: String = if (caseSensitive) this else this.lowercase()
    return recased == recased.reversed()
}

fun Double.sameAsFloat(): Boolean =
    (this.toFloat()).toDouble() == this
