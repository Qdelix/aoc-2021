fun main() {

    fun part1(input: List<String>): Int {
        val array = Array(895) { CharArray(1311) { ".".single() } }
        val dots = (input - input.subList(input.size - 13, input.size)).map { it.split(",") }
        dots.forEach {
            val x = it[0].toInt()
            val y = it[1].toInt()
            array[y][x] = "#".single()
        }
        val folds = input.subList(input.size - 12, input.size).map { it.removePrefix("fold along ").split("=") }
        val fold = folds.first()
        var sum = 0
        when (fold[0]) {
            "y" -> {
            }
            "x" -> {
                val newArray = Array(array.size) { CharArray(((array.first().size - 1) / 2) + 1) { ".".single() } }
                val foldX = fold[1].toInt()
                array.forEachIndexed { indexArray, chars ->
                    chars.forEachIndexed { index, c ->
                        if (c == "#".single() && index < foldX) {
                            newArray[indexArray][index] = "#".single()
                        } else if (c == "#".single() && index > foldX) {
                            val distance = index - foldX
                            newArray[indexArray][foldX - distance] = "#".single()
                        }
                    }
                }
                sum = newArray.sumOf { it.sumOf { (if (it == "#".single()) 1 else 0) as Int } }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var array = Array(895) { CharArray(1311) { ".".single() } }
        val dots = (input - input.subList(input.size - 13, input.size)).map { it.split(",") }
        dots.forEach {
            val x = it[0].toInt()
            val y = it[1].toInt()
            array[y][x] = "#".single()
        }
        val folds = input.subList(input.size - 12, input.size).map { it.removePrefix("fold along ").split("=") }
        folds.forEach { fold ->
            when (fold[0]) {
                "y" -> {
                    val newArray = Array(((array.size - 1) / 2) + 1) { CharArray(array.first().size) { ".".single() } }
                    val foldY = fold[1].toInt()
                    array.forEachIndexed { indexArray, chars ->
                        chars.forEachIndexed { index, c ->
                            if (c == "#".single() && indexArray < foldY) {
                                newArray[indexArray][index] = "#".single()
                            } else if (c == "#".single() && indexArray > foldY) {
                                val distance = indexArray - foldY
                                newArray[foldY - distance][index] = "#".single()
                            }
                        }
                    }
                    array = newArray
                }
                "x" -> {
                    val newArray = Array(array.size) { CharArray(((array.first().size - 1) / 2) + 1) { ".".single() } }
                    val foldX = fold[1].toInt()
                    array.forEachIndexed { indexArray, chars ->
                        chars.forEachIndexed { index, c ->
                            if (c == "#".single() && index < foldX) {
                                newArray[indexArray][index] = "#".single()
                            } else if (c == "#".single() && index > foldX) {
                                val distance = index - foldX
                                newArray[indexArray][foldX - distance] = "#".single()
                            }
                        }
                    }
                    array = newArray
                }
            }
        }
        array.forEach {
            println(it)
        }
        return 0
    }

    val input = readInput("Day13")
    println(part1(input))
    println(part2(input))
}