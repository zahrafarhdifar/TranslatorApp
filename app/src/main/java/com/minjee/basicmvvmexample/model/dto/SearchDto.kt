package com.minjee.basicmvvmexample.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchDto (
    val ok: Boolean,
    val result: String,
) : Parcelable
