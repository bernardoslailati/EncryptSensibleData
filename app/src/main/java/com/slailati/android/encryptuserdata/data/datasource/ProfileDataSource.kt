package com.slailati.android.encryptuserdata.data.datasource

import com.slailati.android.encryptuserdata.data.model.Profile

interface ProfileDataSource {

    suspend fun saveProfile(profile: Profile): Boolean

    suspend fun getProfile(): Profile

}