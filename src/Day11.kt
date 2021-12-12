fun main() {

    data class AdjacentOcto(val rowIndex: Int, val octoIndex: Int)
    data class FlashPosition(val rowIndex: Int, val octoIndex: Int)

    fun part1(input: List<String>): Int {
        val adjacentPositions = listOf(
                AdjacentOcto(-1, -1),
                AdjacentOcto(-1, 0),
                AdjacentOcto(-1, 1),
                AdjacentOcto(0, -1),
                AdjacentOcto(0, 1),
                AdjacentOcto(1, -1),
                AdjacentOcto(1, 0),
                AdjacentOcto(1, 1),
        )
        val octopuses = input.map { it.map { num -> num.digitToInt() }.toIntArray() }
        var sum = 0
        repeat(100) {
            val flashedPositions = mutableListOf<FlashPosition>()
            octopuses.forEachIndexed { indexRow, _ ->
                octopuses[indexRow].forEachIndexed { index, _ ->
                    octopuses[indexRow][index] += 1
                    if (octopuses[indexRow][index] == 10) flashedPositions.add(FlashPosition(indexRow, index))
                }
            }
            var i = 0
            while (i < flashedPositions.size) {
                adjacentPositions.forEach {
                    val adjRowIndex = flashedPositions[i].rowIndex + it.rowIndex
                    val adjOctoIndex = flashedPositions[i].octoIndex + it.octoIndex
                    if (adjRowIndex in 0..9 && adjOctoIndex in 0..9) {
                        octopuses[adjRowIndex][adjOctoIndex] += 1
                        if (octopuses[adjRowIndex][adjOctoIndex] == 10) {
                            flashedPositions.add(FlashPosition(adjRowIndex, adjOctoIndex))
                        }

                    }
                }
                i++
            }
            octopuses.forEachIndexed { indexRow, _ ->
                octopuses[indexRow].forEachIndexed { index, _ ->
                    if (octopuses[indexRow][index] > 9) {
                        sum += 1
                        octopuses[indexRow][index] = 0
                    }
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val adjacentPositions = listOf(
                AdjacentOcto(-1, -1),
                AdjacentOcto(-1, 0),
                AdjacentOcto(-1, 1),
                AdjacentOcto(0, -1),
                AdjacentOcto(0, 1),
                AdjacentOcto(1, -1),
                AdjacentOcto(1, 0),
                AdjacentOcto(1, 1),
        )
        val octopuses = input.map { it.map { num -> num.digitToInt() }.toIntArray() }
        var step = 0
        var sum = 0
        while (sum < 100) {
            sum = 0
            step++
            val flashedPositions = mutableListOf<FlashPosition>()
            octopuses.forEachIndexed { indexRow, _ ->
                octopuses[indexRow].forEachIndexed { index, _ ->
                    octopuses[indexRow][index] += 1
                    if (octopuses[indexRow][index] == 10) flashedPositions.add(FlashPosition(indexRow, index))
                }
            }
            var i = 0
            while (i < flashedPositions.size) {
                adjacentPositions.forEach {
                    val adjRowIndex = flashedPositions[i].rowIndex + it.rowIndex
                    val adjOctoIndex = flashedPositions[i].octoIndex + it.octoIndex
                    if (adjRowIndex in 0..9 && adjOctoIndex in 0..9) {
                        octopuses[adjRowIndex][adjOctoIndex] += 1
                        if (octopuses[adjRowIndex][adjOctoIndex] == 10) {
                            flashedPositions.add(FlashPosition(adjRowIndex, adjOctoIndex))
                        }

                    }
                }
                i++
            }
            octopuses.forEachIndexed { indexRow, _ ->
                octopuses[indexRow].forEachIndexed { index, _ ->
                    if (octopuses[indexRow][index] > 9) {
                        sum += 1
                        octopuses[indexRow][index] = 0
                    }
                }
            }
        }
        return step
    }

    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}