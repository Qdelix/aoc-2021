import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        val array = Array(999) { IntArray(999) { 0 } }
        val values = input.map { it.split(",", " -> ").map { number -> number.toInt() } }
        values.forEach { points ->
            val (x1, y1, x2, y2) = points
            when {
                x1 == x2 -> {
                    val lowerValue = if (y1 < y2) y1 else y2
                    val biggerValue = if (y1 < y2) y2 else y1
                    for (i in lowerValue..biggerValue) {
                        array[i][x1] += 1
                    }
                }
                y1 == y2 -> {
                    val lowerValue = if (x1 < x2) x1 else x2
                    val biggerValue = if (x1 < x2) x2 else x1
                    for (i in lowerValue..biggerValue) {
                        array[y1][i] += 1
                    }
                }
            }
        }
        return array.sumOf { row -> row.filter { it > 1 }.size }
    }


    fun part2(input: List<String>): Int {
        val array = Array(999) { IntArray(999) { 0 } }
        val values = input.map { it.split(",", " -> ").map { number -> number.toInt() } }
        values.forEach { points ->
            val (x1, y1, x2, y2) = points
            when {
                x1 == x2 -> {
                    val lowerValue = if (y1 < y2) y1 else y2
                    val biggerValue = if (y1 < y2) y2 else y1
                    for (i in lowerValue..biggerValue) {
                        array[i][x1] += 1
                    }
                }
                y1 == y2 -> {
                    val lowerValue = if (x1 < x2) x1 else x2
                    val biggerValue = if (x1 < x2) x2 else x1
                    for (i in lowerValue..biggerValue) {
                        array[y1][i] += 1
                    }
                }
                abs(x1 - x2) == abs(y1 - y2) -> {
                    val size = abs(x1 - x2)
                    for (i in 0..size) {
                        val y = if(y1 < y2) y1 + i else y1 - i
                        val x = if(x1 < x2) x1 + i else x1 - i
                        array[y][x] += 1
                    }
                }
            }
        }
        return array.sumOf { row -> row.filter { it > 1 }.size }
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}