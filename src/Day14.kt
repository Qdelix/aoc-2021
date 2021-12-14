fun main() {

    fun part1(input: List<String>): Int {
        var steps = input.first().windowed(2)
        val codes = input.drop(2).map { it.split(" -> ") }
        var result = ""
        repeat(10) {
            var nextSteps: String = steps.first().first().toString()
            steps.forEach { letters ->
                nextSteps += codes.first { code -> code.first() == letters }[1]
                nextSteps += letters[1]
            }
            steps = nextSteps.windowed(2)
            result = nextSteps
        }
        val counts = result.groupingBy { it }.eachCount().values.sorted()
        return counts.last() - counts.first()
    }

    fun part2(input: List<String>): Long {
        val steps = input.first().windowed(2)
        val codes = input.drop(2).map { it.split(" -> ") }.associateBy({ it[0] }, { it[1] })
        var lettersCount = steps.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
        repeat(40) {
            lettersCount = lettersCount.flatMap { (letters, count) ->
                listOf("${letters[0]}${codes[letters]}" to count, "${codes[letters]}${letters[1]}" to count)
            }.groupingBy { it.first }.fold(0L) { sum, elementCount -> sum + elementCount.second }
        }
        val letterCountMap = lettersCount.toList()
                .flatMap { (letters, count) -> listOf(letters[0] to count, letters[1] to count) }
                .groupingBy { it.first }.fold(0L) { sum, elementCount -> sum + elementCount.second }
                .toMutableMap()
        letterCountMap[input.first().first()] = (letterCountMap[input.first().first()] ?: 0) + 1
        letterCountMap[input.first().last()] = (letterCountMap[input.first().last()] ?: 0) + 1
        val l = letterCountMap.map { it.value / 2 }
        val min = l.minOrNull()
        val max = l.maxOrNull()
        return (max ?: 0) - (min ?: 0)
    }

    val input = readInput("Day14")
    println(part1(input))
    println(part2(input))
}