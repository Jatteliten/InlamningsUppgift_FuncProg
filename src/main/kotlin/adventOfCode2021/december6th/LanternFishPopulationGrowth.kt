package adventOfCode2021.december6th

import java.io.File

class LanternFishPopulationGrowth {
    val data: List<String> = File("src/main/kotlin/adventOfCode2021/december6th/Input").readLines()
    val testData: List<String> = File("src/main/kotlin/adventOfCode2021/december6th/testInput").readLines()

    // keeping the A-section even though the B-section could solve both, just to show progress
    fun checkA(input: List<String>): Int{
        fun check(fish: List<Int>, daysPassed: Int, dayLimit: Int = 80): Int{
            return if (daysPassed == dayLimit) fish.size
            else{
                val newFish = mutableListOf<Int>()
                fish.forEach{f ->
                    if(f == 0){
                        newFish.add(8)
                        newFish.add(6)
                    }else newFish.add(f-1)
                }

                check(newFish, daysPassed + 1)
            }
        }
        return check(input[0].split(",").map { it.toInt()}.toList(), 0)
    }

    //way faster than checkA. Doesn't generate a huge list
    fun checkB(input: List<String>, dayLimit: Int): Long {
        fun check(fish: MutableList<Long>, daysPassed: Int, dayLimit: Int): Long {
            return if (daysPassed == dayLimit) fish.sum()
            else {
                var bornFish = 0L
                fish.indices.forEach { cycle ->
                    when{
                        cycle == 0 && fish[cycle] > 0 -> {
                            bornFish = fish[cycle]
                        }
                        fish[cycle] > 0 -> {
                            fish[cycle - 1] += fish[cycle]
                        }
                    }
                    fish[cycle] = 0
                }
                fish[6] += bornFish
                fish[8] += bornFish
                check(fish, daysPassed + 1, dayLimit)
            }
        }

        val fishList = MutableList(9) { 0L }
        input[0].split(",").map { it }.forEach { index -> fishList[index.toInt()]++ }
        return check(fishList, 0, dayLimit)
    }

    // inspired by https://github.com/tginsberg/advent-2021-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2021/Day06.kt
    fun changedCheck(input: List<String>, days: Int): Long{
        val fishList = MutableList(9){0L}
            .apply { input[0].split(",").map { it.toInt() }.forEach{ index -> this[index]++} }

        repeat(days){
            var bornFish = 0L
            fishList.forEachIndexed{ index, value ->
                if(index == 0){
                    bornFish += value
                }else{
                    fishList[index - 1] += value
                }
                fishList[index] = 0
            }
            fishList[6]+= bornFish
            fishList[8]+= bornFish
        }
        return fishList.sum()
    }

}