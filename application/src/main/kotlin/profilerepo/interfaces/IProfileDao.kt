package profilerepo.interfaces

import profilerepo.dto.ProfileDto
import domain.InnHash
import domain.Profile
import domain.ProfileId

interface IProfileDao {
    fun findProfile(innHash: InnHash) : Profile?;
    fun saveProfile(profile: Profile);
    fun getAllProfiles(): Collection<ProfileDto>;
    fun getById(id: ProfileId): Profile
    fun delete(profile: Profile)
}