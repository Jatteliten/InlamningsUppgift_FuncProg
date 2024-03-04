package adventOfCode2021.december8th

import org.junit.Test
import org.junit.jupiter.api.Assertions

class NumberFinderTest {
    private val n = NumberFinder()

    @Test
    fun checkATest(){
        Assertions.assertEquals(26, n.checkA(n.testData))
    }

    @Test
    fun checkBTest(){
        Assertions.assertEquals(61229, n.checkB(n.testData))
    }

    @Test
    fun checkBSmallTest(){
        Assertions.assertEquals(5353, n.checkB(n.smallTestData))
    }

}