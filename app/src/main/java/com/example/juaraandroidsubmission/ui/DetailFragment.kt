package com.example.juaraandroidsubmission.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.juaraandroidsubmission.R
import com.example.juaraandroidsubmission.data.local.Pahlawan
import com.example.juaraandroidsubmission.databinding.FragmentDetailBinding
import com.example.juaraandroidsubmission.viewmodel.PahlawanViewModel
import com.example.juaraandroidsubmission.viewmodel.ViewModelFactory


class DetailFragment : Fragment() {

    private val viewModel: PahlawanViewModel by activityViewModels {
        ViewModelFactory(requireContext())
    }

    private var _binding: FragmentDetailBinding? =null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pahlawan.observe(viewLifecycleOwner){
            bindData(it)
        }
    }

    private fun bindData(pahlawan: Pahlawan) {
        binding?.apply {
            Glide.with(requireContext())
                .load(pahlawan.imageAsset)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(ivPahlawan)
            tvNama.text = pahlawan.name.toString()
            tvDeskripsi.text = pahlawan.deskripsi.toString()
            btnWiki.setOnClickListener {
                val action = DetailFragmentDirections.actionDetailFragmentToWikiFragment()
                findNavController().navigate(action)
            }
        }
    }
}