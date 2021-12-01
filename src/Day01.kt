fun main() {
    val input = readInput("Day01")
    val increasedList = input.zipWithNext { a, b -> a.toInt() < b.toInt() }
    val increasedSize = increasedList.filter { it }.size
    print(increasedSize)
}
