fun main() {
    val input = readInput("Day01")
    val increasedList = input.zipWithNext { a, b -> a.toInt() < b.toInt() }
    val increasedSize = increasedList.filter { it }.size
    println("1st part: $increasedSize")

    val windowedList = input.windowed(3)
            .zipWithNext { a, b -> getSumOfElementsInList(a) < getSumOfElementsInList(b) }
    val increasedWindowsSize = windowedList.filter { it }.size
    println("2nd part: $increasedWindowsSize")
}

private fun getSumOfElementsInList(list: List<String>) = list.sumOf { it.toInt() }
