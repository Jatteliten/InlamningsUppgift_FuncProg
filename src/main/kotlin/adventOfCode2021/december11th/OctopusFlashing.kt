package adventOfCode2021.december11th

import java.io.File

class OctopusFlashing {

    val data: List<String> = File("src/main/kotlin/adventOfCode2021/december11th/Input").readLines()
    val testData: List<String> = File("src/main/kotlin/adventOfCode2021/december11th/testInput").readLines()

    class Octopus(var value: Int, var flashed: Boolean = false)

    fun checkA(input: List<String>): Int {
        val octopuses = input.map { s -> s.map { Octopus(it.toString().toInt()) }.toList() }
        var total = 0

        repeat (100) {
            incrementOctopuses(octopuses)
            octopuses.forEach { list ->
                list.forEach{ o ->
                    if(o.flashed) {
                        total++
                        o.value = 0
                        o.flashed = false
                    }
                }
            }
        }
        return total
    }

    fun checkB(input: List<String>): Int {
        val octopuses = input.map { s -> s.map { Octopus(it.toString().toInt()) }.toList() }
        var total = 0

        while(true) {
            total++
            incrementOctopuses(octopuses)

            if(octopuses.all { list -> list.all { it.flashed }})
                break

            octopuses.forEach { list ->
                list.forEach{ o ->
                    if(o.flashed) {
                        o.value = 0
                        o.flashed = false
                    }
                }
            }
        }
        return total
    }

    private fun incrementOctopuses(octopuses: List<List<Octopus>>) {
        octopuses.forEachIndexed { x, list ->
            list.forEachIndexed { y, o ->
                checkIfOctopusIsFlashing(o, x, y, octopuses)
            }
        }
    }

    private fun checkIfOctopusIsFlashing(octopus: Octopus, x: Int, y: Int, octopuses: List<List<Octopus>>){
        octopus.value++
        if(octopus.value > 9 && !octopus.flashed){
            octopus.flashed = true
            flash(x, y, octopuses)
        }
    }

    private fun flash(x: Int, y: Int, octopuses: List<List<Octopus>>){
        if (y != 0) {
            checkIfOctopusIsFlashing(octopuses[x][y-1], x, y-1, octopuses)
            if (x != 0)
                checkIfOctopusIsFlashing(octopuses[x-1][y-1], x-1, y-1, octopuses)
            if (x != octopuses[x].size - 1)
                checkIfOctopusIsFlashing(octopuses[x+1][y-1], x+1, y-1, octopuses)
        }
        if (y != octopuses[x].size - 1) {
            checkIfOctopusIsFlashing(octopuses[x][y+1], x, y+1, octopuses)
            if (x != 0)
                checkIfOctopusIsFlashing(octopuses[x-1][y+1], x-1, y+1, octopuses)
            if (x != octopuses[x].size - 1)
                checkIfOctopusIsFlashing(octopuses[x+1][y+1], x+1, y+1, octopuses)
        }
        if (x != 0)
            checkIfOctopusIsFlashing(octopuses[x-1][y], x-1, y, octopuses)
        if (x != octopuses.size - 1)
            checkIfOctopusIsFlashing(octopuses[x+1][y], x+1, y, octopuses)
    }

}