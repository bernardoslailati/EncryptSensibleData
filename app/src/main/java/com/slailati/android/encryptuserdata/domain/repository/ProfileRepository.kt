package com.slailati.android.encryptuserdata.domain.repository

import com.slailati.android.encryptuserdata.data.model.Profile

interface ProfileRepository {

    suspend fun saveProfile(profile: Profile): Boolean

    suspend fun getDecryptedProfile(): String

}