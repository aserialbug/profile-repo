package profilerepo.extensions

import profilerepo.dto.ProfileDto
import domain.Profile


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