package com.android.testwebview.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.android.testwebview.readString
import com.android.testwebview.repository.TestRepository
import com.android.testwebview.writeString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadViewModel @Inject constructor(
    testRepository: TestRepository,
    private val appContext:Application
) : AndroidViewModel(appContext) {
    val links = testRepository.getLinks()

    fun saveLink(url:String){
        viewModelScope.launch(Dispatchers.IO){
            appContext.writeString(NAME_USER_KEY,url)
        }
    }
val getLink = appContext.readString(NAME_USER_KEY).asLiveData()
    companion object{
        const val NAME_USER_KEY = "name"
    }
}