package com.android.testwebview.repository


import com.android.testwebview.data.Test
import com.android.testwebview.data.api.TestApi
import retrofit2.Response
import javax.inject.Inject

class TestRepository @Inject constructor(
    private val testApi: TestApi
) {

    fun getLinks(): Response<Test> = testApi.getLinks().execute()

}