package com.example.profilerepo.domain

private const val PHONE_MAX_LENGTH = 20
private const val PHONE_MIN_LENGTH = 3

@JvmInline
value class Phone(private val value: String)  {

    init {
        require(value.isNotBlank()) {
            "Value cannot be blank"
        }

        require(value.length <= PHONE_MAX_LENGTH) {
            "Phone не может быть длиннее $PHONE_MAX_LENGTH"
        }

        require(value.length > PHONE_MIN_LENGTH) {
            "Phone не может быть короче $PHONE_MIN_LENGTH символов"
        }
    }

    override fun toString(): String = value;

}