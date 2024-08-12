package com.example.profilerepo.dto

import java.util.UUID

data class CreateProfileAttributes(
    val id: UUID,
    val inn: String,
    val phone: String,
    val email: String,
    val mfaId: String)
