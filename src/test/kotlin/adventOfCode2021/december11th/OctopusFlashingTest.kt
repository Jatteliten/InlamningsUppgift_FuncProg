package adventOfCode2021.december11th

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class OctopusFlashingTest {
    private val o = OctopusFlashing()

    @Test
    fun checkATest(){
        Assertions.assertEquals(1656, o.checkA(o.testData))
    }

    @Test
    fun checkBTest(){
        Assertions.assertEquals(195, o.checkB(o.testData))
    }
}