package com.minjee.basicmvvmexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.minjee.basicmvvmexample.model.dto.SearchDto
import com.minjee.basicmvvmexample.repository.SearchRepository

class MainViewModel: ViewModel() {

    var wordSearchResult = MutableLiveData<SearchDto>()

    // Get the updated text from our model and post the value to MainFragment
    fun searchWord(word: String, selectedLanguage: String, destLang: String): MutableLiveData<SearchDto> {
        return SearchRepository.searchWord(word, selectedLanguage, destLang)
    }
}