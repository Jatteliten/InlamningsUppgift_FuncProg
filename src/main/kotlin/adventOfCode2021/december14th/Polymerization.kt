package adventOfCode2021.december14th

import java.io.File

class Polymerization {
    val data: List<String> = File("src/main/kotlin/adventOfCode2021/december14th/Input").readLines()
    val testData: List<String> = File("src/main/kotlin/adventOfCode2021/december14th/testInput").readLines()

    fun checkA(input: List<String>): Long{
        val template = input[0].toList()
        val instructions = input.subList(2, input.size).map { it.split(" -> ") }
        return checkPairs(template, instructions)
    }

    fun checkB(input: List<String>, loops: Int): Long{
        val pairs = input.subList(2, input.size)
            .associate { it.split(" -> ").first to 0L }
            .toMutableMap()
        val instructions = input.subList(2, input.size)
            .associate { it.split(" -> ").first to
                    listOf("${it.first()}${it.last()}", "${it.last()}${it[1]}") }

        for (i in 1..< input[0].length) {
            pairs["${input[0][i - 1]}${input[0][i]}"] = pairs["${input[0][i - 1]}${input[0][i]}"]!! + 1
        }

        return checkPairsB(pairs, instructions, loops)
    }

    private fun checkPairsB(pairs: Map<String, Long>, instructions: Map<String, List<String>>, loops: Int): Long{
        fun check(pairs: Map<String, Long>,  i: Int):Long{
            return if(i == loops) {
                val charsMap = findCharOccurrences(pairs)
                (charsMap.values.maxOf { it } + 1) - charsMap.values.minOf { it }
            }
            else{
                val newPairs = pairs.toMutableMap()
                for(pair in pairs){
                    if (pair.value > 0){
                        instructions[pair.key]!!.forEach { e ->
                            newPairs[e] = newPairs[e]!! + pair.value
                        }
                        newPairs[pair.key] = newPairs[pair.key]!! - pair.value
                    }
                }
                check(newPairs, i + 1)
            }
        }
        return check(pairs, 0)
    }

    private fun findCharOccurrences(pairs: Map<String, Long>): Map<Char, Long> {
        val newMap = mutableMapOf<Char, Long>()
        for(pair in pairs){
            if(!newMap.containsKey(pair.key.first())){
                newMap[pair.key.first()] = pair.value
            }else{
                newMap[pair.key.first()] = newMap[pair.key.first()]!! + pair.value
            }
        }
        return newMap
    }

    // Old method for solving part A. Cannot handle exponential growth as well as checkPairsB
    private fun checkPairs(template: List<Char>, instructions: List<List<String>>): Long{
        fun check(t: List<Char>, ins: List<List<String>>, steps: Int): Long{
            return if(steps == 10) (t.groupingBy { it }.eachCount().values.max() -
                    t.groupingBy { it }.eachCount().values.min()).toLong()
            else{
                val newResult = mutableListOf<Char>()
                for(i in 0..t.size - 2){
                    newResult.add(t[i])
                    instructions.find { it.first == "${t[i]}${t[i + 1]}"}?.toList()?.last?.let { newResult.add(it.single()) }
                }
                newResult.add(t.last)
                check(newResult, ins, steps + 1)
            }
        }
        return check(template, instructions, 0)
    }
}