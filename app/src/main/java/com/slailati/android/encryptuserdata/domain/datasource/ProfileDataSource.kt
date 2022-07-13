package com.slailati.android.encryptuserdata.domain.datasource

import com.slailati.android.encryptuserdata.data.model.Profile

interface ProfileDataSource {

    suspend fun saveProfile(profile: Profile): Boolean

    suspend fun getDecryptedProfile(): String

}