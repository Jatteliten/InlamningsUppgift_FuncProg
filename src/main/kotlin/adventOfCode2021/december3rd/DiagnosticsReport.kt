package adventOfCode2021.december3rd

import java.io.File

class DiagnosticsReport {
    val data: List<String> = File("src/main/kotlin/adventOfCode2021/december3rd/Input").readLines()
    val testData: List<String> = File("src/main/kotlin/adventOfCode2021/december3rd/testInput").readLines()

    fun checkA(input: List<String>): Int{
        return checkGammaOrEpsilon(gamma = true, input).toInt(2) *
                checkGammaOrEpsilon(gamma = false, input).toInt(2)
    }

    fun checkB(input: List<String>): Int{
        return findSuitableBinary(input, gamma = true).toInt(2) *
                findSuitableBinary(input, gamma = false).toInt(2)
    }

    fun checkImprovedA(input: List<String>): Int{
        return improvedCheckGammaOrEpsilon(gamma = true, input).toInt(2) *
                improvedCheckGammaOrEpsilon(gamma = false, input).toInt(2)
    }

    fun checkImprovedB(input: List<String>) : Int{
        return improvedFindSuitableBinary(input, oxygen = true).toInt(2) *
                improvedFindSuitableBinary(input, oxygen = false).toInt(2)
    }

    private fun checkGammaOrEpsilon(gamma: Boolean, input: List<String>): String{
        fun check(row: Int, binary: String): String{
            return if(row == input[0].length) binary
            else{
                var counter = 0
                input.forEach{ c -> when (c[row]){'0' -> counter-- else -> counter++} }
                when(gamma) {
                    true -> if (counter > 0) check(row + 1, binary + "1")
                                else check(row + 1, binary + "0")
                    false -> if (counter > 0) check(row + 1, binary + "0")
                                else check(row + 1, binary + "1")}
            }
        }
        return check(0, "")
    }

    private fun findSuitableBinary(input: List<String>, gamma: Boolean): String{
        fun check(index: Int, list: List<String>): String{
            return if(list.size == 1) list[0]
            else{
                val newList = mutableListOf<String>()
                var count = 0
                list.forEach{s -> if(s[index] == '1') count++ else count--}
                if(gamma) {
                    list.forEach { s ->
                        if (s[index] == '1' && count < 0 ||
                            s[index] == '0' && count >= 0)
                            newList.add(s)
                    }
                }else{
                    list.forEach { s ->
                        if (s[index] == '1' && count >= 0 ||
                            s[index] == '0' && count < 0)
                            newList.add(s)
                    }
                }
                check(index + 1, newList)
            }
        }
        return check(0, input)
    }

    // solution inspired by chatGPT
    private fun improvedCheckGammaOrEpsilon(gamma: Boolean, input: List<String>): String {
        fun check(row: Int, binary: String): String =
            if (row == input[0].length) binary
            else {
                val counter = input.sumBy { c -> if (c[row] == '0') -1 else 1 }

                check(row + 1, binary + if (gamma == (counter > 0)) "1" else "0")
            }
        return check(0, "")
    }

    // solution inspired by chatGPT
    private fun improvedFindSuitableBinary(input: List<String>, oxygen: Boolean): String {
        fun check(index: Int, list: List<String>): String {
            return if (list.size == 1) list[0]
            else {
                val count = list.groupBy { it[index] }
                    .mapValues { if (it.key == '1') it.value.size else -it.value.size }
                    .values.sum()

                val newList = list.filter { s ->
                    (s[index] == '1' && (oxygen && count >= 0 || !oxygen && count < 0))
                            || (s[index] == '0' && (oxygen && count < 0 || !oxygen && count >= 0))
                }

                check(index + 1, newList)
            }
        }
        return check(0, input)
    }

}