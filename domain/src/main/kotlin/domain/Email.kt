package domain

@JvmInline
value class Email(private val value: String) {

    init {
        require(value.isNotBlank()) {
            "Value cannot be blank"
        }

        require(value.length <= EMAIL_MAX_LENGTH) {
            "domain.Email не может быть длиннее $EMAIL_MAX_LENGTH символов"
        }

        require(value.length > EMAIL_MIN_LENGTH) {
            "domain.Email не может быть короче $EMAIL_MIN_LENGTH символов"
        }
    }

    companion object {
        const val EMAIL_MAX_LENGTH = 255
        const val EMAIL_MIN_LENGTH = 5
    }

    override fun toString(): String = value;
}