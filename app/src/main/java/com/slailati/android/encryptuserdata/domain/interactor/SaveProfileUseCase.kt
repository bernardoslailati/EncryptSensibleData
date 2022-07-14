package com.slailati.android.encryptuserdata.domain.interactor

import com.slailati.android.encryptuserdata.data.model.Profile
import com.slailati.android.encryptuserdata.data.repository.ProfileRepository

class SaveProfileUseCase(private val repository: ProfileRepository) {
    
    suspend fun execute(params: Params): Boolean = repository.saveProfile(params.profile)

    data class Params(val profile: Profile)
    
}