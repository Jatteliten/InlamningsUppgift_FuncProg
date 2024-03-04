package adventOfCode2021.december14th

fun main(){
    val p = Polymerization()

    println("A: " + p.checkB(p.data, loops = 10))
    println("B: " + p.checkB(p.data, loops = 40))

}