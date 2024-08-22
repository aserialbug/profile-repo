package profilerepo.services

import domain.HashingAlgorithm
import domain.Inn
import domain.InnHash
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