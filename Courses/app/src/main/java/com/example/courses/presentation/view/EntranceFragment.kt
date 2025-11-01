package com.example.courses.presentation.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.courses.R
import com.example.courses.databinding.FragmentEntranceBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.net.toUri

@AndroidEntryPoint
class EntranceFragment : Fragment()  {

    private var _binding: FragmentEntranceBinding? = null
    private val binding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEntranceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.emailEditText.doOnTextChanged { text, _, _, _ ->
            binding.enterButton.isEnabled = !binding.emailInput.isErrorEnabled && !binding.passwordEditText.text.isNullOrEmpty()
            if (isEmailValidate(text)) {
                binding.emailInput.isErrorEnabled = false
            } else {
                binding.emailInput.error = "Incorrect email"
                binding.emailInput.isErrorEnabled = true
            }
        }

        binding.passwordEditText.doOnTextChanged { text, _, _, _ ->
            binding.enterButton.isEnabled = !binding.emailInput.isErrorEnabled && !binding.passwordEditText.text.isNullOrEmpty()
        }



        binding.enterButton.setOnClickListener {
            findNavController().navigate(R.id.action_entranceFragment_to_homeFragment)
        }

        binding.vkButton.setOnClickListener {
            openUri("https://vk.com/".toUri())
        }

        binding.classmateButton.setOnClickListener {
            openUri("https://ok.ru/".toUri())
        }

    }

    private fun openUri(uri: Uri) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, uri)
            val chooser = Intent.createChooser(intent, "Открыть с помощью")
            context?.startActivity(chooser)
        } catch (e: Exception) {
            Toast.makeText(context, "Не удалось открыть ссылку", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isEmailValidate(email: CharSequence?): Boolean {
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}