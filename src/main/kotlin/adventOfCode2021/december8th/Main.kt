package adventOfCode2021.december8th

fun main(){
    val n = NumberFinder()
    println("A: " + n.checkA(n.data))
    println("B: " + n.checkB(n.data))
    println("A: " + n.improvedCheckA())
    println("A: " + n.improveCheckB())
}