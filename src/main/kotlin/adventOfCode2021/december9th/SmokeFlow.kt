package adventOfCode2021.december9th

import java.io.File

class SmokeFlow {
    class Coordinate(var value: Int, var x: Int, var y: Int, var touched: Boolean = false)

    val data: List<String> = File("src/main/kotlin/adventOfCode2021/december9th/Input").readLines()
    val testData: List<String> = File("src/main/kotlin/adventOfCode2021/december9th/testInput").readLines()

    fun checkA(input: List<String>): Int{
        val points = input.map{ it.toList().map { c -> c.toString().toInt() } }
        var total = 0
        input.map{ it.toList() }.forEachIndexed { i, element ->
        element.forEachIndexed{ j, _ ->
            val adjacentPoints = findAdjacentCoordinates(i, points, j)
            if(adjacentPoints.all { it > points[i][j] }) total += (points[i][j] + 1)
        }}
        return total
    }

    fun checkB(input: List<String>): Int {
        val coordinates = mutableListOf<MutableList<Coordinate>>()

        for(i in input){
            coordinates.add(mutableListOf())
        }

        input.forEachIndexed{x, list ->
            list.forEachIndexed{y, c ->
                coordinates[x].add(Coordinate(c.toString().toInt(), x, y))
            }
        }

        val sums = mutableListOf<Int>()

        coordinates.forEach{ list ->
            list.forEach{c ->
                if(c.value != 9 && c.value != 8) {
                    sums.add(checkBasinAmount(coordinates, c))
                    coordinates.forEach{l ->
                        l.forEach{cc -> cc.touched = false}
                    }
                }
            }
        }

        sums.sort()

        return sums[sums.size-1] * sums[sums.size-2] * sums[sums.size-3]
    }

    private fun findAdjacentCoordinates(i: Int, points: List<List<Int>>, j: Int): MutableList<Int> {
        val adjacentPoints = mutableListOf<Int>()
        if (i != 0) adjacentPoints.add(points[i - 1][j])
        if (j != 0) adjacentPoints.add(points[i][j - 1])
        if (i != points.size - 1) adjacentPoints.add(points[i + 1][j])
        if (j != points[i].size - 1) adjacentPoints.add(points[i][j + 1])
        return adjacentPoints
    }
    
    private fun checkBasinAmount(input: List<List<Coordinate>>, c: Coordinate): Int{
        fun check(list: List<Coordinate>, counter: Int): Int{
            return if (list.isEmpty()) counter
            else{
                val newList = mutableListOf<Coordinate>()

                list.forEach{co ->
                    if(co.x != 0 && input[co.x-1][co.y].value >= co.value && input[co.x-1][co.y].value < 9 &&
                        !input[co.x-1][co.y].touched){
                        input[co.x-1][co.y].touched = true
                        newList.add(input[co.x-1][co.y])
                    }
                    if(co.x != input.size-1 && input[co.x+1][co.y].value >= co.value && input[co.x+1][co.y].value < 9 &&
                        !input[co.x+1][co.y].touched){
                        input[co.x+1][co.y].touched = true
                        newList.add(input[co.x+1][co.y])
                    }
                    if(co.y != 0 && input[co.x][co.y-1].value >= co.value && input[co.x][co.y-1].value < 9 &&
                        !input[co.x][co.y-1].touched){
                        input[co.x][co.y-1].touched = true
                        newList.add(input[co.x][co.y-1])
                    }
                    if(co.y != input[0].size-1 && input[co.x][co.y+1].value >= co.value && input[co.x][co.y+1].value < 9 &&
                        !input[co.x][co.y+1].touched){
                        input[co.x][co.y+1].touched = true
                        newList.add(input[co.x][co.y+1])
                    }
                }
                check(newList, counter + newList.size)
            }
        }

        c.touched = true
        return check(listOf(c), 1)
    }

    /*
    fun checkBOhGodWhatWasIThinking(input: List<String>): Int {
        val points = input.map { it.toList().map { c -> c.toString().toInt() } }
        var total = 1

        val filteredPoints = mutableListOf<MutableList<Coordinate>>()

        for (p in points) {
            filteredPoints.add(mutableListOf())
        }

        points.forEachIndexed { i, element ->
            element.forEachIndexed { j, number ->
                val adjacentPoints = findAdjacentCoordinates(i, points, j)
                if (number != 9 && adjacentPoints.any { it > number}) {
                    filteredPoints[i].add(Coordinate(1))
                }
                else filteredPoints[i].add(Coordinate(0))
            }
        }

        filteredPoints.forEachIndexed{ x, list ->
            list.forEachIndexed{ y, coordinate ->
                if(coordinate.value != 0){
                    if(x != 0 && filteredPoints[x - 1][y].value != 0) coordinate.cluster.add(filteredPoints[x-1][y])
                    if(x != filteredPoints.size - 1 && filteredPoints[x + 1][y].value != 0) coordinate.cluster.add(filteredPoints[x+1][y])
                    if(y != 0 && filteredPoints[x][y - 1].value != 0) coordinate.cluster.add(filteredPoints[x][y-1])
                    if(y != filteredPoints[x].size - 1 && filteredPoints[x][y + 1].value != 0) coordinate.cluster.add(filteredPoints[x][y+1])
                }
            }
        }

        filteredPoints.forEach { row ->
            row.forEach { e ->
                e.cluster = findClusterCoordinates(filteredPoints, e)
            }
        }

        val sortedLists: MutableList<Coordinate> = mutableListOf()

        filteredPoints.forEach {row ->
            row.forEach{e ->
                if(!sortedLists.any { it in e.cluster })
                    sortedLists.add(e)
            }
        }

        sortedLists.sortByDescending { it.cluster.size }

        for (i in 0..< 3) {
            total *= sortedLists[i].cluster.size
        }

        return total
    }


    private fun findClusterCoordinates(list: MutableList<MutableList<Coordinate>>, c: Coordinate): MutableList<Coordinate>{
        fun check(l: MutableList<Coordinate>, x: Int, y: Int): MutableList<Coordinate>{
            return if(x == list.size && y == list[0].size) l
            else{
                list.forEach{list ->
                    list.forEach{coordinate ->
                        if(l.any{e -> e.cluster.contains(coordinate)} && !l.contains(coordinate))
                            l.add(coordinate)
                    }
                }
                if(x == list.size) check(l, 0, y + 1)
                else check(l, x + 1, y)
            }
        }

        return check(mutableListOf(c), 0, 0)
    }

     */

}