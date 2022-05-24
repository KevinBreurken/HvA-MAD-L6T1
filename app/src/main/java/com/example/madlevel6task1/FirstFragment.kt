package com.example.madlevel6task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel6task1.adapter.ColorAdapter
import com.example.madlevel6task1.databinding.FragmentFirstBinding
import com.example.madlevel6task1.model.ColorItem
import com.example.madlevel6task1.vm.ColorViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val colors = arrayListOf<ColorItem>()
    private lateinit var colorAdapter: ColorAdapter
    private val viewModel: ColorViewModel by viewModels()
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorAdapter = ColorAdapter(colors, ::onColorClick)
        binding.rvColors.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.rvColors.adapter = colorAdapter

        observeColors()
    }

    private fun observeColors() {
        viewModel.colorItems.observe(viewLifecycleOwner, {
            colors.clear()
            colors.addAll(it)
            colorAdapter.notifyDataSetChanged()
        })
    }

    private fun onColorClick(colorItem: ColorItem) {
        Snackbar.make(binding.rvColors, "This color is: ${colorItem.name}", Snackbar.LENGTH_LONG)
            .show()
    }
}
