package pop

import main.Statistics.Companion.normalDistributionDivisorRange
import kotlin.random.Random

class Pop(
    private val id: Int,
    private val dna: Int
) {
    constructor() : this(emptyMap())

    constructor(dna: Int) : this(allPops.size, dna)

    constructor(map: Map<String, Any>) : this(
        map["id"] as? Int ?: allPops.size,
        map["dna"] as? Int ?: generateDNA()
    )

    init {
        findNextValidPopId()
        if (allPops.containsKey(id)) throw Exception("Cannot create Pop with duplicate id $id")
        allPops[id] = this
    }

    companion object {
        var nextPopId = 0
        val allPops = mutableMapOf<Int, Pop>()

        private const val dnaLength = 9

        private fun generateDNA(): Int {
            var dnaBuilder = 0
            for (i in 0 until dnaLength) {
                dnaBuilder = dnaBuilder * 10 + Random.nextInt(0, 10)
            }
            return dnaBuilder
        }
    }

    fun getAttribute(attribute: Attribute): Int {
        val divisorRange = normalDistributionDivisorRange
        val divisorOffset = (normalDistributionDivisorRange - 1) / 2
        val popDistributionValue = (((attribute.id.toLong() * dna.toLong()) % divisorRange) - divisorOffset).toInt()
        return attribute.getValue(popDistributionValue)
    }

    override fun toString(): String {
        return "Pop{" +
                "id=$id," +
                "dna=$dna" +
                "}"
    }

    private fun findNextValidPopId()  {
        while (Pop.allPops.containsKey(Pop.nextPopId)) Pop.nextPopId++
    }
}