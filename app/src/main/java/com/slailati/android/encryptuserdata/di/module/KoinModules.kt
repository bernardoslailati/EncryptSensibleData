package com.slailati.android.encryptuserdata.di.module

import com.slailati.android.encryptuserdata.domain.datasource.ProfileDataSource
import com.slailati.android.encryptuserdata.domain.datasource.ProfileDataSourceImpl
import com.slailati.android.encryptuserdata.domain.repository.ProfileRepository
import com.slailati.android.encryptuserdata.domain.repository.ProfileRepositoryImpl
import com.slailati.android.encryptuserdata.ui.viewmodel.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val domainModule = module {
//    factory { (scope: CoroutineScope) -> AuthenticationUseCase(get(), scope) }
}

val dataModule = module {
    single { ProfileDataSourceImpl(androidContext()) as ProfileDataSource }
    single { ProfileRepositoryImpl(get()) as ProfileRepository }
}

val viewModelModule = module {
    single { ProfileViewModel(get()) }
}