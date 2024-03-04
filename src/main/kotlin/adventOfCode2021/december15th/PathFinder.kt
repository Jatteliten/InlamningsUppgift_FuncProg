package adventOfCode2021.december15th

import java.io.File
import java.util.*
import kotlin.collections.HashMap

class PathFinder {
    val data: List<String> = File("src/main/kotlin/adventOfCode2021/december15th/Input").readLines()
    val testData: List<String> = File("src/main/kotlin/adventOfCode2021/december15th/testInput").readLines()

    fun checkA(input: List<String>): Int{
        val grid = input.map { e -> e.map { it.toString().toInt() } }
        val gridArray = traversePathDownAndToTheRight(grid)

        return gridArray[gridArray.size-1][gridArray[0].size-1] - gridArray[0][0]
    }

    // Had to ask chatGPT for Dijkstra's algorithm. checkA could also use it, but I wanted to keep my solution
    // that doesn't require moving upward or to the left, since that was enough for it. checkA also shows off some
    // dynamic programming
    fun checkB(input: List<String>): Int{
        val grid = input.map { e -> e.map { it.toString().toInt() } }
        val expandedYGrid = MutableList(grid.size*5){ mutableListOf<Int>() }

        for(i in grid.indices){
            expandedYGrid[i] = grid[i].toMutableList()
        }

        expandDownward(grid, expandedYGrid)
        val fullyExpandedGrid: MutableList<MutableList<Int>> = expandedYGrid.toMutableList()
        fullyExpandedGrid.forEach{ it.addAll(expandRightward(it))}

        return dijkstrasAlgorithm(fullyExpandedGrid.map{it.toIntArray()}.toTypedArray()) - fullyExpandedGrid[0][0]
    }

    private fun traversePathDownAndToTheRight(grid: List<List<Int>>): Array<IntArray> {
        val gridArray = Array(grid.size) { IntArray(grid[0].size) }
        gridArray[0][0] = grid[0][0]

        for (i in 1..< grid[0].size) {
            gridArray[0][i] = gridArray[0][i - 1] + grid[0][i]
        }

        for (i in 1..< grid.size) {
            gridArray[i][0] = gridArray[i - 1][0] + grid[i][0]
        }

        for (row in 1..< grid.size) {
            for (col in 1..< grid[0].size) {
                gridArray[row][col] = grid[row][col] + minOf(
                    gridArray[row - 1][col],
                    gridArray[row][col - 1]
                )
            }
        }
        return gridArray
    }

    private fun expandDownward(grid: List<List<Int>>, expandedYGrid: MutableList<MutableList<Int>>) {
        for (i in grid.size..<expandedYGrid.size) {
            expandedYGrid[i] = expandedYGrid[i - grid.size].map {
                if (it + 1 < 10) {
                    it + 1
                } else {
                    1
                }
            }.toMutableList()
        }
    }

    private fun expandRightward(originalList: List<Int>): List<Int> {
        fun expand(list: List<Int>, expandingList: List<Int>, counter: Int): List<Int> {
            return if(counter == 5) expandingList
            else{
                val oldList: MutableList<Int> = mutableListOf()
                val newList = expandingList.toMutableList()
                list.forEach{ e ->
                    if(e+1 < 10) {
                        newList.add((e + 1))
                        oldList.add((e + 1))
                    }else{
                        newList.add(1)
                        oldList.add(1)
                    }
                }
                expand(oldList, newList, counter + 1)
            }
        }
        return expand(originalList, listOf(),1)
    }

    // completely written by chatGPT. I take no credit
    private fun dijkstrasAlgorithm(grid: Array<IntArray>): Int {
        val rows = grid.size
        val cols = grid[0].size
        val distances = HashMap<Pair<Int, Int>, Int>()
        val priorityQueue = PriorityQueue<Pair<Pair<Int, Int>, Int>>(compareBy { it.second })

        for (i in 0..< rows) {
            for (j in 0..< cols) {
                distances[Pair(i, j)] = Int.MAX_VALUE
            }
        }

        distances[Pair(0, 0)] = grid[0][0]
        priorityQueue.offer(Pair(Pair(0, 0), grid[0][0]))

        val directions = arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

        while (priorityQueue.isNotEmpty()) {
            val (current, distance) = priorityQueue.poll()

            for ((dx, dy) in directions) {
                val nextX = current.first + dx
                val nextY = current.second + dy

                if (nextX in 0..< rows && nextY in 0..< cols) {
                    val nextPoint = Pair(nextX, nextY)
                    val newDistance = distance + grid[nextX][nextY]

                    if (newDistance < distances[nextPoint]!!) {
                        distances[nextPoint] = newDistance
                        priorityQueue.offer(Pair(nextPoint, newDistance))
                    }
                }
            }
        }
        return distances[Pair(rows - 1, cols - 1)] ?: -1
    }

}