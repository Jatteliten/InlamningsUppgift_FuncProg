package adventOfCode2021.december7th

fun main(){
    val c = CrabShuffle()
    println("A: " + c.checkA(c.data))
    println("Improved A: " + c.improvedCheckA(c.data))
    println("B: " + c.checkB(c.data))
}