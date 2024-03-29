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

    fun getUnderlings(bossName: String, emptyList: MutableList<String>): List<String> {
        fun checkUnderling(underlings: Set<String>): List<String>{
            val newUnderlings = underlings.toMutableSet()
                .apply { underlings.forEach{tomte ->
                    tomtar[tomte]?.let { addAll(it) }} }

            return if (newUnderlings == underlings) underlings.toList()
            else checkUnderling(newUnderlings)
        }
        tomtar[bossName]?.let { emptyList.addAll(it) }
        return checkUnderling(emptyList.toSet())
    }
    
}