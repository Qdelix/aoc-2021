fun main() {

    fun part1(input: List<String>): Int {
        val draws = input[0].split(",").map { it.toInt() }
        val boards = input
                .drop(1)
                .chunked(6)
                .map { board ->
                    board.filter { line ->
                        line.isNotBlank()
                    }
                }
        val cleanedBoards = boards.map { board ->
            board.map {
                it.split("  ", " ")
                        .filter { number -> number.isNotBlank() }
                        .map { number -> MarkedNumber(number = number.toInt()) }
            }
        }
        var bingoedBoard: List<List<MarkedNumber>> = listOf()
        var lastCalledNumber = 0
        draws.first loop@{ drawNumber ->
            cleanedBoards.forEach { board ->
                if (board.any rowLoop@{ row ->
                            row.any {
                                if (it.number == drawNumber) {
                                    it.marked = true
                                    return@rowLoop true
                                }
                                false
                            }
                        }) {
                    if (isBoardBingoed(board)) {
                        bingoedBoard = board
                        lastCalledNumber = drawNumber
                        return@loop true
                    }
                }
            }
            false
        }

        val unmarkedNumbersSum = bingoedBoard.sumOf { line ->
            line.sumOf { number ->
                if (!number.marked) number.number else 0
            }
        }
        return unmarkedNumbersSum * lastCalledNumber
    }


    fun part2(input: List<String>): Int {
        val draws = input[0].split(",").map { it.toInt() }
        val boards = input
                .drop(1)
                .chunked(6)
                .map { board ->
                    board.filter { line ->
                        line.isNotBlank()
                    }
                }
        val cleanedBoards = boards.map { board ->
            board.map {
                it.split("  ", " ")
                        .filter { number -> number.isNotBlank() }
                        .map { number -> MarkedNumber(number = number.toInt()) }
            }
        }.toMutableList()
        var bingoedBoard: List<List<MarkedNumber>> = listOf()
        var lastCalledNumber = 0
        draws.forEach loop@{ drawNumber ->
            cleanedBoards.forEach { board ->
                board.forEach rowLoop@{ row ->
                    row.any {
                        if (it.number == drawNumber) {
                            it.marked = true
                            return@rowLoop
                        }
                        false
                    }
                }
            }
            val boardsToRemove: MutableList<List<List<MarkedNumber>>> = mutableListOf()
            cleanedBoards.forEach { board ->
                if (isBoardBingoed(board)) {
                    boardsToRemove.add(board)
                    bingoedBoard = board
                    lastCalledNumber = drawNumber
                }
            }
            cleanedBoards -= boardsToRemove.toSet()

        }

        val unmarkedNumbersSum = bingoedBoard.sumOf { line ->
            line.sumOf { number ->
                if (!number.marked) number.number else 0
            }
        }
        return unmarkedNumbersSum * lastCalledNumber
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

fun isBoardBingoed(board: List<List<MarkedNumber>>): Boolean {
    val isAnyLineMarked = board.any { line ->
        line.all { it.marked }
    }
    val lineSize = board.first().size
    var isAnyRowMarked = false
    for (i in 0 until lineSize) {
        if (!isAnyRowMarked) {
            isAnyRowMarked = board.all { it[i].marked }
        }
    }
    return isAnyLineMarked || isAnyRowMarked
}

data class MarkedNumber(
        val number: Int,
        var marked: Boolean = false
)