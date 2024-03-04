package adventOfCode2021.december4th

import org.junit.Test
import org.junit.jupiter.api.Assertions

class BingoTest {
    private val b = Bingo()

    @Test
    fun checkATest(){
        Assertions.assertEquals(4512, b.checkA(b.testData))
    }

    @Test
    fun checkBTest(){
        Assertions.assertEquals(1924, b.checkB(b.testData))
    }

}