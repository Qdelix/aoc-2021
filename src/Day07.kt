import kotlin.math.abs

private const val EMPTY_VALUE = -1

fun main() {

    fun part1(input: List<String>): Int {
        val values = input.first().split(",").map { it.toInt() }
        var lowestSum = EMPTY_VALUE
        values.forEach { value ->
            val sum = values.sumOf { abs(it - value) }
            if (lowestSum == EMPTY_VALUE || sum < lowestSum) {
                lowestSum = sum
            }
        }
        return lowestSum
    }


    fun part2(input: List<String>): Int {
        val values = input.first().split(",").map { it.toInt() }
        var lowestSum = EMPTY_VALUE
        val maxNumber = values.maxOf { it }
        for (value in 0..maxNumber) {
            var sum = 0
            values.forEach {
                val diff = abs(it - value)
                for (i in 1..diff) {
                    sum += i
                }
            }
            if (lowestSum == EMPTY_VALUE || sum < lowestSum) {
                lowestSum = sum
            }
        }
        return lowestSum
    }

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}