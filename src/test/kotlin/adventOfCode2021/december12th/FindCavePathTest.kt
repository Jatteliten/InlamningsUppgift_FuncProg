package adventOfCode2021.december12th

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FindCavePathTest {
    private val f = FindCavePath()

    @Test
    fun checkATest(){
        Assertions.assertEquals(226, f.checkA(f.testData))
    }

    @Test
    fun checkBTest(){
        Assertions.assertEquals(3509, f.checkB(f.testData))
    }
}