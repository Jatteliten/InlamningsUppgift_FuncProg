package adventOfCode2021.december7th

import org.junit.Test
import org.junit.jupiter.api.Assertions

class CrabShuffleTest {
    private val c = CrabShuffle()

    @Test
    fun checkATest(){
        Assertions.assertEquals(37, c.checkA(c.testData))
    }

    @Test
    fun checkBTest(){
        Assertions.assertEquals(168, c.checkB(c.testData))
    }


}