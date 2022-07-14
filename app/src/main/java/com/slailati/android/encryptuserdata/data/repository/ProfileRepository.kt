package com.slailati.android.encryptuserdata.data.repository

import com.slailati.android.encryptuserdata.data.model.Profile

interface ProfileRepository {

    suspend fun saveProfile(profile: Profile): Boolean

    suspend fun getProfile(): Profile

}