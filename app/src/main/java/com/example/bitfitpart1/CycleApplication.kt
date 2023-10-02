package com.example.bitfitpart1

import android.app.Application

class CycleApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}