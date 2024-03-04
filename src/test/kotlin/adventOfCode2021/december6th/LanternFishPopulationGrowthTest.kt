package adventOfCode2021.december6th

import org.junit.Test
import org.junit.jupiter.api.Assertions

class LanternFishPopulationGrowthTest {
    private val l = LanternFishPopulationGrowth()

    @Test
    fun checkATest(){
        Assertions.assertEquals(5934, l.checkA(l.testData))
    }

    @Test
    fun checkBTest(){
        // part 1
        Assertions.assertEquals(5934, l.checkB(l.testData, 80))
        // part 2
        Assertions.assertEquals(26984457539, l.checkB(l.testData, 256))
    }
}