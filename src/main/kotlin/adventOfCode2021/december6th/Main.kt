package adventOfCode2021.december6th

fun main(){
    val l = LanternFishPopulationGrowth()

    println("A: " + l.checkA(l.data))
    println("B: " + l.checkB(l.data, 256))
    println("changed A: " + l.changedCheck(l.data, 80))
    println("changed B: " + l.changedCheck(l.data, 256))
}