package adventOfCode2021.december13th

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MapFoldingTest {
    private val m = MapFolding()

    @Test
    fun checkATest(){
        Assertions.assertEquals(17, m.check(m.testData))
    }
}