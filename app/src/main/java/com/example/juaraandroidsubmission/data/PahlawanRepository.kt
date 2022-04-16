package com.example.juaraandroidsubmission.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.juaraandroidsubmission.data.local.Pahlawan
import com.example.juaraandroidsubmission.data.local.PahlawanDao
import com.example.juaraandroidsubmission.data.remote.PahlawanApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PahlawanRepository(private val pahlawanDao: PahlawanDao) {

    val pahlawans= pahlawanDao.getPahlawans()


    suspend fun refreshPahlawans() {
        withContext(Dispatchers.IO){
            val pahlawansFromApi = PahlawanApi.retrofitService.pahlawans()
            pahlawanDao.insertAll(pahlawansFromApi)
        }
    }


}