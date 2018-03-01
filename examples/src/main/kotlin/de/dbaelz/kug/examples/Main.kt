package de.dbaelz.kug.examples

fun main(args: Array<String>) {
    // Example for a higher order function. See HigherOrderExamples.kt
    exampleHigherOrderFunction()

    // Repeat is a cool extension function on a String
    println("-".repeat(50))

    // Extension Functions:
    // From a top level function to an extension function
    // See comments in ExtensionFunctionExample.kt
    println(paddingStart("String without Padding", 42))
    println(StringHelper.paddingStart("String without Padding", 42))
    println("String without Padding".padStart(42))
}