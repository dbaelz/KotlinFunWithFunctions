package de.dbaelz.kug.examples

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