package com.example.profilerepo.services

import com.example.profilerepo.domain.HashingAlgorithm
import com.example.profilerepo.domain.Inn
import com.example.profilerepo.domain.InnHash
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.util.*

@Service
class HashingService {
    fun createHash(inn: Inn, algorithm: HashingAlgorithm) : InnHash {
        val md = MessageDigest.getInstance(algorithm.toString())
        val digest = md.digest(inn.toByteArray())
        return InnHash(Base64.getEncoder().encodeToString(digest), algorithm)
    }
}