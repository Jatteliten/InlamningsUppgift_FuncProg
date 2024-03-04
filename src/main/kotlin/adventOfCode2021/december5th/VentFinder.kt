package adventOfCode2021.december5th

import java.io.File

class VentFinder {
    val data: List<String> = File("src/main/kotlin/adventOfCode2021/december5th/Input").readLines()
    val testData: List<String> = File("src/main/kotlin/adventOfCode2021/december5th/testInput").readLines()

    class Coordinate(var x: Int, var y: Int)

    fun checkA(input: List<String>): Int{
        val instructions = input
            .flatMap { s -> s.split(" -> ")
                .flatMap { coordinates -> coordinates.split(",")
                    .map {it.toInt() }}}.toList()

        val filteredInstructions = instructions
            .chunked(4)
                .flatMap{
                    (firstX, firstY, secondX, secondY) ->
                    if(firstX == secondX || firstY == secondY) listOf(firstX, secondX, firstY, secondY) else emptyList()
                }.toList()

        val coordinateList = filteredInstructions
            .chunked(4)
                .flatMap {
                    (firstX, secondX, firstY, secondY) ->
                    if(firstX == secondX)
                        (minOf(firstY, secondY)..maxOf(firstY, secondY)).map { Coordinate(firstX, it) }
                    else
                        (minOf(firstX, secondX)..maxOf(firstX, secondX)).map { Coordinate(it, firstY) }
                }

        return coordinateList.groupingBy { it.x to it.y }.eachCount().count { it.value > 1 }
    }

    fun checkB(input: List<String>): Int{
        return input
            .flatMap { s -> s.split(" -> ")
                .flatMap { coordinates -> coordinates.split(",")
                    .map {it.toInt() }}}
            .chunked(4)
            .flatMap { (firstX, firstY, secondX, secondY) ->
                var startX = firstX
                var startY = firstY
                val result = mutableListOf<Coordinate>()

                while(true){
                    result.add(Coordinate(startX, startY))
                    if(startX == secondX && startY == secondY){
                        break
                    }
                    startX += if (startX < secondX) 1 else if (startX > secondX) -1 else 0
                    startY += if (startY < secondY) 1 else if (startY > secondY) -1 else 0
                }
                result
            }
            .groupingBy { it.x to it.y }.eachCount().count { it.value > 1 }
    }

    // just having fun
    fun checkAWithMaximumCallsInARow(input: List<String>): Int{
        return input
            .flatMap { s -> s.split(" -> ")
                .flatMap { coordinates -> coordinates.split(",")
                    .map {it.toInt() }}}
            .chunked(4)
                .flatMap{ (firstX, firstY, secondX, secondY) ->
                    if(firstX == secondX || firstY == secondY)
                        listOf (firstX, secondX, firstY, secondY)
                    else emptyList() }
            .chunked(4)
                .flatMap { (firstX, secondX, firstY, secondY) ->
                    if(firstX == secondX)
                        (minOf(firstY, secondY)..maxOf(firstY, secondY)).map { Coordinate(firstX, it) }
                    else
                        (minOf(firstX, secondX)..maxOf(firstX, secondX)).map { Coordinate(it, firstY) }}
                .groupingBy { it.x to it.y }.eachCount().count { it.value > 1 }
    }

}