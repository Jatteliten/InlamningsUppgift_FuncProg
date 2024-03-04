package adventOfCode2021.december3rd

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DiagnosticsReportTest {
    private val d = DiagnosticsReport()
    @Test
    fun checkATest() {
        Assertions.assertEquals(198, d.checkA(d.testData))
    }

    @Test
    fun checkBTest() {
        Assertions.assertEquals(230, d.checkB(d.testData))
    }
}