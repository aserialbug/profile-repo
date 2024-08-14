package com.example.profilerepo.domain

import java.time.Instant

class Profile(
    val id: ProfileId,
    val innHash: InnHash,
    phone: Phone,
    email: Email,
    val mfaId: MfaId,
    val createdAt: Instant,
    var updatedAt: Instant,
    version: Long
) {
    var phone: Phone = phone
        set(value) {
            if(value == field)
                return;

            field = value;
            version++;
            updatedAt = Instant.now()
        }

    var email: Email = email
        set(value) {
            if(value == field)
                return;

            field = value;
            version++;
            updatedAt = Instant.now()
        }

    var version: Long = version
        private set(_)
        {
            if(!dirty) {
                field++
                dirty = true
            }
        }

    var dirty: Boolean = false
        private set;

    constructor(
        id: ProfileId,
        innHash: InnHash,
        phone: Phone,
        email: Email,
        mfaId: MfaId,
    ) : this(id, innHash, phone, email, mfaId, Instant.now(), Instant.now(), 0) {}
}