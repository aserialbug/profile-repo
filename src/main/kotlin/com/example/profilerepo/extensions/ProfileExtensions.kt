package com.example.profilerepo.extensions

import com.example.profilerepo.domain.Profile
import com.example.profilerepo.dto.ProfileDto

fun Profile.toDto(): ProfileDto {
    return ProfileDto(
        this.id.asUuid(),
        this.phone.toString(),
        this.email.toString(),
        this.mfaId.toString(),
        this.createdAt,
        this.updatedAt,
        this.version
    )
}