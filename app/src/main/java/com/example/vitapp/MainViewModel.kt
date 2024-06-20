// File: com/example/vitapp/MainViewModel.kt
package com.example.vitapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // Example LiveData property
    private val _someLiveData = MutableLiveData<String>()
    val someLiveData: LiveData<String> get() = _someLiveData

    init {
        _someLiveData.value = "Hello, Data Binding!"
    }
}
