package profilerepo.dto

import java.util.UUID

data class CreateProfileAttributes(
    val inn: String,
    val phone: String,
    val email: String,
    val mfaId: String)
