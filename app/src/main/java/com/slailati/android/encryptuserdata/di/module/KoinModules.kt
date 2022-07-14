package com.slailati.android.encryptuserdata.di.module

import com.google.gson.Gson
import com.slailati.android.encryptuserdata.data.coverters.Converters
import com.slailati.android.encryptuserdata.data.coverters.GsonParser
import com.slailati.android.encryptuserdata.data.coverters.JsonParser
import com.slailati.android.encryptuserdata.data.datasource.ProfileDataSource
import com.slailati.android.encryptuserdata.data.datasource.ProfileDataSourceImpl
import com.slailati.android.encryptuserdata.data.repository.ProfileRepository
import com.slailati.android.encryptuserdata.data.repository.ProfileRepositoryImpl
import com.slailati.android.encryptuserdata.domain.interactor.GetProfileUseCase
import com.slailati.android.encryptuserdata.domain.interactor.SaveProfileUseCase
import com.slailati.android.encryptuserdata.ui.viewmodel.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val domainModule = module {
    single { GetProfileUseCase(get()) }
    single { SaveProfileUseCase(get()) }
}

val dataModule = module {
    single { Gson() }
    single { GsonParser(get()) as JsonParser }
    single { Converters(get()) }
    
    single { ProfileDataSourceImpl(androidContext(), get()) as ProfileDataSource }
    single { ProfileRepositoryImpl(get()) as ProfileRepository }
}

val viewModelModule = module {
    single { ProfileViewModel(get(), get()) }
}