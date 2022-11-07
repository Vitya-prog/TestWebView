package com.android.testwebview.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.testwebview.data.Test
import com.android.testwebview.repository.DataRepository
import com.android.testwebview.repository.TestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadViewModel @Inject constructor(
    private val testRepository: TestRepository,
    private val dataRepository: DataRepository
) : ViewModel() {
    val links = MutableLiveData<Test>()
    var saveLink :MutableLiveData<String> = MutableLiveData("none")

    fun getLinks(){
        viewModelScope.launch(Dispatchers.IO) {
           val response = testRepository.getLinks()
            if (response.isSuccessful){
                val test = response.body()!!
                saveData(test.home!!)
                links.postValue(test)
            }

        }
    }

   suspend fun saveData(link:String){
     dataRepository.saveLink(link)

    }
    fun receiveDate() {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.getLink().collect{
                saveLink.postValue(it)
            }
        }
    }
}