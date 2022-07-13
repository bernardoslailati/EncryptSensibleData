package com.slailati.android.encryptuserdata.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.slailati.android.encryptuserdata.R
import com.slailati.android.encryptuserdata.databinding.FragmentRegisterProfileBinding
import com.slailati.android.encryptuserdata.ui.extension.createProfile
import com.slailati.android.encryptuserdata.ui.extension.gone
import com.slailati.android.encryptuserdata.ui.extension.visible
import com.slailati.android.encryptuserdata.ui.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterProfileFragment : Fragment() {

    private val profileViewModel by sharedViewModel<ProfileViewModel>()

    private var _binding: FragmentRegisterProfileBinding? = null
    private val binding get() = _binding!!

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
            fabSaveProfile.setOnClickListener {
                pbLoadingSavingProfile.visible()
                profileViewModel.saveProfile(createProfile())
            }
        }

        addObservers()
    }

    private fun addObservers() {
        lifecycleScope.launchWhenStarted {
            profileViewModel.isProfileSaved().collectLatest {
                it?.let { isProfileSaved ->
                    binding.pbLoadingSavingProfile.gone()
                    showIsSavedProfileSnackbar(isProfileSaved)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showIsSavedProfileSnackbar(isProfileSaved: Boolean) {
        Snackbar.make(
            requireView(),
            getString(if (isProfileSaved) R.string.saved_profile_success_message else R.string.saved_profile_error_message),
            Snackbar.LENGTH_LONG
        ).setAction(getString(R.string.action_yes)) {
            if (isProfileSaved)
                requireView().findNavController().navigate(R.id.action_register_to_details)
            else {
                with(binding) {
                    pbLoadingSavingProfile.visible()
                    profileViewModel.saveProfile(createProfile())
                }
            }
        }.show()
    }

}