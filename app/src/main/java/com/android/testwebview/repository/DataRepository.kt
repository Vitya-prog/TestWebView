package com.android.testwebview.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

const val NAME = "LINK"

val Context.datastore : DataStore<Preferences> by  preferencesDataStore(name = NAME)
class DataRepository(private val context: Context) {
    companion object{
        val LINK = stringPreferencesKey("LINK")
    }

    suspend fun saveLink(url:String){
    context.datastore.edit { link->
        link[LINK] = url
        }
    }
    fun getLink() = context.datastore.data.map {
        it[LINK]
    }
}
