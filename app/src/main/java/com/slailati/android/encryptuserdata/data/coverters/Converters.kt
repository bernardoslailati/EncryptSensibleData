package com.slailati.android.encryptuserdata.data.coverters

import com.google.gson.reflect.TypeToken
import com.slailati.android.encryptuserdata.data.model.Profile

class Converters(private val jsonParser: JsonParser) {
    
    fun fromProfileJson(json: String): Profile {
        return jsonParser.fromJson(json, object : TypeToken<Profile>() {}.type) ?: Profile()
    }
    
    fun toProfileJson(profile: Profile): String {
        return jsonParser.toJson(profile, object : TypeToken<Profile>() {}.type) ?: ""
    }
    
}