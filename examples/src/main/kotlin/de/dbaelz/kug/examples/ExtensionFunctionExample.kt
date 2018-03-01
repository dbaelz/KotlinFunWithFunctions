package de.dbaelz.kug.examples

// Typical example of a helper method. Well known from Java.
fun paddingStart(input: String, length: Int, paddingChar: Char = ' '): String {
    if (length < 0 || input.length <= length)
        return input

    val sb = StringBuilder()
    for(i in 1..(length - input.length)) {
        sb.append(paddingChar)
    }
    sb.append(input)
    return sb.toString()
}

// Similar to static helper classes in Java, such a method could (but shouldn't!) be used in Kotlin.
object StringHelper {
    fun paddingStart(input: String, length: Int, paddingChar: Char = ' '): String {
        if (length < 0 || input.length <= length)
            return input

        val sb = StringBuilder()
        for(i in 1..(length - input.length)) {
            sb.append(paddingChar)
        }
        sb.append(input)
        return sb.toString()
    }
}

// The idiomatic Kotlin way: Use a extension function.
// Such a function is provided by the Kotlin Standard Library. See Strings.kt#String.padStart()
fun String.padStart(length: Int, paddingChar: Char = ' '): String {
    if (length < 0 || this.length <= length)
        return this

    val sb = StringBuilder()
    for(i in 1..(length - this.length)) {
        sb.append(paddingChar)
    }
    sb.append(this)
    return sb.toString()
}