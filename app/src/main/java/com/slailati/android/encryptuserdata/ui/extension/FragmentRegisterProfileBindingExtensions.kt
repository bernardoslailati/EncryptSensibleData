package com.slailati.android.encryptuserdata.ui.extension

import com.slailati.android.encryptuserdata.data.model.Profile
import com.slailati.android.encryptuserdata.databinding.FragmentRegisterProfileBinding

fun FragmentRegisterProfileBinding.createProfile() =
    Profile(
        name = tietName.text.toString(),
        phoneNumber = tietPhoneNumber.text.toString(),
        federalRegistration = tietFederalRegistration.text.toString(),
        password = tietPassword.text.toString()
    )