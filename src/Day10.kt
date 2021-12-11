fun main() {

    fun part1(input: List<String>): Int {
        val closingLetters = listOf(")".single(), "]".single(), ">".single(), "}".single())
        val correctLetters = listOf("()", "[]", "<>", "{}")
        var sum = 0
        input.forEach {
            var charList = ""
            val firstWrongLetter = it.firstOrNull { letter ->
                charList += letter
                if (letter in closingLetters && charList.takeLast(2) !in correctLetters) {
                    true
                } else if (charList.takeLast(2) in correctLetters) {
                    charList = charList.dropLast(2)
                    false
                } else false
            }.toString()
            sum += when (firstWrongLetter) {
                ")" -> 3
                "]" -> 57
                "}" -> 1197
                ">" -> 25137
                else -> 0
            }
        }
        return sum
    }

    fun part2(input: List<String>): Long {
        val closingLetters = listOf(")".single(), "]".single(), ">".single(), "}".single())
        val correctLetters = listOf("()", "[]", "<>", "{}")
        val correctLines = input.filter {
            var charList = ""
            it.forEach { letter ->
                charList += letter
                if (charList.takeLast(2) in correctLetters) {
                    charList = charList.dropLast(2)
                }
            }
            charList.all { letter ->
                letter !in closingLetters
            }
        }
        val sumList = mutableListOf<Long>()
        correctLines.forEach {
            var charList = ""
            it.forEach { letter ->
                charList += letter
                if (charList.takeLast(2) in correctLetters) {
                    charList = charList.dropLast(2)
                }
            }
            var sum = 0L
            charList.reversed().forEach { letter ->
                sum = when (letter.toString()) {
                    "(" -> sum * 5 + 1
                    "[" -> sum * 5 + 2
                    "{" -> sum * 5 + 3
                    "<" -> sum * 5 + 4
                    else -> sum
                }
            }
            sumList.add(sum)
        }
        return sumList.sorted().getMiddleValue()
    }

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}

fun List<Long>.getMiddleValue(): Long {
    return if (size > 1) drop(1).dropLast(1).getMiddleValue() else first()
}
