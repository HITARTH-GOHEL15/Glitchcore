package com.example.hd_1

import android.app.Application
import com.example.hd_1.di.initKoin

class GlitchcoreApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}