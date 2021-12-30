package com.minjee.basicmvvmexample.services

import com.minjee.basicmvvmexample.model.dto.SearchDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VajehYabService {

    @GET("translate/")
    fun searchWord(@Query("text") word: String, @Query("type") type: String, @Query("from") lang: String, @Query("to") destLang: String): Call<SearchDto>

}

