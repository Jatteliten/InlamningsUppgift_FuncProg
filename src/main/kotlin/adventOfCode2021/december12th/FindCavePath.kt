package adventOfCode2021.december12th

import java.io.File

class FindCavePath {
    val data: List<String> = File("src/main/kotlin/adventOfCode2021/december12th/Input").readLines()
    val testData: List<String> = File("src/main/kotlin/adventOfCode2021/december12th/testInput").readLines()

    class Cave(var name: String, var connections: MutableList<Cave> = mutableListOf()){
        var large: Boolean = name.all { c -> c.isUpperCase() }
    }

    class Path(var visited: MutableList<Cave> = mutableListOf(), var reachedEnd: Boolean,
               var visitedSmallCavesTwice: Boolean = false)

    fun checkA(input: List<String>): Int {
        return findPaths(initializeCaveLists(input).find{it.name == START }!!)
    }

    fun checkB(input: List<String>): Int{
        return findPaths(initializeCaveLists(input).find{it.name == START }!!, true)
    }

    private fun initializeCaveLists(input: List<String>): List<Cave> {
        val caves = input.flatMap { e -> e.split(SPLIT).distinct() }.map { Cave(it) }.toList()

        input.forEach { e ->
            val firstCave = caves.find { it.name == e.split(SPLIT).first() }
            val lastCave = caves.find { it.name == e.split(SPLIT).last() }

            firstCave?.connections?.add(lastCave!!)
            lastCave?.connections?.add(firstCave!!)
        }
        return caves
    }

    private fun findPaths(start: Cave, partTwo: Boolean = false): Int{
        fun pathing(currentPaths: List<Path>, totalPaths: MutableList<Path>): Int{
            return if(currentPaths.isEmpty()) totalPaths.filter { e -> e.reachedEnd }.size
            else{
                val newPaths = mutableListOf<Path>()
                currentPaths.forEach{ path ->
                    if(!path.reachedEnd) {
                        path.visited.last.connections.forEach { cave ->
                            if (cave.name != START &&
                                (!path.visitedSmallCavesTwice && partTwo ||
                                        !cave.large && !path.visited.contains(cave) ||
                                            cave.large)) {
                                val newPath = Path(path.visited.toMutableList(), reachedEnd = (cave.name == END),
                                    visitedSmallCavesTwice = (path.visited.contains(cave) && !cave.large || path.visitedSmallCavesTwice))
                                newPath.visited.add(cave)
                                totalPaths.add(newPath)
                                newPaths.add(newPath)
                            }
                        }
                    }
                }
                pathing(newPaths, totalPaths)
            }
        }
        return pathing(listOf(Path(visited = mutableListOf(start), reachedEnd = false)), mutableListOf())
    }

    companion object {
        const val START = "start"
        const val END = "end"
        const val SPLIT = "-"
    }
}