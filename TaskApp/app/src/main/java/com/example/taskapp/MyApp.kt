package com.example.taskapp

import android.app.Application
import com.example.taskapp.data.database.Database

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Database.init(this)
    }

}