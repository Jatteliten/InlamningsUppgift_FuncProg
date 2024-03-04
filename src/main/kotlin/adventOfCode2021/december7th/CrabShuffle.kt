package adventOfCode2021.december7th

import java.io.File
import kotlin.math.abs

class CrabShuffle {
    val data: List<String> = File("src/main/kotlin/adventOfCode2021/december7th/Input").readLines()
    val testData: List<String> = File("src/main/kotlin/adventOfCode2021/december7th/testInput").readLines()

    fun checkA(input: List<String>): Int{
        val crabPositions = input[0].split(",").map{ it.toInt() }
        var minFuelCost = Int.MAX_VALUE
        for(i in crabPositions.min()..crabPositions.max()){
            var fuelCost = 0
            for(j in crabPositions){
                fuelCost += abs(j - i)
            }
            if(fuelCost < minFuelCost){
                minFuelCost = fuelCost
            }
        }
        return minFuelCost
    }

    fun checkB(input: List<String>): Int{
        val crabPositions = input[0].split(",").map{ it.toInt() }
        var minFuelCost = Int.MAX_VALUE
        for(i in crabPositions.min()..crabPositions.max()){
            var fuelCost = 0
            for(j in crabPositions){
                for((fuel) in (minOf(j, i)..maxOf(j, i)).withIndex()){
                    fuelCost += fuel
                }
            }
            if(fuelCost < minFuelCost){
                minFuelCost = fuelCost
            }
        }
        return minFuelCost
    }

    // Checking median inspired by:
    // https://github.com/aormsby/advent-of-code-2021/blob/main/src/main/kotlin/d7_TheTreacheryOfWhales/TheTreacheryOfWhales.kt
    fun improvedCheckA(input: List<String>): Int{
        fun List<Int>.median(): Int =
            if(this.size% 2 == 0) (this[this.size / 2] + this[(this.size - 1) / 2]) / 2 else this[this.size/2]

        val crabPositions = input[0].split(",").map { it.toInt() }
        return crabPositions.sumOf{ abs(it - crabPositions.sorted().median()) }
    }

}