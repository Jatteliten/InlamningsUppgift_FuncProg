package adventOfCode2021.december5th

fun main(){
    val v = VentFinder()

    println("A: " + v.checkA(v.data))
    println("A with maximum calls in a row: " + v.checkAWithMaximumCallsInARow(v.data))
    println("B: " + v.checkB(v.data))
}