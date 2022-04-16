package com.example.juaraandroidsubmission.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.juaraandroidsubmission.adapter.PahlawanRecyclerViewAdapter
import com.example.juaraandroidsubmission.databinding.FragmentHomeBinding
import com.example.juaraandroidsubmission.viewmodel.PahlawanViewModel
import com.example.juaraandroidsubmission.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {


    private val viewModel: PahlawanViewModel by activityViewModels {
        ViewModelFactory(requireContext())
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() =_binding!!

    private var pahlawanAdapter: PahlawanRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pahlawanAdapter = PahlawanRecyclerViewAdapter()
        pahlawanAdapter?.onItemClick = { pahlawan ->
            viewModel.onPahlawanClicked(pahlawan)
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
            findNavController().navigate(action)
        }

        viewModel.allPahlawans.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                binding.tvGagal.visibility = View.VISIBLE
            }else{
                binding.tvGagal.visibility = View.GONE
                pahlawanAdapter?.setData(it)
            }
        }

        val gridLayoutManager = GridLayoutManager(context, 2)
        with(binding.rvPahlawan){
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = pahlawanAdapter
        }
    }

}