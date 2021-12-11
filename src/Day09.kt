fun main() {

    fun part1(input: List<String>): Int {
        val numberList = mutableListOf<Int>()
        val tab = input.map { it.toCharArray().map { num -> Character.getNumericValue(num) } }
        val tabSize = tab.size
        val lineSize = tab.first().size
        tab.forEachIndexed { indexLine, tabLine ->
            tabLine.forEachIndexed { indexNumber, number ->
                val previousLineNumber = if (indexLine > 0) tab[indexLine - 1][indexNumber] else 10
                val linePreviousNumber = if (indexNumber > 0) tab[indexLine][indexNumber - 1] else 10
                val nextLineNumber = if (indexLine < tabSize - 1) tab[indexLine + 1][indexNumber] else 10
                val lineNextNumber = if (indexNumber < lineSize - 1) tab[indexLine][indexNumber + 1] else 10
                if (number < previousLineNumber &&
                        number < linePreviousNumber &&
                        number < nextLineNumber &&
                        number < lineNextNumber) {
                    numberList.add(number)
                }
            }
        }
        return numberList.sumOf { it + 1 }
    }

    fun part2(input: List<String>): Long {
        val numberList = mutableListOf<Int>()
        val tab = input.map { it.toCharArray().map { num -> Character.getNumericValue(num) } }
        tab.forEachIndexed { indexLine, tabLine ->
            tabLine.forEachIndexed { indexNumber, number ->
                val tabSize = tab.size
                val lineSize = tab.first().size
                val previousLineNumber = if (indexLine > 0) tab[indexLine - 1][indexNumber] else 10
                val linePreviousNumber = if (indexNumber > 0) tab[indexLine][indexNumber - 1] else 10
                val nextLineNumber = if (indexLine < tabSize - 1) tab[indexLine + 1][indexNumber] else 10
                val lineNextNumber = if (indexNumber < lineSize - 1) tab[indexLine][indexNumber + 1] else 10
                if (
                        indexLine == 41 && indexNumber == 65 &&
                        number < previousLineNumber &&
                        number < linePreviousNumber &&
                        number < nextLineNumber &&
                        number < lineNextNumber) {
                    numberList.add(CheckNumber().checkNextNumber(indexLine, indexNumber, tab, indexLine to indexNumber))

                }
            }
        }
        val longList = numberList.sortedDescending().subList(0, 3).map { it.toLong() }
        val multi = longList.reduce { acc, i ->
            acc * i
        }

        return multi
    }

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}

class CheckNumber {
    private val previousPositions: MutableList<Pair<Int, Int>> = mutableListOf()

    fun checkNextNumber(indexLine: Int, indexNumber: Int, tab: List<List<Int>>, previousPosition: Pair<Int, Int>): Int {
        previousPositions.add(previousPosition)
        val tabSize = tab.size
        var size = 1
        val number = tab[indexLine][indexNumber]
        val lineSize = tab.first().size
        if (indexLine > 0) {
            val nextNumber = tab[indexLine - 1][indexNumber]
            if (nextNumber < 9 && !(previousPositions.contains(indexLine - 1 to indexNumber)) && (number == nextNumber + 1 || number == nextNumber - 1)) {
                size += checkNextNumber(indexLine - 1, indexNumber, tab, indexLine to indexNumber)
            }
        }
        if (indexNumber > 0) {
            val nextNumber = tab[indexLine][indexNumber - 1]
            if (nextNumber < 9 && !(previousPositions.contains(indexLine to indexNumber - 1)) && (number == nextNumber + 1 || number == nextNumber - 1)) {
                size += checkNextNumber(indexLine, indexNumber - 1, tab, indexLine to indexNumber)
            }
        }
        if (indexLine < tabSize - 1) {
            val nextNumber = tab[indexLine + 1][indexNumber]
            if (nextNumber < 9 && !(previousPositions.contains(indexLine + 1 to indexNumber)) && (number == nextNumber + 1 || number == nextNumber - 1)) {
                size += checkNextNumber(indexLine + 1, indexNumber, tab, indexLine to indexNumber)
            }
        }
        if (indexNumber < lineSize - 1) {
            val nextNumber = tab[indexLine][indexNumber + 1]
            if (nextNumber < 9 && !(previousPositions.contains(indexLine to indexNumber + 1)) && (number == nextNumber + 1 || number == nextNumber - 1)) {
                size += checkNextNumber(indexLine, indexNumber + 1, tab, indexLine to indexNumber)
            }
        }
//        987840
//        105 98 96
//        1682184
//        124 119 114
        return size
    }

}