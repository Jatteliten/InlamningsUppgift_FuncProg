package adventOfCode2021.december3rd

fun main(){
    val d = DiagnosticsReport()

    println("A: " + d.checkA(d.data))
    println("B: " + d.checkB(d.data))

    println("Improved A: " + d.checkImprovedA(d.data))
    println("Improved B: " + d.checkImprovedB(d.data))

}