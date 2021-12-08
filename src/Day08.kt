fun main() {

    fun part1(input: List<String>): Int {
        val uniqueNumbersCount = IntArray(9) { 0 }
        val pattern = mutableMapOf(1 to 2, 4 to 4, 7 to 3, 8 to 7)
        input.forEach {
            val output = it.split(" | ")[1].split(" ")
            output.forEach { digit ->
                val digitCount = digit.length
                val numberFromPattern = pattern.firstNotNullOfOrNull { p ->
                    if (p.value == digitCount) p.key else null
                }
                if (numberFromPattern != null) {
                    uniqueNumbersCount[numberFromPattern]++
                }
            }
        }
        return uniqueNumbersCount.sumOf { it }
    }


    fun part2(input: List<String>): Int {
        return input.sumOf {
            val splitNumbers = it.split(" | ")
            val inputDigits = splitNumbers[0].split(" ").toMutableList()
            val outputDigits = splitNumbers[1].split(" ")

            val connectedDigits = mutableMapOf<Int, String>()
            connectedDigits[8] = inputDigits.first { digit -> digit.length == 7 }
            connectedDigits[7] = inputDigits.first { digit -> digit.length == 3 }
            connectedDigits[4] = inputDigits.first { digit -> digit.length == 4 }
            connectedDigits[1] = inputDigits.first { digit -> digit.length == 2 }
            connectedDigits[3] = inputDigits.first { digit ->
                val one = connectedDigits[1] ?: ""
                digit.length == 5 && digit.toCharArray().contains(one[0]) && digit.toCharArray().contains(one[1])
            }
            connectedDigits[9] = inputDigits.first { digit ->
                val three = connectedDigits[3] ?: ""
                val four = connectedDigits[4] ?: ""
                val sum = three.toCharArray() + four.toCharArray()
                digit.length == 6 && digit.all { l -> sum.contains(l) }
            }
            connectedDigits[6] = inputDigits.first { digit ->
                val four = connectedDigits[4] ?: ""
                val one = connectedDigits[1] ?: ""
                val diff = four.toSet().subtract(one.toSet())
                digit.length == 6 && diff.all { digit.contains(it) } && digit != connectedDigits[9]
            }
            connectedDigits[0] = inputDigits.first { digit ->
                digit.length == 6 && digit != connectedDigits[9] && digit != connectedDigits[6]
            }
            connectedDigits[5] = inputDigits.first { digit ->
                val nine = connectedDigits[9] ?: ""
                val one = connectedDigits[1] ?: ""
                val diff = nine.toSet().subtract(one.toSet())
                digit.length == 5 && digit != connectedDigits[3] && diff.all { digit.contains(it) }
            }
            connectedDigits[2] = inputDigits.first { digit ->
                digit.length == 5 && digit != connectedDigits[3] && digit != connectedDigits[5]
            }
            var output = ""
            outputDigits.forEach { digit ->
                connectedDigits.forEach { d ->
                    if (d.value.toSet() == digit.toSet()) {
                        output += d.key
                    }
                }
            }
            output.toInt()
        }
    }

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}