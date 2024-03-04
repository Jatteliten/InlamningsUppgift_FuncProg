package adventOfCode2021.december13th

fun main(){
    val m = MapFolding()

    println("A: " + m.check(m.data))
    println("B: ")
    m.check(m.data, partA = false)
}