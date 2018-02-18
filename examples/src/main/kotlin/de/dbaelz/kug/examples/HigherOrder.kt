package de.dbaelz.kug.examples

fun higherOrder() {
    val startTime = System.currentTimeMillis()

    println(getPrimes(1_000))

    val duration = System.currentTimeMillis() - startTime
    println("Duration: $duration")
}

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
