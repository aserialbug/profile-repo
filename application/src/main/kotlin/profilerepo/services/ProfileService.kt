package profilerepo.services

import profilerepo.dto.CreateProfileAttributes
import profilerepo.dto.ProfileDto
import profilerepo.interfaces.IProfileDao
import domain.*
import org.springframework.stereotype.Service

@Service
class ProfileService(
    val profileDao: IProfileDao,
    val hashingService: HashingService
) {


    fun getProfiles(): Collection<ProfileDto> {
        return profileDao.getAllProfiles();
    }

    fun createOrUpdateProfile(attributes: CreateProfileAttributes) : Profile {

        val inn = Inn(attributes.inn)
        val innHash = hashingService.createHash(inn, HashingAlgorithm.SHA256)
        val email = Email(attributes.email)
        val phone = Phone(attributes.phone)
        var profile = profileDao.findProfile(innHash)

        if(profile == null) {
            val profileId = ProfileId.new()
            val mfa = MfaId(attributes.mfaId)
            profile = Profile(profileId, innHash, phone, email, mfa);
        }
        else {
            profile.email = email;
            profile.phone = phone
        }

        profileDao.saveProfile(profile);
        return profile;
    }

    fun deleteProfile(id: String) {
        val profileId = ProfileId.parse(id)

        val profile = profileDao.getById(profileId);
        profileDao.delete(profile)
    }
}