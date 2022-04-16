package com.example.juaraandroidsubmission.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Pahlawan(
    @PrimaryKey
    val id: Int? = null,
    val name: String? = null,
    val deskripsi: String? = null,
    val imageAsset: String? = null,
    val urlWiki: String? = null,
)
