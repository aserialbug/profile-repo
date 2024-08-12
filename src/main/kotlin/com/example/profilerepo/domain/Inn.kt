package com.example.profilerepo.domain

private const val INDIVIDUAL_INN_LENGTH = 12
private const val COMPANY_INN_LENGTH = 10

@JvmInline
value class Inn(private val value: String) {
    init {
        require(value.isNotBlank()) {
            "Value cannot be blank"
        }

        require(value.length == INDIVIDUAL_INN_LENGTH ||
                value.length == COMPANY_INN_LENGTH) {
            "Invalid INN length"
        }

        require(value.all { char -> char.isDigit() }) {
            "INN mush contain numbers only"
        }

        // Другие валидации согласно
        // https://www.consultant.ru/document/cons_doc_LAW_134082/947eeb5630c9f58cbc6103f0910440cef8eaccac/
    }

    override fun toString(): String = value;
    fun toByteArray(): ByteArray = value.toByteArray();
}