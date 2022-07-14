package com.slailati.android.encryptuserdata.domain.interactor

import com.slailati.android.encryptuserdata.data.model.Profile
import com.slailati.android.encryptuserdata.data.repository.ProfileRepository

class GetProfileUseCase(private val repository: ProfileRepository) {
    
    suspend fun execute(): Profile = repository.getProfile()

}