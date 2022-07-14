package com.slailati.android.encryptuserdata.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.slailati.android.encryptuserdata.data.model.Profile
import com.slailati.android.encryptuserdata.domain.usecase.GetProfileUseCase
import com.slailati.android.encryptuserdata.domain.usecase.SaveProfileUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val saveProfileUseCase: SaveProfileUseCase,
    private val getProfileUseCase: GetProfileUseCase,
) : BaseViewModel() {
    
    private val _profile: MutableLiveData<Profile> by lazy { MutableLiveData<Profile>() }
    fun profile() = _profile
    
    private val _isProfileSaved = MutableSharedFlow<Boolean?>()
    fun isProfileSaved(): SharedFlow<Boolean?> = _isProfileSaved
    
    fun saveProfile(profile: Profile) {
        viewModelScope.launch {
            val isProfileSaved =
                saveProfileUseCase.execute(params = SaveProfileUseCase.Params(profile = profile))
            _isProfileSaved.emit(isProfileSaved)
        }
    }
    
    fun getProfile() {
        viewModelScope.launch {
            val profile = getProfileUseCase.execute()
            _profile.postValue(profile)
        }
    }
}