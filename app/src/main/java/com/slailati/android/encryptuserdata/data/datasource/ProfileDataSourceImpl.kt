package com.slailati.android.encryptuserdata.data.datasource

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.slailati.android.encryptuserdata.data.coverters.Converters
import com.slailati.android.encryptuserdata.data.model.Profile
import java.lang.Exception

class ProfileDataSourceImpl(context: Context, private val converters: Converters) :
    ProfileDataSource {
    
    private val encryptedSharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            context,
            MasterKey.DEFAULT_MASTER_KEY_ALIAS,
            MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
    
    override suspend fun saveProfile(profile: Profile): Boolean {
        return try {
            encryptedSharedPreferences.edit {
                putString(PROFILE_KEY, converters.toProfileJson(profile))
                apply()
            }
            true
        } catch (e: Exception) {
            false
        }
    }
    
    override suspend fun getProfile(): Profile =
        converters.fromProfileJson(
            encryptedSharedPreferences.getString(
                PROFILE_KEY,
                converters.toProfileJson(Profile())
            ).toString()
        )
    
    companion object {
        const val PROFILE_KEY = "profile"
    }
    
}