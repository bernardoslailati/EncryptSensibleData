package com.slailati.android.encryptuserdata.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.android.material.snackbar.Snackbar
import com.slailati.android.encryptuserdata.R
import com.slailati.android.encryptuserdata.databinding.FragmentRegisterProfileBinding
import com.slailati.android.encryptuserdata.ui.Profile

class RegisterProfileFragment : Fragment() {

    private var _binding: FragmentRegisterProfileBinding? = null
    private val binding get() = _binding!!

    private val encryptedSharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            "secret_shared_prefs",
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            requireActivity(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun saveProfile(profile: Profile) {
        encryptedSharedPreferences.edit {
            putString("profile", profile.toString())
            apply()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRegisterProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            fabSaveProfile.setOnClickListener { _ ->
                saveProfile(
                    Profile(
                        tietName.text.toString(),
                        tietPhoneNumber.text.toString(),
                        tietFederalRegistration.text.toString(),
                        tietPassword.text.toString()
                    )
                )
                Snackbar.make(view,
                    getString(R.string.saved_profile_success_message),
                    Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.action_yes)) {
                        requireView().findNavController().navigate(R.id.action_register_to_details)
                    }.show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}