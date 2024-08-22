package domain

class InnHash(private val value: String,
              val algorithm: HashingAlgorithm
) {

    init {
        require(value.isNotBlank()) {
            "Value cannot be blank"
        }

        require(value.length == algorithm.hashLength) {
            "Value must be of specific length"
        }
    }

    companion object {
        fun parse(value: String): InnHash {
            require(value.isNotBlank()) {
                "Hash value cannot be blank"
            }

            val sectors = value.split(':');
            if(sectors.count() != 2)
                throw IllegalArgumentException("Value $value is not valid domain.InnHash")

            return InnHash(sectors[1], HashingAlgorithm.parse(sectors[0]));
        }
    }

    override fun toString(): String = "${algorithm}:${value}";
}