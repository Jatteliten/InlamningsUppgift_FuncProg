package adventOfCode2021.december10th

import java.io.File

class SyntaxErrors {
    val data: List<String> = File("src/main/kotlin/adventOfCode2021/december10th/Input").readLines()
    val testData: List<String> = File("src/main/kotlin/adventOfCode2021/december10th/testInput").readLines()

    fun checkA(input: List<String>): Int{
        var points = 0
        for(i in input){
            val syntaxList = mutableListOf<Char>()
            for (j in i){
                if(syntaxList.isNotEmpty()){
                    if(j == '(' || j == '{' || j == '<' || j == '[') {
                        syntaxList.add(j)
                    }else{
                        if(checkSyntaxCloser(j, syntaxList)){
                            syntaxList.removeLast()
                        } else{
                            points+=checkPoints(j)
                            break
                        }
                    }
                }else{
                    syntaxList.add(j)
                }
            }
        }
        return points
    }

    fun checkB(input: List<String>): Long{
        val points = mutableListOf<Long>()
        for(i in input){
            val syntaxList = mutableListOf<Char>()
            for ((index, j) in i.withIndex()) {
                    if (syntaxList.isNotEmpty()) {
                        if (j == '(' || j == '{' || j == '<' || j == '[') {
                            syntaxList.add(j)
                        } else {
                            if (checkSyntaxCloser(j, syntaxList)) {
                                syntaxList.removeLast()
                            } else {
                                break
                            }
                        }
                    } else {
                        syntaxList.add(j)
                    }
                if (index == i.length - 1) {
                    points.add(improvedCheckListCompletionPoints(syntaxList))
                }
            }
        }
        return points.sorted()[points.size/2]
    }

    private fun checkListCompletionPoints(list: List<Char>): Long{
        var points = 0L
        list.reversed().forEach { i ->
            points *= 5
            when (i) {
                '(' -> points += 1
                '[' -> points += 2
                '{' -> points += 3
                '<' -> points += 4
            }
        }
        return points
    }

    // inspired by chatGPT. Does the same thing but looks cleaner. Taught me about .fold
    private fun improvedCheckListCompletionPoints(list:List<Char>): Long{
        return list.reversed().fold(0L){points, c ->
            points * 5 + when(c){
                '(' -> 1
                '[' -> 2
                '{' -> 3
                '<' -> 4
                else -> 0
            }
        }
    }

    private fun checkSyntaxCloser(current: Char, list: List<Char>): Boolean{
        return (current == ')' && list[list.size-1] == '(') ||
                (current == '}' && list[list.size-1] == '{') ||
                (current == '>' && list[list.size-1] == '<') ||
                (current == ']' && list[list.size-1] == '[')
    }

    private fun checkPoints(current: Char): Int{
        when (current){
            ')' -> return 3
            ']' -> return 57
            '}' -> return 1197
            '>' -> return 25137
        }
        return 0
    }

}