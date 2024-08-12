package com.example.profilerepo.services

import com.example.profilerepo.dao.ProfileDao
import com.example.profilerepo.domain.*
import com.example.profilerepo.dto.CreateProfileAttributes
import com.example.profilerepo.dto.ProfileDto
import org.springframework.stereotype.Service

@Service
class ProfileService(
    val profileDao: ProfileDao,
    val hashingService: HashingService) {


    fun getProfiles(): Collection<ProfileDto> {
        return profileDao.getAllProfiles();
    }

    fun createOrUpdateProfile(attributes: CreateProfileAttributes) : Profile{

        val inn = Inn(attributes.inn)
        val innHash = hashingService.createHash(inn, HashingAlgorithm.SHA256)
        val email = Email(attributes.email)
        val phone = Phone(attributes.phone)
        var profile = profileDao.findProfile(innHash)

        if(profile == null) {
            val profileId = ProfileId(attributes.id)
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