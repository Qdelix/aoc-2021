fun main() {

    fun rec(path: List<String>, map: Map<String, MutableSet<String>>): Int {
        return if (path.last() == "end") {
            1
        } else {
            map[path.last()]?.sumOf {
                if (!isStringUpperCase(it) && path.contains(it)) {
                    0
                } else {
                    rec(path + it, map)
                }
            } ?: 0
        }
    }

    fun rec2(path: List<String>, map: Map<String, MutableSet<String>>): Int {
        return if (path.last() == "end") 1 else {
            map[path.last()]?.sumOf {
                if (!(path.size > 1 && path.last() == "start") &&
                        !(path.groupingBy { it }.eachCount().any { !isStringUpperCase(it.key) && it.value >= 2 } &&
                                !isStringUpperCase(it) && path.contains(it))) {
                    rec2(path + it, map)
                } else 0
            } ?: 0
        }
    }

    fun getMap(input: List<String>): MutableMap<String, MutableSet<String>> {
        val map = mutableMapOf<String, MutableSet<String>>()
        input.forEach {
            val line = it.split("-")
            if (map[line[0]]?.isNotEmpty() == true) {
                map[line[0]]?.add(line[1])
            } else {
                map[line[0]] = mutableSetOf()
                map[line[0]]?.add(line[1])
            }
            if (map[line[1]]?.isNotEmpty() == true) {
                map[line[1]]?.add(line[0])
            } else {
                map[line[1]] = mutableSetOf()
                map[line[1]]?.add(line[0])
            }
        }
        return map
    }

    fun part1(input: List<String>): Int {
        val map = getMap(input)
        return rec(mutableListOf("start"), map)
    }

    fun part2(input: List<String>): Int {
        val map = getMap(input)
        return rec2(mutableListOf("start"), map)
    }

    val input = readInput("Day12")
    println(part1(input))
    println(part2(input))
}

private fun isStringUpperCase(str: String): Boolean {
    val charArray = str.toCharArray()
    for (i in charArray.indices) {
        if (!Character.isUpperCase(charArray[i])) return false
    }
    return true
}