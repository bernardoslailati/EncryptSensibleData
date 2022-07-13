package com.slailati.android.encryptuserdata.domain.datasource

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.slailati.android.encryptuserdata.data.model.Profile
import java.lang.Exception

class ProfileDataSourceImpl(context: Context) : ProfileDataSource {

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
                putString("profile", profile.toString())
                apply()
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getDecryptedProfile(): String =
        encryptedSharedPreferences.getString("profile", "").toString()

}