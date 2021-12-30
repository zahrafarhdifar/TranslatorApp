package com.minjee.basicmvvmexample.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.minjee.basicmvvmexample.R
import com.minjee.basicmvvmexample.databinding.FragmentMainBinding
import com.minjee.basicmvvmexample.model.dto.SearchDto
import com.minjee.basicmvvmexample.viewmodel.MainViewModel

/*
 *      MainFragment
 *      - shows the UI
 *      - listens to viewModel for updates on UI
 */
class MainFragment: Fragment() {

    // View Binding
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var dialog : BottomSheetDialog

    private val viewModel: MainViewModel by activityViewModels()

    private var selectedLanguage = "fa"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        fragmentTextUpdateObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupClickListeners() {
        binding.searchWordBtn.setOnClickListener {
            binding.searchProgressBar.visibility = View.VISIBLE
            binding.searchWordBtn.visibility = View.INVISIBLE
            var destLang = ""
            if (selectedLanguage == "fa") destLang = "en" else destLang = "fa"
            viewModel.searchWord(binding.searchWordEt.text.toString(), selectedLanguage, destLang).observe(viewLifecycleOwner, {searchResult ->
                binding.searchProgressBar.visibility = View.INVISIBLE
                binding.searchWordBtn.visibility = View.VISIBLE
                if (binding.searchWordEt.text.toString() != searchResult.result)
                {
                    navigateToResultPage(searchResult)
                }
            })
        }
        binding.selectLanguageBtn.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun fragmentTextUpdateObserver() {

    }

    private fun navigateToResultPage(data: SearchDto) {
        val action = MainFragmentDirections.navigateToResult(data, binding.searchWordEt.text.toString(), selectedLanguage)
        view!!.findNavController().navigate(action)
    }

    private fun showBottomSheet() {
        dialog = BottomSheetDialog(this.requireContext())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_language_selection, null)
        view.findViewById<LinearLayoutCompat>(R.id.persian_language_btn).setOnClickListener{selectLanguage("fa")}
        view.findViewById<LinearLayoutCompat>(R.id.english_language_btn).setOnClickListener{selectLanguage("en")}
        view.findViewById<LinearLayoutCompat>(R.id.arabic_language_btn).setOnClickListener{selectLanguage("ar")}
        dialog.setContentView(view)
        dialog.show()
    }

    private fun selectLanguage(language: String) {
        selectedLanguage = language
        when(language) {
            "fa" -> binding.selectLanguageBtn.setImageResource(R.drawable.flag_iran)
            "en" -> binding.selectLanguageBtn.setImageResource(R.drawable.flag_united_states)
            "ar" -> binding.selectLanguageBtn.setImageResource(R.drawable.flag_saudi_arabia)
        }
        dialog.cancel()
    }
}