package com.minjee.basicmvvmexample.repository

import androidx.lifecycle.MutableLiveData
import com.minjee.basicmvvmexample.model.dto.SearchDto
import com.minjee.basicmvvmexample.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val token = "705910:61c6e59a12ebc7.65930355"
const val type = "google"

object SearchRepository  {

    var result = MutableLiveData<SearchDto>()

    fun searchWord(word: String, language: String, destLang: String): MutableLiveData<SearchDto> {

        val call = RetrofitClient.apiInterface.searchWord(word = word, type = "json", lang = destLang, destLang =  language)

        call.enqueue(object : Callback<SearchDto>{
            override fun onResponse(call: Call<SearchDto>, response: Response<SearchDto>) {
                result.postValue(response.body())
                result =  MutableLiveData<SearchDto>()
            }

            override fun onFailure(call: Call<SearchDto>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return result

    }

}