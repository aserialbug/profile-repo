package com.example.profilerepo.dto

import java.time.Instant
import java.util.*

data class ProfileDto(
    val id: UUID,
    val phone: String,
    val email: String,
    val mfaId: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val version: Long
)