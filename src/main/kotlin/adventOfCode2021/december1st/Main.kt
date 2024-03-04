package adventOfCode2021.december1st

fun main(){
    val s = SonarSweep()

    println("A: " + s.sweepA(s.data))
    println("B: " + s.sweepB(s.data))
    println("improved A: " + s.improvedSweep(s.data, 2))
    println("improved B: " + s.improvedSweep(s.data, 4))
}