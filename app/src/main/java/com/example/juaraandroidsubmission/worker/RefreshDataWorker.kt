package com.example.juaraandroidsubmission.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.juaraandroidsubmission.data.PahlawanRepository
import com.example.juaraandroidsubmission.data.local.PahlawanDatabase
import retrofit2.HttpException

class RefreshDataWorker(
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val pahlawanDao = PahlawanDatabase.getDatabase(applicationContext).pahlawanDao()
        val repository = PahlawanRepository(pahlawanDao)

        return try {
            repository.refreshPahlawans()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}