package com.android.testwebview.data.api

import com.android.testwebview.data.Test
import retrofit2.Call
import retrofit2.http.GET

interface TestApi {
@GET("prod")
fun getLinks(): Call<Test>
}
