package adventOfCode2021.december2nd

import java.io.File
import java.lang.IllegalArgumentException

class PositionChecker {
    val data: List<String> = File("src/main/kotlin/adventOfCode2021/december2nd/Input").readLines()
    val testData: List<String> = File("src/main/kotlin/adventOfCode2021/december2nd/testInput").readLines()

    fun checkA(input: List<String>): Int{
        fun check(index: Int, h: Int, v: Int): Int{
            return if (index == input.size) h*v
            else {
                val command = input[index].split(" ").elementAt(0)
                val value = input[index].split(" ").elementAt(1).toInt()
                when (command) {
                    "forward" -> check(index + 1, h + value, v)
                    "down" -> check(index + 1, h, v + value)
                    "up" -> check(index + 1, h, v - value)
                    else -> throw IllegalArgumentException ("Invalid command")
                }
            }
        }
        return check(0, 0, 0)
    }

    fun checkB(input: List<String>): Int{
        fun check(index: Int, aim: Int, h: Int, v: Int): Int{
            return if (index == input.size) h*v
            else {
                val command = input[index].split(" ").elementAt(0)
                val value = input[index].split(" ").elementAt(1).toInt()
                when (command) {
                    "forward" -> check(index + 1, aim, h + value, v + value * aim)
                    "down" -> check(index + 1, aim + value, h, v)
                    "up" -> check(index + 1, aim - value, h, v)
                    else -> throw IllegalArgumentException ("Invalid command")
                }

            }
        }
        return check(0, 0, 0, 0)
    }

}