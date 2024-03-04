package adventOfCode2021.december9th

import org.junit.Test
import org.junit.jupiter.api.Assertions

class SmokeFlowTest {
    private val s = SmokeFlow()

    @Test
    fun checkATest(){
        Assertions.assertEquals(15, s.checkA(s.testData))
    }

    @Test
    fun checkBTest(){
        Assertions.assertEquals(1134, s.checkB(s.testData))
    }
}