package domain

@JvmInline
value class Phone(private val value: String)  {

    init {
        require(value.isNotBlank()) {
            "Value cannot be blank"
        }

        require(value.length <= PHONE_MAX_LENGTH) {
            "domain.Phone не может быть длиннее $PHONE_MAX_LENGTH"
        }

        require(value.length > PHONE_MIN_LENGTH) {
            "domain.Phone не может быть короче $PHONE_MIN_LENGTH символов"
        }
    }

    companion object {
        const val PHONE_MAX_LENGTH = 50
        const val PHONE_MIN_LENGTH = 3
    }

    override fun toString(): String = value;

}