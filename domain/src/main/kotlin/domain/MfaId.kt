package domain

@JvmInline
value class MfaId(private val value: String)  {

    init {
        require(value.isNotBlank()) {
            "Value cannot be blank"
        }

        require(value.length <= MAX_MFA_ID_LENGTH) {
            "MFA-ID не может быть длиннее $MAX_MFA_ID_LENGTH"
        }
    }

    companion object {
        const val MAX_MFA_ID_LENGTH = 255
    }

    override fun toString(): String = value;
}