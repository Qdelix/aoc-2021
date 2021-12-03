fun main() {

    fun part1(input: List<String>): Int {
        val bits = input.map { it.toCharArray() }
        val bitsSize = bits.first().size
        val gamma = StringBuilder()
        val epsilon = StringBuilder()
        for (i in 0 until bitsSize) {
            val zeros = bits.count { Character.getNumericValue(it[i]) == 0 }
            val ones = bits.count { Character.getNumericValue(it[i]) == 1 }
            gamma.append(if (zeros > ones) "0" else "1")
            epsilon.append(if (zeros > ones) "1" else "0")
        }
        val gammaDecimal = convertBinaryToDecimal(gamma.toString().toLong())
        val epsilonDecimal = convertBinaryToDecimal(epsilon.toString().toLong())
        return gammaDecimal * epsilonDecimal
    }

    fun getOxygenRating(input: List<CharArray>): Int {
        var bits = input
        val bitsSize = bits.first().size
        var oxygenBits: CharArray = charArrayOf()
        for (i in 0 until bitsSize) {
            val zeros = bits.count { Character.getNumericValue(it[i]) == 0 }
            val ones = bits.count { Character.getNumericValue(it[i]) == 1 }
            when {
                zeros > ones -> bits = bits.filter { Character.getNumericValue(it[i]) == 0 }
                ones >= zeros -> bits = bits.filter { Character.getNumericValue(it[i]) == 1 }
            }
            if (bits.size == 1) oxygenBits = bits.first()
        }
        return convertBinaryToDecimal(String(oxygenBits).toLong())
    }

    fun getCo2Rating(input: List<CharArray>): Int {
        var bits = input
        val bitsSize = input.first().size
        var co2Bits: CharArray = charArrayOf()
        for (i in 0 until bitsSize) {
            val zeros = bits.count { Character.getNumericValue(it[i]) == 0 }
            val ones = bits.count { Character.getNumericValue(it[i]) == 1 }
            when {
                zeros <= ones -> bits = bits.filter { Character.getNumericValue(it[i]) == 0 }
                ones < zeros -> bits = bits.filter { Character.getNumericValue(it[i]) == 1 }
            }
            if (bits.size == 1) co2Bits = bits.first()
        }
        return convertBinaryToDecimal(String(co2Bits).toLong())
    }

    fun part2(input: MutableList<String>): Int {
        val bits = input.map { it.toCharArray() }
        val oxygenRating = getOxygenRating(bits)
        val co2Rating = getCo2Rating(bits)
        return oxygenRating * co2Rating
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input.toMutableList()))
}