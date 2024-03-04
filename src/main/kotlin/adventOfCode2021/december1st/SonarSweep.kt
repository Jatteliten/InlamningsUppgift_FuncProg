package adventOfCode2021.december1st

import java.io.File

class SonarSweep {
    val data: List<String> = File("src/main/kotlin/adventOfCode2021/december1st/Input").readLines()
    val testData: List<String> = File("src/main/kotlin/adventOfCode2021/december1st/testInput").readLines()

    fun sweepA(input: List<String>): Int{
        fun sweep(line: Int, counter: Int): Int{
            return if (line == input.size) counter
            else sweep(line+1, if(input[line].toInt() > input[line-1].toInt()) counter+1 else counter)
        }
        return sweep(1, 0)
    }

    fun sweepB(input: List<String>): Int{
        fun sweep(line: Int, counter: Int): Int{
            return if (line == input.size) counter
            else sweep(line+1, if(input[line].toInt() > input[line - 3].toInt()) counter+1 else counter)
        }
        return sweep(3, 0)
    }

    // solution inspired by: https://www.reddit.com/r/adventofcode/comments/r66vow/comment/hms6ztr/
    fun improvedSweep(input: List<String>, windowSize: Int): Int{
        // compares two numbers in a sequence of windowSize for the whole list
        // since the middle values for a window is always the same, it compares the edge values
        return input.map { it.toInt() }.windowed(windowSize).count {it.last > it.first}
    }

}