package com.example.profilerepo.domain

import com.example.profilerepo.extensions.isEmpty
import java.util.*

@JvmInline
value class ProfileId(private val id: UUID) {
    init {
        require(!id.isEmpty()) {
            "id cannot be empty"
        }
    }

    companion object {
        fun parse(value: String): ProfileId {
            if(value.isBlank())
                throw IllegalArgumentException("ProfileId cannot be blank")

            return ProfileId(UUID.fromString(value))
        }

        fun new(): ProfileId = ProfileId(UUID.randomUUID())
    }

    fun asUuid() = id;
    override fun toString(): String = id.toString();
    fun new() = ProfileId(UUID.randomUUID())
}