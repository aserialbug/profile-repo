package profilerepo.infrastructure.controllers

import org.springframework.web.bind.annotation.*
import profilerepo.dto.CreateProfileAttributes
import profilerepo.dto.ProfileDto
import profilerepo.extensions.toDto
import profilerepo.services.ProfileService

@RequestMapping("/profiles")
@RestController
class ProfilesController(val profileService: ProfileService) {

    @GetMapping
    fun getProfiles(): Collection<ProfileDto> =
        profileService.getProfiles();

    @PostMapping
    fun createOrUpdateProfile(@RequestBody attributes: CreateProfileAttributes): ProfileDto =
        profileService.createOrUpdateProfile(attributes).toDto();

    @DeleteMapping("/{profileId}")
    fun deleteProfile(@PathVariable profileId: String) =
        profileService.deleteProfile(profileId);
}