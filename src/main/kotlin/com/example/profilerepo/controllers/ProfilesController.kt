package com.example.profilerepo.controllers

import com.example.profilerepo.dto.CreateProfileAttributes
import com.example.profilerepo.dto.ProfileDto
import com.example.profilerepo.extensions.toDto
import com.example.profilerepo.services.ProfileService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfilesController(val profileService: ProfileService) {

    @GetMapping("/profiles")
    fun getProfiles(): Collection<ProfileDto> =
        profileService.getProfiles();

    @PostMapping("/profiles")
    fun createOrUpdateProfile(@RequestBody attributes: CreateProfileAttributes): ProfileDto =
        profileService.createOrUpdateProfile(attributes).toDto();

    @DeleteMapping("/profiles/{profileId}")
    fun deleteProfile(@PathVariable profileId: String) =
        profileService.deleteProfile(profileId);
}