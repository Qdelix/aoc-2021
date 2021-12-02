private const val FIRST_ELEMENT_ON_LIST = 0
private const val SECOND_ELEMENT_ON_LIST = 1

fun main() {

    fun getSumOfMovement(movements: List<Movement>, type: MovementType): Int {
        return movements.filter { it.type == type }.sumOf { it.value }
    }

    fun part1(movements: List<Movement>): Int {
        val horizontalPosition = getSumOfMovement(movements, MovementType.FORWARD)
        val depthDown = getSumOfMovement(movements, MovementType.DOWN)
        val depthUp = getSumOfMovement(movements, MovementType.UP)
        val depth = depthDown - depthUp
        return horizontalPosition * depth
    }

    fun part2(movements: List<Movement>): Int {
        var aim = 0
        var horizontalPosition = 0
        var depth = 0
        movements.forEach { movement ->
            val x = movement.value
            when (movement.type) {
                MovementType.FORWARD -> {
                    horizontalPosition += x
                    depth += aim * x
                }
                MovementType.DOWN -> aim += x
                MovementType.UP -> aim -= x
                else -> {}
            }
        }
        return horizontalPosition * depth
    }

    val input = readInput("Day02")
    val movements = input.map {
        val element = it.split(" ")
        Movement(toEnum(element[FIRST_ELEMENT_ON_LIST]), element[SECOND_ELEMENT_ON_LIST].toInt())
    }
    println(part1(movements))
    println(part2(movements))
}

data class Movement(
        val type: MovementType,
        val value: Int
)

enum class MovementType {
    FORWARD,
    DOWN,
    UP,
    UNKNOWN
}

fun toEnum(movement: String): MovementType {
    return when (movement) {
        "forward" -> MovementType.FORWARD
        "down" -> MovementType.DOWN
        "up" -> MovementType.UP
        else -> MovementType.UNKNOWN
    }
}
