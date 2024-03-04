package adventOfCode2021.december5th

import org.junit.Test
import org.junit.jupiter.api.Assertions

class VentFinderTest {
    private val v = VentFinder()

    @Test
    fun checkATest(){
        Assertions.assertEquals(5, v.checkA(v.testData))
    }

    @Test
    fun checkBTest(){
        Assertions.assertEquals(12, v.checkB(v.testData))
    }
}