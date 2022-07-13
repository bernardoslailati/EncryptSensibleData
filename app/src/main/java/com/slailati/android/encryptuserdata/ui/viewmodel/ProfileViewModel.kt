package com.slailati.android.encryptuserdata.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.slailati.android.encryptuserdata.data.model.Profile
import com.slailati.android.encryptuserdata.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val profileRepository: ProfileRepository) : BaseViewModel() {

    private val _profile: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    fun profile() = _profile

    private val _isProfileSaved = MutableSharedFlow<Boolean?>()
    fun isProfileSaved(): SharedFlow<Boolean?> = _isProfileSaved

    fun saveProfile(profile: Profile) {
        viewModelScope.launch {
            val isProfileSaved = profileRepository.saveProfile(profile)
            _isProfileSaved.emit(isProfileSaved)
        }
    }

    fun getDecryptedProfile() {
        viewModelScope.launch {
            val profile = profileRepository.getDecryptedProfile()
            _profile.postValue(profile)
        }
    }

}