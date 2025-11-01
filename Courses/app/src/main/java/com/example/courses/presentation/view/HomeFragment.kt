package com.example.courses.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.courses.R
import com.example.courses.databinding.FragmentHomeBinding
import com.example.courses.presentation.CourseAdapterDelegate
import com.example.courses.presentation.State
import com.example.courses.presentation.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var courseAdapter: CourseAdapterDelegate? = null

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel.getCourses()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseAdapter = CourseAdapterDelegate({ course ->
            val bundle = bundleOf("COURSE_KEY" to course)
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
        }, { hasLike, course ->
            if (!hasLike)
                viewModel.deleteFavoriteCourse(course.id)
            else
                viewModel.insertFavoriteCourse(course)
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.courses.collect {
                courseAdapter?.submitList(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                setState(it)
            }
        }


        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = courseAdapter
        }

        binding.sortButton.setOnClickListener {
            if (viewModel.courses.value.isNotEmpty()) {
                val sortedCourses = viewModel.courses.value.sortedBy {
                    it.publishDate
                }
                courseAdapter?.submitList(sortedCourses)
            }
        }

    }

    private fun setState(state: State) {
        when (state) {
            is State.Loading -> {
                binding.progressBar.isVisible = true
                binding.recyclerView.isVisible = false
                binding.errorTextView.isVisible = false
            }

            is State.Success -> {
                binding.progressBar.isVisible = false
                binding.recyclerView.isVisible = true
                binding.errorTextView.isVisible = false
            }

            is State.Error -> {
                binding.progressBar.isVisible = false
                binding.recyclerView.isVisible = false
                binding.errorTextView.isVisible = true
                binding.errorTextView.text = state.message

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        courseAdapter = null
    }
}