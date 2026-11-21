package com.onlydevs.artiko

import android.app.Application
import com.onlydevs.artiko.data.ArtikoApi
import com.onlydevs.artiko.data.createArtikoApi

class ArtikoApplication : Application() {
    val api: ArtikoApi by lazy { createArtikoApi(BuildConfig.API_BASE_URL) }
}
