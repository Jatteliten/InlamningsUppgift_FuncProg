package adventOfCode2021.december10th

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SyntaxErrorsTest {
    private val s = SyntaxErrors()

    @Test
    fun checkATest(){
        Assertions.assertEquals(26397, s.checkA(s.testData))
    }

    @Test
    fun checkBTest(){
        Assertions.assertEquals(288957, s.checkB(s.testData))
    }
}