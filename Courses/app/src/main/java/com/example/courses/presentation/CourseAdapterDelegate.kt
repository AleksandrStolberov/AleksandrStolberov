package com.example.courses.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.courses.R
import com.example.courses.databinding.ItemCourseBinding
import com.example.courses.formatToTwoLines
import com.example.domain.Course

class CourseAdapterDelegate(
    private val onCourseClick: (Course) -> Unit,
    private val onFavoriteClick: (Boolean, Course) -> Unit
) : ListAdapter<Course, CourseAdapterDelegate.CourseViewHolder>(CourseDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CourseViewHolder(binding, onCourseClick, onFavoriteClick)
    }

    override fun onBindViewHolder(
        holder: CourseViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }


    class CourseViewHolder(
        private val binding: ItemCourseBinding,
        private val onCourseClick: (Course) -> Unit,
        private val onFavoriteClick: (Boolean, Course) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var hasLike = false

        fun bind(item: Course) {
            hasLike = item.hasLike
            with(binding) {
                courseImage.setImageResource(R.drawable.sample_img)
                titleTextView.text = item.title
                infoTextView.text = item.text.formatToTwoLines()
                priceTextView.text = item.price + " ₽"
                scoreAndDate.scoreTextView.text = item.rate
                scoreAndDate.dateTextView.text = dateFormatter(item.publishDate)
                favoriteImage.setImageResource(if (hasLike) R.drawable.favorite_green_img else R.drawable.favorite)
            }
            binding.moreButton.setOnClickListener {
                onCourseClick(item)
            }
            binding.favoriteButton.setOnClickListener {
                hasLike = !hasLike
                binding.favoriteImage.setImageResource(if (hasLike) R.drawable.favorite_green_img else R.drawable.favorite)
                onFavoriteClick(hasLike, item.copy(hasLike = hasLike))
            }
        }

        private fun dateFormatter(date: String): String {
            val list = date.split('-').toMutableList()
            if (list.size > 1) {
                list[1] = when (list[1]) {
                    "01" -> "Января"
                    "02" -> "Февраля"
                    "03" -> "Марта"
                    "04" -> "Апреля"
                    "05" -> "Мая"
                    "06" -> "Июня"
                    "07" -> "Июля"
                    "08" -> "Августа"
                    "09" -> "Сентября"
                    "10" -> "Октября"
                    "11" -> "Ноября"
                    "12" -> "Декабря"
                    else -> ""

                }
                val temp = list[0]
                list[0] = list[2]
                list[2] = temp
            } else
                ""
            return list.joinToString(" ")
        }
    }

    object CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }

    }
}