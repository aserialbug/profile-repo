package com.example.profilerepo.domain

private const val EMAIL_MAX_LENGTH = 50
private const val EMAIL_MIN_LENGTH = 5

@JvmInline
value class Email(private val value: String) {

    init {
        require(value.isNotBlank()) {
            "Value cannot be blank"
        }

        require(value.length <= EMAIL_MAX_LENGTH) {
            "Email не может быть длиннее $EMAIL_MAX_LENGTH символов"
        }

        require(value.length > EMAIL_MIN_LENGTH) {
            "Email не может быть короче $EMAIL_MIN_LENGTH символов"
        }
    }

    override fun toString(): String = value;
}