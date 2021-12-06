private const val NEW_LANTERNFISH_TIMER = 8
private const val LANTERNFISH_FULL_TIMER = 6

data class Lanternfish(
        var timer: Int
) {
    fun nextDay() {
        when (timer) {
            0 -> timer = LANTERNFISH_FULL_TIMER
            else -> timer -= 1
        }
    }
}

fun main() {

    fun part1(input: List<String>): Int {
        val fish = input.first().split(",").map { Lanternfish(it.toInt()) }.toMutableList()
        repeat(80) {
            var numberOfFishToAdd = 0
            fish.forEach {
                if (it.timer == 0) numberOfFishToAdd += 1
                it.nextDay()
            }
            repeat(numberOfFishToAdd) {
                fish.add(Lanternfish(NEW_LANTERNFISH_TIMER))
            }
        }
        return fish.size
    }


    fun part2(input: List<String>): Long {
        val fish = input.first().split(",").map { it.toInt() }.toMutableList()
        var array = LongArray(9)
        fish.forEach {
            array[it]++
        }
        var nextDayArray = LongArray(9)
        repeat(256) {
            nextDayArray = LongArray(9)
            for (i in 0 until 9) {
                if (i == 0) {
                    nextDayArray[6] += array[i]
                    nextDayArray[8] += array[i]
                } else {
                    nextDayArray[i - 1] += array[i]
                }
            }
            array = nextDayArray
        }
        return array.sumOf { it }
    }

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}