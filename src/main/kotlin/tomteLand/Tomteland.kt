package tomteLand

class Tomteland {

    private val tomtar = mapOf(
        "Tomten" to listOf("Glader", "Butter"),
        "Glader" to listOf("Tröger", "Trötter", "Blyger"),
        "Butter" to listOf("Rådjuret", "Nyckelpigan", "Haren", "Räven"),
        "Trötter" to listOf("Skumtomten"),
        "Skumtomten" to listOf("Dammråttan"),
        "Räven" to listOf("Gråsuggan", "Myran"),
        "Myran" to listOf("Bladlusen"))

    fun getUnderlings(bossName: String, res: MutableList<String>): List<String> {
        fun checkUnderling(underlings: Set<String>): List<String>{
            val newUnderlings = underlings.toMutableSet()
                .apply { underlings.forEach{e ->
                    tomtar[e]?.let { addAll(it) }} }

            return if (newUnderlings.size == underlings.size) newUnderlings.toList()
            else checkUnderling(newUnderlings)
        }
        tomtar[bossName]?.let { res.addAll(it) }
        return checkUnderling(res.toSet())
    }

}