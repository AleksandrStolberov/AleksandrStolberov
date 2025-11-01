package com.example.courses.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.courses.databinding.FragmentFavoriteBinding
import com.example.courses.presentation.CourseAdapterDelegate
import com.example.courses.presentation.viewModel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private var courseAdapter: CourseAdapterDelegate? = null

    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        viewModel.getFavoriteCourse()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseAdapter = CourseAdapterDelegate({ course ->

        }, { hasLike, course ->
            if (!hasLike)
                viewModel.deleteFavoriteCourse(course.id)
            else
                viewModel.insertFavoriteCourse(course)
        })

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = courseAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.courses.collect {
                courseAdapter?.submitList(it)
            }
        }


    }

}