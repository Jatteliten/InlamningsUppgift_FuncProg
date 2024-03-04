package adventOfCode2021.december13th

import java.io.File

class MapFolding {
    val data: List<String> = File("src/main/kotlin/adventOfCode2021/december13th/Input").readLines()
    val testData: List<String> = File("src/main/kotlin/adventOfCode2021/december13th/testInput").readLines()

    fun check(input: List<String>, partA: Boolean = true): Int{
        val map = mutableListOf<MutableList<String>>()
        val folds = input.map { it }.filter { s -> s.contains("=") }
        var maxX = 0
        var maxY = 0

        input.forEach { line ->
            if(line.contains(",")){
                val (y, x) = line.split(",").map { it.toInt() }
                maxX = maxOf(maxX, x)
                maxY = maxOf(maxY, y)
            }
        }

        for(x in 0..maxX){
            map.add(mutableListOf())
            for(y in 0..maxY){
                map[x].add(".")
            }
        }

        input.forEach { s ->
            if(s.contains(",")){
                val coordinates = s.split(",")
                map[coordinates.last.toInt()][coordinates.first.toInt()] = POINT
            }
        }

        return foldMap(map, folds, partA)
    }

    private fun foldMap(map: List<List<String>>, folds: List<String>, partA: Boolean): Int{
        fun fold(oldMap: List<List<String>>, f: List<String>): Int{
            return if (f.isEmpty()) {
                if (!partA) {
                    oldMap.forEach { list ->
                        list.forEach { value -> print(value) }
                        println()
                    }
                }
                oldMap.flatten().count { it == POINT }
            }else {
                val newMap = oldMap.map { it.toMutableList() }.toMutableList()
                val newF = f.toMutableList()
                val fold = f.first.split("along").last.let {
                    it.split("=").first.trim() to it.split("=").last.trim() }
                if(fold.first == "y"){
                    for(y in 0..fold.second.toInt()){
                        newMap.removeLast()
                    }
                    for(y in 0..fold.second.toInt()){
                        for(x in oldMap[0].indices){
                            if(oldMap[oldMap.size - (1 + y)][x] == POINT) newMap[y][x] = POINT
                        }
                    }
                }else{
                    for(x in 0..fold.second.toInt()){
                        for(i in newMap.indices){
                            newMap[i].removeLast()
                        }
                    }
                    for(x in 0..< fold.second.toInt()){
                        for(y in oldMap.indices){
                            if(oldMap[y][oldMap[x].size - (1 + x)] == POINT) newMap[y][x] = POINT
                        }
                    }
                }
                if(partA) newF.clear() else newF.removeFirst()
                fold(newMap, newF)
            }
        }
        return fold(map, folds)
    }

    companion object {
        const val POINT = "#"
    }
}