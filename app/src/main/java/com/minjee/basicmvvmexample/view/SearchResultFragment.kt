package com.minjee.basicmvvmexample.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.minjee.basicmvvmexample.R
import com.minjee.basicmvvmexample.databinding.FragmentMainBinding
import com.minjee.basicmvvmexample.databinding.FragmentSearchResultBinding

class SearchResultFragment: Fragment() {

    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!

    val args: SearchResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillData()
        setFlag()
    }

    private fun fillData() {
        binding.wordMeaning.text = args.searchResult.result
        binding.word.text = args.word
    }

    private fun setFlag() {
        when (args.language) {
            "fa" -> binding.selectedLanguageImg.setImageResource(R.drawable.flag_iran)
            "en" -> binding.selectedLanguageImg.setImageResource(R.drawable.flag_united_states)
            "ar" -> binding.selectedLanguageImg.setImageResource(R.drawable.flag_saudi_arabia)
        }
    }

}