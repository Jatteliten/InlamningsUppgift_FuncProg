package adventOfCode2021.december15th

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PathFinderTest {
    private val p = PathFinder()

    @Test
    fun checkATest(){
        Assertions.assertEquals(40, p.checkA(p.testData))
    }

    @Test
    fun checkBTest(){
        Assertions.assertEquals(315, p.checkB(p.testData))
    }
}