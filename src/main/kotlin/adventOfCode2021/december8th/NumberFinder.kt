package adventOfCode2021.december8th

import java.io.File

class NumberFinder {
    val data: List<String> = File("src/main/kotlin/adventOfCode2021/december8th/Input").readLines()
    val testData: List<String> = File("src/main/kotlin/adventOfCode2021/december8th/testInput").readLines()
    val smallTestData: List<String> = File("src/main/kotlin/adventOfCode2021/december8th/smallTestInput").readLines()

    fun checkA(input: List<String>): Int{
        var counter = 0
        input.map { it.split("|") }
            .map { it[1].trim() }
            .forEach{e ->
            counter += e.split(" ")
                .count{it.length == 2 || it.length == 4 || it.length == 3 || it.length == 7} }
        return counter
    }

    fun checkB(input: List<String>): Int{
        val digitCodes = input.map { it.split("|") }.map { it[0].trim() }
        val digits = input.map { it.split("|") }.map { it[1].trim() }

        var result = 0
        for(i in digits.indices){
            val chars = digitCodes[i].split(" ")
            val numbers = digits[i].split(" ")

            val one = chars.filter { it.length == 2 }.flatMap { it.toList() }
            val four = chars.filter { it.length == 4 }.flatMap { it.toList() }
            val seven = chars.filter { it.length == 3 }.flatMap { it.toList() }
            val eight = chars.filter { it.length == 7 }.flatMap { it.toList() }
            val three = chars.filter { it.length == 5 }.filter { it.toList().containsAll(seven) }.flatMap { it.toList() }
            val nine = chars.filter { it.length == 6 }.filter { it.toList().containsAll(four) }.flatMap { it.toList() }
            val zero = chars.filter { it.length == 6 }
                .filter { !it.toList().containsAll(four) && it.toList().containsAll(seven) }.flatMap { it.toList() }
            val six = chars.filter { it.length == 6 }
                .filter { !it.toList().containsAll(seven) }.flatMap { it.toList() }
            val five = chars.filter { it.length == 5 }.filter { !it.toList().containsAll(seven) }
                .filter { four.count { char -> it.contains(char) } >= 3 }.flatMap { it.toList() }
            val two = chars.filter { it.length == 5 }.filter { !it.toList().containsAll(seven) }
                .filter { four.count { char -> it.contains(char) } != 3 }.flatMap { it.toList() }

            fun returnInteger(list: List<Char>): Int {
                return when {
                    zero.toSet() == list.toSet() -> 0
                    one.toSet() == list.toSet() -> 1
                    two.toSet() == list.toSet() -> 2
                    three.toSet() == list.toSet() -> 3
                    four.toSet() == list.toSet() -> 4
                    five.toSet() == list.toSet() -> 5
                    six.toSet() == list.toSet() -> 6
                    seven.toSet() == list.toSet() -> 7
                    eight.toSet() == list.toSet() -> 8
                    nine.toSet() == list.toSet() -> 9
                    else -> 0
                }
            }

            result += returnInteger(numbers[0].toList()) * 1000
            result += returnInteger(numbers[1].toList()) * 100
            result += returnInteger(numbers[2].toList()) * 10
            result += returnInteger(numbers[3].toList())
        }
        return result
    }

    // solution inspired by https://www.reddit.com/r/adventofcode/comments/rbj87a/comment/hnpn4xm/
    private val entries = data.map { it.split(" | ") }
        .map { (patterns, output) ->
            patterns.split(" ").map { it.toSet() } to output.split(" ").map { it.toSet() }
        }

    fun improvedCheckA(): Int {
        return entries.flatMap { it.second }.count { it.size in arrayOf(2, 3, 4, 7) }
    }

    fun improveCheckB(): Int {
        return entries.sumOf { (patterns, output) ->
            val mappedDigits = mutableMapOf(
                1 to patterns.first { it.size == 2 },
                4 to patterns.first { it.size == 4 },
                7 to patterns.first { it.size == 3 },
                8 to patterns.first { it.size == 7 },
            )

            with(mappedDigits) {
                put(3, patterns.filter { it.size == 5 }.first { (it intersect getValue(1)).size == 2 })
                put(2, patterns.filter { it.size == 5 }.first { (it intersect getValue(4)).size == 2 })
                put(5, patterns.filter { it.size == 5 }.first { it !in values })
                put(6, patterns.filter { it.size == 6 }.first { (it intersect getValue(1)).size == 1 })
                put(9, patterns.filter { it.size == 6 }.first { (it intersect getValue(4)).size == 4 })
                put(0, patterns.filter { it.size == 6 }.first { it !in values })
            }

            val mappedPatterns = mappedDigits.entries.associateBy({ it.value }) { it.key }
            output.joinToString("") { mappedPatterns.getValue(it).toString() }.toInt()
        }
    }

}