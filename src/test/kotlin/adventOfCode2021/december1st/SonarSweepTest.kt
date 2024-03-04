package adventOfCode2021.december1st

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SonarSweepTest {
    private val s = SonarSweep()

    @Test
    fun sweepATest() {
        Assertions.assertEquals(7, s.sweepA(s.testData))
    }

    @Test
    fun sweepBTest() {
        Assertions.assertEquals(5, s.sweepB(s.testData))
    }

    @Test
    fun improvedSweepTest() {
        Assertions.assertEquals(7, s.improvedSweep(s.testData, 2))
        Assertions.assertEquals(5, s.improvedSweep(s.testData, 4))
    }
}