package domain

class HashingAlgorithm(private val alg: String, val hashLength: Int) {
    companion object {
        val SHA256: HashingAlgorithm = HashingAlgorithm("SHA-256", 44)

        fun parse(value: String): HashingAlgorithm {
            when(value) {
                "SHA-256" -> return SHA256
                else -> throw IllegalArgumentException("Unknown algorithm $value")
            }
        }
    }
    override fun toString(): String = alg
}