package com.example.myapplication.domain.models

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name") var name: String? = null,
    @SerializedName("region") var region: String? = null,
    @SerializedName("code") var code: String? = null,
    @SerializedName("capital") var capital: String? = null,
)
