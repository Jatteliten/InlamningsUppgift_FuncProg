package adventOfCode2021.december4th

import java.io.File

class Bingo {
    val data: List<String> = File("src/main/kotlin/adventOfCode2021/december4th/Input").readLines()
    val testData: List<String> = File("src/main/kotlin/adventOfCode2021/december4th/testInput").readLines()

    class BoardNumber(var value: Int, var checked: Boolean = false)
    class Board(var numbers: List<Int>, var lines: List<List<BoardNumber>>)

    fun checkA(input: List<String>): Int {
        val draw = input[0].split(",").map { it.toInt() }
        val boards = createBoardsList(input)

        return drawNumbers(draw, boards)
    }

    fun checkB(input: List<String>): Int {
        val draw = input[0].split(",").map { it.toInt() }
        val boards = createBoardsList(input)

        return drawNumbers(draw, listOf(findLastBoard(boards, draw)))
    }

    private fun createBoardsList(input: List<String>) =
        input.subList(2, input.size)
            .filter { it.isNotBlank() }
            .chunked(5)
            .map { lines ->
                Board(
                    lines.flatMap { e -> (e.trim().split("\\s+".toRegex())) }
                        .map { it.toInt() },
                    lines.map { e -> e.trim().split("\\s+".toRegex())
                        .map { ex -> BoardNumber(ex.toInt()) }
                    }
                )
            }

    private fun drawNumbers(draw: List<Int>, boards: List<Board>): Int {
        for ((counter, d) in draw.withIndex()) {
            for (b in boards) {
                if (b.numbers.contains(d)) {
                    if (b.lines.any { line ->
                            line.any { num ->
                                if (num.value == d) {
                                    num.checked = true
                                    return@any true
                                }
                                false
                            }
                        }) {
                        if (counter > 5) {
                            if (checkForBingo(b)) {
                                return d * (b.lines.sumOf { e -> e.sumOf { num -> if (!num.checked) num.value else 0 } })
                            }
                        }
                    }
                }
            }
        }
        return 0
    }

    private fun checkForBingo(board: Board): Boolean {
        board.lines.forEach { l ->
            if (l.all { e -> e.checked })
                return true
        }
        for (i in 0..< board.lines[0].size) {
            if (board.lines.map { it[i] }.all { e -> e.checked })
                return true
        }
        return false
    }

    private fun findLastBoard(input: List<Board>, draw: List<Int>): Board {
        fun find(list: List<Board>): Board{
            return if (list.size == 1) list[0]
            else {
                val lastBoardCheck = list.toMutableList()
                for ((counter, d) in draw.withIndex()) {
                    for (b in list) {
                        if (b.numbers.contains(d)) {
                            if (b.lines.any { line ->
                                    line.any { num ->
                                        if (num.value == d) {
                                            num.checked = true
                                            return@any true
                                        }
                                        false
                                    }
                                }) {
                                if (counter > 5) {
                                    if (checkForBingo(b)) {
                                        lastBoardCheck.remove(b)
                                        break
                                    }
                                }
                            }
                        }
                    }
                    if (lastBoardCheck.size < list.size) {
                        break
                    }
                }
                find(lastBoardCheck)
            }
        }
        return find(input)
    }


}