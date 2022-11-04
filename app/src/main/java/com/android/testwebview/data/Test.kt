package com.android.testwebview.data


import com.google.gson.annotations.SerializedName


data class Test(
    @SerializedName("link")
    val link:String? = "",
    @SerializedName("home")
    val home:String? = ""
)
