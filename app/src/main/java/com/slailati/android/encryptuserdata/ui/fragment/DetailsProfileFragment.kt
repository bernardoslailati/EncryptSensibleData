package com.slailati.android.encryptuserdata.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.slailati.android.encryptuserdata.R
import com.slailati.android.encryptuserdata.databinding.FragmentDetailsProfileBinding
import com.slailati.android.encryptuserdata.ui.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailsProfileFragment : Fragment() {

    private val profileViewModel by sharedViewModel<ProfileViewModel>()

    private var _binding: FragmentDetailsProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.getDecryptedProfile()

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        addObservers()
    }

    private fun addObservers() {
        profileViewModel.profile().observe(viewLifecycleOwner) {
            it?.let {
                binding.tvSavedDecryptedProfile.text = getString(
                    R.string.saved_decrypted_profile,
                    it
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}