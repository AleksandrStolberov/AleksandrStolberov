package com.example.courses.presentation.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.courses.R
import com.example.courses.databinding.FragmentDetailsBinding
import com.example.domain.Course
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val course = arguments?.getParcelable("COURSE_KEY", Course::class.java) ?: error("")

        with(binding) {
            posterImageView.setImageResource(R.drawable.sample_img)
            tittleTextView.text = course.title
            dateAndScore.scoreTextView.text = course.rate
            dateAndScore.dateTextView.text = course.publishDate
            infoTextView.text = course.text
        }
    }

}