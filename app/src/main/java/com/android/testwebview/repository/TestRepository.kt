package com.android.testwebview.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.testwebview.data.Test
import com.android.testwebview.data.api.TestApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
private const val TAG ="TestRepository"
class TestRepository @Inject constructor(
    private val testApi: TestApi
) {

    fun getLinks():LiveData<Test>{
        val linkLiveData = MutableLiveData<Test>()
        testApi.getLinks().enqueue(object: Callback<Test>{
            override fun onResponse(call: Call<Test>, response: Response<Test>) {
                linkLiveData.value = response.body()
            }

            override fun onFailure(call: Call<Test>, t: Throwable) {
                Log.d(TAG,"$t")
            }
        })
        return linkLiveData
    }

}