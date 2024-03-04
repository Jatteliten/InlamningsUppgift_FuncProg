package adventOfCode2021.december2nd

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PositionCheckerTest {
    private val p = PositionChecker()

    @Test
    fun checkATest() {
        Assertions.assertEquals(150, p.checkA(p.testData))
    }

    @Test
    fun checkBTest() {
        Assertions.assertEquals(900, p.checkB(p.testData))
    }
}