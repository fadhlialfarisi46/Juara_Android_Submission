package com.example.juaraandroidsubmission.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.juaraandroidsubmission.data.PahlawanRepository
import com.example.juaraandroidsubmission.data.local.Pahlawan
import com.example.juaraandroidsubmission.data.local.PahlawanDao
import com.example.juaraandroidsubmission.data.local.PahlawanDatabase
import kotlinx.coroutines.launch

class PahlawanViewModel(context: Context) : ViewModel() {

    private val pahlawanDao: PahlawanDao = PahlawanDatabase.getDatabase(context).pahlawanDao()
    private val repository: PahlawanRepository = PahlawanRepository(pahlawanDao)

    private val _pahlawan = MutableLiveData<Pahlawan>()
    val pahlawan: LiveData<Pahlawan> get() = _pahlawan

    init {
        viewModelScope.launch {
            try{
                repository.refreshPahlawans()
            }catch (e: Exception){
                Log.e("viewmodel", e.message.toString())
            }
        }
    }


    val allPahlawans: LiveData<List<Pahlawan>> = repository.pahlawans

    fun onPahlawanClicked(pahlawan: Pahlawan) {
        _pahlawan.value = pahlawan
    }
}

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PahlawanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PahlawanViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}