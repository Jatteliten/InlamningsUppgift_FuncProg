package adventOfCode2021.december14th

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PolymerizationTest {
    private val p = Polymerization()

    @Test
    fun checkA(){
        Assertions.assertEquals(1588, p.checkB(p.testData, loops = 10))
    }

    @Test
    fun checkB(){
        Assertions.assertEquals(2188189693529, p.checkB(p.testData, loops = 40))
    }
}