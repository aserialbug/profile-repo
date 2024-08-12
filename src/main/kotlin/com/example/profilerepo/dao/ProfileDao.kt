package com.example.profilerepo.dao

import com.example.profilerepo.domain.*
import com.example.profilerepo.dto.ProfileDto
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.jdbc.core.RowCallbackHandler
import org.springframework.stereotype.Service
import java.sql.ResultSet
import java.sql.Timestamp
import java.util.*

private const val GET_PROFILE_BY_ID_SQL = "select * from profiles where id = ?"
private const val DELETE_PROFILE_SQL = "delete from profiles where id = ?"
private const val SELECT_ALL_PROFILES_SQL = "select * from profiles"
private const val FIND_PROFILE_INN_SQL = "select * from profiles where inn_hash = ?"
private const val INSERT_OR_UPDATE_PROFILE_SQL = "insert into profiles (id, inn_hash, phone, email, mfa_id, created_at, " +
        "updated_at, version) values (?, ?, ?, ?, ?, ?, ?, ?) on conflict (inn_hash) do update set phone = ?, email = ?, updated_at = ?, version = ?"

@Service
class ProfileDao(val db: JdbcTemplate) {

    fun findProfile(innHash: InnHash) : Profile? {
        return readProfiles(FIND_PROFILE_INN_SQL, innHash.toString()).firstOrNull();
    }

    fun saveProfile(profile: Profile) {
        db.update(
            INSERT_OR_UPDATE_PROFILE_SQL,
            profile.id.asUuid(),
            profile.innHash.toString(),
            profile.phone.toString(),
            profile.email.toString(),
            profile.mfaId.toString(),
            Timestamp.from(profile.createdAt),
            Timestamp.from(profile.updatedAt),
            profile.version,
            profile.phone.toString(),
            profile.email.toString(),
            Timestamp.from(profile.updatedAt),
            profile.version)
    }

    fun getAllProfiles(): Collection<ProfileDto> = db.query(SELECT_ALL_PROFILES_SQL) { response, _ ->
        ProfileDto(
            UUID.fromString(response.getString("id")),
            response.getString("phone"),
            response.getString("email"),
            response.getString("mfa_id"),
            response.getTimestamp("created_at").toInstant(),
            response.getTimestamp("updated_at").toInstant(),
            response.getLong("version")
        )
    }

    fun getById(id: ProfileId): Profile {
        return readProfiles(GET_PROFILE_BY_ID_SQL, id.asUuid()).single();
    }

    fun delete(profile: Profile) {
        db.update(DELETE_PROFILE_SQL, profile.id.asUuid())
    }

    private fun <TParam> readProfiles(sql: String, queryParameter: TParam): List<Profile> {
        return db.query(sql, { rs: ResultSet, _: Int ->  Profile(
            ProfileId(UUID.fromString(rs.getString("id"))),
            InnHash.parse(rs.getString("inn_hash")),
            Phone(rs.getString("phone")),
            Email(rs.getString("email")),
            MfaId(rs.getString("mfa_id")),
            rs.getTimestamp("created_at").toInstant(),
            rs.getTimestamp("updated_at").toInstant(),
            rs.getLong("version")
        )}, queryParameter)
    }
}