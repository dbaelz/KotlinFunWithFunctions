package de.dbaelz.kug.examples


fun exampleHigherOrderFunction() {

    // Execution time example

    // Usage without a higher order function
    val startTime = System.currentTimeMillis()
    getPrimes(1_000_000)
    val duration = System.currentTimeMillis() - startTime

    println("Duration without Higher Order Function: $duration")

    // Usage with a higher order function that takes a function literal as parameter
    // Such a function literal could either be a lambda (typical usage) or an anonymous function
    val lambdaDuration = measureTimeInMillis {
        //
        getPrimes(1_000_000)
    }
    println("Duration with Higher Order Function (Lambda): $lambdaDuration")

    val anonymousFunctionDuration = measureTimeInMillis(fun() {
        //
        getPrimes(1_000_000)
    })
    println("Duration with Higher Order Function (Anonymous Function): $anonymousFunctionDuration")

    // Also a function reference could be used
    val functionReferenceDuration = measureTimeInMillis(::functionReference)
    println("Duration with Higher Order Function (Function Reference): $functionReferenceDuration")


    // Another function reference example
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    // We provide the method to filter the even values as function reference
    val evenNumbers = numbers.filter(::isEven)
    println("Even numbers: $evenNumbers")
}

// Higher order function
// Takes a function literal as parameter. Measures the time, executes (invokes) the provided function and returns the duration
// Such a function is provided by the Kotlin Standard Library. See Timing.kt#measureTimeMillis()
fun measureTimeInMillis(block: () -> Unit): Long {
    val startTime = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - startTime
}

fun functionReference() {
    getPrimes(1_000_000)
}

fun isEven(value: Int): Boolean {
    return value % 2 == 0
}


// Just some more or less compute intensive code
fun getPrimes(maxNumber: Int): List<Int> {
    if (maxNumber < 2) return emptyList()

    val primes = mutableListOf<Int>()
    val numbers = Array(maxNumber + 1) { true }
    val sqrtmaxNumber = Math.sqrt(maxNumber.toDouble()).toInt()

    (2..sqrtmaxNumber)
            .filter { numbers[it] }
            .flatMap { (it * it)..maxNumber step it }
            .forEach { numbers[it] = false }

    numbers.forEachIndexed { number, isPrime ->
        if (number >= 2) {
            if (isPrime) {
                primes.add(number)
            }
        }
    }

    return primes
}
