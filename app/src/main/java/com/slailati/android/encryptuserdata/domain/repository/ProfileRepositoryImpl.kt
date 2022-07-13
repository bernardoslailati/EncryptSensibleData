package com.slailati.android.encryptuserdata.domain.repository

import com.slailati.android.encryptuserdata.data.model.Profile
import com.slailati.android.encryptuserdata.domain.datasource.ProfileDataSource

class ProfileRepositoryImpl(
    private val profileDataSource: ProfileDataSource
): ProfileRepository {

    override suspend fun saveProfile(profile: Profile): Boolean {
        return profileDataSource.saveProfile(profile)
    }

    override suspend fun getDecryptedProfile(): String {
        return profileDataSource.getDecryptedProfile()
    }

}