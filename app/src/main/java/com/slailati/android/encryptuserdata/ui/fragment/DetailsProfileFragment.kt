package com.slailati.android.encryptuserdata.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.slailati.android.encryptuserdata.R
import com.slailati.android.encryptuserdata.databinding.FragmentDetailsProfileBinding
import com.slailati.android.encryptuserdata.ui.Profile

class DetailsProfileFragment : Fragment() {

    private var _binding: FragmentDetailsProfileBinding? = null
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

    private fun getProfile(): String =
        encryptedSharedPreferences.getString("profile", "").toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.tvSavedProfileEncryptedAndDecrypted.text = getString(
            R.string.saved_profile_encrypted_and_decrypted,
            getProfile(),
            getProfile()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}